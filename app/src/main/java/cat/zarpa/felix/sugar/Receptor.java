package cat.zarpa.felix.sugar;

import android.widget.Spinner;

/**
 * Created by ${Felix} on ${16//11/2017}.
 */
class Receptor {

    public static int GetUnit(boolean isImperial, Spinner unit_quantity) {
        int id = 0;

        if (isImperial)
        {
            id = ((Imperial) unit_quantity.getSelectedItem()).getId();

        }
        else {
            id = ((Metric) unit_quantity.getSelectedItem()).getId();
        }

        return id;
    }
}
