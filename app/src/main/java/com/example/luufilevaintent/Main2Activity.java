package com.example.luufilevaintent;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    Spinner spFile;
    TextView tvNoidung;
    Button btnMo, btnQuaylai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
        spFile = findViewById(R.id.spFile);
        btnMo = findViewById(R.id.btnMo);
        tvNoidung = findViewById(R.id.tvNoidung);


        ArrayList<String> listfile = getIntent().getStringArrayListExtra("listfile");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listfile);
        spFile.setAdapter(adapter);

        spFile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnMo = findViewById(R.id.btnMo);
        btnMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                String line = null;
                String tenfile = spFile.getSelectedItem().toString();
                try{
                    InputStream fin = openFileInput(tenfile);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                    while((line = br.readLine())!=null){
                        buffer.append(line).append("\n");
                    }
                    tvNoidung.setText(buffer.toString());
                }catch (Exception e ){
                    Toast.makeText(Main2Activity.this, "Loi input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnQuaylai = findViewById(R.id.btnQuaylai);
        btnQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
