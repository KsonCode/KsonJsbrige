package com.example.kson.ksonjsbrige;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.kson.ksonjsbrige.entity.LoginEntity;
import com.example.kson.ksonjsbrige.entity.AdEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 登录
     * @param view
     */
    public void login(View view) {

        startActivity(new Intent(this,WebViewActivity.class));

//        HashMap<String,String> params = new HashMap<>();
//        params.put("mobile","18612991023");
//        params.put("password","222222");
//
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.zhaoapi.cn/")
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);
//        Observable<LoginEntity> observable1 = apiService.login(params);//第一个observable对象
//        Observable<AdEntity> observable2 = apiService.getAd();
//
//        Observable.merge(observable1,observable2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//
//                        if (o instanceof LoginEntity){
//
//                            Log.e("xxx1:",((LoginEntity) o).data.mobile+"");
//
//                        }else if (o instanceof AdEntity){
//
//                            Log.e("xxx2:",((AdEntity) o).data.size()+"");
//                        }
//
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                });

    }
}
