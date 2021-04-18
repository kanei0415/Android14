package com.kyh.amyApp.handler;

import android.view.View;

import com.kyh.amyApp.adapter.UserAdapter;

public interface OnUserDataItemClickListener {
    public void onItemClick(UserAdapter.ViewHolder holder, View view, int position);


}
