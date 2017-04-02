package com.example.k550.mytanslator;

/**
 * Created by K550 on 2017/3/15.
 */
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class TabView {
    private Context context;//�����Ļ���
    public TabView(Context context){
        this.context = context;
    }
    public View getTabView(String title, int icon){
        LinearLayout layout = new LinearLayout(context);
        //
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.argb(120, 200, 200, 200));
        //
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 0, 0);
        //
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setSingleLine(true);
        tv.setText(title);
        tv.setTextColor(Color.BLUE);
        tv.setTextSize(10);
        layout.addView(tv,params);
        //
        ImageView iv = new ImageView(context);
        iv.setImageResource(icon);
        layout.addView(iv, params);
        return layout;
    }
}
