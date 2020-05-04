package com.example.taptestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.taptestapp.Adapter.CoinsAdapter;
import com.example.taptestapp.Interfaces.iOnDataRec;
import com.example.taptestapp.Model.CoinObj;
import com.example.taptestapp.Network.NetworkMgr;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements iOnDataRec {

    RecyclerView rv;
    private static Context context;
    private LinearLayoutManager layoutManager;
    private CoinsAdapter mAdapter;
    private ConstraintLayout pbLoading;
    private NetworkMgr networkMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.coins_rv);
        pbLoading = findViewById(R.id.pb_loading);

        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        mAdapter = new CoinsAdapter(new ArrayList<CoinObj>());
        rv.setAdapter(mAdapter);

        showPL();

        networkMgr = new NetworkMgr();

        networkMgr.createRequestPeriodicly(this);
    }

    private void showPL() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    private void hidePL() {
        pbLoading.setVisibility(View.GONE);
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onDataRec(ArrayList<CoinObj> list) {
        mAdapter.setNewList(list);
        mAdapter.notifyDataSetChanged();
        hidePL();
    }
}
