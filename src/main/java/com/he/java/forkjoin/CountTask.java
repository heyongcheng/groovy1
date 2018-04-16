package com.he.java.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author heyc
 * @date 2018/4/16 14:05
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;

    private int start;

    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        System.out.println("T:" + Thread.currentThread().getName()+ " start:" + start + " end:" + end);
        int sum = 0;
        if (end - start <= THRESHOLD) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end)/2;
            final CountTask leftTask = new CountTask(start, middle);
            final CountTask rightTask = new CountTask(middle + 1, end);

            final ForkJoinTask leftFork = leftTask.fork();
            final ForkJoinTask rightFork = rightTask.fork();

            final int left = leftTask.join();
            final int right = rightTask.join();
            sum = left + right;
            System.out.println("T:" + Thread.currentThread().getName()+ " middle sum:" + sum);
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        final CountTask countTask = new CountTask(1, 10);
        final ForkJoinTask<Integer> task = pool.submit(countTask);
        try {
            final Integer result = task.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
