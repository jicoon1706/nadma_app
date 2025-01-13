package com.example.group03;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    Context context;
    List<InformationDetails> list;

    public InfoAdapter(Context context, List<InformationDetails> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_info,parent,false);
        return new InfoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoAdapter.ViewHolder holder, int position) {
        InformationDetails informationDetails = list.get(position);
        holder.tvDate.setText("Date: " + informationDetails.getDate());
        holder.tvTitle.setText("Title: " + informationDetails.getTitle());
        holder.tvDetails.setText("Details: " + informationDetails.getDetails());


        //holder btnkemaskini
        holder.btnKemasKini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String details = informationDetails.getDetails();
                String date = informationDetails.getDate();
                String title = informationDetails.getTitle();
                //Intent with passing data
                Intent i = new Intent(context, UpdateInfo.class);
                i.putExtra("dataTitle", title);
                i.putExtra("dataDetails", details);
                i.putExtra("dataDate", date);
                context.startActivity(i);


            }
        });

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dapatkan unique id ,then set to null
                DatabaseReference dbRef = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/").getReference("Information");
                Toast.makeText(context, "Delete Successful", Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDate, tvDetails, tvTitle;
        Button btnKemasKini, btnHapus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            btnKemasKini = itemView.findViewById(R.id.btnKemasKini);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }


}

