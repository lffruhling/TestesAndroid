package com.webdroidteam.teste_design_4;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class tab_layout_a extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHas
        if (container == null) {
            return null;
        }

        // Inflamos o layout tab_layout_a.xml
        return (RelativeLayout) inflater.inflate(R.layout.activity_tab_layout_a, container, false);
    }

    public  void teste (View view){
        //Toast.makeText(getContext(),"tocou",Toast.LENGTH_SHORT).show();
        Log.i("TESTE","CLICK");
    }
}
