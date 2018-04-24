package com.antonioleiva.mvpexample.app.Main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.antonioleiva.mvpexample.app.Main.fragment.payment.PropertyFeeFragment;
import com.antonioleiva.mvpexample.app.Main.fragment.payment.PropertyRuleFragment;
import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Utils.OnMenuListItemClick;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.antonioleiva.mvpexample.app.Main.fragment.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    private BadgeItem badgeItem; //添加角标
    private List<Fragment> mList; //ViewPager的数据源

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   getSupportActionBar().hide(); //标题栏不显示
        initBottomNavigationBar();
        initFragments();
        //设置默认的Item
        setDefaultFragment();
        //监听实现的方法
        bottomNavigationBar.setTabSelectedListener(this);
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

                    final PropertyFeeFragment propertyFeeFragment = PropertyFeeFragment.newInstance();
                    propertyFeeFragment.setOnMenuListItemClick(new OnMenuListItemClick() {
                        @Override
                        public void onClick(View view, int row) {
                            if(row == 1){
                                PropertyRuleFragment propertyRuleFragment = PropertyRuleFragment.newInstance();
                                FragmentManager fm = getSupportFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                ft.hide(propertyFeeFragment);
                                ft.addToBackStack(null);
                                if(propertyRuleFragment.isAdded()){
                                    ft.show(propertyRuleFragment);
                                }
                                else{
                                    ft.add(R.id.mFrame,propertyRuleFragment);
                                }
                                ft.commitAllowingStateLoss();
                            }
                        }
                    });
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                            //替换为TwoFragment
                    ft.hide(mList.get(1));
                    ft.addToBackStack(null);
                    if(propertyFeeFragment.isAdded()) {
                        ft.show(propertyFeeFragment);
                    }
                    else{
                        ft.add(R.id.mFrame,propertyFeeFragment);
                    }
                    ft.commitAllowingStateLoss();
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

//        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        viewPager.setOnPageChangeListener(this);
//        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), mList);
//        viewPager.setAdapter(mainAdapter); //视图加载适配器
    }


    //初始化底部导航条
    private void initBottomNavigationBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this);
        badgeItem = new BadgeItem()
                .setHideOnSelect(true) //设置被选中时隐藏角标
                .setBackgroundColor(Color.RED)
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
        bottomNavigationBar.setBarBackgroundColor(R.color.black);
        //选中时的颜色,在BACKGROUND_STYLE_STATIC下，表示选中Item的图标和文本颜色。
        // 而在BACKGROUND_STYLE_RIPPLE下，表示整个容器的背景色。默认Theme's Primary Color
        //bottomNavigationBar.setActiveColor(R.color.black);
        //未选中时的颜色，表示未选中Item中的图标和文本颜色。默认为 Color.LTGRAY
        //bottomNavigationBar.setInActiveColor("#FFFFFF");


        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "主页").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "缴费").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.drawable.ic_favorite_white_24dp, "求助").setActiveColorResource(R.color.white).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_launch_white_24dp, "更多").setActiveColorResource(R.color.white))
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
}