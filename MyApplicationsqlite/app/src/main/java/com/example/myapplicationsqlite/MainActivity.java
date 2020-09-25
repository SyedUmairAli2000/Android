package com.example.myapplicationsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MySQLite db=new MySQLite(this);

    EditText txtName,txtPhone,txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtName=findViewById(R.id.txtName);
        txtPhone=findViewById(R.id.txtPhone);
        txtEmail=findViewById(R.id.txtEmail);





    }

    public void insertData(View view)
    {
        String Name=txtName.getText().toString();
        String Phone=txtPhone.getText().toString();
        String Email=txtEmail.getText().toString();

       boolean IsInsert= db.insertStudent(Name,Phone,Email);
       if(IsInsert)
       {
           Toast.makeText(this, "Insert Successfully", Toast.LENGTH_SHORT).show();
       }
       else
       {
           Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
       }

    }

    public void fetch(View view)
    {
        Cursor cursor=db.fetchStudents();
        String str="";
        while(cursor.moveToNext())
        {
            str+=cursor.getString(0)+"\n";
            str+=cursor.getString(1)+"\n";
            str+=cursor.getString(2)+"\n";
            str+=cursor.getString(3)+"\n\n";


        }

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Student Record");
        builder.setMessage(str);
        builder.create().show();

    }
}
