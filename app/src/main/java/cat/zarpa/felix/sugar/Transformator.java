package cat.zarpa.felix.sugar;

/**
 * Created by ${Felix} on ${16//11/2017}.
 */
public class Transformator {
    public static double transformUnitToBaseUnit(double value, int unit) {

        switch (unit) {
            case 0:
                value = value / 1000;
                break;

            case 1:
                value = value / 100;
                break;

            case 2:
                value = value / 10;
                break;

            // case 3 liters

            case 4:
                value = value * 10;
                break;

            case 5:
                value = value * 100;
                break;

            case 6:
                value = value * 1000;
                break;

            case 7:
                value = value / 1000;
                break;

            case 8:
                value = value / 100;
                break;

            case 9:
                value = value / 10;
                break;

            // case 10 grams

            case 11:
                value = value * 10;
                break;

            case 12:
                value = value * 100;
                break;

            case 13:
                value = value * 1000;
                break;

            default:
                break;
        }
        return value;
    }
}
