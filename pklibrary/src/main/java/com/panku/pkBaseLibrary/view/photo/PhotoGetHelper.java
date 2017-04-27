package com.panku.pkBaseLibrary.view.photo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.ImageView;


import com.panku.pkBaseLibrary.R;
import com.panku.pkBaseLibrary.util.FileHelp;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.pkBaseLibrary.view.SelectPhotoDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hyn on 2015/11/20.
 */
public class PhotoGetHelper {
    private static final String TAG = "PhotoGetHelper";
    private Context context;
    private SelectPhotoDialog dlg;
    // 无动作
    public static final int NONE = 0;
    // 拍照
    private static final int PHOTOHRAPH = 1;
    // 缩放
    private static final int PHOTOZOOM = 2;
    // 结果
    private static final int PHOTORESOULT = 3;
    // 图片路径
    public static final String IMAGE_UNSPECIFIED = "image/*";

    public static final String IMAGE_NAME = "temp.png";
    /**
     * 图片Bitmap
     */
    private Bitmap photo = null;
    /**
     * 图片文件
     */
    private File file;

    public PhotoGetHelper() {
    }

    public PhotoGetHelper(Context context) {
        this.context = context;
        dlg = new SelectPhotoDialog(context, R.style.myDialog);
    }

    /**
     * 打开弹窗
     */
    public void showDialog() {
        dlg.setOnSelectMenuListener(new SelectPhotoDialog.OnSelectMenuListener() {
            @Override
            public void onSelect(String s) {
                if (s.equals(context.getResources().getString(R.string.cbb_photoGet_makePhoto))) {
                    Intent makePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file = new File(Environment
                            .getExternalStorageDirectory(), IMAGE_NAME);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//适配7.0相机
                        Uri imgUri = FileProvider.getUriForFile(context,
                                "com.panku.pkBaseLibrary.view.fileprovider", file);
                        makePhotoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        makePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                    } else {
                        makePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                                .fromFile(file));
                    }
                    ((Activity) context).startActivityForResult(makePhotoIntent, PHOTOHRAPH);
                } else if (s.equals(context.getResources().getString(R.string.cbb_photoGet_select))) {
                    Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
                    cameraIntent.setDataAndType(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            IMAGE_UNSPECIFIED);
                    ((Activity) context).startActivityForResult(cameraIntent, PHOTOZOOM);
                }
            }
        });
        dlg.show();
    }

    /**
     * 回调处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     * @param imageView
     */
    public ResultInfo operationResult(int requestCode, int resultCode, Intent data, ImageView imageView) {
        ResultInfo resultInfo = new ResultInfo();
        if (resultCode == NONE) {
            return resultInfo;
        }
        // 拍照
        if (requestCode == PHOTOHRAPH) {
            // 设置文件保存路径这里放在跟目录下
            File picture = new File(Environment.getExternalStorageDirectory()
                    + File.separator + IMAGE_NAME);
            startPhotoZoom(Uri.fromFile(picture));
        }
        if (data == null) {
            return resultInfo;
        }
        // 读取相册缩放图片
        if (requestCode == PHOTOZOOM) {
            startPhotoZoom(data.getData());
            return resultInfo;
        }
        // 处理结果
        if (requestCode == PHOTORESOULT) {
            resultInfo.setIsDealFinished(true);
            Bundle extras = data.getExtras();
            if (extras != null) {
                photo = extras.getParcelable("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 -//
//                photo = getRoundedCornerBitmap(photo, 10);
                // 100)压缩文件
                if (imageView != null) {
                    imageView.setImageBitmap(photo);
                }
                // 将返回的数据生成文件存储到本地
                saveMyBitmap(IMAGE_NAME, photo);
                // 创建file对象
                Log.i(TAG, FileHelp.getSaveFilePath());
                file = new File(FileHelp.getSaveFilePath(), IMAGE_NAME);
                if (file == null && !file.exists()) {
//                    String notFile = context.getResources().getString(R.string.cbb_photoGet_file_not_exit);
                    String notFile = "保存文件失败";
                    ToastUtils.showToast(notFile);
                } else {
                    resultInfo.setFilePath(file.getAbsolutePath());
                }
            }
            return resultInfo;
        }
        return resultInfo;
    }

    /**
     * 获取圆角的bitmap
     *
     * @param bitmap
     * @param pixels
     * @return      
     */
    private static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * 裁剪图片
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        String url = UriToPath.getPath(context, uri);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.i(TAG, "7.0及以上");
            intent.setDataAndType(getImageContentUri(context, new File(url)), IMAGE_UNSPECIFIED);//自己使用Content Uri替换File Uri
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            intent.setDataAndType(Uri.fromFile(new File(url)),
                    IMAGE_UNSPECIFIED);
            Log.i(TAG, "4.4---7.0");
        } else {
            intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
            Log.i(TAG, "4.4以下");
        }
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 240);
        intent.putExtra("outputY", 240);
        intent.putExtra("return-data", true);
        ((Activity) context).startActivityForResult(intent, PHOTORESOULT);
    }

    /*
     * 将 bitmap数据保存到本地文件
     */
    public void saveMyBitmap(String bitName, Bitmap mBitmap) {
        File f = new File(FileHelp.getSaveFilePath(), bitName);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * 转换 content:// uri  适配7.0
     *
     * @param imageFile
     * @return
     */
    public Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }
}
