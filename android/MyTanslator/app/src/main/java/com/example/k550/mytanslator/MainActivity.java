package com.example.k550.mytanslator;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.view.LayoutInflater;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TabHost tabHost;
    //private TabView tabview;//
   // private LayoutInflater inflater1;
    //用于添加每一个选项卡的id
    private String[] tags = {"A_tag", "B_tag", "C_tag", "D_tag"};
    //所添加选项卡的文本信息
    private String[] titles = {"新闻", "图片", "视频", "收藏"};
    //所添加选项卡的图片信息
    private int[] images = {R.drawable.tb1, R.drawable.tb2,};
    //用于跳转至不同的Activity
    private Intent[] intents = new Intent[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



      // tabHost = (TabHost) LayoutInflater.from(MainActivity.this).inflate(R.layout.content_main, null).findViewById(R.id.tabhost);
       tabHost =(TabHost)findViewById(android.R.id.tabhost);
        //TabWidget tabWidget = (TabWidget) findViewById(android.R.id.tabs);
        // tabWidget.setDividerDrawable(null);//设置tabWeight没有竖线分割
        //tabWidget.setBackgroundColor(Color.WHITE);
        //初始化activity管理者
        //LocalActivityManager manager = new LocalActivityManager(MainActivity.this, false);
        //通过管理者保存当前页面状态
        //manager.dispatchCreate(savedInstanceState);
        //调用 TabHost.setup()
        tabHost.setup();

        /*
        init_intent();
        for (int i = 0; i < 2; i++) {
            //加载底部导航栏布局
            LayoutInflater inflater1 = this.getLayoutInflater();
            View view1 = inflater1.inflate(R.layout.tab, null);
            TextView textView = (TextView) view1.findViewById(R.id.tv_item);
            ImageView imageView = (ImageView) view1.findViewById(R.id.image);
            textView.setText(titles[i]);
            imageView.setImageResource(images[i]);
            //创建选项卡
            TabHost.TabSpec spec = tabHost.newTabSpec(tags[i]);
            spec.setIndicator(view1);
            //设置每个页面的内容
            spec.setContent(R.id.tablayout1);
            //将创建的选项卡添加至tabHost上
            tabHost.addTab(spec);
        }

*/

        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("aaa",getDrawable(R.drawable.tb1)).setContent(R.id.tablayout1));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("aaa",getDrawable(R.drawable.tb2)).setContent(R.id.tablayout2));

        tabHost.setCurrentTab(0);

    }


    //每个页面放置的Activity
    public void init_intent() {
        intents[0] = new Intent(this, collectActivity.class);
        intents[1] = new Intent(this, translateActivity.class);

    }

    public View composeLayout(String s, int i) {
// 定义一个LinearLayout布局
        LinearLayout layout = new LinearLayout(this);
// 设置布局垂直显示
        layout.setOrientation(LinearLayout.VERTICAL);
        ImageView iv = new ImageView(this);

        //imageList.add(iv);
        iv.setImageResource(i);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 5, 0, 0);
        layout.addView(iv, lp);
// 定义TextView
        TextView tv = new TextView(this);
        tv.setGravity(Gravity.CENTER);
        tv.setSingleLine(true);
        tv.setText(s);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(10);
        layout.addView(tv, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        return layout;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
