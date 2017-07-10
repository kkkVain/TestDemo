package com.example.kkkk.mockitotest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by kkkk on 2017/6/20.
 */

public class LoginView extends AppCompatActivity implements IView, View.OnClickListener {

    private EditText mEditUser;
    private EditText mEditPsd;

    private Button mButtonLogin;

    private TextView mTextResult;


    IPresenter mLoginPresenter;


    @Override
    public void onResultLoad(String result){
        mTextResult.setText(result);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        initView();
    }

    public void initView(){
        mLoginPresenter = new LoginPresenter(this);
        mEditPsd = (EditText)findViewById(R.id.password_edit);
        mEditUser = (EditText)findViewById(R.id.username_edit);
        mTextResult = (TextView)findViewById(R.id.result_text);
        mButtonLogin = (Button)findViewById(R.id.login_button);
        mButtonLogin.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.login_button:
                String s1 = mEditUser.getText().toString();
                String s2 = mEditPsd.getText().toString();

                Log.i("LV", s1 + " hhh " + s2);
                if(s1 != null && s2 != null){
                    mLoginPresenter.doLogin(s1, s2);
                }

        }
    }
}
