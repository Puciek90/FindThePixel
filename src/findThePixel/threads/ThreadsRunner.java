package findThePixel.threads;// Created by Mateusz Płuciennik on 09.11.16.

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import findThePixel.picture.PixelFinder;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadsRunner{
    private Color searchedColor;
    private Image image;
    private int threadPool;

    public ThreadsRunner(Color searchedColor, Image image,int threadPool) {
        this.searchedColor = searchedColor;
        this.image = image;
        this.threadPool = threadPool;
    }

    public int runThreads() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(threadPool);
        Set<Future<Integer>> set = new HashSet<Future<Integer>>();

        //todo: Do wątku trzeba przekazać tylko kawałek obrazu!! Trzeba go podzielić przed przekazaniem.

        for (int i = 0; i< threadPool; i++) {
            PixelFinder callable = new PixelFinder(searchedColor, image);
            Future<Integer> future = pool.submit(callable);
            set.add(future);
        }

        int sum = 0;
        for(Future<Integer> future : set) {
            sum += future.get();
        }

        pool.shutdownNow();

        return sum;
    }
}
