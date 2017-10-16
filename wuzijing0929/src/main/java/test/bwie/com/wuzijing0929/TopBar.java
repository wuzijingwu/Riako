package test.bwie.com.wuzijing0929;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dell on 2017/9/29.
 */

public class TopBar extends LinearLayout implements View.OnClickListener {
    private TextView title;
    private Button leftBtn;
    private Button rightBtn;

    public TopBar(Context context) {
        super(context);
        initView(context, null);
    }

    public TopBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View inflate = inflate(context, R.layout.top_bar_layout, this);
        title = (TextView) inflate.findViewById(R.id.title_tv);
        leftBtn = (Button) inflate.findViewById(R.id.left_btn);
        rightBtn = (Button) inflate.findViewById(R.id.right_btn);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        if (attrs == null) {
            return;
        }
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        title.setText(typedArray.getString(R.styleable.TopBar_title_text));
        leftBtn.setText(typedArray.getString(R.styleable.TopBar_left_btn_text));
        rightBtn.setText(typedArray.getString(R.styleable.TopBar_right_btn_text));

        title.setTextColor(typedArray.getColor(R.styleable.TopBar_title_text_color, Color.BLACK));
        leftBtn.setTextColor(typedArray.getColor(R.styleable.TopBar_left_btn_text_color, Color.BLACK));
        rightBtn.setTextColor(typedArray.getColor(R.styleable.TopBar_right_btn_text_color, Color.BLACK));

        title.setTextSize(typedArray.getDimension(R.styleable.TopBar_title_text_size, 16));
        leftBtn.setTextSize(typedArray.getDimension(R.styleable.TopBar_left_btn_text_size, 16));
        rightBtn.setTextSize(typedArray.getDimension(R.styleable.TopBar_right_btn_text_size, 16));

        title.setBackgroundColor(typedArray.getColor(R.styleable.TopBar_title_bg_color, Color.BLUE));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            leftBtn.setBackground(typedArray.getDrawable(R.styleable.TopBar_left_btn_bg_color));
            rightBtn.setBackground(typedArray.getDrawable(R.styleable.TopBar_right_btn_bg_color));
        } else {
            leftBtn.setBackgroundDrawable(typedArray.getDrawable(R.styleable.TopBar_left_btn_bg_color));
            rightBtn.setBackgroundDrawable(typedArray.getDrawable(R.styleable.TopBar_right_btn_bg_color));
        }
    }

    @Override
    public void onClick(View v) {
        String content = "";
        if (v.equals(leftBtn)) {
            content = "点击了返回";
        } else if (v.equals(rightBtn)) {
            content = "点击了更多";
        }
        Toast.makeText(v.getContext(), content, Toast.LENGTH_LONG).show();
    }


}
