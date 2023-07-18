package com.learnjava.forkjoin;

import com.learnjava.util.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ForkJoinUsingRecursion extends RecursiveTask<List<String>> {

    private final List<String> input;

    public ForkJoinUsingRecursion(final List<String> input) {
        this.input = input;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinUsingRecursion forkJoinUsingRecursion = new ForkJoinUsingRecursion(DataSet.namesList());
        stopWatch.start();

        List<String> resultList = forkJoinPool.invoke(forkJoinUsingRecursion);

        stopWatch.stop();
        log("Final Result : " + resultList);
        log("Total Time Taken : " + stopWatch.getTime());
    }


    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length() + " - " + name;
    }

    @Override
    protected List<String> compute() {
        if (input.size() == 1) {
            return List.of(addNameLengthTransform(input.get(0)));
        }

        int mid = input.size() / 2;
        ForkJoinTask<List<String>> leftTask = new ForkJoinUsingRecursion(input.subList(0, mid));
        ForkJoinTask<List<String>> rightTask = new ForkJoinUsingRecursion(input.subList(mid, input.size()));

        leftTask.fork();
        rightTask.fork();

        List<String> result = new ArrayList<>();
        result.addAll(leftTask.join());
        result.addAll(rightTask.join());
        return result;
    }
}
