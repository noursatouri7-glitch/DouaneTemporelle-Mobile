package com.example.douanetemporelle_mobile;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
public class VerificationActivity extends AppCompatActivity {

   // hedhom les variables li bech nestaamlouhom bech naffichiw les informations ta3 les voyageurs
    private TextView textNomVoyageur; //
    private TextView textAgeTemporel;
    private TextView textMotif;
    // hedhom les boutons li bech ne9blou wala mane9blouch el voyage
    private Button btnApprouver;
    private Button btnRefuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // nafichiw l interface li bel xml kol variable talk9a l element mte3ha bel id
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_verification);
        textNomVoyageur = findViewById(R.id.textNomVoyageur);
        textAgeTemporel = findViewById(R.id.textAgeTemporel);
        textMotif = findViewById(R.id.textMotif);

        btnApprouver = findViewById(R.id.btnApprouver);
        btnRefuser = findViewById(R.id.btnRefuser);
        //les informations mta3 el voyageur li tba3thouna mel page saisie activity
        String nom = getIntent().getStringExtra("nom");
        int anneeNaissance = getIntent().getIntExtra("anneeNaissance", 0);
        int anneeDestination = getIntent().getIntExtra("anneeDestination", 0);
        String motif = getIntent().getStringExtra("motif");

        int ageTemporel = anneeDestination - anneeNaissance;
        textAgeTemporel.setText("Âge temporel : " + ageTemporel + " ans");

        textNomVoyageur.setText("Nom : " + nom);// esm voyageur fel passeport
        textMotif.setText("Motif : " + motif);// 3leh voyageur yheb yemchi passe wala future

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}