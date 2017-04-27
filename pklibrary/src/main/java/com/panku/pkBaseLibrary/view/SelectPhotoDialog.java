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
import android.widget.TextView;

import com.panku.pkBaseLibrary.R;


/**
 * Created by hyn on 2017/3/2.
 * 拍照、从相册选取图片提示框
 */

public class SelectPhotoDialog extends Dialog {
    private OnSelectMenuListener onSelectMenuListener;
    private View dlgView;
    private Context context;

    public SelectPhotoDialog(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public SelectPhotoDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
        initView();
    }

    /**
     * 初始化dialog
     */
    public void initView() {
        dlgView = LayoutInflater.from(context).inflate(R.layout.dlg_headpic, null);
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

        dlgView.findViewById(R.id.btn_dimis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        dlgView.findViewById(R.id.btn_makePhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (onSelectMenuListener != null)
                    onSelectMenuListener.onSelect(((TextView) dlgView.findViewById(R.id.btn_makePhoto)).getText().toString());
            }
        });
        dlgView.findViewById(R.id.btn_fromCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (onSelectMenuListener != null)
                    onSelectMenuListener.onSelect(((TextView) dlgView.findViewById(R.id.btn_fromCamera)).getText().toString());
            }
        });

    }

    public void setOnSelectMenuListener(OnSelectMenuListener onSelectMenuListener) {
        this.onSelectMenuListener = onSelectMenuListener;
    }

    public interface OnSelectMenuListener {
        public void onSelect(String type);
    }
}
