package com.example.sabari_ex7;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Read extends AppCompatActivity {
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 2;
    private TextView fileContentsTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read);

        fileContentsTextView = findViewById(R.id.content);
// Request the READ_EXTERNAL_STORAGE permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);
        } else {
// Permission already granted, perform file reading
            Button readButton = findViewById(R.id.read2);
            readButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText fileNameEditText = findViewById(R.id.name);
                    String fileName = fileNameEditText.getText().toString();
                    readFileFromSDCard(fileName);
                }
            });
        }
    }

    private void readFileFromSDCard(String fileName) {
        if (isExternalStorageReadable()) {
//            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File(getExternalFilesDir(null), "ex7"); // Change to your directory
//            File directory = new File(sdCard.getAbsolutePath() + "/ex7"); // Change to your directory
            File file = new File(directory, fileName + ".txt");
            if (file.exists()) {
                try {
                    // Log the absolute path
                    Log.d("FilePath", "Absolute Path: " + file.getAbsolutePath());
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    StringBuilder text = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        text.append(line);
                        text.append('\n');
                    }
                    br.close();
                    fileContentsTextView.setText(text.toString());
                } catch (IOException e) {
                    Log.e("FileReadError", "Error reading file on SD card: " + e.getMessage());
                    fileContentsTextView.setText("Error reading file.");
                }
            } else {
                fileContentsTextView.setText("File not found.");
            }
        } else {
            fileContentsTextView.setText("SD card is not available for reading.");
        }
    }

    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }
}