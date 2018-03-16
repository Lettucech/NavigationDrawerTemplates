package com.gmail.pingkiuho.navigationdrawertemplates.model;

import java.util.List;

/**
 * Created by Brian Ho on 16/3/2018.
 */

public class MenuItemInfo {
    private final String tag;
    private Integer iconResId;
    private String title;
    private List<MenuItemInfo> childItems;
    private boolean childExpanded = false;

    public static Builder builder(String tag) {
        return new Builder(tag);
    }

    private MenuItemInfo(String tag, Integer iconResId, String title, List<MenuItemInfo> childItems, boolean childExpanded) {
        this.tag = tag;
        this.iconResId = iconResId;
        this.title = title;
        this.childItems = childItems;
        this.childExpanded = childExpanded;
    }

    public String getTag() {
        return tag;
    }

    public Integer getIconResId() {
        return iconResId;
    }
    public void setIconResId(Integer iconResId) {
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public List<MenuItemInfo> getChildItems() {
        return childItems;
    }
    public void setChildItems(List<MenuItemInfo> childItems) {
        this.childItems = childItems;
    }

    public boolean isChildExpanded() {
        return childExpanded;
    }
    public void setChildExpanded(boolean childExpanded) {
        this.childExpanded = childExpanded;
    }

    @Override
    public String toString() {
        return "MenuItemInfo{" +
                "tag='" + tag + '\'' +
                ", iconResId=" + iconResId +
                ", title='" + title + '\'' +
                ", childItems=" + childItems +
                ", childExpanded=" + childExpanded +
                '}';
    }

    public static class Builder {
        private final String tag;
        private Integer iconResId;
        private String title;
        private List<MenuItemInfo> childItems;
        private boolean childExpanded = false;

        private Builder(String tag) {
            this.tag = tag;
        }

        public Builder setIconResId(Integer iconResId) {
            this.iconResId = iconResId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setChildItems(List<MenuItemInfo> childItems) {
            this.childItems = childItems;
            return this;
        }

        public Builder setChildExpanded(boolean childExpanded) {
            this.childExpanded = childExpanded;
            return this;
        }

        public MenuItemInfo build() {
            return new MenuItemInfo(tag, iconResId, title, childItems, childExpanded);
        }

    }
}
