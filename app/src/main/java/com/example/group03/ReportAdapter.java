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

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder>{

    Context context;
    List<ReportDetails> list;

    public ReportAdapter(Context context, List<ReportDetails> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_edit_report,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ViewHolder holder, int position) {
        ReportDetails reportDetails = list.get(position);
        holder.tvDisaster.setText("Disaster Type: " + reportDetails.getDisaster());
        holder.tvSeverity.setText("Severity Level: " + reportDetails.getSeverity());
        holder.tvArea.setText("Area:" + reportDetails.getArea());

       //holder btnkemaskini
        holder.btnKemasKini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String disaster = reportDetails.getDisaster();
                String severity = reportDetails.getSeverity();
                String area = reportDetails.getArea();
                String date = reportDetails.getDate();
                String state = reportDetails.getState();
                String district = reportDetails.getDistrict();
                String issue = reportDetails.getIssue();
                //Intent with passing data
                Intent i = new Intent(context, UpdateReport.class);
                i.putExtra("dataDisaster", disaster);
                i.putExtra("dataSeverity", severity);
                i.putExtra("dataArea", area);
                i.putExtra("dataDate", date);
                i.putExtra("dataState", state);
                i.putExtra("dataDistrict", district);
                i.putExtra("dataIssue", issue);
                context.startActivity(i);


            }
        });

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dapatkan unique id ,then set to null
                DatabaseReference dbRef = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/").getReference("Report");
                dbRef.child(reportDetails.getArea()).setValue(null);
                Toast.makeText(context, "Delete Successful", Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDisaster, tvSeverity, tvArea;
        Button btnKemasKini, btnHapus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDisaster = itemView.findViewById(R.id.tvDisaster);
            tvSeverity = itemView.findViewById(R.id.tvSeverity);
            tvArea = itemView.findViewById(R.id.tvArea);
            btnKemasKini = itemView.findViewById(R.id.btnKemasKini);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }


}
