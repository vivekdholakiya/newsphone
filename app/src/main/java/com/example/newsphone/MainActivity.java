package com.example.newsphone;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem mhome, mbusiness, menter, mgeneral, mhealth, mscience, msport, mtach;
    PagerAdapter pagerAdapter;
    Toolbar mtoolbar;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;


    String api = "3cd3cc2c4be045caa48e900fc41032b1";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        mhome = findViewById(R.id.home);
        mbusiness = findViewById(R.id.business);
        menter = findViewById(R.id.entert);
        mgeneral = findViewById(R.id.general);
        mhealth = findViewById(R.id.health);
        mscience = findViewById(R.id.science);
        msport = findViewById(R.id.sport);
        mtach = findViewById(R.id.tech);


        ViewPager viewPager = findViewById(R.id.fc);
        tabLayout = findViewById(R.id.include);


        pagerAdapter = new pageadapter(getSupportFragmentManager(), 8);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2 || tab.getPosition() == 3 || tab.getPosition() == 4 || tab.getPosition() == 5 || tab.getPosition() == 6 || tab.getPosition() == 7 || tab.getPosition() == 8)
                    ;
                {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }
}