package com.panku.pkBaseLibrary.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.panku.pkBaseLibrary.R;
import com.panku.pkBaseLibrary.view.wheelview.OnWheelChangedListener;
import com.panku.pkBaseLibrary.view.wheelview.WheelView;
import com.panku.pkBaseLibrary.view.wheelview.adapter.NumericWheelAdapter;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by hyn on 2017/3/6.
 * 自定义选择日期
 */

public class SelectDateDialog extends Dialog {
    private View dlgView;
    private Context context;
    private DialogButtonListener dialogButtonListener;
    private TextView tv_positive;//确认按钮
    private TextView tv_negative;//取消按钮
    private WheelView wv_year;
    private WheelView wv_month;
    private WheelView wv_day;
    private String selectDate = "";
    private int curYear = getWv_year();
    private int curMonth = getWv_month();
    private int curDay = getWv_day();
    private int MIN_YEAR = 1900;
    private int MAX_YEAR = getWv_year();

    public SelectDateDialog(Context context) {
        super(context, R.style.myDialog);
        this.context = context;
        initDialog();
    }

    /**
     * 显示日期
     */
    private void initDialog() {
        dlgView = LayoutInflater.from(context).inflate(R.layout.dlg_select_date, null);
        setContentView(dlgView);
        //设置dialog大小
        final Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.BOTTOM);
        DisplayMetrics dm = new DisplayMetrics();//获取屏幕宽度
        manager.getDefaultDisplay().getMetrics(dm);
        params.width = dm.widthPixels;
        dialogWindow.setAttributes(params);
        dialogWindow.setWindowAnimations(R.style.AnimBottom);  // 设置弹出的动画效果

        wv_year = (WheelView) dlgView.findViewById(R.id.wv_year);
        wv_month = (WheelView) dlgView.findViewById(R.id.wv_month);
        wv_day = (WheelView) dlgView.findViewById(R.id.wv_day);
        initYear();
        initMonth();
        initDay(curYear, curMonth);

        wv_year.setCurrentItem(curYear - MIN_YEAR);
        wv_month.setCurrentItem(curMonth - 1);
        wv_day.setCurrentItem(curDay - 1);

        wv_year.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                curYear = newValue + MIN_YEAR;
                initDay(curYear, curMonth);
            }
        });
        wv_month.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                curMonth = newValue + 1;
                initDay(curYear, curMonth);
            }
        });
        wv_day.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                curDay = newValue + 1;
            }
        });

        // 设置监听
        tv_positive = (TextView) dlgView.findViewById(R.id.tv_positive);
        tv_negative = (TextView) dlgView.findViewById(R.id.tv_negative);
        tv_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate = String.format(Locale.CHINA, "%4d-%d-%d", curYear, curMonth, curDay);
//                Toast.makeText(context, selectDate, Toast.LENGTH_LONG).show();
                dialogButtonListener.positiveClick();
            }
        });
        tv_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogButtonListener.negativeClick();
            }
        });

    }

    /**
     * 设置日期
     *
     * @return 选中的日期
     */
    public String setDate() {
        return selectDate;
    }

    /**
     * 初始化年
     */
    private void initYear() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context, MIN_YEAR, MAX_YEAR);
        numericWheelAdapter.setLabel("");
        //		numericWheelAdapter.setTextSize(15);  设置字体大小
        wv_year.setViewAdapter(numericWheelAdapter);
        wv_year.setCyclic(true);
    }

    /**
     * 初始化月
     */
    private void initMonth() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context, 1, 12, "%02d");
        numericWheelAdapter.setLabel("");
        //		numericWheelAdapter.setTextSize(15);  设置字体大小
        wv_month.setViewAdapter(numericWheelAdapter);
        wv_month.setCyclic(true);
    }

    /**
     * 初始化天
     *
     * @param year
     * @param month
     */
    private void initDay(int year, int month) {
        Log.i("day", month + "月" + getDay(year, month) + "天");
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context, 1, getDay(year, month), "%02d");
        numericWheelAdapter.setLabel("");
        //		numericWheelAdapter.setTextSize(15);  设置字体大小
        wv_day.setViewAdapter(numericWheelAdapter);
        wv_day.setCyclic(true);
    }

    /**
     * 获取天数
     *
     * @param year
     * @param month
     * @return
     */
    private int getDay(int year, int month) {
        int day = 30;
        if (month == 2 && year % 4 == 0) {//闰年2月份29天
            day = 29;
        } else {
            day = 28;
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            day = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            day = 30;
        }
        return day;
    }

    /**
     * 设置按钮监听
     *
     * @param positiveListener
     */
    public void setDialogButtonListener(DialogButtonListener positiveListener) {
        this.dialogButtonListener = positiveListener;
    }


    public interface DialogButtonListener {
        void positiveClick();

        void negativeClick();
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public int getWv_year() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public int getWv_month() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public int getWv_day() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DATE);
    }

    /**
     * 设置启始年份
     *
     * @param min_year
     * @return
     */
    public int setMIN_YEAR(int min_year) {
        MIN_YEAR = min_year;
        return MIN_YEAR;
    }

    /**
     * 设置最大年份
     *
     * @param max_year
     * @return
     */
    public int setMAX_YEAR(int max_year) {
        MAX_YEAR = max_year;
        return MAX_YEAR;
    }
}
