package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 imageSlider;
    private Handler handler;
    private int currentPage = 0;
    private final int delay = 3000;
    private final int numPages = 5;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ViewPager (Image Slider)
        imageSlider = findViewById(R.id.imageSlider);

        // Image list for the slider
        List<Integer> imageList = Arrays.asList(
                R.drawable.adobo,
                R.drawable.bicolexpress,
                R.drawable.dinuguan,
                R.drawable.sinigang,
                R.drawable.caldereta
        );

        // Set the adapter for the image slider
        ImageSlideAdapter adapter = new ImageSlideAdapter(this, imageList);
        imageSlider.setAdapter(adapter);

        // Initialize the handler for auto sliding
        handler = new Handler();
        startAutoSlide();

        // Initialize BottomNavigationView and set listener
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                // Handle home navigation
                return true;
            } else if (item.getItemId() == R.id.navigation_recipes) {
                // Handle recipes navigation
                Intent intent = new Intent(MainActivity.this, Recipes.class);
                startActivity(intent);  // Go to Recipes Activity
                return true;
            } else {
                return false;
            }
        });

        // Set up click listeners for the CardViews
        setupCardViewClickListeners();
    }

    private void setupCardViewClickListeners() {
        // Chicken Adobo Card
        findViewById(R.id.adoboCard).setOnClickListener(v -> {
            // Navigate to the Adobo Activity
            Intent intent = new Intent(MainActivity.this, Adobo.class);
            startActivity(intent);
        });

        // Caldereta Card
        findViewById(R.id.calderetaCard).setOnClickListener(v -> {
            // Navigate to the Caldereta Activity
            Intent intent = new Intent(MainActivity.this, Caldereta.class);
            startActivity(intent);
        });

        // Menudo Card
        findViewById(R.id.menudoCard).setOnClickListener(v -> {
            // Navigate to the Menudo Activity
            Intent intent = new Intent(MainActivity.this, Menudo.class);
            startActivity(intent);
        });
    }

    private void startAutoSlide() {
        Runnable autoSlideRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == numPages) {
                    currentPage = 0;
                }
                imageSlider.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, delay);
            }
        };

        handler.postDelayed(autoSlideRunnable, delay);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAutoSlide();
    }
}
