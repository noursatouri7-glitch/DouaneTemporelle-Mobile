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
import android.content.Intent;
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
        textNomVoyageur = findViewById(R.id.textNomVoyageur_ns);
        textAgeTemporel = findViewById(R.id.textAgeTemporel_ns);
        textMotif = findViewById(R.id.textMotif_ns);

        btnApprouver = findViewById(R.id.btnApprouver_ns);
        btnRefuser = findViewById(R.id.btnRefuser_ns);
        //les informations mta3 el voyageur li tba3thouna mel page saisie activity
        String nom = getIntent().getStringExtra("nom");
        int anneeNaissance = getIntent().getIntExtra("anneeNaissance", 0);
        int anneeDestination = getIntent().getIntExtra("anneeDestination", 0);
        String motif = getIntent().getStringExtra("motif");

        int ageTemporel = anneeDestination - anneeNaissance;
        textAgeTemporel.setText("Âge temporel : " + ageTemporel + " ans");

        textNomVoyageur.setText("Nom : " + nom);// esm voyageur fel passeport
        textMotif.setText("Motif : " + motif);// 3leh voyageur yheb yemchi passe wala future
        //nverifiw ken l voyage fih risque wale
        //houni 3ana 2 conditions loula verification ken motif verification du passe w thenia neverifiw
        //kenl age temproel nombre premier
        if ("Modification du passé".equalsIgnoreCase(motif) && estPremier(ageTemporel)) {
            //ken les 2 conditions shah nsakrou bouton approuver
            btnApprouver.setEnabled(false);
        }
        // ki l voyageur yenzel ala approuver yemchi lel page resultat
        btnApprouver.setOnClickListener(v -> {
            //na3mlou intent explicite bech n7ellou Resultactivity
            Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
            //naba3thou esm l voyageur
            intent.putExtra("nom", nom);
            //naba3thou verdict true maaneha voyageur approuve
            intent.putExtra("verdict", true);
            //hne nhelou page result
            startActivity(intent);
        });

        //ki l voyageur yenzel ala refuser yemchi lel page resultat
        btnRefuser.setOnClickListener(v -> {
            Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
            intent.putExtra("nom", nom);
            intent.putExtra("verdict", false);
            startActivity(intent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // methode tverifi ken nombre premier wale
    private boolean estPremier(int nombre) {

        // ken nombre asgher m 2 ma3neha mahouch premier
        if (nombre < 2) {
            return false;
        }

        // njarbou les diviseurs mel 2 hatekchi l noss taa nombre khater diviseur maynejemch ykoun akber
        //m nombre//2
        for (int i = 2; i <= nombre/2; i++) {

            // ken el reste mta3 el division 0 mahouch premier
            if (nombre % i == 0) {
                return false;
            }
        }

        // ken ma lkinech un divieur maaneha le nombre est premier
        return true;
    }

}