package com.example.kkkk.mockitotest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;
import okhttp3.Response;

import static org.mockito.Mockito.when;


/**
 * Created by kkkk on 2017/6/23.
 */

public class LoginPresenterTest {

    LoginPresenter  loginPresenter;

    @Mock
    LoginView loginView;

    @Mock
    UserModel userModel;

    /*
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
    */

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Before
    public void setUp() {
        loginPresenter = new LoginPresenter(loginView);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void doLoginTest() throws Exception {
        loginPresenter.doLogin("wo", "ni");
        loginPresenter.setmUserModel(userModel);
        // 验证该方法是否得到调用
        Mockito.verify(userModel, Mockito.times(1)).performLogin("wo", "ni");
    }


    @Test
    public void getResponseTest() {
        loginPresenter.doLogin("wo", "ni");
        // TestSubscriber 是用来检测subscriber的行为（next , error , complete是否正确的）
        TestObserver<Integer> testObserver = new TestObserver<>();

      // 你到底想return 啥- -
        //  when(userModel.performLogin("wo", "ni")).thenReturn(Observable<Response>);
        try{
            userModel.performLogin("wo", "ni").
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(testObserver);
            testObserver.assertNoErrors();
        }finally{
            RxJavaPlugins.reset();
        }


    }

    @Test
    public void showSuccessTest() {
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return null;
            }
        });
    }

}