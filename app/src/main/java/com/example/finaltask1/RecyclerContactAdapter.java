package com.example.finaltask1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finaltask1.DataModel.DataModal;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

    Context context;
    Activity activity;
    ArrayList<DataModal> userList;


    RecyclerContactAdapter(Activity activity, Context context, ArrayList<DataModal> userList) {
        this.context = context;
        this.userList = userList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerContactAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtName.setText(userList.get(position).name);
        holder.txtEmail.setText(userList.get(position).email);

        //Error
        holder.userCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ManageUserActivity.class);
                intent.putExtra("email", userList.get(position).email);
                intent.putExtra("name", userList.get(position).name);
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtEmail;
        LinearLayout userCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.userName);
            txtEmail = itemView.findViewById(R.id.userEmail);
            userCard = itemView.findViewById(R.id.userRow);
        }
    }

}
