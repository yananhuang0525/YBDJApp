package com.panku.pkBaseLibrary.http;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import com.panku.pkBaseLibrary.R;
import com.panku.pkBaseLibrary.view.DialogLoadingView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.Map;

/**
 * Created by hyn on 2017/3/1.
 */

public class HttpHelp {
    private static String TAG = "HttpHelp";
    private volatile static HttpHelp instance;
    private Handler handler;
    private ImageOptions options;

    private HttpHelp() {
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static HttpHelp getInstance() {
        if (instance == null) {
            synchronized (HttpHelp.class) {
                if (instance == null) {
                    instance = new HttpHelp();
                }
            }
        }
        return instance;
    }

    /**
     * 异步get请求
     *
     * @param url
     * @param maps
     * @param callBack
     */
    public void get(String url, Map<String, String> maps, final XCallBack callBack) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                onSuccessResponse(result, callBack);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });
    }

    /**
     * 异步get请求
     *
     * @param url
     * @param maps
     * @param callBack
     */
    public void get(final Dialog dialog, String url, Map<String, String> maps, final XCallBack callBack) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        if (dialog != null) {
            dialog.show();
        }
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                onSuccessResponse(result, callBack);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    /**
     * 异步post请求
     *
     * @param url
     * @param maps
     * @param callback
     */
    public void post(String url, Map<String, String> maps, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.i(TAG, params.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.i("HYN-onSuccess", result);
                onSuccessResponse(result, callback);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("HYN-onError", ex.toString());
                onErrorMsg(ex, callback);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 带加载框的异步post请求
     *
     * @param dialog
     * @param url
     * @param maps
     * @param callback
     */
    public void post(final Dialog dialog, String url, Map<String, String> maps, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.i(TAG, params.toString());
        x.http().post(params, new Callback.ProgressCallback<String>() {

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
                if (dialog != null) {
                    dialog.show();
                }
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

            }

            @Override
            public void onSuccess(String result) {
                Log.i("HYN-onSuccess", result);
                onSuccessResponse(result, callback);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("HYN-onError", ex.toString());
                onErrorMsg(ex, callback);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    /**
     * 带缓存数据的异步 get请求
     *
     * @param url
     * @param maps
     * @param pnewCache
     * @param callback
     */
    public void getCache(String url, Map<String, String> maps, final boolean pnewCache, final XCallBack callback) {

        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.i(TAG, params.toString());
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                onSuccessResponse(result, callback);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                boolean newCache = pnewCache;
                if (newCache) {
                    newCache = !newCache;
                }
                if (!newCache) {
                    newCache = !newCache;
                    onSuccessResponse(result, callback);
                }
                return newCache;
            }
        });
    }

    /**
     * 带缓存数据的异步 post请求
     *
     * @param url
     * @param maps
     * @param pnewCache
     * @param callback
     */
    public void postCache(String url, Map<String, String> maps, final boolean pnewCache, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.i(TAG, params.toString());
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                onSuccessResponse(result, callback);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }


            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                boolean newCache = pnewCache;
                if (newCache) {
                    newCache = !newCache;
                }
                if (!newCache) {
                    newCache = !newCache;
                    onSuccessResponse(result, callback);
                }
                return newCache;
            }
        });
    }


    /**
     * 正常图片显示
     *
     * @param iv
     * @param url
     * @param option
     */
    public void bindCommonImage(ImageView iv, String url, int failImg, boolean option) {
        if (option) {
            options = new ImageOptions.Builder().setLoadingDrawableId(R.mipmap.allshare).setFailureDrawableId(failImg).build();
            x.image().bind(iv, url, options);
        } else {
            x.image().bind(iv, url);
        }
    }

    /**
     * 圆形图片显示
     *
     * @param iv
     * @param url
     * @param option
     */
    public void bindCircularImage(ImageView iv, String url, boolean option) {
        if (option) {
            options = new ImageOptions.Builder().setLoadingDrawableId(R.mipmap.allshare).setFailureDrawableId(R.mipmap.allshare).setCircular(true).build();
            x.image().bind(iv, url, options);
        } else {
            x.image().bind(iv, url);
        }
    }

    /**
     * 加载圆形头像
     *
     * @param iv
     * @param url
     * @param option
     */
    public void bindCircularImageHead(ImageView iv, String url, boolean option) {
        if (option) {
            options = new ImageOptions.Builder().setLoadingDrawableId(R.mipmap.ic_default).setFailureDrawableId(R.mipmap.ic_default).setCircular(true).build();
            x.image().bind(iv, url, options);
        } else {
            x.image().bind(iv, url);
        }
    }

    /**
     * 文件上传
     *
     * @param url
     * @param maps
     * @param file
     * @param callback
     */
    public void upLoadFile(String url, Map<String, String> maps, Map<String, File> file, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        if (file != null) {
            for (Map.Entry<String, File> entry : file.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue().getAbsoluteFile());
            }
        }
        Log.i(TAG, params.toString());
        // 有上传文件时使用multipart表单, 否则上传原始文件流.
        params.setMultipart(true);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                onSuccessResponse(result, callback);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    /**
     * 上传文件
     *
     * @param dialog
     * @param url
     * @param maps
     * @param file
     * @param callback
     */
    public void upLoadFile(final Dialog dialog, String url, Map<String, String> maps, Map<String, File> file, final XCallBack callback) {
        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        if (file != null) {
            for (Map.Entry<String, File> entry : file.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue().getAbsoluteFile());
            }
        }
        Log.i(TAG, params.toString());
        // 有上传文件时使用multipart表单, 否则上传原始文件流.
        params.setMultipart(true);
        x.http().post(params, new Callback.ProgressCallback<String>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
                if (dialog != null) {
                    dialog.show();
                }
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

            }

            @Override
            public void onSuccess(String result) {
                onSuccessResponse(result, callback);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });

    }

    /**
     * 文件下载
     *
     * @param url
     * @param maps
     * @param callBack
     */
    public void downLoadFile(String url, Map<String, String> maps, final XDownLoadCallBack callBack) {

        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.i(TAG, params.toString());
        params.setAutoRename(true);// 断点续传
        params.setSaveFilePath("");
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(final File result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onSuccess(result);
                        }
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onFinished();
                        }
                    }
                });
            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(final long total, final long current, final boolean isDownloading) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onLoading(total, current, isDownloading);
                        }
                    }
                });
            }
        });

    }


    /**
     * 异步get请求返回结果,json字符串
     *
     * @param result
     * @param callBack
     */
    private void onSuccessResponse(final String result, final XCallBack callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onSuccess(result);
                }
            }
        });
    }

    /**
     * 请求错误返回
     *
     * @param er
     * @param callBack
     */
    private void onErrorMsg(final Throwable er, final XCallBack callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onError(er);
            }
        });
    }

    public interface XCallBack {
        void onSuccess(String result);

        void onError(Throwable errorMsg);
    }

    public interface XDownLoadCallBack extends XCallBack {
        void onSuccess(File result);

        void onLoading(long total, long current, boolean isDownloading);

        void onFinished();
    }


}
