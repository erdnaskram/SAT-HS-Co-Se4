package DP1_Composite_Pattern;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Kompositum extends Komponente{
    private String abteilungsname;
    private Blatt abteilungsleitung;
    private List<Komponente> komponenten;

    public Kompositum(@NotNull Blatt abteilungsleitung, String abteilungsname) {
        this.abteilungsleitung = abteilungsleitung;
        this.abteilungsname = abteilungsname;
        komponenten = new ArrayList<>();
        abteilungsleitung.setAbteilung(abteilungsname);
    }

    public void addKomponent(@NotNull Blatt komponente) {
        komponente.setAbteilung(abteilungsname);
        komponenten.add(komponente);
    }

    public void addKomponent(Kompositum komponente) {
        komponenten.add(komponente);
    }

    public void deleteKomponent(Komponente komponente) {
        komponenten.remove(komponente);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(abteilungsleitung.toString());
        sb.append("\n[\n");
        komponenten.forEach(komponente -> sb.append("\t").append(komponente.toString()).append("\n"));
        sb.append("]");
        return sb.toString();
    }

    public Blatt getAbteilungsleitung() {
        return abteilungsleitung;
    }

    public void setAbteilungsleitung(Blatt abteilungsleitung) {
        this.abteilungsleitung = abteilungsleitung;
    }

    public List<Komponente> getkomponenten() {
        return komponenten;
    }
}
