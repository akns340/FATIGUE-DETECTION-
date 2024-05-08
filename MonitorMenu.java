package com.example.hardik.automateddriverdrowsinesscontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MonitorMenu extends Fragment {
    Button b; // Changed MagicButton to Button
    SeekBar s;
    TextView ttv;
    private String key_2 = "Hardik's project";
    private String key_4 = "sensitivity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_monitor_menu, container, false);

        b = (Button) root.findViewById(R.id.magic_button); // Changed type to Button
        s = (SeekBar) root.findViewById(R.id.seekBar2);
        ttv = (TextView) root.findViewById(R.id.textView21);

        // Set initial text for the sleep sensitivity TextView
        updateSensitivityText(s.getProgress());

        // SeekBar listener to update the sensitivity text
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateSensitivityText(progress);
            }
        });

        // Set click listener for the magic button (now a regular Button)
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the FaceTrackerActivity with necessary data
                Intent i = new Intent(getActivity(), FaceTrackerActivity.class);
                i.putExtra(key_4, String.valueOf(s.getProgress()));
                i.putExtra(key_2, String.valueOf(System.currentTimeMillis())); // Using current time as a placeholder
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                getActivity().finish();
            }
        });

        // Set click listener for the statistics button
        Button statisticsButton = root.findViewById(R.id.statisticsButton);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the StatisticsActivity
                Intent intent = new Intent(getActivity(), StatisticsActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    // Update sleep sensitivity text based on SeekBar progress
    private void updateSensitivityText(int progress) {
        String sensitivity;
        switch (progress) {
            case 0:
                sensitivity = "0.5 seconds";
                break;
            case 1:
                sensitivity = "0.75 seconds";
                break;
            case 2:
                sensitivity = "1 second";
                break;
            case 3:
                sensitivity = "1.25 seconds";
                break;
            case 4:
                sensitivity = "1.5 seconds";
                break;
            case 5:
                sensitivity = "1.75 seconds";
                break;
            case 6:
                sensitivity = "2 seconds";
                break;
            case 7:
                sensitivity = "2.25 seconds";
                break;
            default:
                sensitivity = "2.5 seconds";
        }
        ttv.setText(sensitivity);
    }
}
