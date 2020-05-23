package com.example.firstprojectever.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstprojectever.Activity.Home;
import com.example.firstprojectever.R;
import com.example.firstprojectever.Storage.InventoryDBHelper;
import com.example.firstprojectever.Storage.Storage;
import com.example.firstprojectever.Storage.data_booking;
import com.example.firstprojectever.Storage.data_kos;
import com.example.firstprojectever.Storage.data_user;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolders> {

    public ArrayList<data_booking> bookings;
    Context ctx;
    InventoryDBHelper db;

    public Adapter2(ArrayList<data_booking> bookings, Context ctx){
        this.bookings = bookings;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view2,parent,false);
        return new ViewHolders(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, final int position) {
        data_booking dbk = Storage.bookings.get(position);

        holder.bookId.setText(dbk.getBookingId());
        holder.userId.setText(dbk.getUserId());
        holder.bookName.setText("Booked by : " + dbk.getUsername());
        holder.bookKost.setText(dbk.getKosname());
        holder.bookDate.setText("Booking date : " +dbk.getBookdate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle("Order booking");
                alert.setMessage("Do you  want to cancel your booking order from " + Storage.bookings.get(position).getKosname() + " ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = new InventoryDBHelper(v.getContext());
                        Integer deleteData = db.deleteBookingsRow(Storage.bookings.get(position).getBookingId());
                        Log.i("The data is ", "booking id index deleted : " + Storage.bookings.get(position).getBookingId());
                        if(deleteData > 0){
                            Toast.makeText(ctx, "Data updated!", Toast.LENGTH_SHORT).show();
                            Storage.tempBookingIdHasDeleted = Storage.bookings.get(position).getBookingId();
                            Storage.bookings.remove(Storage.bookings.get(position));
                            Intent i = new Intent(v.getContext(), Home.class);
                            v.getContext().startActivity(i);
                        }

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Storage.bookings.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder{

        TextView bookId,bookName,bookKost,bookDate,userId;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            bookId = itemView.findViewById(R.id.bookId);
            bookName = itemView.findViewById(R.id.bookName);
            bookKost = itemView.findViewById(R.id.bookKost);
            bookDate = itemView.findViewById(R.id.bookDate);
            userId = itemView.findViewById(R.id.userId);
        }

    }
}
