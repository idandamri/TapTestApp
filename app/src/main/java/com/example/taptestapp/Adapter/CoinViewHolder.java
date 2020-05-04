package com.example.taptestapp.Adapter;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CoinViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    public CoinViewHolder(TextView v) {
        super(v);
        textView = v;
    }
}
