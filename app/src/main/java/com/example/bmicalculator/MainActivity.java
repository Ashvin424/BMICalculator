package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText weightInput, heightInput;
    private Button calculateBtn;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the UI elements
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        calculateBtn = findViewById(R.id.calculateBtn);
        resultView = findViewById(R.id.resultView);

        // Set the button click listener
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        // Get input from the user
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();

        // Check if the input is valid
        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            resultView.setText("Please enter valid weight and height");
            return;
        }

        try {
            // Parse the input into floats
            float weight = Float.parseFloat(weightStr);
            float heightInCm = Float.parseFloat(heightStr);

            // Check if the inputs are positive
            if (weight <= 0 || heightInCm <= 0) {
                resultView.setText("Weight and height must be positive numbers.");
                return;
            }

            // Convert height from cm to meters
            float heightInMeters = heightInCm / 100;

            // Calculate BMI
            float bmi = weight / (heightInMeters * heightInMeters);

            // Determine the BMI category and provide advice
            String bmiCategory;
            String advice;

            if (bmi < 18.5) {
                bmiCategory = "Underweight";
                advice = "You should consider gaining weight for a healthier range.";
            } else if (bmi >= 18.5 && bmi < 24.9) {
                bmiCategory = "Normal weight";
                advice = "You are fit according to your weight. Keep maintaining your healthy lifestyle!";
            } else if (bmi >= 25 && bmi < 29.9) {
                bmiCategory = "Overweight";
                advice = "You should consider losing weight for better health.";
            } else {
                bmiCategory = "Obese";
                advice = "It's recommended to lose weight for your well-being.";
            }

            // Display the result with BMI category and advice
            resultView.setText(String.format("Your BMI: %.2f\nCategory: %s\nAdvice: %s", bmi, bmiCategory, advice));

        } catch (NumberFormatException e) {
            resultView.setText("Invalid input format. Please enter numeric values.");
        }
    }
    
}
