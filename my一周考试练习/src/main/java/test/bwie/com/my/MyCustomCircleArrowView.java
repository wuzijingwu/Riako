package test.bwie.com.my;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dell on 2017/9/28.
 */

public class MyCustomCircleArrowView extends View {

    private Paint paint;

    public MyCustomCircleArrowView(Context context) {
        super(context);
    }

    public MyCustomCircleArrowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);






    }

    public MyCustomCircleArrowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
//        context.obtainStyledAttributes(attrs,R)


    }

    public void initView(Context context){
        paint = new Paint();
    }


}
