package com.antonioleiva.mvpexample.app.main;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.personalInfo.NoticeListActivity;
import com.antonioleiva.mvpexample.app.util.OnMenuListItemClick;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.antonioleiva.mvpexample.app.main.fragment.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, Toolbar.OnMenuItemClickListener {

    private BottomNavigationBar bottomNavigationBar;
    private BadgeItem badgeItem; //添加角标
    private List<Fragment> mList; //ViewPager的数据源
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide(); //标题栏不显示
        //像获取普通控件那样获取Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        //以下四个方法分别用来设置导航按钮、Logo、主标题和副标题
        //如果xml文件中没有设置这些属性或者想覆盖xml文件中设置的值可调用这些方法
//        mToolbar.setNavigationIcon(R.drawable.nav_icon);
//        mToolbar.setLogo(R.drawable.logo_icon);
//        mToolbar.setTitle("主标题");
//        mToolbar.setSubtitle("副标题");

        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(this);
        initBottomNavigationBar();
        initFragments();
        //设置默认的Item
        setDefaultFragment();
        //监听实现的方法
        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult request:"+requestCode+", resultCode:" + resultCode + "\n");
        onTabSelected(resultCode);
    }
    //初始化ViewPager
    private void initFragments() {
        // 实例化fragment放入数组
        mList = new ArrayList<>();
        mList.add(new HomePageFragment());
        PaymentFragment paymentFragment = PaymentFragment.newInstance();
        paymentFragment.setOnMenuListItemClick(new OnMenuListItemClick() {
            @Override
            public void onClick(View view, int row) {
            //    System.out.println("点击"+row);
                if(row == 0){

//                    final PropertyFeeFragment propertyFeeFragment = PropertyFeeFragment.newInstance();
//                    propertyFeeFragment.setOnMenuListItemClick(new OnMenuListItemClick() {
//                        @Override
//                        public void onClick(View view, int row) {
//                            if(row == 1){
//                                PropertyRuleFragment propertyRuleFragment = PropertyRuleFragment.newInstance();
//                                FragmentManager fm = getSupportFragmentManager();
//                                FragmentTransaction ft = fm.beginTransaction();
//                                ft.hide(propertyFeeFragment);
//                                ft.addToBackStack(null);
//                                if(propertyRuleFragment.isAdded()){
//                                    ft.show(propertyRuleFragment);
//                                }
//                                else{
//                                    ft.add(R.id.mFrame,propertyRuleFragment);
//                                }
//                                ft.commitAllowingStateLoss();
//                            }
//                        }
//                    });
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
//                            //替换为TwoFragment
//                    ft.hide(mList.get(1));
//                    ft.addToBackStack(null);
//                    if(propertyFeeFragment.isAdded()) {
//                        ft.show(propertyFeeFragment);
//                    }
//                    else{
//                        ft.add(R.id.mFrame,propertyFeeFragment);
//                    }
//                    ft.commitAllowingStateLoss();
                }
                if(row == 1){

                }
            }
        });
        mList.add(paymentFragment);
        mList.add(new MovieFragment());
        ExtendFragment extendFragment = ExtendFragment.newInstance();
        extendFragment.setOnMenuListItemClick(new OnMenuListItemClick() {
            @Override
            public void onClick(View view, int row) {
                Toast.makeText(getApplicationContext(), row,
                        Toast.LENGTH_SHORT).show();
            }
        });
        mList.add(new ExtendFragment());
    }


    //初始化底部导航条
    private void initBottomNavigationBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this);
        badgeItem = new BadgeItem()
                .setHideOnSelect(true) //设置被选中时隐藏角标
                .setBackgroundColor(R.color.colorAccent)
                .setText("99");
        /**
         * 设置模式
         * 1、BottomNavigationBar.MODE_DEFAULT 默认
         * 如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式
         *
         * 2、BottomNavigationBar.MODE_FIXED 固定大小
         * 填充模式，未选中的Item会显示文字，没有换挡动画。
         *
         * 3、BottomNavigationBar.MODE_SHIFTING 不固定大小
         * 换挡模式，未选中的Item不会显示文字，选中的会显示文字。在切换的时候会有一个像换挡的动画
         */
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        /**
         * 设置背景的样式
         * 1、BottomNavigationBar.BACKGROUND_STYLE_DEFAULT 默认
         * 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC 。
         * 如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
         *
         * 2、BottomNavigationBar.BACKGROUND_STYLE_STATIC 导航条的背景色是白色，
         * 加上setBarBackgroundColor（）可以设置成你所需要的任何背景颜色
         * 点击的时候没有水波纹效果
         *
         * 3、BottomNavigationBar.BACKGROUND_STYLE_RIPPLE 导航条的背景色是你设置的处于选中状态的
         * Item的颜色（ActiveColor），也就是setActiveColorResource这个设置的颜色
         * 点击的时候有水波纹效果
         */
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //设置导航条背景颜色
        //在BACKGROUND_STYLE_STATIC下，表示整个容器的背景色。
        // 而在BACKGROUND_STYLE_RIPPLE下，表示选中Item的图标和文本颜色。默认 Color.WHITE
        bottomNavigationBar.setBarBackgroundColor(R.color.bottomNavigation);
        //选中时的颜色,在BACKGROUND_STYLE_STATIC下，表示选中Item的图标和文本颜色。
        // 而在BACKGROUND_STYLE_RIPPLE下，表示整个容器的背景色。默认Theme's Primary Color
        //bottomNavigationBar.setActiveColor(R.color.black);
        //未选中时的颜色，表示未选中Item中的图标和文本颜色。默认为 Color.LTGRAY
        //bottomNavigationBar.setInActiveColor("#FFFFFF");


        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_home_grey600_24dp, "主页").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_payment_grey600_24dp, "缴费").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_help_grey600_24dp, "求助").setActiveColorResource(R.color.colorPrimary).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_open_in_new_black_24dp, "更多").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise(); //所有的设置需在调用该方法前完成

        //如果使用颜色的变化不足以展示一个选项Item的选中与非选中状态，
        // 可以使用BottomNavigationItem.setInActiveIcon()方法来设置。
//        new BottomNavigationItem(R.drawable.ic_home_white_24dp, "公交")//这里表示选中的图片
//                .setInactiveIcon(ContextCompat.getDrawable(this,R.mipmap.ic_launcher));//非选中的图片
    }
    /**
     * 设置默认的Item
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.mFrame, mList.get(0));
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        if (mList != null) {
            if (position < mList.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //当前的fragment
                Fragment from = fm.findFragmentById(R.id.mFrame);
                //点击即将跳转的fragment
                Fragment to = mList.get(position);
                if (to.isAdded()) {
                    // 隐藏当前的fragment，显示下一个
                    ft.hide(from).show(to);
                } else {
                    // 如果要跳转的fragment被隐藏，显示
                    if (to.isHidden()) {
                        ft.show(to);
                    }
                    // 隐藏当前的fragment，add下一个到Activity中
                    ft.hide(from).add(R.id.mFrame, to);
                }
                ft.commitAllowingStateLoss();
            }
            switch (position){
                case 0:
                    getSupportActionBar().setTitle("主页");
                    break;
                case 1:
                    getSupportActionBar().setTitle("缴费");
                    break;
                case 2:
                    getSupportActionBar().setTitle("求助");
                    break;
                case 3:
                    getSupportActionBar().setTitle("更多");
                    break;
            }
        }else {
            initFragments();
        }
    }

    @Override
    public void onTabUnselected(int position) {
        //这儿也要操作隐藏，否则Fragment会重叠
        if (mList != null) {
            if (position < mList.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = mList.get(position);
                // 隐藏当前的fragment
                ft.hide(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 显示 OverFlow 中的图标
     */
//    @Override
//    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
//        if (menu != null) {
//            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
//                try {
//                    Method m = menu.getClass().getDeclaredMethod(
//                            "setOptionalIconsVisible", Boolean.TYPE);
//                    m.setAccessible(true);
//                    m.invoke(menu, true);
//                } catch (Exception e) {
//                }
//            }
//        }
//        return super.onPrepareOptionsPanel(view, menu);
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(MainActivity.this, "点击了菜单项" + item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_message:
                Intent intent = new Intent(MainActivity.this, NoticeListActivity.class);
                intent.putExtra("id",0);
                startActivityForResult(intent,0);
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}