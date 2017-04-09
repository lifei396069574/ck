package com.example.administrator.myandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import fragment.MenuLeftFragment;
import utils.MyAsynctask;
import utils.URL;

public class MainActivity extends SlidingFragmentActivity{

    private ListView mLv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();

        initdata();

        initRightMenu();

    }


    public void initview() {

        mLv = (ListView) findViewById(R.id.lv);

    }


    public void initdata() {

        MyAsynctask myAsynctask = new MyAsynctask(this, URL.url,mLv);
        myAsynctask.execute();

    }


    private void initRightMenu()
    {

        Fragment leftMenuFragment = new MenuLeftFragment();
        setBehindContentView(R.layout.left_menu_frame);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        int kuan = width/3 *2;
        //侧滑菜单的宽度
        menu.setBehindWidth(kuan);
        //剩余主页面的宽度
     //   menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        // menu.setBehindScrollScale(1.0f);
        menu.setSecondaryShadowDrawable(R.drawable.shadow);

    }

    public void showLeftMenu(View view)
    {
        getSlidingMenu().showMenu();
    }


}
