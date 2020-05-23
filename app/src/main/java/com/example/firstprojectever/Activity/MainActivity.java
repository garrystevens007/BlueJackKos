package com.example.firstprojectever.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.firstprojectever.R;
import com.example.firstprojectever.Storage.InventoryDBHelper;
import com.example.firstprojectever.Storage.Storage;
import com.example.firstprojectever.Storage.data_user;

import static com.example.firstprojectever.Storage.Storage.users;

public class MainActivity extends AppCompatActivity{

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvReg;
    InventoryDBHelper db;
    data_user ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestSendReceiveSMS();
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);
        tvReg = findViewById(R.id.tvReg);
        db = new InventoryDBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idname,pass;
                idname = etUsername.getText().toString();
                pass = etPassword.getText().toString();
                Boolean validate_Login = db.login_Validate(idname,pass);
                int flag = 0;
                int pos = 0;

                if(idname.trim().equals("")){
                    Toast.makeText(MainActivity.this,"Username required",Toast.LENGTH_SHORT).show();
                }else if(pass.trim().equals("")){
                    Toast.makeText(MainActivity.this,"Password required",Toast.LENGTH_SHORT).show();
                }

                if(validate_Login == true){
                    //Toast.makeText(MainActivity.this,"ID is " + current_id,Toast.LENGTH_SHORT).show();
                    String current_id = db.getCurrentId(idname,pass);
                    if(users.size() == 0){
                        users.add(new data_user(current_id,idname,pass));
                        Storage.currentUserId = current_id;
                        Storage.currentUsername = idname;
                        Log.i("Curr storage", "number " + Storage.currentUserId);
                        Intent ii = new Intent(MainActivity.this,Home.class);
                        startActivity(ii);
                        pos++;
                        finish();
                    }else {
                        for (int i = 0; i < users.size(); i++) {
                            Log.i("Users size : " , "total array data " + users.size());
                            Log.i("Curr userId: ","curr user id " + users.get(i).getUserId() + "curr id db" + current_id);
                            if (users.get(i).getUserId().equals(current_id)) {
                                Log.i("Users size : " , "total array data " + users.size());
                                Storage.currentUserId = current_id;
                                Storage.currentUsername = idname;
                                Log.i("Curr storage", "number " + Storage.currentUserId);
                                Intent ii = new Intent(MainActivity.this,Home.class);
                                startActivity(ii);
                                pos++;
                                finish();
                            } else {
                                users.add(new data_user(current_id,idname,pass));
                            }
                        }
                    }


                }else{
                    Toast.makeText(MainActivity.this,"Incorrect username or password",Toast.LENGTH_SHORT).show();
                }
            }
        });


        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Registration.class);
                startActivity(i);
            }
        });

    }

    private void requestSendReceiveSMS(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS},0);
    }


}
