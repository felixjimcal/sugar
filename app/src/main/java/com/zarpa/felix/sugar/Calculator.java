package com.zarpa.felix.sugar;

/**
 * Created by ${Felix} on ${16//11/2017}.
 */
class Calculator {
    public int calculate(int product_total_quantity, int product_exampe_quantity, int product_sugar_quantity) {
        int total_sugar = 0;

        total_sugar = product_total_quantity / product_exampe_quantity * product_sugar_quantity;

        return total_sugar;
    }
}
