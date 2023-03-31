package com.example.week10;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class UserViewHolder extends RecyclerView.ViewHolder {


    ImageView avatarView;
    private TextView fullName, email, degreeProgram, degrees;



    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        avatarView = itemView.findViewById(R.id.avatarView);
        fullName = itemView.findViewById(R.id.textFullName);
        email = itemView.findViewById(R.id.textEmail);
        degreeProgram = itemView.findViewById(R.id.textDegreeProgram);
        degrees = itemView.findViewById(R.id.TVdegrees);

    }
    public void bind(User user) {
        fullName.setText(user.getFullName());
        email.setText(user.getEmail());
        degreeProgram.setText(user.getDegreeProgram());

        if (user.getDegrees().size() != 0) {
            StringBuilder degreesText = new StringBuilder();
            degreesText.append("Suoritetut tutkinnot:\n");
            for (String degree : user.getDegrees()) {
                degreesText.append("-").append(degree).append("\n");
            }
            degrees.setText(degreesText.toString().trim());
        } else {
            degrees.setText(null);
        }
    }




}
