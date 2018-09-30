package com.yx.Pharmacy.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KID on 2018/7/26.
 */

public class VPageAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments=new ArrayList<>();
    List<String>titles=new ArrayList<>();
    public VPageAdapter(FragmentManager fm, List<Fragment> fragments, List<String>titles) {
        super(fm);
        this.fragments = fragments;
        this.titles=titles;
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public Fragment getItem(int position) {
        return getCount() > position ? fragments.get(position) : null;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
    public void add(Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }
}
