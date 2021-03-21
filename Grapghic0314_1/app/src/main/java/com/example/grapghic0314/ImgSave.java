package com.example.grapghic0314;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImgSave {
    MainActivity context;

    String strFilePath;

    public ImgSave (Context context) {
        this.context = (MainActivity)context;
    }

    public String getStrFilePath() {
        return strFilePath;
    }

    public void imageFileSave(View v) {
        v.buildDrawingCache();
        Bitmap bitmap = v.getDrawingCache();

        FileOutputStream fileOutputStream;

        SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date date = new Date();

        String strFolderPath = context.getExternalFilesDir(null).getAbsolutePath();
        Log.d("ExternalPath", strFolderPath);

        File folder = new File(strFolderPath);
        if(!folder.exists()) {
            folder.mkdir();
        }

        strFilePath = strFolderPath + "/" + day.format(date) + ".png";


        File fileCacheItem = new File(strFilePath);

        try {
            fileOutputStream = new FileOutputStream(fileCacheItem);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
