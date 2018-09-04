package com.zarpa.felix.sugar;

import org.junit.Assert;
import org.junit.Test;

import cat.zarpa.felix.sugar.Transformator;
import cat.zarpa.felix.sugar.Calculator;

import static org.hamcrest.core.Is.is;

/**
 * Created by ${Felix} on ${16//11/2017}.
 */
public class PrintTotalSugarFeature {

    @Test
    public void PrintTotalOfSugarInAProcessedProduct()
    {
        double sugar_example, example_quantity, total_product;

        // Grams
        sugar_example = Transformator.transformUnitToBaseUnit(50, 10);

        // Mililiters
        example_quantity = Transformator.transformUnitToBaseUnit(100, 0);

        // Centiliters
        total_product = Transformator.transformUnitToBaseUnit(33, 1);

        Calculator calculator = new Calculator();
        double total_sugar = calculator.calculate(sugar_example, example_quantity, total_product);

        Assert.assertThat(total_sugar, is(165.0));
    }
}
