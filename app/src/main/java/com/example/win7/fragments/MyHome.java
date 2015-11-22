package com.example.win7.fragments;

import com.example.win7.adapters.MyFragmentPageAdapter;
import com.example.win7.asthmadoc.R;
import com.example.win7.inner.fragments.Fragment1;
import com.example.win7.inner.fragments.Fragment2;
import com.example.win7.inner.fragments.Fragment3;
import com.example.win7.inner.fragments.Fragment4;
import com.example.win7.inner.fragments.WeatherFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win7 on 11/9/2015.
 */
public class MyHome extends Fragment implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    TabHost tabHost;
    View v;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tabs_viewpager_layout,container,false);


        //We put TabHostView Pager here
        initViewPager();
        initTabHost();

        return v;
    }
    public class FakeContent implements TabHost.TabContentFactory {
        Context context;
        public FakeContent(Context mcontext){
            context = mcontext;
        }

        @Override
        public View createTabContent(String tag) {
            View fakeView = new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;
        }
    }

    private void initTabHost(){
        tabHost = (TabHost)v.findViewById(android.R.id.tabhost);
        tabHost.setup();


        String[] tabNames = {"Weather","Mood","Stats","Info"};


        for(int i=0;i < tabNames.length;i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);

            tabSpec.setContent(new FakeContent(getActivity()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);

    }


    private void initViewPager()
    {
        viewPager = (ViewPager)v.findViewById(R.id.view_pager);

        List<Fragment> listFragments = new ArrayList<Fragment>();
        listFragments.add(new WeatherFragment());
        listFragments.add(new Fragment2());
        listFragments.add(new Fragment3());
        listFragments.add(new Fragment4());


        MyFragmentPageAdapter myFragmentPageAdapter = new MyFragmentPageAdapter(getChildFragmentManager(),listFragments);
        viewPager = (ViewPager) v.findViewById(R.id.view_pager);
        viewPager.setAdapter(myFragmentPageAdapter);
        viewPager.addOnPageChangeListener(this); // setOnPageChangeListener
    }
    @Override
    public void onTabChanged(String tabId)
    {
        int pos = this.tabHost.getCurrentTab();
        this.viewPager.setCurrentItem(pos);

       HorizontalScrollView hScrollView = (HorizontalScrollView) v.findViewById(R.id.h_scroll_view);
        View tabView = tabHost.getCurrentTabView();
       int scrollPos = tabView.getLeft()
              - (hScrollView.getWidth() - tabView.getWidth()) / 2;
        hScrollView.smoothScrollTo(scrollPos, 0);




    }


    @Override
    public void onPageSelected(int position)
    {
        this.tabHost.setCurrentTab(position);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
