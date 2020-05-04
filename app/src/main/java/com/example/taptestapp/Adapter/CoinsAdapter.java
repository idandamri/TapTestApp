package com.example.taptestapp.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taptestapp.Model.CoinObj;
import com.example.taptestapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CoinsAdapter extends RecyclerView.Adapter<CoinViewHolder> {
    ArrayList<CoinObj> list;

    public CoinsAdapter(ArrayList<CoinObj> list) {
        this.list = list;
    }

    public ArrayList<CoinObj> getList() {
        return list;
    }

    public void setNewList(ArrayList<CoinObj> newList){
        list = newList;
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        TextView coinTV = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coin_item_layout, parent, false);
        CoinViewHolder vh = new CoinViewHolder(coinTV);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
