package com.webdroidteam.teste_layout_1.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.webdroidteam.teste_layout_1.R;

public class Orcar extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        // Inflamos o layout Orcar.xml
        return (RelativeLayout) inflater.inflate(R.layout.activity_orcar, container, false);
    }
}
