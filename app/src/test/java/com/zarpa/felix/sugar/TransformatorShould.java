package com.zarpa.felix.sugar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import cat.zarpa.felix.sugar.Transformator;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.hamcrest.core.Is.is;

/**
 * Created by ${Felix} on ${16//11/2017}.
 */
@RunWith(JUnitParamsRunner.class)
public class TransformatorShould {
    @Test
    @Parameters({
            "1, 0, 0.001", // Mililiters
            "1000, 0, 1", // Mililiters
            "1, 1, 0.01", // Centiliters
            "1000, 1, 10" // Centiliters
    })
    public void TransformUnitToBaseUnit(double quantity, int id, double expected)
    {
        double base_unit = Transformator.transformUnitToBaseUnit(quantity, id);
        Assert.assertThat(expected, is(base_unit));
    }

}
