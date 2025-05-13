package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class BicolExpress extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private TextToSpeech tts;
    private TextView ingredientsTextView, instructionsTextView;
    private Button speakPauseButton;
    private boolean isSpeaking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bicol_express);

        // Initialize views
        ingredientsTextView = findViewById(R.id.ingredients);
        instructionsTextView = findViewById(R.id.instructions);
        speakPauseButton = findViewById(R.id.floatingButton);

        // Initialize TextToSpeech
        tts = new TextToSpeech(getApplicationContext(), status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });

        // Speak/Pause button behavior
        speakPauseButton.setOnClickListener(v -> {
            if (!isSpeaking) {
                String ingredients = ingredientsTextView.getText().toString();
                String instructions = instructionsTextView.getText().toString();
                String textToRead = "Ingredients: " + ingredients + ". Instructions: " + instructions;
                tts.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null, null);
                isSpeaking = true;
                speakPauseButton.setText("Stop");
            } else {
                tts.stop(); // Acts like a pause
                isSpeaking = false;
                speakPauseButton.setText("Speak");
            }
        });

        // Bottom Navigation setup
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.navigation_home) {
                    startActivity(new Intent(BicolExpress.this, MainActivity.class));
                    return true;
                } else if (item.getItemId() == R.id.navigation_recipes) {
                    startActivity(new Intent(BicolExpress.this, Recipes.class));
                    return true;
                }
                return false;
            });

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onPause() {
        if (tts != null && isSpeaking) {
            tts.stop();
            isSpeaking = false;
            speakPauseButton.setText("Speak");
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
