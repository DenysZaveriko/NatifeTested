package com.example.natifetested.volleyservice;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singl {
    private static Singl instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private  Singl(Context context){
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static  Singl getInstance(Context ctx){
        if (instance == null){
            instance = new Singl(ctx);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
    return requestQueue;

    }
    public <T> void addToRequestQueue(Request <T> req){
        getRequestQueue().add(req);
    }
}
