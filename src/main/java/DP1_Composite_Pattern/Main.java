package DP1_Composite_Pattern;

public class Main {
    public static void main(String[] args) {
        Blatt reichEgon = new Blatt("Reich","Egon","Geschäftsführer", (float) 40239.03);
        Kompositum management = new Kompositum(reichEgon, "Management");
    }
}
