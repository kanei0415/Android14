package com.kyh.amyApp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kyh.amyApp.R;
import com.kyh.amyApp.handler.OnUserDataItemClickListener;
import com.kyh.amyApp.model.UserData;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>
implements OnUserDataItemClickListener {
    ArrayList<UserData> items = new ArrayList<>();

    static OnUserDataItemClickListener Listener;

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        UserData item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(UserData item) {
        items.add(item);
    }

    public UserData getItem(int index) {
        return items.get(index);
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(Listener != null) {
            Listener.onItemClick(holder, view, position);
        }
    }

    public void setOnItemClickListener(OnUserDataItemClickListener listener) {
        this.Listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView userName, userTel, userAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            userTel = itemView.findViewById(R.id.userTel);
            userAddress = itemView.findViewById(R.id.userAddress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Listener != null) {
                        Listener.onItemClick(ViewHolder.this, v, getAdapterPosition());
                    }
                }
            });
        }

        public void setItem(UserData item) {
            userName.setText(item.getName());
            userTel.setText(item.getTel());
            userAddress.setText(item.getAddress());
        }
    }
}
