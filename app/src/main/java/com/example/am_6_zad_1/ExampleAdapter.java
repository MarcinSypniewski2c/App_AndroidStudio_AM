package com.example.am_6_zad_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExamleViewHolder> {
    private ArrayList<ExampleItem> mel;
    public static class ExamleViewHolder extends RecyclerView.ViewHolder {

        public TextView name_tv;
        public TextView value_tv;
        public TextView unit_tv;

        public ExamleViewHolder(@NonNull View itemView) {
            super(itemView);
            name_tv = itemView.findViewById(R.id.name_id);
            value_tv = itemView.findViewById(R.id.value_id);
            unit_tv = itemView.findViewById(R.id.unit_id);
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> ea_list){
        mel = ea_list;
    }

    @NonNull
    @Override
    public ExamleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        ExamleViewHolder evh = new ExamleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExamleViewHolder holder, int position) {
        ExampleItem curr_item = mel.get(position);

        holder.name_tv.setText(curr_item.get_name());
        holder.value_tv.setText(curr_item.get_value());
        holder.unit_tv.setText(curr_item.get_unit());
    }

    @Override
    public int getItemCount() {
        return mel.size();
    }
}
