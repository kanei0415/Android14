package com.example.listview0404;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    String[] fruits = {
            "Apple", "Banana", "WaterMelon", "Orange", "Strawberry",
            "Apple", "Banana", "WaterMelon", "Orange", "Strawberry",
            "Apple", "Banana", "WaterMelon", "Orange", "Strawberry",
            "Apple", "Banana", "WaterMelon", "Orange", "Strawberry",
            "Apple", "Banana", "WaterMelon", "Orange", "Strawberry",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        String[] city = getResources().getStringArray(R.array.city);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_single_choice,
                city);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d("OnListItemClick", "position : " + position);
        String item = getListAdapter().getItem(position).toString();
        Toast.makeText(this,"선택: " + item, Toast.LENGTH_SHORT).show();
    }
}