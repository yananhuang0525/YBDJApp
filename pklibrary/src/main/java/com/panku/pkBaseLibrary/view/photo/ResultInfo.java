package com.panku.pkBaseLibrary.view.photo;

/**
 * Created by Administrator on 2015/11/23 0023.
 */
public class ResultInfo {
    /**
     * 图片是否处理完成
     */
    private boolean isDealFinished;
    /**
     * 图片保存路径 仅当isDealFinished属性为true时有值
     */
    private String filePath;

    public boolean isDealFinished() {
        return isDealFinished;
    }

    public void setIsDealFinished(boolean isDealFinished) {
        this.isDealFinished = isDealFinished;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
