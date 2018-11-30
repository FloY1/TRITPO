package com.example.admin;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import com.googlecode.tesseract.android.TessBaseAPI;

public class AddActivity extends AppCompatActivity {

    private String mCurrentPhotoPath;
    private File imagee;
    private ImageView image;
    private TextView text;
    private Uri photoURI;
    private final int PIC_CROP = 2;
    private DataDAO db = new DataDAO(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        image = findViewById(R.id.imageView);
        text = findViewById(R.id.textView);

    }


    public void photoAddButtonClick(View view) {

        dispatchTakePictureIntent();


    }

    public void photoNextButtonClick(View view) {
        try {
          extractText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 1) {
            image.setImageURI(photoURI);


    }



    }



    private File createImageFile() {
        imagee = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/2.jpg");
        mCurrentPhotoPath = imagee.getAbsolutePath();
        return imagee;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = createImageFile();
        if (photoFile != null) {
            photoURI = FileProvider.getUriForFile(this,
                    "com.example.admin/",
                    photoFile);

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

        }


    }





    private String extractText() {

        TessBaseAPI tessBaseApi = new TessBaseAPI();
        String datapath = Environment.getExternalStorageDirectory() + "/tesseract/";
        File dir = new File(datapath + "tessdata/");
        if (!dir.exists())
            dir.mkdirs();

        tessBaseApi.init( datapath, "rus");

        tessBaseApi.setImage(imagee);
        String extractedText = tessBaseApi.getUTF8Text();
        tessBaseApi.end();
        text.setText(extractedText);
        //addInBase(extractedText);
        return extractedText;
    }

    void addInBase(String text){
        String product = "das";
        int count = 1;
        int cost = 1;

        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataDAO.KEY_Cost,count);
        contentValues.put(DataDAO.KEY_Count,cost);
        contentValues.put(DataDAO.KEY_Product,product);
        database.insert(DataDAO.TABLE_CONTACT,null,contentValues);
    }

}







