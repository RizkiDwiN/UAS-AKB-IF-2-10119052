package com.rizki.catkur;
/**
 * Tanggal Pengerjaan : 10 AGUSTUS 2022 , 13.56 WIB
 * NIM : 10119052
 * Nama : Rizki Dwi Nugraha
 * Kelas : IF-2
 **/
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Catkur> catkur;

    Adapter(Context context,List<Catkur> catkur){
        this.inflater = LayoutInflater.from(context);
        this.catkur = catkur;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title = catkur.get(position).getTitle();
        String date = catkur.get(position).getDate();
        String time = catkur.get(position).getTime();
        holder.nTitle.setText(title);
        holder.nDate.setText(date);
        holder.nTime.setText(time);

    }

    @Override
    public int getItemCount() {
        return  catkur.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView nTitle,nDate,nTime;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nTitle = itemView.findViewById(R.id.nTitle);
            nDate = itemView.findViewById(R.id.nDate);
            nTime = itemView.findViewById(R.id.nTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(),Details.class);
                    i.putExtra("ID",catkur.get(getAdapterPosition()).getID());
                    view.getContext().startActivity(i);
                }
            });


        }
    }
}
