package com.edmond.jyview.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edmond on 16-4-3.
 */
public class JYPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<Fragment>();

    public JYPagerAdapter(FragmentManager fm){
        super(fm);
    }

    public JYPagerAdapter(FragmentManager fragmentManager,
                          ArrayList<Fragment> fragments){
        super(fragmentManager);this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int index) {
        return fragments.get(index);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
