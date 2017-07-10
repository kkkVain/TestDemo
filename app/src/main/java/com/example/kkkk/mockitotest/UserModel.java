package com.example.kkkk.mockitotest;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URLEncoder;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kkkk on 2017/6/20.
 */

// 做数据处理，比如网络请求，数据库处理等等
public class UserModel implements IModel {

    private String result;
    private Gson mGson = new Gson();
    private JsonParser mJsonParser = new JsonParser();


    /*

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    // okhttp这解耦真是差劲= = 要吐血 我怎么让performLogin把结果传回去

                    /* 这里确定得到结果了
                    但是，如果想用MVP模式，比如把model处理的数据返回给presenter
                    很难返回去啊- -除非又给presenter加回调接口....
                    thread + handler 就是难用啊..... 吐血....
                   !!! 嗯- -所以还是要靠 Rxjava- -


            }

        }
    };

    */

    private String url = "http://gc.ditu.aliyun.com/geocoding?a=";

    @Override
    public Observable<Integer> performLogin(String username, String password)  {
        try{
            String city = "苏州市";
            String Url = url + URLEncoder.encode(city, "utf-8");
            Request request = new Request.Builder()
                    .url(Url)
                    .build();

            return Observable.create((ObservableEmitter<Integer> e) -> {
                Log.i("UM", "im do net");
              //  Response response = new OkHttpClient().newCall(request).execute();
              //  AddressResponse a = handleResponse(response);
             //   Log.i("getAleveel", String.valueOf(a.getAlevel()));

                int x = 10;
                e.onNext(10);
                e.onComplete();
            }).subscribeOn(Schedulers.io());

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public AddressResponse handleResponse(Response response){
        try{
            if(response != null && response.body() != null){
                String jsonStr = response.body().string();
                Log.i("handle", jsonStr);
                JsonElement jsonElement = mJsonParser.parse(jsonStr);
                if(!jsonElement.isJsonObject()){
                    throw new JsonParseException("Root is not JsonObject");
                }
                JsonObject j = jsonElement.getAsJsonObject();

                String addr = j.get("lon").getAsString();
                //JsonObject root = jsonElement.getAsJsonObject();
                Log.i("response", " not null " + addr);
                return mGson.fromJson(jsonElement,  AddressResponse.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


/*  runnable 和 Handler 这玩意儿太难用了= =  又臭又长...

    class NetRunnable implements Runnable {
        @Override
        public void run() {

        /*
        FormBody formBody = new FormBody.Builder()
                            .add("username", username)
                            .add("password", password)
                            .build();
                            *
            try{


                Log.i("um", Url);


                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        result = response.body().toString();
                        Message msg = mHandler.obtainMessage();
                        msg.what = 1;
                        mHandler.sendMessage(msg);

                        Log.i("Moder", result);
                    }
                });

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    */

}
