package com.example.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.json.user.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.UserViewHolder> {

    List<User> userList;
    Context mContext;
    String baseURL = "https://lebavui.github.io";
    private OnUserClickListener listener;

    public Adapter(List<User> userList, Context mContext) {
        this.userList = userList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);

        String username = user.username;
        String email = user.email;


        holder.textViewUsername.setText(username);
        holder.textViewEmail.setText(email);

        Picasso.get()
                .load(baseURL + user.avatar.photo)
                .into(holder.imageAvatar);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setListener(OnUserClickListener listener) {
        this.listener = listener;
    }

    public interface OnUserClickListener {
        void onUserClick(User user);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView imageAvatar;
        TextView textViewUsername, textViewEmail;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            imageAvatar = itemView.findViewById(R.id.avatar);
            textViewUsername = itemView.findViewById(R.id.username);
            textViewEmail = itemView.findViewById(R.id.email);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onUserClick(userList.get(position));
                }
            });
        }
    }

}