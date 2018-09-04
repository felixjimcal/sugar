package cat.zarpa.felix.sugar;

/**
 * Created by ${Felix} on ${16//11/2017}.
 */
public class Calculator {
    public double calculate(double product_total_quantity, double product_exampe_quantity, double product_sugar_quantity) {
        double total_sugar = 0;

        total_sugar = product_total_quantity / product_exampe_quantity * product_sugar_quantity;

        return total_sugar;
    }
}
