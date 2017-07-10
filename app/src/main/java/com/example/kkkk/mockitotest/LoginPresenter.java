package com.example.kkkk.mockitotest;

import android.util.Log;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Response;

/**
 * Created by kkkk on 2017/6/20.
 */

public class LoginPresenter implements IPresenter {
    private LoginView mLoginView;
    private UserModel mUserModel = new UserModel();


    // mUser 需要考虑依赖注入来解耦
    public LoginPresenter(LoginView mLoginView) {
        this.mLoginView = mLoginView; // 可以直接进行UI操作
    }
    public void setmUserModel(UserModel mUserModel){
        this.mUserModel = mUserModel;
    }

    // = = 所以这就是，observable连续传递的原因？？需要等 model 的结果出来！
    // 虽然在modle中，用rxjava等待了 okhttp的回复，但这里不会等？？ - -打log看看...
    public void doLogin(String username, String password){
        try{
            Observable.just("empty")
                    .flatMap(ignore -> mUserModel.performLogin(username, password))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            this::showSuccess, //只用map，就得到的是observable<response>
                            this::showException,
                            this::showComplete
                    );


            /* 这里不用rxjava时，为什么他为null？ 因为那边的网络请求在io线程运行，所以performLogin没执行完
            就接着走下去了。
            if(result != null) Log.i("LP", result);
            else Log.i("LP", "result is null ");
            */

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void showException(Throwable e) {

        Log.i("Mani", " show Exception");
    }

    private void showSuccess(int value) {
        try{
            // value.body().string();终于得到了结果- -但是，
            // 真的是得到响应报文...html一串都拿到了- -我只想要json呀
            String result = String.valueOf(value);
            mLoginView.onResultLoad(result);
            Log.i("Model", "onNext " + result + " in " + Thread.currentThread().getName());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void showComplete() {
        Log.i("Main", " complete");
    }

}
