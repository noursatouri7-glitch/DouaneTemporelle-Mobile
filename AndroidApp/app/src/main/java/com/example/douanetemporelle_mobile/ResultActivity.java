package com.example.douanetemporelle_mobile;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

        // el fond w el message yetbaddlou 7asb el verdict
        if (approuve) {
            // approuvé => fond vert + "Bon voyage [Nom]"
            racine.setBackgroundColor(ContextCompat.getColor(this, R.color.result_vert));
            tvMessage.setText(getString(R.string.result_msg_approuve, nom));
        } else {
            // refusé => fond rouge + "[Nom] est en état d'arrestation temporelle"
            racine.setBackgroundColor(ContextCompat.getColor(this, R.color.result_rouge));
            tvMessage.setText(getString(R.string.result_msg_refuse, nom));
        }

        // "Nouveau voyageur": nrj3ou l MainActivity w nsakrou kol el activités el 9dom
        btnNouveau.setOnClickListener(v -> {
            Intent retour = new Intent(ResultActivity.this, MainActivity.class);
            // CLEAR_TASK yfassakh el pile lkol, NEW_TASK ybda pile jdida
            retour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(retour);
        });
    }
}