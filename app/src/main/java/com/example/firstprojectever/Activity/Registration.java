package com.example.firstprojectever.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.firstprojectever.R;
import com.example.firstprojectever.Storage.InventoryDBHelper;

import java.util.Calendar;

import static com.example.firstprojectever.Storage.Storage.users;

public class Registration extends AppCompatActivity {

    public static EditText date,username,pass,pnum,cpass;
    public static DatePickerDialog dpdialog;
    public static Button btnRegister;
    public static RadioGroup totrad;
    public static RadioButton radbut;
    public static CheckBox cbox;
    public Cursor cursor;
    InventoryDBHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db = new InventoryDBHelper(this);

        date = (EditText)findViewById(R.id.date);
        username = findViewById(R.id.etUname);
        pass = findViewById(R.id.etPwd);
        pnum = findViewById(R.id.pnum);
        cpass = findViewById(R.id.etCpass);
        btnRegister = findViewById(R.id.btnRegister);
        totrad = (RadioGroup) findViewById(R.id.radioButt) ;
        cbox = (CheckBox) findViewById(R.id.cboxx);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                dpdialog = new DatePickerDialog(Registration.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                dpdialog.show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,passs,cpasss,phonenumb,gender,bdate;
                String regPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}";
                String regUname = "(?=.*[0-9])(?=.*[a-zA-Z]).{3,25}";
                int flag  = 0;
                int checking = 0;
                int radio = totrad.getCheckedRadioButtonId();
                radbut = findViewById(radio);
                name = username.getText().toString();
                passs = pass.getText().toString();
                cpasss = cpass.getText().toString();
                phonenumb = pnum.getText().toString();
                bdate = date.getText().toString();
                String userid = null;
                long uid = db.getUserCount() + 1;
                if(uid<10)userid = "US00"+uid;
                else if(uid<100)userid = "US0"+uid;
                else if(uid<1000)userid = "US"+uid;
                String message = "Thank You for joining Blue-Jack Kos !";

                //name
                    if(users.size() > 0){
                        for(int i = 0; i < users.size();i++){
                            if(users.get(i).getUsername().compareTo(name) == 0){
                                Toast.makeText(Registration.this, "This username is unavailable", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }

                    if(name.trim().equals("")) {
                        Toast.makeText(Registration.this, "Username required", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(name.length() < 3 ){
                        Toast.makeText(Registration.this,"Username too short",Toast.LENGTH_SHORT).show();
                        return;
                    }else if(name.length() > 25){
                        Toast.makeText(Registration.this,"Username too long",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if((name.matches(regUname)) == false){
                        Toast.makeText(Registration.this,"Please contain 1 number and alphabetic",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //password
                    if(passs.trim().equals("")) {
                        Toast.makeText(Registration.this, "Password required", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if((passs.matches(regPattern)) == false){
                        Toast.makeText(Registration.this, "Please contain atleast 1 Uppercase letter and 1 number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //match_password
                    if(cpasss.trim().equals("")){
                        Toast.makeText(Registration.this,"Confirmed password required",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if(passs.compareTo(cpasss)!= 0){
                        Toast.makeText(Registration.this,"Password not same",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //phone number
                    if(phonenumb.length() < 10){
                        Toast.makeText(Registration.this,"Phone number too short",Toast.LENGTH_SHORT).show();
                        return;
                    }else if(phonenumb.length() > 12){
                        Toast.makeText(Registration.this,"Phone number too long",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //radbutt
                    if(totrad.getCheckedRadioButtonId() == -1){
                        Toast.makeText(Registration.this,"Please choose your gender",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //data
                    if(bdate.isEmpty()){
                        Toast.makeText(Registration.this,"Please choose your date of birth",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //checkbox
                    if(!cbox.isChecked()){
                        Toast.makeText(Registration.this,"Please fill the checkbox",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    gender = radbut.getText().toString();

                    Boolean validate_Username = db.validate_Username(name);
                    if(validate_Username == true){
                        Boolean insert = db.insertUser(userid,name,passs,phonenumb,bdate,gender);
                            if(insert == true){
                            SmsManager.getDefault().sendTextMessage(phonenumb,null,message,null,null);
                            Toast.makeText(Registration.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Registration.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }else{
                        Toast.makeText(Registration.this,"Username already taken!",Toast.LENGTH_SHORT).show();
                    }

//                    users.add(new data_user(userid,name,passs,phonenumb,bdate,gender));

            }
        });
    }
}
