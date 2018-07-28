package com.flag.butler;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.flag.butler.MainPageFragment.Page1Fragment;
import com.flag.butler.MainPageFragment.Page2Fragment;
import com.flag.butler.MainPageFragment.Page3Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private MenuItem menuItem;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null){
                    menuItem.setChecked(false);
                    menuItem = bottomNavigationView.getMenu().getItem(position);
                    menuItem.setChecked(true);
                }else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //关联三个页面
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        list = new ArrayList<>();
        list.add(new Page1Fragment());
        list.add(new Page2Fragment());
        list.add(new Page3Fragment());
        viewPagerAdapter.setList(list);
    }

    //BottomNavigationBar点击监听器
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            menuItem = item;
            switch (item.getItemId()){
                case R.id.page1:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.page2:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.page3:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

}
