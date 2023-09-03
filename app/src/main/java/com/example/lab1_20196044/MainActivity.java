package com.example.lab1_20196044;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView4);
        registerForContextMenu(mTextView);
        Button btnJugar = findViewById(R.id.button);

        btnJugar.setOnClickListener(view -> {
            // Crear un nuevo Intent para abrir AhorcadoActivity
            Intent intent = new Intent(MainActivity.this, ahorcado.class);
            startActivity(intent);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.textView4) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
        }
    }

    public void rojo(MenuItem menuItem){
        mTextView.setTextColor(Color.RED);
        Toast.makeText(this, "btn wifi presionado", Toast.LENGTH_SHORT).show();
    }

    public void verde(MenuItem menuItem){
        mTextView.setTextColor(Color.GREEN);
        Toast.makeText(this, "btn ADD presionado", Toast.LENGTH_SHORT).show();
    }
    public void azul(MenuItem menuItem){
        mTextView.setTextColor(Color.BLUE);
        Toast.makeText(this, "btn ADD presionado", Toast.LENGTH_SHORT).show();
    }


}