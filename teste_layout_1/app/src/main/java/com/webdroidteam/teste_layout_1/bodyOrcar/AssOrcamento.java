package com.webdroidteam.teste_layout_1.bodyOrcar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.webdroidteam.teste_layout_1.R;
import com.webdroidteam.teste_layout_1.SignActivity;

public class AssOrcamento extends AppCompatActivity {
    public static final int ACTIVITY_2 = 1002;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass_orcamento);

        if(getIntent().hasExtra("byteArray")) {
            //ImageView previewThumbnail = new ImageView(this);
            ImageView iv1 = (ImageView) findViewById(R.id.img_ass_orc_ass);
            Bitmap b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
            iv1.setImageBitmap(b);
        }
    }

    public void  voltar (View view){
        finish();
    }

    public void enviar_orc(View view){
        startActivity(new Intent(this, FimOrcamento.class));
        finish();
    }

    public void assinarOrc(View view){
        Intent act2 = new Intent(this, SignActivity.class);
        act2.putExtra("calling-activity", AssOrcamento.ACTIVITY_2);
        // or ActivityConstants.ACTIVITY_3 if called form Activity3
        startActivity(act2);
        /*Intent intent = new Intent(AssExecutar.this, SignActivity.class);
        String nome_img = view.getTag().toString();

        Bundle params = new Bundle();
        params.putString("NOME_IMG", nome_img);
        intent.putExtras(params);
        startActivity(intent);*/
    }


}
