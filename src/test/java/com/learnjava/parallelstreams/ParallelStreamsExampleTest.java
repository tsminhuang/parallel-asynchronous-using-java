package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.stopWatchReset;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParallelStreamsExampleTest {

    ParallelStreamsExample parallelStreamsExample;

    @BeforeEach
    void setUp() {
        parallelStreamsExample = new ParallelStreamsExample();
    }

    @AfterEach
    void cleanUp() {
        stopWatchReset();
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void stringTransform(boolean useParallel) {
        List<String> nameList = DataSet.namesList();

        startTimer();
        List<String> result =
                parallelStreamsExample.stringTransform(nameList, useParallel);
        timeTaken();

        assertEquals(nameList.size(), result.size());
        result.forEach(name -> {
            assertTrue(name.contains("-"));
        });
    }

    @Test
    void stringToLowerCase() {
        List<String> nameList = DataSet.namesList();

        startTimer();
        List<String> result =
                parallelStreamsExample.stringToLowerCase(nameList);
        timeTaken();

        assertEquals(nameList.size(), result.size());
        result.forEach(name -> {
            assertTrue(StringUtils.isAllLowerCase(name));
        });
    }
}