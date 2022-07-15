package com.example.medical;

import static com.google.common.io.Files.getFileExtension;
import static com.google.common.io.Files.newReader;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medical.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String Name, Email, Age, Password, Bloodgroup, Aadhar, phone;
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();


        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
            }
        });

        binding.switchtoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser() {
        Name = binding.name.getText().toString();
        Email = binding.EMAIL.getText().toString();
        Password = binding.Password.getText().toString();
        Aadhar = binding.addAadhaar.getText().toString();
        phone = binding.addPhone.getText().toString();
        Age = binding.addage.getText().toString();
        Bloodgroup = binding.addblood.getText().toString();
        if (Name.isEmpty() || Email.isEmpty() || Password.isEmpty() || Aadhar.isEmpty() || phone.isEmpty() || Age.isEmpty() || Bloodgroup.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }
        auth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (!Name.isEmpty() && !Email.isEmpty() && !Password.isEmpty() && !Aadhar.isEmpty() && !phone.isEmpty() && !Age.isEmpty()  && !Bloodgroup.isEmpty()) {


                                User users = new User(Name, Email,Password,Aadhar, phone, Age, Bloodgroup);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        binding.name.setText("");
                                        binding.EMAIL.setText("");
                                        binding.Password.setText("");
                                        binding.addAadhaar.setText("");
                                        binding.addPhone.setText("");
                                        binding.addage.setText("");
                                        binding.addblood.setText("");


                                        Toast.makeText(MainActivity.this, "Successfuly Updated", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this,LoginActivity.class));

                                    }
                                });
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}