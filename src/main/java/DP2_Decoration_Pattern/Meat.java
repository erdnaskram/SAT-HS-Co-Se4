package DP2_Decoration_Pattern;

public class Meat extends Toppings {
    @Override
    public String topping() {
        return getClass().getSimpleName();
    }

    @Override
    public double getCost() {
        return 0.6;
    }
}
