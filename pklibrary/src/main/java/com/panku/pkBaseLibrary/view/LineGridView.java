
package com.panku.pkBaseLibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
//自动绘制分隔线的gridview，最下面少了一条分隔线。复写onMeasure是为了不让滑动，全部展开，这样在最其下边可以加一条分隔线view
public class LineGridView extends GridView{
    public LineGridView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public LineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public LineGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void dispatchDraw(Canvas canvas){
        super.dispatchDraw(canvas);
        View localView1 = getChildAt(0);
        int column = getWidth() / localView1.getWidth();
        int childCount = getChildCount();
        Paint localPaint;
        localPaint = new Paint();
        localPaint.setStyle(Paint.Style.STROKE);
        localPaint.setColor(Color.parseColor("#e1e1e1"));
        for(int i = 0;i < childCount;i++){
            View cellView = getChildAt(i);
            if((i + 1) % column == 0){
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
            }else if((i + 1) > (childCount - (childCount % column))){
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
            }else{
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
            }
        }
        if(childCount % column != 0){
            for(int j = 0 ;j < (column-childCount % column) ; j++){
                View lastView = getChildAt(childCount - 1);
                canvas.drawLine(lastView.getRight() + lastView.getWidth() * j, lastView.getTop(), lastView.getRight() + lastView.getWidth()* j, lastView.getBottom(), localPaint);
            }
        }
    }
    
    @Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}