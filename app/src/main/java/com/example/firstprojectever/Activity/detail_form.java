package com.example.firstprojectever.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.firstprojectever.Storage.InventoryDBHelper;
import com.example.firstprojectever.Storage.array_api_datakos;
import com.example.firstprojectever.Storage.data_booking;
import com.example.firstprojectever.Storage.data_kos;
import com.example.firstprojectever.plugins.PickerDialog;
import com.example.firstprojectever.R;
import com.example.firstprojectever.Storage.Storage;

import java.util.Calendar;

import static com.example.firstprojectever.Storage.Storage.bookings;
import static com.example.firstprojectever.Storage.Storage.users;

public class detail_form extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public array_api_datakos aad;
    public TextView tvTitle, tvFacility, tvPrice, tvLog, tvLat, tvDes, dateView;
    public ImageView imageView;
    public Button btnBook,btnGoogle;
    public static DatePickerDialog dpdialog;
    InventoryDBHelper db;
    String name,address,price,facility,lat,lng,img,lastBookId = "nodata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_form);
        init();
        db = new InventoryDBHelper(this);

        name = getIntent().getExtras().getString("name");
        address = getIntent().getExtras().getString("address");
        price = getIntent().getExtras().getString("price");
        facility = getIntent().getExtras().getString("fasilitas");
        lat = getIntent().getExtras().getString("latitude");
        lng = getIntent().getExtras().getString("longitude");
        img = getIntent().getExtras().getString("img");

        tvTitle.setText(name);
        tvFacility.setText(address);
        tvPrice.setText(price);
        tvDes.setText("Fasilitas : \n" + facility);
        tvLat.setText("Latitude : " + lat);
        tvLog.setText("Longitude : " +lng);
        Glide.with(this).load(img).into(imageView);
        lastBookId = db.getLastBookingId();
        //Toast.makeText(this, "Last book data " + lastBook(), Toast.LENGTH_SHORT).show();


        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickerDialog pickerDialog = new PickerDialog();
                pickerDialog.show(getSupportFragmentManager(), "Date picker");
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(detail_form.this,google_api.class);
                i.putExtra("lng",lng);
                i.putExtra("lat",lat);
                i.putExtra("name",name);
                startActivity(i);
            }
        });
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String userid = Storage.currentUserId;
        Log.i("User ID : ", "Storage id : "+ userid);
        String username = Storage.currentUsername;
        String temp_date = null;
        int flag = 0;
        String curr_book = getBookId();
        //Log.i("Last Book Id ", "Last book id : "+ temp_book);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        int currMonth = month + 1;
        String date = dayOfMonth + "/"+ currMonth + "/" + year;
        temp_date = date;
//        Log.i("Detail Info","User id : " + userid + "Booking id : " +
//                bookingid + "nama : " + name+"uname : " + username + "facility : " + facility + "price : " + price +
//                "address : " + address + "long : " + lng + "lat : "+ lat + "date : " + date);
            for(int i =0;i<bookings.size();i++) {
                if (userid.equals(bookings.get(i).getUserId()) && name.equals(bookings.get(i).getKosname())) {
                    Toast.makeText(detail_form.this, "You can't book same kos !", Toast.LENGTH_SHORT).show();
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                Boolean insert_book = db.insertBook(userid, curr_book, name, username, facility, price, address, lng, lat, date);
                Boolean cons_book = db.consID(curr_book);
                if (insert_book == true && cons_book == true) {
                    Storage.bookings.add(new data_booking(userid, curr_book, name, username, date, facility, price, address, lng, lat));
                    Intent ii = new Intent(detail_form.this, booking_transaction_form.class);
                    startActivity(ii);
                }
            }

    }

    private String getBookId(){
        String bookingid = null;
        long uid = db.getFixedBook() + 1;
        if(uid<10)bookingid = "BK00"+uid;
        else if(uid<100)bookingid = "BK0"+uid;
        else if(uid<1000)bookingid = "BK"+uid;

        return bookingid;
    }

    void init(){
        imageView = findViewById(R.id.imageDetail);
        tvTitle = findViewById(R.id.tvTitle);
        tvFacility = findViewById(R.id.tvAddress);
        tvPrice = findViewById(R.id.tvPrice);
        tvLog = findViewById(R.id.tvLong);
        tvLat = findViewById(R.id.tvLat);
        tvDes = findViewById(R.id.tvFac);
        btnBook = findViewById(R.id.btnBooking);
        btnGoogle = findViewById(R.id.btnGoogle);
    }

    private String lastBook(){
        String lastBookId = db.getLastBookingId();
       return lastBookId;
    }


}