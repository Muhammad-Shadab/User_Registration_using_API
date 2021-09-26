package com.example.userregistration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

    List<FetchUserResponse> listUser;

    public Adapter(List<FetchUserResponse> listUser) {
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        FetchUserResponse current = listUser.get(position);
        holder.textViewUsername.setText("Username: " + current.getUsername());
        holder.textViewAge.setText("Age: " + current.getAge());
        holder.textViewPhone.setText("Phone: " + current.getPhone());
        holder.textViewEmail.setText("Email: " + current.getEmail());
        holder.textViewAddress.setText("Address: " + current.getAddress());
        holder.textViewID.setText("ID: " + current.getId());
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewID, textViewUsername, textViewAge, textViewPhone, textViewEmail, textViewAddress;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.textViewID);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);
        }
    }

}
