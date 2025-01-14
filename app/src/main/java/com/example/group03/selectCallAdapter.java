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

import java.util.List;

public class selectCallAdapter extends RecyclerView.Adapter<selectCallAdapter.ViewHolder> {

    Context context;
    List<DepartmentDetails> list;

    public selectCallAdapter(Context context, List<DepartmentDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the correct layout for the RecyclerView items
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_select_call_layout, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the current department details
        DepartmentDetails departmentDetails = list.get(position);

        // Bind data to the views
        holder.tvDepartment.setText("Department: " + departmentDetails.getDepartment());

        // Handle button click for "Call"
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String department = departmentDetails.getDepartment();

                // Show a toast or perform other actions
                Toast.makeText(context, "Calling " + department, Toast.LENGTH_SHORT).show();

                // Optional: Start a new activity or handle the call
                // Example: Start an intent to dial
                Intent intent = new Intent(Intent.ACTION_DIAL);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        // Declare views
        TextView tvDepartment;
        Button btnCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views
            tvDepartment = itemView.findViewById(R.id.tvDepartment);
            btnCall = itemView.findViewById(R.id.btnCall);
        }




    }









}

