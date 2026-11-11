
package com.example.zatrzymajna7;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int licznik = 0;
    int trafienia = 0;
    int czas = 500;

    Handler handler = new Handler(Looper.getMainLooper());

    TextView cyfra, komunikat, wynik;
    Button start, stop;


    Runnable zmiana = new Runnable() {
        @Override
        public void run() {

            licznik++;

            if (licznik > 9) {
                licznik = 0;
            }

            cyfra.setText("" + licznik);

            handler.postDelayed(this, czas);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cyfra = findViewById(R.id.textView2);
        komunikat = findViewById(R.id.komunikat);
        wynik = findViewById(R.id.trafienia);

        start = findViewById(R.id.button);
        stop = findViewById(R.id.button2);


        stop.setEnabled(false);


        start.setOnClickListener(v -> {

            licznik = 0;

            cyfra.setText("0");
            komunikat.setText("");

            start.setEnabled(false);
            stop.setEnabled(true);

            handler.postDelayed(zmiana, czas);
        });


        stop.setOnClickListener(v -> {

            handler.removeCallbacks(zmiana);

            if (licznik == 7) {

                komunikat.setText("Trafienie!");

                trafienia++;

                wynik.setText("Trafienia: " + trafienia);


                czas = czas - 50;

                if (czas < 200) {
                    czas = 200;
                }

            } else {

                komunikat.setText("Pudło! Zatrzymano na " + licznik);

            }

            start.setEnabled(true);
            stop.setEnabled(false);
        });
    }
}
