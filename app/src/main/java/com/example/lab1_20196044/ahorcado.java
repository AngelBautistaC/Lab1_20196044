package com.example.lab1_20196044;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ahorcado extends AppCompatActivity {


    Dialog myDialog;



    private boolean juegoEnCurso = false;

    private ArrayList<String> estadisticas = new ArrayList<>();

    private String palabraActual = "";
    private TextView[] lineas;
    private ImageView[] partesCuerpo;
    private int errores = 0;
    private List<String> palabras = Arrays.asList("REDES", "PROPA", "PUCP", "TELITO", "TELECO", "BATI");
    private int tiempo = 0; // variable para llevar el tiempo
    private Handler handler = new Handler(); // Handler para actualizar el tiempo
    private boolean juegoTerminado = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorcado);
        getSupportActionBar().setTitle("TeleAhorcado");
        myDialog = new Dialog(this);

        final TextView resultados = findViewById(R.id.resultados);
        final ImageView head = findViewById(R.id.head);
        final ImageView torso = findViewById(R.id.torso);
        final ImageView pieizq = findViewById(R.id.pieizq);
        final ImageView manoizq = findViewById(R.id.manoizq);
        final ImageView manoder = findViewById(R.id.manoder);
        final ImageView pieder = findViewById(R.id.pieder);

        partesCuerpo = new ImageView[]{head, torso, manoder, manoizq, pieizq, pieder};

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

                if (juegoEnCurso && !juegoTerminado) {
                    estadisticas.add("Canceló");

                }
                // Ahora, inicia un nuevo juego
                juegoTerminado = false; // Reinicia el estado del juego
                juegoEnCurso = true;

                tiempo=0;
                errores = 0;

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tiempo++;
                        handler.postDelayed(this, 1000);
                    }
                }, 1000);

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

        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "Re", "S","T", "U", "V", "W", "X", "Y","Z"};

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
                            char expectedChar = letra.charAt(0);
                            if (letra.equals("Re")) {
                                expectedChar = 'R';
                            }
                            if (palabraActual.charAt(i) == expectedChar) {
                                lineas[i].setText(String.valueOf(expectedChar));
                                acierto = true;
                            }
                        }

                        if (!acierto) {
                            partesCuerpo[errores].setVisibility(View.VISIBLE);
                            errores++;
                            if (errores >= partesCuerpo.length) {
                                juegoTerminado = true;
                                resultados.setText("Perdiste");
                                estadisticas.add("Terminó en " + tiempo + " segundos"); // Añadir estadística
                                handler.removeCallbacksAndMessages(null); // Detener el contador de tiempo

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
                            juegoTerminado = true;
                            resultados.setText("Ganó | terminó en " + tiempo + " s");
                            estadisticas.add("Terminó en " + tiempo + " segundos"); // Añadir estadística
                            deshabilitarBotones();
                            handler.removeCallbacksAndMessages(null); // Detener el contador de tiempo
                        }


                    }
                }
            });
        }
    }

    private void habilitarBotones() {
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "Re", "S","T", "U", "V", "W", "X", "Y","Z"};
        for (String letra : letras) {
            TextView letraView = findViewById(getResources().getIdentifier(letra, "id", getPackageName()));
            letraView.setEnabled(true);
            letraView.setBackgroundColor(getResources().getColor(R.color.cyan));
        }
    }

    private void deshabilitarBotones() {
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "Re", "S","T", "U", "V", "W", "X", "Y","Z"};
        for (String letra : letras) {
            TextView letraView = findViewById(getResources().getIdentifier(letra, "id", getPackageName()));
            letraView.setEnabled(false);
        }
    }

    public void statsBtn(MenuItem menuItem){
        myDialog.setContentView(R.layout.popup);
        LinearLayout listaStats = myDialog.findViewById(R.id.listadostats); // Obtener referencia al LinearLayout
        listaStats.removeAllViews(); // Eliminar las vistas previas

        // Añadir estadísticas
        int i = 1;
        for (String stat : estadisticas) {

            TextView newStat = new TextView(this);
            newStat.setText(i + ". " + stat);  // Añadir número al comienzo de la estadística
            i++;  // Incrementar el número de la estadística

   
            newStat.setTextColor(Color.BLACK);
            newStat.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            );

            listaStats.addView(newStat);
        }

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        myDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ahorcado_menu,menu);
        return true;
    }
}
