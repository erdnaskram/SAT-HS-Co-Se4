package DP1_Composite_Pattern;

public class Main {
    public static void main(String[] args) {
        Blatt reichEgon = new Blatt("Reich","Egon",
                "Geschäftsführer", (float) 40239.03);
        Blatt faulLars = new Blatt("Faul","Lars",
                "Leiter Medienabteilung", (float) 4020.03);
        Blatt schmittKonrad = new Blatt("Schmitt","Konrad",
                "Sachbearbeiter", (float) 2020.0);
        Blatt meyerPeter = new Blatt("Meyer","Peter",
                "Sachbearbeiter", (float) 2000.0);
        Kompositum management = new Kompositum(reichEgon, "Management");
        Kompositum medien = new Kompositum(faulLars, "medien");

        management.addAbteilung(medien);

        medien.addMitarbeiter(schmittKonrad);
        medien.addMitarbeiter(meyerPeter);

        System.out.println(management);
    }
}
