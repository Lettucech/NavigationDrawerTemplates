package com.gmail.pingkiuho.navigationdrawertemplates.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.pingkiuho.navigationdrawertemplates.model.GenericViewHolder;
import com.gmail.pingkiuho.navigationdrawertemplates.model.MenuItemInfo;
import com.gmail.pingkiuho.navigationdrawertemplates.view.drawer.CustomMenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Brian Ho on 16/3/2018.
 */

public class DrawerMenuListViewAdapter extends RecyclerView.Adapter<GenericViewHolder> {
    private final static String TAG = DrawerMenuListViewAdapter.class.getSimpleName();
    private Context mContext;
    private List<MenuItemInfo> mItems = new ArrayList<>();
    private RecyclerView mRecyclerView;

    public DrawerMenuListViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenericViewHolder(new CustomMenuItem(mContext));
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        CustomMenuItem item = (CustomMenuItem) holder.itemView;
        MenuItemInfo info = mItems.get(position);

        if (info.getIconResId() != null) {
            item.setIcon(info.getIconResId());
        }
        item.setTitle(info.getTitle());

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle(mRecyclerView.indexOfChild(view));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setMenu(List<MenuItemInfo> items) {
        mItems.clear();


        for (int i = 0; i < items.size(); i++) {
            mItems.addAll(recursiveExpand(items.get(i), null));
        }

        for (MenuItemInfo info: mItems) {
            Log.d(TAG, info.getTag());
        }

    }

    private List<MenuItemInfo> recursiveExpand(MenuItemInfo item, List<MenuItemInfo> items) {
        if (items == null) {
            items = new ArrayList<>();
        }

        if (!item.isChildExpanded()) {
            items.add(item);
            return items;
        }

        for (MenuItemInfo child: item.getChildItems()) {
            items.addAll(recursiveExpand(child, items));
        }
        return items;
    }

    private void toggle(int position) {
        MenuItemInfo parent = mItems.get(position);
        if (parent.getChildItems() == null || parent.getChildItems().isEmpty()) {
            return;
        }

        if (parent.isChildExpanded()) {
            parent.setChildExpanded(false);
            mItems.removeAll(parent.getChildItems());
            notifyItemRangeRemoved(position + 1, parent.getChildItems().size());
        } else {
            parent.setChildExpanded(true);
            mItems.addAll(position + 1, parent.getChildItems());
            notifyItemRangeInserted(position + 1, parent.getChildItems().size());
        }
    }
}