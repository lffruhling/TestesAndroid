package com.webdroidteam.testeassinatura1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Leonardo on 03/05/2016.
 */
public class SerializaImg implements Serializable {
    public Bitmap bitmap;

    public SerializaImg(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    private void writeObject (ObjectOutputStream out) throws IOException{
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
        byte bitmmapBytes[] = byteStream.toByteArray();
        out.write(bitmmapBytes,0,bitmmapBytes.length);
    }

    private void readObject(ObjectInputStream in) throws IOException{
        ByteArrayOutputStream byteStram = new ByteArrayOutputStream();
        int b;
        while ((b = in.read()) != -1)
            byteStram.write(b);
        byte bitmapBytes[] = byteStram.toByteArray();
        bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
    }
}
