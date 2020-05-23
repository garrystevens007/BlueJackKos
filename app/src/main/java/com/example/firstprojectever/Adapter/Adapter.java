package com.example.firstprojectever.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.firstprojectever.Activity.detail_form;
import com.example.firstprojectever.R;
import com.example.firstprojectever.Storage.Storage;
import com.example.firstprojectever.Storage.array_api_datakos;
import com.example.firstprojectever.Storage.data_kos;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    //public data_kos dk;
    private Context mContext;
    public List<array_api_datakos> apiDatakos;
    //private OnImageListener mOnImageListener;
    RequestOptions options;

    public Adapter(Context mContext,List<array_api_datakos> apiDatakos){
        this.mContext = mContext;
        this.apiDatakos = apiDatakos;
        //this.mOnImageListener = onImageListener;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view,viewGroup,false);
        final ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, detail_form.class);
                i.putExtra("img",apiDatakos.get(viewHolder.getAdapterPosition()).getImagekos());
                i.putExtra("name",apiDatakos.get(viewHolder.getAdapterPosition()).getNamakos());
                i.putExtra("address",apiDatakos.get(viewHolder.getAdapterPosition()).getAddresskos());
                i.putExtra("fasilitas",apiDatakos.get(viewHolder.getAdapterPosition()).getFasilitaskos());
                i.putExtra("price",apiDatakos.get(viewHolder.getAdapterPosition()).getPrice());
                i.putExtra("latitude",apiDatakos.get(viewHolder.getAdapterPosition()).getLatkos());
                i.putExtra("longitude",apiDatakos.get(viewHolder.getAdapterPosition()).getLngkos());

                mContext.startActivity(i);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String tempDesc = "";
        String fac = apiDatakos.get(position).getFasilitaskos();
        if(fac.length() > 23){
            tempDesc = fac;
            holder.textFac.setText(tempDesc.substring(0,20) + "...");
        }else{
            holder.textFac.setText(apiDatakos.get(position).getFasilitaskos());
        }

        holder.textTitle.setText(apiDatakos.get(position).getNamakos());

        holder.textPrice.setText("Rp." + apiDatakos.get(position).getPrice());

        Glide.with(mContext).load(apiDatakos.get(position).getImagekos()).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return apiDatakos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgView;
        TextView textTitle,textFac,textPrice;
        ConstraintLayout view_container;
        OnImageListener onImageListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imageView3);
            textTitle = itemView.findViewById(R.id.textTitle);
            textFac = itemView.findViewById(R.id.textFacility);
            textPrice = itemView.findViewById(R.id.textPrice);
            view_container = itemView.findViewById(R.id.container);
            //this.onImageListener = onImageListener;

           // itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onImageListener.onImageClick(getAdapterPosition());
        }
    }

    public interface OnImageListener{
        void onImageClick(int position);
    }

}
