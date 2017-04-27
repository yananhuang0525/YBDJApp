package com.panku.pkBaseLibrary.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by ylw on 16/2/2017.
 */

public class KeyBoardUtils {

    /**
     * 打开软键盘
     * @param mEditText    控件
     * @param mContext     界面上下文
     */
    public static void openKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     * @param mEditText     调用软键盘的控件
     * @param mContext      上下文对象（我的理解是在哪个界面）
     */
    public static void closeKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

}
