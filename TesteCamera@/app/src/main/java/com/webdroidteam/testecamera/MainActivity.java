package com.webdroidteam.testecamera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int FAZER_FOTO = 123;
    private String localArquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null){
            /*pega a foto*/
            Bundle bundle = data.getExtras();
            if(bundle != null){
                /*pega a imagem*/
                Bitmap img = (Bitmap) bundle.get("data");

               /*Seta foto no imageview do layout.xml*/
                ImageView iv = (ImageView) findViewById(R.id.imgView);
                iv.setImageBitmap(img);
            }
        }

        /*if(requestCode == FAZER_FOTO){
            if (resultCode == Activity.RESULT_OK){
                Toast.makeText(this, localArquivo, Toast.LENGTH_LONG).show();
            }else{
                localArquivo = null;
            }
        }*/
    }



}
