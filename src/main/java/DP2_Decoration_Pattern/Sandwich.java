package DP2_Decoration_Pattern;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Sandwich implements Komponente{
    static final DecimalFormat df = new DecimalFormat("0.00");
    private final List<Toppings> toppings;

    public Sandwich() {
        toppings = new ArrayList<>();
        toppings.add(new Bread());
    }

    @Override
    public String topping() {
        StringBuilder sb = new StringBuilder();
        toppings.forEach(topping -> {
            if (sb.isEmpty())
                sb.append(topping.topping());
            else
                sb.append(" with ").append(topping.topping());
        });
        return sb.toString();
    }

    @Override
    public double getCost() {
        double costs = 0;
        for (Toppings topping : toppings) {
            costs += topping.getCost();
        }
        return costs;
    }

    public void addTopping(Toppings topping) {
        toppings.add(topping);
    }

    public void removeTopping(Toppings topping) {
        toppings.remove(topping);
    }

    @Override
    public String toString() {
        return topping() + " Price: " + df.format(getCost()) + " €";
    }
}
