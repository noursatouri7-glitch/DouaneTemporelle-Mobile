package com.example.douanetemporelle_mobile;


// 1- كلاس مساعد يولّد مفاتيح الـ Intent
public class KeysHelper {

    // 2- آخر 4 أرقام من بطاقة الطالب (نفس الرقم عند الثلاثة)
    public static final String MATRICULE = "3867";

    // 3- أسماء المفاتيح بدون المطريكول
    public static final String NOM_VOYAGEUR = "NOM_VOYAGEUR";
    public static final String ANNEE_NAISSANCE = "ANNEE_NAISSANCE";
    public static final String ANNEE_DESTINATION = "ANNEE_DESTINATION";
    public static final String MOTIF = "MOTIF";
    public static final String VERDICT = "VERDICT";

    // 4- دالة ثابتة تبني المفتاح الكامل، مثال: 1234_NOM_VOYAGEUR
    public static String cle(String matricule, String nom) {
        return matricule + "_" + nom;
    }
}




