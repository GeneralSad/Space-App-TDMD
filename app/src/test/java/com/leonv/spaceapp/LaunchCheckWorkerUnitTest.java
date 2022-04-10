package com.leonv.spaceapp;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.work.ListenableWorker;
import androidx.work.testing.TestListenableWorkerBuilder;

import org.junit.Before;
import org.junit.Test;

public class LaunchCheckWorkerUnitTest {
    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void testSleepWorker() {
//        LaunchCheckWorker worker = TestListenableWorkerBuilder.from(context, SleepWorker.class)
//                .build();
//        worker.createWork().subscribe(result ->
//                assertThat(result, is(ListenableWorker.Result.success())));
    }
}
