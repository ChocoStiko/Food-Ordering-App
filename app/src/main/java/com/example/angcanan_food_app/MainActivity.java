package com.example.angcanan_food_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txtTotal;
    Button btnCheckout;
    Button btnClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, 0, 0, 0);
            return insets;
        });


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        txtTotal = (TextView) findViewById(R.id.total);
        btnCheckout = (Button) findViewById(R.id.checkoutbutton);
        btnClear = (Button) findViewById(R.id.Clearbutton);





        List<foods> items = new ArrayList<>();
        items.add(new foods("Yumburger Solo",46, R.drawable.a));
        items.add(new foods("Spicy Chicken Joy w/ \nJolly Spaghetti Solo", 156, R.drawable.b));
        items.add(new foods("Jolly Spaghetti Solo", 75, R.drawable.c));
        items.add(new foods("Palabok Solo", 131, R.drawable.d));
        items.add(new foods("Cheesy Classic Jolly \n Hotdog Solo", 87, R.drawable.e));
        items.add(new foods("Peach Mango Pie", 46, R.drawable.f));
        items.add(new foods("Chocolate Sundae", 54, R.drawable.g));
        items.add(new foods("Jolly Crispy Fries", 60, R.drawable.h));
        items.add(new foods("Mashed Potato", 60, R.drawable.i));
        items.add(new foods("Coke Float", 66, R.drawable.j));

        Adapter adapter = new Adapter(items, total -> {
            txtTotal.setText("Total: ₱" + String.format(Locale.US, "%.2f", total));
        });

        btnClear.setOnClickListener(v -> {
            adapter.clearButton();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnCheckout.setOnClickListener(v ->{

            String total = txtTotal.getText().toString();

            if (total.equals("Total: ₱0.00")){
                return;
            }

            Intent intent = new Intent(MainActivity.this, CheckoutActivity.class);

            StringBuilder orderInfo = new StringBuilder();

            for (foods f: items) {
                if (f.getQuantity() > 0) {
                    orderInfo.append(f.getName()).append(" x");
                    orderInfo.append(f.getQuantity()).append(" = ₱");
                    orderInfo.append(f.getPrice()).append("\n");
                }
            }
            
            intent.putExtra("totalAmount", total);
            intent.putExtra("orderInfo", orderInfo.toString());
            startActivity(intent);
        });
    }
}