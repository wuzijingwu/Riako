package test.bwie.com.myview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import test.bwie.com.myview.R;

/**
 * Created by dell on 2017/9/27.
 */

public class SdeLock extends View {

    private Bitmap jiesuo_bg;
    private Bitmap jiesuo_button;
    private int bg_width;
    private int bg_height;
    private int block_width;
    private int block_height;
    private int measuredWidth;
    private int measuredheight;
    private float currentX;
    private float currentY;
    private int left;
    private int right;
    private float downX;
    private float downy;
    private double sqrt;
    private Boolean onBlock;
    private OnUnlockListener onUnlockListener;


    public void init() {
        jiesuo_bg = BitmapFactory.decodeResource(getResources(), R.mipmap.jiesuo_bg);
        jiesuo_button = BitmapFactory.decodeResource(getResources(), R.mipmap.jiesuo_button);
        bg_width = jiesuo_bg.getWidth();
        bg_height = jiesuo_bg.getHeight();
        block_width = jiesuo_button.getWidth();
//        block_height = jiesuo_button.getHeight();
    }


    public SdeLock(Context context) {
        super(context);
        init();
    }

    public SdeLock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SdeLock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(jiesuo_bg, measuredWidth / 2 - bg_width / 2, measuredheight / 2 - bg_height / 2, null);
        if (currentX < left) {
            currentX = left;
        } else if (currentX > right) {
            currentX = right;
        }
        canvas.drawBitmap(jiesuo_button, currentX, currentY, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = getMeasuredWidth();
        measuredheight = getMeasuredHeight();
        currentX = measuredWidth / 2 - bg_width / 2;
        currentY = measuredheight / 2 - bg_height / 2;
        left = measuredWidth / 2 - bg_width / 2;
        right = measuredWidth / 2 + bg_width / 2 - block_width;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downy = event.getY();
                onBlock = isOnBlock(downX, downy);
                if (onBlock) {
                    Toast.makeText(getContext(), "按下了", Toast.LENGTH_SHORT).show();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (onBlock) {
                    float moveX = event.getX();
                    currentX = moveX - block_width / 2;
                    invalidate();
                }

                break;
            case MotionEvent.ACTION_UP:
                onBlock = false;
                if (currentX < right - 5) {
                    currentX = left;
                } else {
                    if (onUnlockListener != null) {
                        Toast.makeText(getContext(), "解锁", Toast.LENGTH_SHORT).show();
                        onUnlockListener.setUnlock(true);
                    }

                }

                invalidate();
                break;
        }
        return true;
    }

    public Boolean isOnBlock(float downX, float downy) {
        float rx = currentX + block_width / 2;
        float ry = currentY + block_width / 2;
        sqrt = Math.sqrt((this.downX - rx) * (this.downX - rx) + (this.downy - ry) * (this.downy - ry));
        if (sqrt < block_width / 2) {
            return true;
        }
        return false;
    }

    public void setOnUnlockListener(OnUnlockListener onUnlockListener) {
        this.onUnlockListener = onUnlockListener;
    }


}
