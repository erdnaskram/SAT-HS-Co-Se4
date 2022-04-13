package DP1_Composite_Pattern;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kompositum extends Komponente{
    private final String abteilungsname;
    private Blatt abteilungsleitung;
    private final List<Komponente> komponenten;

    public Kompositum(@NotNull Blatt abteilungsleitung, String abteilungsname) {
        this.abteilungsname = abteilungsname;
        setAbteilungsleitung(abteilungsleitung);
        komponenten = new ArrayList<>();
    }

    public void addMitarbeiter(@NotNull Blatt komponente) {
        komponente.setAbteilung(abteilungsname);
        komponenten.add(komponente);
    }

    public void addAbteilung(@NotNull Kompositum komponente) {
        komponenten.add(komponente);
    }

    public void deleteKomponent(Komponente komponente) {
        komponenten.remove(komponente);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(abteilungsleitung.toString()).append("\n");
        sb.append("Zugeordnete Mitarbeiter:").append("\n");
        sb.append("[").append("\n");

        komponenten.forEach(k -> {
            if(k.getClass()==Kompositum.class)
                Arrays.stream(k.toString()
                        .split("\n"))
                        .forEach(s -> sb.append(s = "\t" + s + "\n"));
            else
                sb.append(k).append("\n\n");
        });

        sb.append("]");
        return sb.toString();
    }

    public Blatt getAbteilungsleitung() {
        return abteilungsleitung;
    }

    public void setAbteilungsleitung(Blatt abteilungsleitung) {
        if (this.abteilungsleitung != null) {
            this.abteilungsleitung.setAbteilung("#nicht zugeordnet");
            this.abteilungsleitung.setIstVorgesetzter(false);
        }
        this.abteilungsleitung = abteilungsleitung;
        abteilungsleitung.setAbteilung(abteilungsname);
        abteilungsleitung.setIstVorgesetzter(true);
    }

    public List<Komponente> getkomponenten() {
        return komponenten;
    }
}
