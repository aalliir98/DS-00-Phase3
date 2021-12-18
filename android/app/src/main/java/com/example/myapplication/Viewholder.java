package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

public class Viewholder {

    public TextView textView;

    public Viewholder(View view) {
        textView = view.findViewById(R.id.idTvnode);
    }
}