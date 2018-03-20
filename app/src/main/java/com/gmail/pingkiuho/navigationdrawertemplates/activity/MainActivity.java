package com.gmail.pingkiuho.navigationdrawertemplates.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.gmail.pingkiuho.navigationdrawertemplates.adapter.DrawerMenuListViewAdapter;
import com.gmail.pingkiuho.navigationdrawertemplates.fragment.ContentFragment;
import com.gmail.pingkiuho.navigationdrawertemplates.R;
import com.gmail.pingkiuho.navigationdrawertemplates.model.MenuItemInfo;
import com.gmail.pingkiuho.navigationdrawertemplates.view.drawer.SlideAnimationDrawerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Brian Ho on 14/3/2018.
 */

public class MainActivity extends AppCompatActivity implements DrawerMenuListViewAdapter.OnMenuItemClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout mContentLayout;
    private SlideAnimationDrawerListener mDrawerListener;
    private RecyclerView mMenuRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private DrawerMenuListViewAdapter mMenuRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        setSupportActionBar(mToolbar);

        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mDrawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerListener = SlideAnimationDrawerListener.builder(mContentLayout, SlideAnimationDrawerListener.Preset.SLIDE_END_SCALE_DOWN_ROTATE_Y).build();
        mDrawerLayout.addDrawerListener(mDrawerListener);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mMenuRecyclerViewAdapter = new DrawerMenuListViewAdapter(this);
        mMenuRecyclerView.setLayoutManager(mLayoutManager);
        mMenuRecyclerView.setAdapter(mMenuRecyclerViewAdapter);
        mMenuRecyclerViewAdapter.setMenu(getMenuData());
        mMenuRecyclerViewAdapter.setOnMenuItemClickListener(this);

        mContentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.compass.rewards.uat.action.open");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupDrawerToggle().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setupDrawerToggle().syncState();
    }

    @Override
    public void onMenuItemClick(String tag) {
        switch (tag) {
            case "first-first":
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout_content, ContentFragment.newInstance("First First"))
                        .commit();
                break;
            case "first-second":
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout_content, ContentFragment.newInstance("First Second"))
                        .commit();
                break;
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private List<MenuItemInfo> getMenuData() {
        return new ArrayList<>(
                Arrays.asList(
                        MenuItemInfo.builder("first")
                                .setIconResId(R.drawable.ic_looks_one_black_24dp)
                                .setTitle("First")
                                .setChildItems(Arrays.asList(
                                        MenuItemInfo.builder("first-first").setTitle("First First").build(),
                                        MenuItemInfo.builder("first-second").setTitle("First Second").build()))
                                .setChildExpanded(true)
                                .build(),
                        MenuItemInfo.builder("second")
                                .setIconResId(R.drawable.ic_looks_two_black_24dp)
                                .setTitle("Second")
                                .setChildItems(Arrays.asList(
                                        MenuItemInfo.builder("second-first").setTitle("Second First").build(),
                                        MenuItemInfo.builder("second-second").setTitle("Second Second").build(),
                                        MenuItemInfo.builder("second-third").setTitle("Second Third").build()))
                                .setChildExpanded(true)
                                .build(),
                        MenuItemInfo.builder("third")
                                .setIconResId(R.drawable.ic_looks_one_black_24dp)
                                .setTitle("Third")
                                .build()
                )
        );
    }

    private void bindViews() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mContentLayout = findViewById(R.id.linearLayout_main);
        mNavigationView = findViewById(R.id.navigationView);
        mMenuRecyclerView = findViewById(R.id.recyclerView);
    }


}
