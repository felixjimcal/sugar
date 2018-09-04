package com.zarpa.felix.sugar;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Created by ${Felix} on ${16//11/2017}.
 */
public class CalculatorShould {

    @Test
    public void TotalOfSugarIs100()
    {
        Calculator calculator = new Calculator();

        int total_sugar = calculator.calculate(5,100,33);

        Assert.assertThat(total_sugar, is(0));
    }
}
