package com.webdroidteam.testeassinatura1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    public static final int SIGNATURE_ACTIVITY = 1;
    public ImageView imguser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imguser = (ImageView) findViewById(R.id.img_ass);

        if(getIntent().hasExtra("byteArray")) {
            //ImageView previewThumbnail = new ImageView(this);
            ImageView iv1 = (ImageView) findViewById(R.id.img_ass);
            Bitmap b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
            iv1.setImageBitmap(b);
        }

        Button getSignature = (Button) findViewById(R.id.signature);
        getSignature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, signatureActivity.class);
                startActivityForResult(intent,SIGNATURE_ACTIVITY);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode) {
            case SIGNATURE_ACTIVITY:
                if (resultCode == RESULT_OK) {

                    //recebe intent
                    if(getIntent().hasExtra("byteArray")) {
                        //ImageView previewThumbnail = new ImageView(this);
                        ImageView iv1 = (ImageView) findViewById(R.id.img_ass);
                        Bitmap b = BitmapFactory.decodeByteArray(
                                getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
                        iv1.setImageBitmap(b);
                    }
                    /*
                    Bundle bundle = data.getExtras();
                    String status  = bundle.getString("status");
                    if(status.equalsIgnoreCase("done")) {
                        Toast toast = Toast.makeText(this, "Signature capture successful!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 105, 50);
                        toast.show();
                        Toast.makeText(this, "Voltou", Toast.LENGTH_LONG).show();

                        if(getIntent().hasExtra("byteArray")) {
                            //ImageView previewThumbnail = new ImageView(this);
                            ImageView iv1 = (ImageView) findViewById(R.id.img_ass);
                            Bitmap b = BitmapFactory.decodeByteArray(
                                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
                            iv1.setImageBitmap(b);
                        }

                    }*/
                }
                break;
        }

    }
}
