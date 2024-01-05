package com.example.finaltask1;

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

import com.example.finaltask1.DataModel.CardModal;
import com.example.finaltask1.DataModel.DataModal;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

    Context context;
//    Activity activity;
    ArrayList<DataModal> userList;


    RecyclerContactAdapter(Context context, ArrayList<DataModal> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerContactAdapter.ViewHolder holder, int position) {
        holder.txtName.setText(userList.get(position).name);
        holder.txtEmail.setText(userList.get(position).email);

//        holder.userComponent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, MainActivity.class);
//                activity.startActivityForResult(intent, 1);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtEmail;
        LinearLayout userComponent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.userName);
            txtEmail = itemView.findViewById(R.id.userEmail);
        }
    }
}
