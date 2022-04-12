package DP1_Composite_Pattern;

import java.util.ArrayList;
import java.util.List;

public class Kompostium {
    private Blatt abteilungsleitung;
    private List<Blatt> mitarbeiterListe;
    private List<Kompostium> unterabteilungen;

    public Kompostium(Blatt abteilungsleitung) {
        this.abteilungsleitung = abteilungsleitung;
        mitarbeiterListe = new ArrayList<>();
        unterabteilungen = new ArrayList<>();
    }

    public void addMitarbeiter(Blatt mitarbeiter) {
        mitarbeiterListe.add(mitarbeiter);
    }

    public void deleteMitarbeiter(Blatt mitarbeiter) {
        mitarbeiterListe.remove(mitarbeiter);
    }

    public void addUnterabteilung(Kompostium unterabteilung) {
        unterabteilungen.add(unterabteilung);
    }

    public void deleteUnterabteilung(Kompostium unterabteilung) {
        unterabteilungen.remove(unterabteilung);
    }

    @Override
    public String toString() {
        String ret;
        ret = abteilungsleitung.toString();
        ret += "";
        return "Kompostium{";
    }

    public Blatt getAbteilungsleitung() {
        return abteilungsleitung;
    }

    public void setAbteilungsleitung(Blatt abteilungsleitung) {
        this.abteilungsleitung = abteilungsleitung;
    }

    public List<Blatt> getMitarbeiterListe() {
        return mitarbeiterListe;
    }

    public List<Kompostium> getUnterabteilungen() {
        return unterabteilungen;
    }
}
