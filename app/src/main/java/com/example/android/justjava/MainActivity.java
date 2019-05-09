/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */


     int numberOfCoffees=1;
    boolean hasWhippedCream, hasChocolate;
    String customerName,chocolate,whippedCream;


    public void Increment(View view) {

        if(numberOfCoffees==100)
        {
            Toast.makeText(this,getString(R.string.too_many_coffees), Toast.LENGTH_SHORT).show();

            return;
        }

        else {
            numberOfCoffees++;
            displayQuantity(numberOfCoffees);
        }
    }

    public void Decrement(View view) {

        if(numberOfCoffees==1)
        {

            Toast.makeText(this,getString(R.string.too_few_coffees), Toast.LENGTH_SHORT).show();

            return;
        }

        else {

            numberOfCoffees--;
            displayQuantity(numberOfCoffees);
        }
    }


    public void submitOrder(View view) {

        CheckBox whippedCreamCheckbox = findViewById(R.id.whipped_cream_checkbox);

        hasWhippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = findViewById(R.id.chocolate_checkbox);

        hasChocolate = chocolateCheckbox.isChecked();

        EditText customerEditText = findViewById(R.id.customer_name_et);

        customerName = customerEditText.getText().toString();

        if(hasChocolate) {
            chocolate = getString(R.string.boolean_true);
        }
        else {
            chocolate = getString(R.string.boolean_false);
        }
        if(hasWhippedCream) {
            whippedCream = getString(R.string.boolean_true);
        }
        else {
            whippedCream = getString(R.string.boolean_false);
        }



        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject,customerName));
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }



    private String createOrderSummary() {



            return getString(R.string.order_summary_name,customerName) +"\n" + getString(R.string.order_summary_whipped_cream,whippedCream)  +"\n"+ getString(R.string.order_summary_chocolate,chocolate) + "\n" + getString(R.string.order_summary_quantity,numberOfCoffees) + "\n" + getString(R.string.order_summary_price,calculatePrice(numberOfCoffees, 5)) + "\n" + getString(R.string.thank_you);

    }




    private int calculatePrice(int quantity , int pricePerCup) {

        if(hasWhippedCream)
        {
            pricePerCup+=2;
        }

        if(hasChocolate)
        {
            pricePerCup+=1;
        }


        return quantity*pricePerCup;



    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView =  findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }


}
