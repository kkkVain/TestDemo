package com.example.kkkk.mockitotest;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Created by kkkk on 2017/7/3.
 */
public class UserModelTest {

    UserModel userModel;


    @BeforeClass
    public static void setUpRxSchedulers(){
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }

            @Override
            public Disposable scheduleDirect(Runnable run, long delay, TimeUnit unit) {
                return super.scheduleDirect(run, 0, unit);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
    }


    @Before
    public void setUp() throws Exception {
        userModel = new UserModel();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void performLogin() throws Exception {
        TestObserver<Integer> testObserver = new TestObserver<>();
        userModel.performLogin("wo", "ni").subscribe(testObserver);

        /*
        AddressResponse r = new AddressResponse();
        r.setAddress("");r.setAlevel(4);r.setLat(31.29888);
        r.setLon(120.58531); r.setLevel(2);r.setCityName("");
        */

        int c = 10;
        testObserver.assertNoErrors();
        testObserver.assertSubscribed();
        //testObserver.assertValue(c);
        testObserver.assertResult(c);



     //   testObserver.assertResult();

    }

}