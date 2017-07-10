package com.example.kkkk.mockitotest;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Response;

/**
 * Created by kkkk on 2017/6/20.
 */

public interface IModel {
    Observable<Integer> performLogin(String username, String password) throws IOException;
}
