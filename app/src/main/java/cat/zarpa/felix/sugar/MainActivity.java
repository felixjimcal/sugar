package cat.zarpa.felix.sugar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.zarpa.felix.sugar.R;

public class MainActivity extends Activity {

    private TextView total_text, text_message;
    private EditText txt_sugar_quantity, txt_sugar_example, txt_total_product;
    private Button btn_calculate;
    private Spinner unit_quantity, unit_example, unit_product;
    private Switch switch_measuring;
    private ArrayAdapter<Metric> metric_parameters;
    private ArrayAdapter<Imperial> imperial_parameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        LoadWidgets();

        metric_parameters = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Metric.values());
        imperial_parameters = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Imperial.values());

        SetMeasuring(metric_parameters);

        switch_measuring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SetMeasuring(imperial_parameters);

                } else {
                    SetMeasuring(metric_parameters);
                }
            }
        });

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double sugar, example, product, total_sugar, sugar_cubes;
                    int sugar_unit, example_unit, product_unit;

                    // Collect data
                    // ----------------------------------------------------------------
                    sugar = Double.parseDouble(txt_sugar_quantity.getText().toString());
                    sugar_unit = Receptor.GetUnit(switch_measuring.isChecked(), unit_quantity);

                    example = Double.parseDouble(txt_sugar_example.getText().toString());
                    example_unit = Receptor.GetUnit(switch_measuring.isChecked(), unit_example);

                    product = Double.parseDouble(txt_total_product.getText().toString());
                    product_unit = Receptor.GetUnit(switch_measuring.isChecked(), unit_product);

                    // Transform to base unit (grams, liters)
                    // ----------------------------------------------------------------
                    sugar = Transformator.transformUnitToBaseUnit(sugar, sugar_unit);
                    example = Transformator.transformUnitToBaseUnit(example, example_unit);
                    product = Transformator.transformUnitToBaseUnit(product, product_unit);

                    // Do the maths
                    // ----------------------------------------------------------------
                    total_sugar = Calculator.calculate_sugar(sugar, example, product);
                    sugar_cubes = Calculator.calculate_cubes(total_sugar);

                    // Show the text
                    // ----------------------------------------------------------------
                    @SuppressLint("DefaultLocale")
                    String text = getResources().getString(R.string.hint_total_sugar) + ": " + String.format("%.1f", total_sugar) + " g " +
                            getResources().getString(R.string.total_terrones) + ": " + String.format("%.1f", sugar_cubes);

                    total_text.setText(text);

                    text_message.setText(getResources().getString(R.string.message));

                } catch (NumberFormatException ex) {
                    String message = getResources().getString(R.string.wrong_number_input);
                    ShowToast(message);
                }
            }
        });
    }

    public <E> void SetMeasuring(ArrayAdapter<E> parameters) {
        unit_quantity.setAdapter(parameters);
        unit_example.setAdapter(parameters);
        unit_product.setAdapter(parameters);
    }

    public void ShowToast(String text) {
        Context context = this;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void LoadWidgets() {
        txt_sugar_quantity = findViewById(R.id.edit_txt_sugar_quantity);
        txt_sugar_example = findViewById(R.id.edit_txt_sugar_example);
        txt_total_product = findViewById(R.id.edit_txt_total_product);

        unit_quantity = findViewById(R.id.spinner_sugar);
        unit_example = findViewById(R.id.spinner_sugar_example);
        unit_product = findViewById(R.id.spinner_total_product);

        switch_measuring = findViewById(R.id.switch_measuring);
        total_text = findViewById(R.id.txt_total);
        text_message = findViewById(R.id.txt_message);
        btn_calculate = findViewById(R.id.button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                OpenInstagramProfile();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void OpenInstagramProfile() {
        Uri uri = Uri.parse("https://www.instagram.com/felixjcalvo/");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/xxx")));
        }
    }
}
