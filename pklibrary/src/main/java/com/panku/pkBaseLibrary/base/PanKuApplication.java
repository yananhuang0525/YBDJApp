package com.panku.pkBaseLibrary.base;

import android.app.Application;
import android.content.Context;


/**
 * Created by hyn on 2017/3/25.
 */

public class PanKuApplication extends Application {
    protected static Context _context;  //上下文
    @Override
    public void onCreate() {
        super.onCreate();
//        x.Ext.init(this);
//        x.Ext.setDebug(false);
        if (_context == null) {  //判断是否已经被初始化过
            _context = getApplicationContext();
        }
    }
    public static synchronized PanKuApplication context() {
        return (PanKuApplication) _context;
    }
}
