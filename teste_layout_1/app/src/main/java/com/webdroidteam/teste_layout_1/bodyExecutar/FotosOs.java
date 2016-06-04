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
    private String idOs;
    private static final int FAZER_FOTO = 123;
    private String localArquivo;
    public String nomeComponente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_os);

        //Recebe id da os para buscar os dados
        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        idOs = params.getString("IdOs");
    }

    public void  voltar (View view){
        finish();
    }

    public void finalizar(View view){
        Intent intent = null;
        intent = new Intent(this, AssExecutar.class);
        //Envia a OS Clicada no RecyclerView
        Bundle params = new Bundle();
        params.putString("IdOs", idOs);
        intent.putExtras(params);
        startActivity(intent);

//        startActivity(new Intent(this, AssExecutar.class));
        finish();
    }

    public void chamaCaptura (View view){
        nomeComponente = view.getTag().toString();
        tiraFoto();
    }

    public void tiraFoto(){
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
                ImageView iv1 = (ImageView) findViewById(R.id.imf_fotos_os_f1);
                ImageView iv2 = (ImageView) findViewById(R.id.imf_fotos_os_f2);
                ImageView iv3 = (ImageView) findViewById(R.id.imf_fotos_os_f3);

                if((nomeComponente).equals(iv1.getTag())){
                    iv1.setImageBitmap(img);
                }else if((nomeComponente).equals(iv2.getTag())){
                    iv2.setImageBitmap(img);
                }else if((nomeComponente).equals(iv3.getTag())){
                    iv3.setImageBitmap(img);
                }

            }
        }
    }
}
