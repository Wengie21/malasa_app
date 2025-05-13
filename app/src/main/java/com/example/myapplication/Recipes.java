package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.Arrays;
import java.util.List;

public class Recipes extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);



        // Initialize BottomNavigationView and set listener
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                // Navigate to Home Activity
                Intent intent = new Intent(Recipes.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.navigation_recipes) {
                // Stay in Recipes Activity
                return true;
            } else {
                return false;
            }
        });

        // Set up click listeners for the CardViews (you can add more cards)
        setupCardViewClickListeners();
    }

    private void setupCardViewClickListeners() {
        findViewById(R.id.adoboCard).setOnClickListener(v -> {
            Intent intent = new Intent(Recipes.this, Adobo.class);
            startActivity(intent);
        });

        // Caldereta Card
        findViewById(R.id.calderetaCard).setOnClickListener(v -> {
            // Navigate to the Caldereta Activity
            Intent intent = new Intent(Recipes.this, Caldereta.class);
            startActivity(intent);
        });

        // Menudo Card
        findViewById(R.id.menudoCard).setOnClickListener(v -> {
            // Navigate to the Menudo Activity
            Intent intent = new Intent(Recipes.this, Menudo.class);
            startActivity(intent);
        });
        findViewById(R.id.LaingCard).setOnClickListener(v -> {
            Intent intent = new Intent(Recipes.this, Laing.class);
            startActivity(intent);
        });
        findViewById(R.id.PinakbetCard).setOnClickListener(v -> {
            Intent intent = new Intent(Recipes.this, Pinakbet.class);
            startActivity(intent);
        });
        findViewById(R.id.BicolExpressCard).setOnClickListener(v -> {
            Intent intent = new Intent(Recipes.this, BicolExpress.class);
            startActivity(intent);
        });
        findViewById(R.id.SinigangCard).setOnClickListener(v -> {
            Intent intent = new Intent(Recipes.this, Sinigang.class);
            startActivity(intent);
        });
        findViewById(R.id.TinolaCard).setOnClickListener(v -> {
            Intent intent = new Intent(Recipes.this, Tinola.class);
            startActivity(intent);
        });
        findViewById(R.id.KareKareCard).setOnClickListener(v -> {
            Intent intent = new Intent(Recipes.this, KareKare.class);
            startActivity(intent);
        });
    }
}
