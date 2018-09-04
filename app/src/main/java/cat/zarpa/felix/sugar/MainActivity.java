package cat.zarpa.felix.sugar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.zarpa.felix.sugar.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private TextView total_text, text_message;
    private EditText txt_sugar_quantity, txt_sugar_example, txt_total_product;
    private Button btn_calculate;
    private Spinner sugar_quantity, sugar_example, total_product;
    private Switch switch_measuring;
    private ArrayAdapter<String> metric, imperial;
    private List<String> metric_measuring = new ArrayList<>(), imperial_measuring = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        AddUnits();

        LoadWidgets();

        metric = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, metric_measuring);
        imperial = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, imperial_measuring);

        // Fill spinners
        SetMeasuring(metric);

        switch_measuring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SetMeasuring(imperial);
                    // is_metric = false;
                } else {
                    SetMeasuring(metric);
                    // is_metric = true;
                }
            }
        });

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double sugar, example, product, total_sugar, cubes;
                    int sugar_unit, example_unit, product_unit;

                    // Collect data
                    // ----------------------------------------------------------------
                    sugar = Double.parseDouble(txt_sugar_quantity.getText().toString());
                    sugar_unit = sugar_quantity.getSelectedItemPosition();

                    example = Double.parseDouble(txt_sugar_example.getText().toString());
                    example_unit = sugar_example.getSelectedItemPosition();

                    product = Double.parseDouble(txt_total_product.getText().toString());
                    product_unit = total_product.getSelectedItemPosition();

                    // Transform to base unit (grams , liters)
                    // ----------------------------------------------------------------
                    sugar = TransformUnitToBaseUnit(sugar, sugar_unit);
                    example = TransformUnitToBaseUnit(example, example_unit);
                    product = TransformUnitToBaseUnit(product, product_unit);

                    // Do the maths
                    total_sugar = product / example * sugar;

                    cubes = (total_sugar / 4);
                    @SuppressLint("DefaultLocale")
                    String text = getResources().getString(R.string.hint_total_sugar) + ": " + String.format("%.1f", total_sugar) + " g " +
                            getResources().getString(R.string.total_terrones) + ": " + String.format("%.1f", cubes);

                    total_text.setText(text);

                    text_message.setText(getResources().getString(R.string.message));

                } catch (Exception ex) {
                    ShowToast(ex.getMessage());

                }
            }
        });
    }

    private void AddUnits() {
        metric_measuring.add(getResources().getString(R.string.mililitros));
        metric_measuring.add(getResources().getString(R.string.centilitros));
        metric_measuring.add(getResources().getString(R.string.decilitros));
        metric_measuring.add(getResources().getString(R.string.litros));
        metric_measuring.add(getResources().getString(R.string.dekalitros));
        metric_measuring.add(getResources().getString(R.string.hectolitros));
        metric_measuring.add(getResources().getString(R.string.kilolitros));
        metric_measuring.add(getResources().getString(R.string.miligramo));
        metric_measuring.add(getResources().getString(R.string.centigramos));
        metric_measuring.add(getResources().getString(R.string.decigramos));
        metric_measuring.add(getResources().getString(R.string.gramos));
        metric_measuring.add(getResources().getString(R.string.dekagramos));
        metric_measuring.add(getResources().getString(R.string.hectogramos));
        metric_measuring.add(getResources().getString(R.string.kilogramos));

        imperial_measuring.add(getResources().getString(R.string.onza));
    }

    private double TransformUnitToBaseUnit(double value, int unit) {
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

    private void SetMeasuring(ArrayAdapter<String> units) {
        sugar_quantity.setAdapter(units);
        sugar_example.setAdapter(units);
        total_product.setAdapter(units);
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

        sugar_quantity = findViewById(R.id.spinner_sugar);
        sugar_example = findViewById(R.id.spinner_sugar_example);
        total_product = findViewById(R.id.spinner_total_product);

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
