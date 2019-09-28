package com.example.luufilevaintent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    EditText etTenfile, etNoidung;
    Button btnXoatrang, btnLuu, btnXem;
    ArrayList<String> listfile = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        anhXa();

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenfile = etTenfile.getText().toString();
                listfile.add(tenfile);
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra("tenfile", tenfile);
                i.putStringArrayListExtra("listfile", listfile);


                try{
                    FileOutputStream fout = openFileOutput(tenfile, Context.MODE_PRIVATE);
                    fout.write(etNoidung.getText().toString().getBytes());
                    fout.close();

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Loi luu file", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putStringArrayListExtra("listfile", listfile);
                startActivity(i);
            }
        });
        btnXoatrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNoidung.setText("");
                etTenfile.setText("");
                etTenfile.requestFocus();
            }
        });


    }

    private void anhXa() {
        etTenfile = findViewById(R.id.etTenfile);
        etNoidung = findViewById(R.id.etNoidung);
        btnXoatrang = findViewById(R.id.btnXoatrang);
        btnLuu = findViewById(R.id.btnLuu);
        btnXem = findViewById(R.id.btnXem);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
