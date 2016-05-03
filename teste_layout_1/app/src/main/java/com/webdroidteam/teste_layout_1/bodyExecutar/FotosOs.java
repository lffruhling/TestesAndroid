package com.webdroidteam.teste_layout_1.bodyExecutar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.webdroidteam.teste_layout_1.R;

import java.io.File;

public class FotosOs extends AppCompatActivity {

    private static final int FAZER_FOTO = 123;
    private String localArquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_os);
    }

    public void  voltar (View view){
        finish();
    }

    public void finalizar(View view){
        startActivity(new Intent(this, AssExecutar.class));
        finish();
    }

    public void tiraFoto(View view){
        localArquivo = Environment.getExternalStorageState()+"/"+ System.currentTimeMillis()+".jpg";
        File arquivo = new File(localArquivo);

        Uri localFoto = Uri.fromFile(arquivo);

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera.putExtra(MediaStore.Images.Media.DISPLAY_NAME, localFoto);
        startActivityForResult(camera, FAZER_FOTO);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            /*pega a foto*/
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                /*pega a imagem*/
                Bitmap img = (Bitmap) bundle.get("data");

               /*Seta foto no imageview do layout.xml*/
                ImageView iv = (ImageView) findViewById(R.id.imf_fotos_os_f1);
                iv.setImageBitmap(img);
            }
        }
    }
}
