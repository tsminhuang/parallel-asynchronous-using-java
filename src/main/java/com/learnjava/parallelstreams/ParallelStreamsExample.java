package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ParallelStreamsExample {

    public static void main(String[] args) {
        ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();

        stopWatch.start();

        List<String> resultList =
                parallelStreamsExample.stringTransform(DataSet.namesList(), false);

        stopWatch.stop();
        log("Final Result : " + resultList);
        log("Total Time Taken : " + stopWatch.getTime());
    }

    public List<String> stringTransform(List<String> nameList, boolean useParallel) {

        Stream<String> stream = nameList.stream();
        if (useParallel) {
            stream.parallel();
        }

        return stream
                .map(this::addNameLengthTransform)
                .toList();
    }

    public List<String> stringToLowerCase(List<String> nameList) {
        return nameList.stream()
                .parallel()
                .map(String::toLowerCase)
                .toList();
    }

    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length() + " - " + name;
    }
}
