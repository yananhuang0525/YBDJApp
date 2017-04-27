package com.panku.pkBaseLibrary.util;

import android.content.res.AssetManager;
import android.os.Environment;

import com.panku.pkBaseLibrary.base.PanKuApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


/**
 * Des:
 * User: anxing(13683717560@139.com)
 * Date: 2015-05-29
 * Time: 13:51
 * FIXME
 */
public class FileHelp {
    private final static String TailPathChar = "/";

    /**
     * 修补路径字符串，不以/结尾的，修改为以/结尾
     *
     * @param path
     * @return
     */
    private static String fixPath(String path) {
        path = path.trim();
        if (!path.endsWith(TailPathChar))
            path = path + TailPathChar;
        return path;
    }

    /**
     * 仅仅COPY文件,不判断文件是否存在等
     *
     * @param srcFile
     * @param dstFile
     */
    public static void copyFileOnly(String srcFile, String dstFile) throws IOException {
        InputStream myInput = new FileInputStream(srcFile);
        copyFileOnly(myInput, dstFile);
    }

    /**
     * 仅仅COPY文件,不判断文件是否存在等
     *
     * @param inputStream
     * @param dstFile
     */
    public static void copyFileOnly(InputStream inputStream, String dstFile) throws IOException {
        OutputStream myOutput = null;
        try {
            myOutput = new FileOutputStream(dstFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (inputStream != null)
                inputStream.close();
            if (myOutput != null)
                myOutput.close();
        }

    }

    /**
     * COPY文件，
     *
     * @param srcPath
     * @param srcFileName
     * @param dstPath
     * @param dstFileName
     * @param bExistDelete 存在是否删除
     * @return
     */
    public static boolean copyFile(String srcPath, String srcFileName,
                                   String dstPath, String dstFileName, boolean bExistDelete) {

        boolean isSuccess = false;

        try {
            srcPath = fixPath(srcPath);
            dstPath = fixPath(dstPath);
            InputStream myInput = new FileInputStream(srcPath + srcFileName);
            copyFile(myInput, dstPath, dstFileName, bExistDelete);
            isSuccess = true;

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }

    public static boolean copyFile(InputStream srcStream,
                                   String dstPath, String dstFileName, boolean bExistDelete) {
        boolean isSuccess = false;
        try {
            dstPath = fixPath(dstPath);
            if (bExistDelete) {
                //判断目的是否已经存在文件,存在则删除
                File directory = new File(dstPath + dstFileName);
                if (directory.exists()) {
                    directory.delete();
                }
            }
            //目的路径不存在，则先创建
            File targetPathFile = new File(dstPath);
            if (!targetPathFile.exists()) {
                targetPathFile.mkdirs();
            }
            copyFileOnly(srcStream, dstPath + dstFileName);
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * 读取asset文件，以string 格式返回
     *
     * @param fileName
     * @return
     */
    public static String readAsSetFile(String fileName) {
        try {
            AssetManager assetManager = PanKuApplication.context().getResources().getAssets();
            InputStreamReader inputReader = new InputStreamReader(assetManager.open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String result = "";
            while ((line = bufReader.readLine()) != null)
                result += line;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否存在SD卡
     *
     * @return
     */
    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            return false;
        }
        return true;
    }

    /**
     * 得到根目录
     *
     * @return
     */
    public static String getRootFilePath() {
        if (hasSDCard()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/";// filePath:/sdcard/
        } else {
            return Environment.getDataDirectory().getAbsolutePath() + "/data/"; // filePath:
            // /data/data/
        }
    }

    /**
     * 得到保存文件路径
     *
     * @return
     */
    public static String getSaveFilePath() {
//        String path = getRootFilePath() + "com.panku.data/";
//        createDirectory(path);
//        path = path + "database/";
//        createDirectory(path);
        String path = getRootFilePath() + PanKuApplication.context().getPackageName();
        createDirectory(path);
        path = path + "/database/";
        createDirectory(path);
        return getRootFilePath() + PanKuApplication.context().getPackageName() + "/database/";
    }

    public static boolean fileIsExist(String filePath) {
        if (filePath == null || filePath.length() < 1) {
            return false;
        }

        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();
            return false;
        }
        return true;
    }

    public static void createDirectory(String path) {
        if (path == null || path.length() < 1) {
            return;
        }

        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
    }
}
