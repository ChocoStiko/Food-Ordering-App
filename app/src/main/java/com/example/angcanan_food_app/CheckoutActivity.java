package com.example.angcanan_food_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CheckoutActivity extends AppCompatActivity {


    Button btnOrder, btnCancel;
    TextView txtDetails, txtTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, 0, 0, 0);
            return insets;
        });

        btnOrder = (Button) findViewById(R.id.orderButton);
        btnCancel = (Button) findViewById(R.id.cancelButton);
        txtDetails = (TextView) findViewById(R.id.orderDetail);
        txtTotal = (TextView) findViewById((R.id.orderTotal));

        String orderInfo = getIntent().getStringExtra("orderInfo");
        String totalAmount = getIntent().getStringExtra("totalAmount");

        txtDetails.setText(orderInfo);
        txtTotal.setText(totalAmount);

        btnCancel.setOnClickListener(v ->{
            Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);

            startActivity(intent);
        });
    }

}