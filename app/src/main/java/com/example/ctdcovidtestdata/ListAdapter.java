package com.example.ctdcovidtestdata;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<testresult> Arraytest = new ArrayList<>();

    private ListAdapter(){
    }

    public ListAdapter(Context context, ArrayList<testresult>Arraytest){
        this.context = context;
        this.Arraytest = Arraytest;
    }


    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.test_list_layout, parent, false );
        ListAdapter.ViewHolder viewHolder = new  ListAdapter.ViewHolder(view);

        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.PatientNameL.setText(Arraytest.get(position).getPatientName());
        holder.TestDateL.setText(Arraytest.get(position).getTestDate());
        holder.ResultDateL.setText(Arraytest.get(position).getResultDate());
        holder.ListHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Patient_Result_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Arraytest.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
         TextView PatientNameL, TestDateL, ResultDateL;
         LinearLayout ListHistory;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            PatientNameL = itemView.findViewById(R.id.PatientName);
            TestDateL = itemView.findViewById(R.id.TestDate);
            ResultDateL = itemView.findViewById(R.id.ResultDate);
    }
}

}
