package test.bwie.com.wuzijing0928;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by dell on 2017/9/28.
 */

public class M_Textview extends android.support.v7.widget.AppCompatTextView {
    public M_Textview(Context context) {
        super(context);
    }

    public M_Textview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public M_Textview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {

        Paint mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaint1.setStyle(Paint.Style.FILL);
        Paint mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);

        //绘制外层矩形
        canvas.drawRect(0, 0, getMeasuredWidth(),getMeasuredHeight(), mPaint1);
        //绘制内层矩形
        canvas.drawRect(10, 10, getMeasuredWidth()-10, getMeasuredHeight()-10, mPaint2);
        canvas.save();
        //绘制文字前平移10像素
        canvas.translate(10, 0);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:




                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;



        }




        return super.onTouchEvent(event);




    }
}
