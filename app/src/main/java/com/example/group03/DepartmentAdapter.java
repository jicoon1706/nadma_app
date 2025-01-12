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

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder>{

    Context context;
    List<DepartmentDetails> list;

    public DepartmentAdapter(Context context, List<DepartmentDetails> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rv_department_layout,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull DepartmentAdapter.ViewHolder holder, int position) {
        DepartmentDetails departmentDetails = list.get(position);
        holder.tvDepartment.setText("Department: " + departmentDetails.getDepartment());
        holder.tvDepartmentId.setText("Department ID: " + departmentDetails.getDepartmentId());
        holder.tvAddress.setText("Address:" + departmentDetails.getAddress());

        //holder btnUpdate
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String department = departmentDetails.getDepartment();
                String departmentId = departmentDetails.getDepartmentId();
                String address = departmentDetails.getAddress();

                //Intent with passing data
                Intent i = new Intent(context, UpdateDepartment.class);
                i.putExtra("Department", department);
                i.putExtra("DepartmentID", departmentId);
                i.putExtra("Address", address);
                context.startActivity(i);


            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dapatkan unique id ,then set to null
                DatabaseReference dbRef = FirebaseDatabase.getInstance("https://group03-49e49-default-rtdb.firebaseio.com/").getReference("DepartmentInfo");
                dbRef.child(departmentDetails.getDepartmentId()).setValue(null);
                Toast.makeText(context, "Delete Successful", Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDepartment, tvDepartmentId, tvAddress;
        Button btnUpdate, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDepartment = itemView.findViewById(R.id.tvDepartment);
            tvDepartmentId = itemView.findViewById(R.id.tvId);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }


}
