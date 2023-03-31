package com.example.week10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private Context context;
    private ArrayList<User> users;


    public UserListAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user_view, parent, false));
    }
    public void sortUsers(ArrayList<User> userList) {
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return user.getLastName().compareTo(t1.getLastName());
            }
        });
        users = userList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {



        switch (users.get(position).getImage()){
            case 0:
                holder.avatarView.setImageResource(R.drawable.avatar1);
                break;
            case 1:
                holder.avatarView.setImageResource(R.drawable.avatar2);
                break;
            case 2:
                holder.avatarView.setImageResource(R.drawable.avatar3);
                break;
        }
        holder.bind(users.get(position));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
