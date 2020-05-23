package com.example.firstprojectever.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstprojectever.Adapter.Adapter2;
import com.example.firstprojectever.R;
import com.example.firstprojectever.Storage.InventoryDBHelper;
import com.example.firstprojectever.Storage.Storage;
import com.example.firstprojectever.Storage.data_booking;
import com.example.firstprojectever.Storage.data_kos;

import static com.example.firstprojectever.Storage.Storage.bookings;
import static com.example.firstprojectever.Storage.Storage.curr_book;
//import static com.example.firstprojectever.Storage.Storage.user_id;


public class booking_transaction_form extends AppCompatActivity  {

    public data_booking dbk;
    public data_kos dks;
    public RecyclerView rv;
    public TextView bookId,bookKost,userId,bookDate,bookName,bookTransaction;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.booking_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home){
            Intent ii  = new Intent(booking_transaction_form.this,Home.class);
            startActivity(ii);
        }else if(item.getItemId() == R.id.logout){
            Intent i = new Intent(booking_transaction_form.this,MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_transaction_form);
        bookTransaction = findViewById(R.id.bookTransaction);
        rv = findViewById(R.id.recyclerView2);
        rv.setHasFixedSize(true);
        bookings.clear();
        InventoryDBHelper db = new InventoryDBHelper(this);
        SQLiteDatabase obj = db.getReadableDatabase();
        if(obj!=null){
            bookings.addAll(db.dataBookings(Storage.currentUserId));
        }

        if(bookings.size()!=0){
            bookTransaction.setVisibility(View.GONE);
        }

        showData();
    }

    private void showData(){
        Adapter2 adapter2 = new Adapter2(bookings,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter2);
    }

}
