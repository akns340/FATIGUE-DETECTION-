package com.example.hardik.automateddriverdrowsinesscontrol;
// StatisticsActivity.java
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        // Retrieve sleep statistics (replace with actual retrieval code)
        int averageDurationHours = 7;
        int averageDurationMinutes = 30;
        int percentageAsleep = 70; // Example percentage
        String predictedTime = "10:30 PM"; // Example predicted time

        // Update UI components with retrieved statistics
        TextView averageDurationValueTextView = findViewById(R.id.averageDurationValueTextView);
        averageDurationValueTextView.setText(String.format("%d hours %d minutes", averageDurationHours, averageDurationMinutes));

        ProgressBar percentageProgressBar = findViewById(R.id.percentageProgressBar);
        percentageProgressBar.setProgress(percentageAsleep);

        TextView predictedTimeValueTextView = findViewById(R.id.predictedTimeValueTextView);
        predictedTimeValueTextView.setText(predictedTime);
    }
}
