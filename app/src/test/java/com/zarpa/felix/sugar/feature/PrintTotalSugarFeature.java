package com.zarpa.felix.sugar.feature;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Created by ${Felix} on ${16//11/2017}.
 */
public class PrintTotalSugarFeature {

    @Test
    public void PrintTotalOfSugarInAProcessedProduct()
    {
        int sugar_example = 5, example_quantity = 100, total_product = 33;
        Calculator calculator = new Calculator();

        int total_sugar = calculator.calculate(sugar_example, example_quantity, total_product);

        Assert.assertThat(total_sugar, is(100));
    }

}
