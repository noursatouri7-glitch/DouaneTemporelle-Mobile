package com.example.douanetemporelle_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SaisieActivity extends AppCompatActivity {

    // 1- Ta3rif l-variables mta3 l-interface (kol l-IDs fihom gg)
    private EditText etNomGg, etNaissanceGg, etDestinationGg; // Blassat win ydakhil l-ism, 3am l-oulada, w 3am l-oujehha
    private Spinner spMotifGg; // Liste deroulante bech yekhtar sebba mta3 l-voyage
    private Button btnInspecterGg; // Bouton mta3 l-inspection (l-fahs)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie); // Rbatt l-Activity b-fichier XML mta3ha

        // 2- Rbatt l-variables b-eléments l-interface l-haqiqiyin bel-ID
        etNomGg = findViewById(R.id.et_nom_gg);
        etNaissanceGg = findViewById(R.id.et_naissance_gg);
        etDestinationGg = findViewById(R.id.et_destination_gg);
        spMotifGg = findViewById(R.id.sp_motif_gg);
        btnInspecterGg = findViewById(R.id.btn_inspecter_gg);

        // 3- Ta3biya mta3 l-Spinner bel-khiyarāt l-mawjouda fi fichier strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.motifs_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMotifGg.setAdapter(adapter); // Tarkib l-Adapter fi l-Spinner

        // 4- Chnoua ysîr kif l-utilisateur yekklikî 3la l-bouton "Inspecter le profil"
        btnInspecterGg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inspecter(); // Nadiou 3la l-methode l-responsable 3la l-khedma kol
            }
        });
    }

    // Methode responsable 3la l-verification, qraet l-données, w ba3th-hom lel-Activity l-jeya
    private void inspecter() {
        // 5- Qraet l-données l-mektouba fi l-champs w na7iou l-espace l-zaed b-trim()
        String nom = etNomGg.getText().toString().trim();
        String naissanceStr = etNaissanceGg.getText().toString().trim();
        String destinationStr = etDestinationGg.getText().toString().trim();
        String motif = spMotifGg.getSelectedItem().toString(); // Qraet l-choix l-actuel mta3 l-Spinner

        // 6- L-verif bech n-thabtou elli hata champ ma houa faregh
        if (nom.isEmpty() || naissanceStr.isEmpty() || destinationStr.isEmpty()) {
            // Kif thama champ faregh, n-kharjou message d'erreur (Toast)
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return; // N-habbssou l-execution houni w ma n-kamlouch
        }

        // 7- Convertir l-a3wam min Texte (String) l-Entier (int) bech n-najmou n-stahmlouhom
        int naissance = Integer.parseInt(naissanceStr);
        int destination = Integer.parseInt(destinationStr);

        // 8- Tahdir Intent explicite bech n-hallou l-Activity l-thania (VerificationActivity)
        Intent intent = new Intent(SaisieActivity.this, VerificationActivity.class);

        // Ba3th l-données ma3 l-Intent b-est3mal l-classe KeysHelper bech n-nadhmiou l-clés
        intent.putExtra(KeysHelper.cle(KeysHelper.MATRICULE, KeysHelper.NOM_VOYAGEUR), nom);
        intent.putExtra(KeysHelper.cle(KeysHelper.MATRICULE, KeysHelper.ANNEE_NAISSANCE), naissance);
        intent.putExtra(KeysHelper.cle(KeysHelper.MATRICULE, KeysHelper.ANNEE_DESTINATION), destination);
        intent.putExtra(KeysHelper.cle(KeysHelper.MATRICULE, KeysHelper.MOTIF), motif);

        // Lancement mta3 l-Activity l-thania w ba3th l-données m3aha
        startActivity(intent);
    }
}