package com.example.kson.ksonjsbrige;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.kson.ksonjsbrige.entity.UserInfo;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;


public class WebViewActivity extends AppCompatActivity {

    private static final String TAG = WebViewActivity.class.getSimpleName();
    private BridgeWebView mWebView;
    private UserInfo userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        initSettings();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        userInfo = new UserInfo();
        userInfo.name = "kson";
        userInfo.age = "100";

    }

    private void initView() {
        mWebView = findViewById(R.id.webView);
    }

    /**
     * 初始化webview
     */
    private void initSettings() {
        WebSettings webSettings = mWebView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//brigewebview
        mWebView.setDefaultHandler(new DefaultHandler());
        registerHandler();
        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/js.html");


        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });

        //js调android方式1
        mWebView.addJavascriptInterface(new AndroiFromJs(),"formjs");


        //js调android方式二
//        mWebView.setWebViewClient(new WebViewClient(){
//
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // 步骤2：根据协议的参数，判断是否是所需要的url
//                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
//                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
//
//                Uri uri = Uri.parse(url);
//                // 如果url的协议 = 预先约定的 js 协议
//                // 就解析往下解析参数
//                if ( uri.getScheme().equals("js")) {
//
//                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
//                    // 所以拦截url,下面JS开始调用Android需要的方法
//                    if (uri.getAuthority().equals("webview")) {
//
//                        //  步骤3：
//                        // 执行JS所需要调用的逻辑
//                        System.out.println("js调用了Android的方法");
//
//                    }
//
//                    return true;
//                }
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//        });




    }

    /**
     * 注册
     */
    private void registerHandler() {

        mWebView.registerHandler("getUserInfo", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i(TAG, "handler = getUserInfo, data from web = " + data);
                Toast.makeText(WebViewActivity.this,"js调用java："+data,Toast.LENGTH_SHORT).show();

                //android 返回给js的消息
                function.onCallBack(new Gson().toJson(userInfo));
            }
        });

    }


    public void loadJs1(View view) {
        mWebView.loadUrl("javascript:callJS()");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void loadJs2(View view) {
        mWebView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {

                Toast.makeText(WebViewActivity.this,value,Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * @param view
     */
    public void javatoweb(View view) {


//Java 调JS的functionJs方法并得到返回值
        mWebView.callHandler("functionInJs", "data from Java", new CallBackFunction() {

            @Override
            public void onCallBack(String data) {
                // TODO Auto-generated method stub
                Log.i(TAG, "reponse data from js " + data);
                Toast.makeText(WebViewActivity.this, "datafromjs:"+data, Toast.LENGTH_SHORT).show();
            }

        });

//        mWebView.send("java");

    }
}
