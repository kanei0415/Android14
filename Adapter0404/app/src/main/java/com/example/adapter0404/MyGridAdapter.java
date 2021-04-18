package com.example.adapter0404;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyGridAdapter extends BaseAdapter {

    private static final Integer[] POST_ID = {
        R.drawable.mov01,
        R.drawable.mov02,
        R.drawable.mov03,
        R.drawable.mov04,
        R.drawable.mov05,
        R.drawable.mov06,
        R.drawable.mov07,
        R.drawable.mov08,
        R.drawable.mov09,
        R.drawable.mov10,
        R.drawable.mov01,
        R.drawable.mov02,
        R.drawable.mov03,
        R.drawable.mov04,
        R.drawable.mov05,
        R.drawable.mov06,
        R.drawable.mov07,
        R.drawable.mov08,
        R.drawable.mov09,
        R.drawable.mov10,
        R.drawable.mov01,
        R.drawable.mov02,
        R.drawable.mov03,
        R.drawable.mov04,
        R.drawable.mov05,
        R.drawable.mov06,
        R.drawable.mov07,
        R.drawable.mov08,
        R.drawable.mov09,
        R.drawable.mov10,
    };

    Context context;

    public MyGridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return POST_ID.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);

        imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setPadding(5, 5, 5, 5);
        imageView.setImageResource(POST_ID[position]);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(context, R.layout.dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);

                ImageView ivPoster = view.findViewById(R.id.tmp);
                ivPoster.setImageResource(POST_ID[position]);

                dlg.setTitle("상세 포스트 정보");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setView(view);
                dlg.setNegativeButton("확인", null);
                dlg.show();
            }
        });

        return imageView;
    }
}
