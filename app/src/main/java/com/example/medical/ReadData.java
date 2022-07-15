package com.example.medical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.medical.databinding.ActivityReadDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReadData extends AppCompatActivity {

    ActivityReadDataBinding binding;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentuser = auth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Users").child(currentuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    Toast.makeText(ReadData.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                    binding.tvName.setText(user.name);
                    binding.tvEmail.setText(user.email);
                    binding.tvPassword.setText(user.pass);
                    binding.tvAge.setText(user.age);
                    binding.tvAadhar.setText(user.aadhar);
                    binding.tvBloodgrp.setText(user.bloodgroup);
                    binding.tvCcode.setText(user.phone);
                } else {

                    Toast.makeText(ReadData.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();

                }


            }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }