package com.panku.pkBaseLibrary.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panku.pkBaseLibrary.R;


/**
 * Created by hyn on 2017/3/7.
 * 自定义简单的提示对话框
 */

public class SimplePromptDialog extends Dialog {
    private OnSelectMenuListener onSelectMenuListener;
    private View dlgView;
    private Context context;

    private TextView tv_title;//标题文字
    private LinearLayout ll_title;//标题文字
    private TextView tv_content;//提示内容
    private TextView tv_yes;//按钮文字
    private TextView tv_no;//取消按钮
    private LinearLayout ll_no;//取消按钮区域

    public SimplePromptDialog(Context context) {
        super(context, R.style.myDialog);
        this.context = context;
        initView();
    }

    private void initView() {
        dlgView = LayoutInflater.from(context).inflate(R.layout.dlg_prompt, null);
        setContentView(dlgView);
        //设置dialog大小
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.CENTER);
        DisplayMetrics dm = new DisplayMetrics();//获取屏幕宽度
        manager.getDefaultDisplay().getMetrics(dm);
        params.width = (int) (dm.widthPixels * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
        dialogWindow.setAttributes(params);
        tv_title = (TextView) dlgView.findViewById(R.id.tv_title);
        tv_content = (TextView) dlgView.findViewById(R.id.tv_content);
        tv_yes = (TextView) dlgView.findViewById(R.id.tv_yes);
        tv_no = (TextView) dlgView.findViewById(R.id.tv_no);
        ll_title = (LinearLayout) dlgView.findViewById(R.id.ll_title);
        ll_no = (LinearLayout) dlgView.findViewById(R.id.ll_no);

        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSelectMenuListener != null) {
                    onSelectMenuListener.onYes();
                }
                dismiss();
            }
        });
        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setOnSelectMenuListener(OnSelectMenuListener onSelectMenuListener) {
        this.onSelectMenuListener = onSelectMenuListener;
    }

    /**
     * 设置标题是否显示
     *
     * @param showTitle
     */
//    public void setShowTitle(boolean showTitle) {
//        if (showTitle) {
//            tv_title.setVisibility(View.VISIBLE);
//        } else {
//            tv_title.setVisibility(View.GONE);
//        }
//    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitleText(String title) {
        if (!"".equals(title)) {
            ll_title.setVisibility(View.VISIBLE);
            tv_title.setText(title);
        } else {
            ll_title.setVisibility(View.GONE);
        }
    }

    /**
     * 设置提示内容
     *
     * @param content
     */
    public void setContentText(String content) {
        if (!"".equals(content)) {
            tv_content.setText(content);
        }
    }

    /**
     * 设置按钮文字
     *
     * @param btnText 按钮文字
     */
    public void setBtnText(String btnText) {
        if (!"".equals(btnText)) {
            tv_yes.setText(btnText);
        } else {
            tv_yes.setText("确认");
        }
    }

    /**
     * 显示取消按钮
     */
    public void setShowCancel() {
        ll_no.setVisibility(View.VISIBLE);
    }

    public interface OnSelectMenuListener {
        void onYes();
    }
}
