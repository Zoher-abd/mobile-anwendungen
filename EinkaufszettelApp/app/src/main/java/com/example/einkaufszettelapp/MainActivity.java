package com.example.einkaufszettelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText inputKategorie, inputProdukt;
    Button btnKategorie, btnProdukt;
    Spinner spinnerKategorien;
    ListView listProdukte;

    EinkaufsDatenbank db;
    EinkaufsDao dao;
    List<Kategorie> kategorienListe = new ArrayList<>();
    ArrayAdapter<String> spinnerAdapter;
    ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI-Elemente
        inputKategorie = findViewById(R.id.inputKategorie);
        inputProdukt = findViewById(R.id.inputProdukt);
        btnKategorie = findViewById(R.id.btnKategorie);
        btnProdukt = findViewById(R.id.btnProdukt);
        spinnerKategorien = findViewById(R.id.spinnerKategorien);
        listProdukte = findViewById(R.id.listProdukte);

        // Datenbank initialisieren
        db = Room.databaseBuilder(getApplicationContext(),
                        EinkaufsDatenbank.class, "einkaufsdb")
                .allowMainThreadQueries() // für Einfachheit, später besser mit Async
                .build();

        dao = db.dao();

        // Spinner initial füllen
        ladeKategorienInSpinner();

        // Kategorie hinzufügen
        btnKategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputKategorie.getText().toString().trim();
                if (!name.isEmpty()) {
                    Kategorie k = new Kategorie();
                    k.name = name;
                    dao.insertKategorie(k);
                    inputKategorie.setText("");
                    ladeKategorienInSpinner();
                }
            }
        });

        // Produkt hinzufügen
        btnProdukt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String produktName = inputProdukt.getText().toString().trim();
                int index = spinnerKategorien.getSelectedItemPosition();

                if (!produktName.isEmpty() && index >= 0) {
                    Produkt p = new Produkt();
                    p.name = produktName;
                    p.kategorieId = kategorienListe.get(index).id;
                    dao.insertProdukt(p);
                    inputProdukt.setText("");
                    ladeProdukteZuKategorie(kategorienListe.get(index).id);
                }
            }
        });

        // Wenn Kategorie im Spinner gewählt → Produkte anzeigen
        spinnerKategorien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int kategorieId = kategorienListe.get(i).id;
                ladeProdukteZuKategorie(kategorieId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                listAdapter.clear();
            }
        });
    }

    // Kategorien in Spinner laden
    private void ladeKategorienInSpinner() {
        kategorienListe = dao.getAlleKategorien();
        List<String> kategorienNamen = new ArrayList<>();
        for (Kategorie k : kategorienListe) {
            kategorienNamen.add(k.name);
        }
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kategorienNamen);
        spinnerKategorien.setAdapter(spinnerAdapter);
    }

    // Produkte zu einer Kategorie laden und in Liste anzeigen
    private void ladeProdukteZuKategorie(int kategorieId) {
        List<Produkt> produkte = dao.getProdukteFuerKategorie(kategorieId);
        List<String> produktNamen = new ArrayList<>();
        for (Produkt p : produkte) {
            produktNamen.add(p.name);
        }
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produktNamen);
        listProdukte.setAdapter(listAdapter);
    }
}
