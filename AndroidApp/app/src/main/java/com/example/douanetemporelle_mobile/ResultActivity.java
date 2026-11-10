package com.example.douanetemporelle_mobile;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // n7ottou el layout elli 3malnah 3al écran
        setContentView(R.layout.activity_result);

        // nrbtou el variables bel IDs mta3 el XML
        View racine = findViewById(R.id.layout_racine_gj);
        TextView tvMessage = findViewById(R.id.tv_message_gj);
        Button btnNouveau = findViewById(R.id.btn_nouveau_gj);

        // njibou el Intent elli lanca hedhi el activité
        Intent intent = getIntent();

        // n9raw el verdict (boolean) w el esm b el clés mta3 CleExtra
        boolean approuve = intent.getBooleanExtra(CleExtra.cleVerdict(CleExtra.MATRICULE), false);
        String nom = intent.getStringExtra(CleExtra.cleNom(CleExtra.MATRICULE));
    }
}