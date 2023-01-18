package com.example.newsphone;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pageadapter extends FragmentPagerAdapter {

    int tabcount;


    public pageadapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                return new homef();
            case 1:
                return new businessf();
            case 2:
                return new enterf();
            case 3:
                return new generalf();
            case 4:
                return new healthf();
            case 5:
                return new sciencef();
            case 6:
                return new sportf();
            case 7:
                return new tachf();

            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
