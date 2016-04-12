package com.webdroidteam.teste_design_4;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class tab_layout_c extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        // Inflamos o layout tab_layout_a.xml
        return (RelativeLayout) inflater.inflate(R.layout.activity_tab_layout_c, container, false);

    }
}
