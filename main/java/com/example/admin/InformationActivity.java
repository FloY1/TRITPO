package com.example.admin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InformationActivity extends AppCompatActivity {
    ListView listView;
    private DataDAO db = new DataDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
            listView =findViewById(R.id.ListView);

           SQLiteDatabase database = db.getWritableDatabase();
           Cursor cursor = database.query(DataDAO.TABLE_CONTACT,null,null,null,null,null,null) ;
        int idIndex = cursor.getColumnIndex(DataDAO.KEY_ID);
        int  productIndex = cursor.getColumnIndex(DataDAO.KEY_Product);
        int costIndex = cursor.getColumnIndex(DataDAO.KEY_Cost);
        int countIndex = cursor.getColumnIndex(DataDAO.KEY_Count);
        String s[] =  new String[cursor.getCount()];
        int i =0;


        if(cursor.moveToFirst()){
            do{

                s[i] = cursor.getString(productIndex)+"      "+cursor.getInt(countIndex)+"    "+cursor.getInt(costIndex);
                i++;
            }while (cursor.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.sample_list_component,s);
            listView.setAdapter(adapter);
        }
    }
}
