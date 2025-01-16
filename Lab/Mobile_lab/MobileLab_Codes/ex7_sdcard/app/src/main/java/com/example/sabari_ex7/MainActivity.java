package com.example.sabari_ex7;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // Request the WRITE_EXTERNAL_STORAGE permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            // Permission already granted, perform file operations
            Button button = findViewById(R.id.write);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText et1 = findViewById(R.id.et1);
                    String file = et1.getText().toString();
                    EditText et2 = findViewById(R.id.et2);
                    String content = et2.getText().toString();
                    createAndWriteFileToSDCard(file,content);
                }
            });
        }

        Button read = findViewById(R.id.read1);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Read.class);
                startActivity(intent);
            }
        });
    }

    private void createAndWriteFileToSDCard(String fileName,String fileContent) {
        // Check if external storage is available
        if (isExternalStorageWritable()) {
//            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File(getExternalFilesDir(null), "ex7");
//            File directory = new File(sdCard.getAbsolutePath() + "/ex7");

            // Change to your desired directory
            directory.mkdirs();
            File file = new File(directory, fileName+".txt"); // Change the file name as needed
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(fileContent.getBytes());
                fos.close();
                Toast.makeText(this, "File created and written to SD card", Toast.LENGTH_SHORT).show();
                // Log the absolute path
                Log.d("FilePath", "Absolute Path: " + file.getAbsolutePath());
            } catch (IOException e) {
                Log.e("FileWriteError", "Error writing to file on SD card: " + e.getMessage());
            }
        } else {
            Toast.makeText(this, "SD card is not available for writing.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}