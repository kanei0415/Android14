package com.kyh.amyApp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kyh.amyApp.R;
import com.kyh.amyApp.adapter.UserAdapter;
import com.kyh.amyApp.handler.OnUserDataItemClickListener;
import com.kyh.amyApp.model.UserData;
import com.kyh.amyApp.util.RandData;

public class MainActivity extends AppCompatActivity {

    public static final String NAME_KEY = "NAME_KEY", TEL_KEY = "TEL_KEY", ADDRESS_KEY = "ADDRESS_KEY";

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        UserAdapter userAdapter = new UserAdapter();

        UserData[] items = new UserData[100];

        RandData randData = new RandData();
        for(int i=0 ; i<items.length ; i++) {
            items[i] = randData.getRandUserData();
            userAdapter.addItem(items[i]);
        }

        recyclerView.setAdapter(userAdapter);

        userAdapter.setOnItemClickListener(new OnUserDataItemClickListener() {
            @Override
            public void onItemClick(UserAdapter.ViewHolder holder, View view, int position) {
                Intent intent = new Intent(getApplicationContext(), UserDataView.class);

                UserData tmp = userAdapter.getItem(position);

                intent.putExtra(NAME_KEY, tmp.getName());
                intent.putExtra(TEL_KEY, tmp.getTel());
                intent.putExtra(ADDRESS_KEY, tmp.getAddress());

                startActivity(intent);
            }
        });
    }
}