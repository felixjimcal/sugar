package cat.zarpa.felix.sugar;

/**
 * Created by ${Felix} on ${16//11/2017}.
 */
public class Calculator {
    public static double calculate( double product_sugar_quantity, double product_exampe_quantity, double product_total_quantity) {
        double total_sugar;

        total_sugar = product_total_quantity / product_exampe_quantity * product_sugar_quantity;

        return total_sugar;
    }
}
