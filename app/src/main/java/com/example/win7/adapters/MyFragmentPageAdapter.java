package com.example.win7.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by win7 on 11/3/2015.
 */
public class MyFragmentPageAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragments;
    public MyFragmentPageAdapter(FragmentManager fm,List<Fragment> listFragments){
        super(fm);
        this.listFragments = listFragments;

    }

    public Fragment getItem(int position){

        return listFragments.get(position);
    }

    public int getCount(){
        return listFragments.size();
    }
}
