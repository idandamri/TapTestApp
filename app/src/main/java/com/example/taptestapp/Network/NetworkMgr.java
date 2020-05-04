package com.example.taptestapp.Network;

import android.os.Handler;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.taptestapp.Interfaces.iOnDataRec;
import com.example.taptestapp.MainActivity;
import com.example.taptestapp.Model.CoinObj;
import com.example.taptestapp.Model.ResponsObj;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NetworkMgr {

    private Runnable runnable;
    private Handler handler = new Handler();

    public void createRequestPeriodicly(final iOnDataRec dataRecListener){
        runnable = new Runnable() {
            @Override
            public void run() {
                requestWithSomeHttpHeaders(dataRecListener); // Volley Request
/**when I will want to use the interval of 2 seconds (just implementation - not al the way through)**/
//                handler.postDelayed(runnable, 2000);
            }
        };
        handler.post(runnable);
    }

    public void requestWithSomeHttpHeaders(final iOnDataRec dataRecListener) {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.getContext());
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?start=1&limit=5000&convert=USD";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        dataRecListener.onDataRec(parseJsonToList(response));
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-CMC_PRO_API_KEY", "f654c1f4-e841-4027-a4a3-61dfb1500cac");
                return params;
            }
        };
        queue.add(getRequest);

    }

    public static ArrayList<CoinObj> parseJsonToList(String response) {
        ArrayList<CoinObj> retVal = new ArrayList<>();
        try {
            Gson gson = new Gson();
            ResponsObj responsObj = gson.fromJson(response, ResponsObj.class);
            retVal = responsObj.getData();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return retVal;
    }
}