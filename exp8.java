package com.example.textchanger;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btnColor, btnFont, btnToast;
    boolean isBig = false;
    boolean isBold = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btnColor = findViewById(R.id.btnColor);
        btnFont = findViewById(R.id.btnFont);
        btnToast = findViewById(R.id.btnToast);

        // Change Text Color
        btnColor.setOnClickListener(v -> {
            textView.setTextColor(Color.RED);
        });

        // Change Font Size & Style
        btnFont.setOnClickListener(v -> {
            if (!isBig) {
                textView.setTextSize(32);
                textView.setTypeface(null, Typeface.BOLD);
                isBig = true;
                isBold = true;
            } else {
                textView.setTextSize(24);
                textView.setTypeface(null, Typeface.NORMAL);
                isBig = false;
                isBold = false;
            }
        });

        // Show Toast Message
        btnToast.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this,
                    "Button Clicked!",
                    Toast.LENGTH_SHORT).show();
        });
    }
}