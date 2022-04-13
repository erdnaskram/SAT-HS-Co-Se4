package DP1_Composite_Pattern;

public class Blatt extends Komponente {
    private static int counter = 100000;
    private String nachname;
    private String vorname;
    private final int personalnummer;
    private String abteilung;
    private String zustaendigkeit;
    private float gehalt;
    private boolean istVorgesetzter = false;

    public Blatt(String nachname, String vorname, String zustaendigkeit, float gehalt) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.zustaendigkeit = zustaendigkeit;
        this.gehalt = gehalt;
        personalnummer = ++counter;
    }

    @Override
    public String toString() {
        if (istVorgesetzter)
            return "Name: " + nachname.toUpperCase() + " " + vorname +
                    " Personalnummer: " + personalnummer +
                    " Abteilung: " + abteilung.toUpperCase() +
                    " Zuständigkeit: " + zustaendigkeit +
                    " Gehalt: " + gehalt;
        else
            return "Name: " + nachname + " " + vorname +
                    " Personalnummer: " + personalnummer +
                    " Abteilung: " + abteilung.toUpperCase() +
                    " Zuständigkeit: " + zustaendigkeit +
                    " Gehalt: " + gehalt;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public float getPersonalnummer() {
        return personalnummer;
    }

    public String getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(String abteilung) {
        this.abteilung = abteilung;
    }

    public String getZustaendigkeit() {
        return zustaendigkeit;
    }

    public void setZustaendigkeit(String zustaendigkeit) {
        this.zustaendigkeit = zustaendigkeit;
    }

    public float getGehalt() {
        return gehalt;
    }

    public void setGehalt(float gehalt) {
        this.gehalt = gehalt;
    }

    public boolean isIstVorgesetzter() {
        return istVorgesetzter;
    }

    public void setIstVorgesetzter(boolean istVorgesetzter) {
        this.istVorgesetzter = istVorgesetzter;
    }
}
