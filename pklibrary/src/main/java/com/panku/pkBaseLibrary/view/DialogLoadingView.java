package com.panku.pkBaseLibrary.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ant.liao.GifView;
import com.panku.pkBaseLibrary.R;


/**
 * @author 黎新建:
 * @version 创建时间：2014年9月22日 上午9:18:57 请求网络对话框工具类
 * 
 */
public class DialogLoadingView extends Dialog {

	private Context context;
	private static int style = R.style.sdk_base_dialog_style;// 主题
	private float alpha = 0.0f;// 弹出时候大背景透明度，0.0f纯白，1.0f纯黑

	public DialogLoadingView(Context context
			) {
		super(context, style);
		this.context = context;
		init();
	}
	private void init() {

		View dialogView = LayoutInflater.from(context).inflate(
				R.layout.dl_net_loading, null);


		//进度条动画

		((GifView)dialogView.findViewById(R.id.view_gif)).setGifImage(R.raw.core_ic_loading);

		setContentView(dialogView);
		setCanceledOnTouchOutside(false);
		Window window = getWindow(); // 得到对话框

		WindowManager.LayoutParams params = window.getAttributes();
		params.dimAmount = alpha; // 设置大背景颜色
		window.setAttributes(params);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	}

}
