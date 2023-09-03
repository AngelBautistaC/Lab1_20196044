package com.example.lab1_20196044;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ahorcado extends AppCompatActivity {

    private String palabraActual = "";
    private TextView[] lineas;
    private ImageView[] partesCuerpo;
    private int errores = 0;
    private List<String> palabras = Arrays.asList("REDES", "PROPA", "PUCP", "TELITO", "TELECO", "BATI");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorcado);
        getSupportActionBar().setTitle("TeleAhorcado");

        final TextView resultados = findViewById(R.id.resultados);
        final ImageView head = findViewById(R.id.head);
        final ImageView torso = findViewById(R.id.torso);
        final ImageView pieizq = findViewById(R.id.pieizq);
        final ImageView manoizq = findViewById(R.id.manoizq);
        final ImageView manoder = findViewById(R.id.manoder);
        final ImageView pieder = findViewById(R.id.pieder);

        partesCuerpo = new ImageView[]{head, torso, pieizq, manoizq, manoder, pieder};

        final TextView linea1 = findViewById(R.id.linea1);
        final TextView linea2 = findViewById(R.id.linea2);
        final TextView linea3 = findViewById(R.id.linea3);
        final TextView linea4 = findViewById(R.id.linea4);
        final TextView linea5 = findViewById(R.id.linea5);
        final TextView linea6 = findViewById(R.id.linea6);

        lineas = new TextView[]{linea1, linea2, linea3, linea4, linea5, linea6};

        final Button nuevoJuegoBtn = findViewById(R.id.nuevojuego);
        final Random random = new Random();

        nuevoJuegoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errores = 0;
                habilitarBotones();
                for (ImageView parte : partesCuerpo) {
                    parte.setVisibility(View.INVISIBLE);
                }

                int randomIndex = random.nextInt(palabras.size());
                palabraActual = palabras.get(randomIndex);
                for (int i = 0; i < lineas.length; i++) {
                    if (i < palabraActual.length()) {
                        lineas[i].setVisibility(View.VISIBLE);
                        lineas[i].setText("_");
                    } else {
                        lineas[i].setVisibility(View.INVISIBLE);
                    }
                }
                resultados.setText("");
            }
        });

        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "erre", "S","T", "U", "V", "W", "X", "Y","Z"};

        for (final String letra : letras) {
            final TextView letraView = findViewById(getResources().getIdentifier(letra, "id", getPackageName()));
            letraView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    letraView.setEnabled(false);
                    letraView.setBackgroundColor(getResources().getColor(R.color.naranja));

                    if (!palabraActual.isEmpty()) {
                        boolean acierto = false;
                        for (int i = 0; i < palabraActual.length(); i++) {
                            if (palabraActual.charAt(i) == letra.charAt(0)) {
                                lineas[i].setText(letra);
                                acierto = true;
                            }
                        }

                        if (!acierto) {
                            partesCuerpo[errores].setVisibility(View.VISIBLE);
                            errores++;
                            if (errores >= partesCuerpo.length) {
                                resultados.setText("Perdiste");
                                deshabilitarBotones();
                            }
                        }

                        boolean haGanado = true;
                        for (int i = 0; i < palabraActual.length(); i++) {
                            if ("_".equals(lineas[i].getText().toString())) {
                                haGanado = false;
                                break;
                            }
                        }

                        if (haGanado) {
                            resultados.setText("Ganaste");
                            deshabilitarBotones();
                        }

                    }
                }
            });
        }
    }

    private void habilitarBotones() {
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "erre", "S","T", "U", "V", "W", "X", "Y","Z"};
        for (String letra : letras) {
            TextView letraView = findViewById(getResources().getIdentifier(letra, "id", getPackageName()));
            letraView.setEnabled(true);
            letraView.setBackgroundColor(getResources().getColor(R.color.cyan));
        }
    }

    private void deshabilitarBotones() {
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "erre", "S","T", "U", "V", "W", "X", "Y","Z"};
        for (String letra : letras) {
            TextView letraView = findViewById(getResources().getIdentifier(letra, "id", getPackageName()));
            letraView.setEnabled(false);
        }
    }

    public void statsBtn(MenuItem menuItem){
        Toast.makeText(this, "holi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ahorcado_menu,menu);
        return true;
    }
}
