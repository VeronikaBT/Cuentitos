package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

public class Leon extends AppCompatActivity {
    private TextView tv;
    private ScrollView fd;
    private TextView ti;
    private TextView con;
    private Switch swi;
    private Button au;
    private Button di;
    private int tamano=8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leon);
        tv  = (TextView) findViewById(R.id.historia);
        fd = (ScrollView) findViewById(R.id.back);
        ti = (TextView) findViewById(R.id.tituloc1);
        con = (TextView) findViewById(R.id.historia);
        swi=(Switch) findViewById(R.id.switchTema);
        au=(Button) findViewById(R.id.aumentar);
        di=(Button) findViewById(R.id.disminuir);
        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(swi.isChecked()){
                    updateTheme("DARK","#FF000000","#FFFFFFFF","#FFFFFFFF","#37474f","#37474f");
                }else {
                    updateTheme("DEFAULT","#E3F59B","#E3F59B","#E3F59B","#FF3700B3","#FF3700B3");
                }
            }
        });
        loadThem();
    }
    public void Aumentar(View v){
        tamano=tamano+8;
        tv.setTextSize(tamano);
    }

    public void Descender(View v){
        if (tamano<=0)
            tamano=0;
        else
            tamano=tamano-4;
        tv.setTextSize(tamano);
    }
    public void Inicio (View v){
        Intent inicio = new Intent(this,MainActivity.class);
        startActivity(inicio);
    }
    public void updateTheme(String key,String t1,String t2,String t3,String t4,String t5){
        SharedPreferences savePreferences = getSharedPreferences("config_them",MODE_PRIVATE);
        SharedPreferences.Editor ObjEditor = savePreferences.edit();
        ObjEditor.putString("them",key);
        ObjEditor.commit();

        fd.setBackgroundColor(Color.parseColor(t1));
        ti.setBackgroundColor(Color.parseColor(t2));
        con.setBackgroundColor(Color.parseColor(t3));
        au.setBackgroundColor(Color.parseColor(t4));
        di.setBackgroundColor(Color.parseColor(t5));
    }

    public void loadThem(){
        SharedPreferences loadPreferences = getSharedPreferences("config_them",MODE_PRIVATE);
        String actualTheme = loadPreferences.getString("theme","DEFAULT");
        if(actualTheme.equals("DEFAULT")){
            updateTheme("DEFAULT","#E3F59B","#E3F59B","#E3F59B","#FF3700B3","#FF3700B3");
        }else if (actualTheme.equals("DARK")){
            updateTheme("DARK","#FF000000","#FFFFFFFF","#FFFFFFFF","#37474f","#37474f");
            swi.setChecked(true);
        }
    }
}