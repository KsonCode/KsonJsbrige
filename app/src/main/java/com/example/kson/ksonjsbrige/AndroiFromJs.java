package com.example.kson.ksonjsbrige;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class AndroiFromJs {

    @JavascriptInterface
    public void hello(String msg){
        Log.e("test:",msg);
    }
}
