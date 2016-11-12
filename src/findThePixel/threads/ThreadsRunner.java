package findThePixel.threads;// Created by Mateusz Płuciennik on 09.11.16.

import findThePixel.picture.PixelFinder;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadsRunner{
    private Color searchedColor;
    private Image image;
    private int threadPool;
    private List<Image> dividedPicture;

    public ThreadsRunner(Color searchedColor, Image image,int threadPool) {
        this.searchedColor = searchedColor;
        this.image = image;
        this.threadPool = threadPool;
    }

    public ThreadsRunner(Color searchedColor, List<Image> dividedPicture,int threadPool) {
        this.searchedColor = searchedColor;
        this.dividedPicture = dividedPicture;
        this.threadPool = threadPool;
    }

    public int runThreads() throws ExecutionException, InterruptedException {
        int sum = 0;
        ExecutorService pool = Executors.newFixedThreadPool(threadPool);
        Set<Future<Integer>> set = new HashSet<Future<Integer>>();

        //todo: Do wątku trzeba przekazać tylko kawałek obrazu!! Trzeba go podzielić przed przekazaniem.

        if (threadPool == 1) {
            PixelFinder callable = new PixelFinder(searchedColor, image);
            Future<Integer> future = pool.submit(callable);
            set.add(future);

            for(Future<Integer> futures : set) {
                sum += futures.get();
            }

        } else {

            for (Image piece : dividedPicture) {
                Future<Integer> future = pool.submit(new PixelFinder(searchedColor, piece));
                set.add(future);
            }

            for(Future<Integer> futures : set) {
                sum += futures.get();
            }
        }

        pool.shutdownNow();
        return sum;
    }
}
