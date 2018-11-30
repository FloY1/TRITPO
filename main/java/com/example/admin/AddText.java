package com.example.admin;
чё
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddText extends AppCompatActivity {
    private DataDAO db = new DataDAO(this);


    private EditText text1;
    private EditText text2;
    private EditText text3;
    private TextView textState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text);



        text1 = findViewById(R.id.editText2);
        text2 = findViewById(R.id.editText3);
        text3 = findViewById(R.id.editText4);
        textState = findViewById(R.id.text_state);
    }
    public void AddClick(View view){
        addInBase();

    }
    void addInBase(){
        String product = text1.getText().toString();
        int count = Integer.parseInt(text2.getText().toString());
        int cost = Integer.parseInt(text3.getText().toString());

        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataDAO.KEY_Cost,count);
        contentValues.put(DataDAO.KEY_Count,cost);
        contentValues.put(DataDAO.KEY_Product,product);
        database.insert(DataDAO.TABLE_CONTACT,null,contentValues);
        textState.setText("Успешно добавленно");

    }
}
