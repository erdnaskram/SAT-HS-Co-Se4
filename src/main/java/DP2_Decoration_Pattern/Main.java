package DP2_Decoration_Pattern;

public class Main {
    public static void main(String[] args) {
        Sandwich langweiligesSandwich = new Sandwich();
        Sandwich veggieSandwich = new Sandwich();
        Sandwich hamburgerSandwich = new Sandwich();
        Sandwich normalesSandwich = new Sandwich();
        Sandwich doppelKaeseSandwich = new Sandwich();
        Sandwich premiumSandwich = new Sandwich();

        veggieSandwich.addTopping(new Cheese());

        hamburgerSandwich.addTopping(new Cheese());
        hamburgerSandwich.addTopping(new Meat());

        normalesSandwich.addTopping(new Cheese());
        normalesSandwich.addTopping(new Meat());
        normalesSandwich.addTopping(new Pickles());

        doppelKaeseSandwich.addTopping(new Cheese());
        doppelKaeseSandwich.addTopping(new Meat());
        doppelKaeseSandwich.addTopping(new Pickles());
        doppelKaeseSandwich.addTopping(new Cheese());

        premiumSandwich.addTopping(new KoberindFilet());
        premiumSandwich.addTopping(new Cheese());
        premiumSandwich.addTopping(new Meat());
        premiumSandwich.addTopping(new Pickles());

        System.out.println(langweiligesSandwich);
        System.out.println(veggieSandwich);
        System.out.println(hamburgerSandwich);
        System.out.println(normalesSandwich);
        System.out.println(doppelKaeseSandwich);
        System.out.println(premiumSandwich);
    }
}
