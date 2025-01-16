package com.example.nitheesh_ex_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editName, editPhone, editAddress, editAge, editEmergencyName,
            editEmergencyRelationship, editEmergencyAddress, editEmergencyPhone,
            editEmployer, editText;
    private Spinner spinnerMaritalStatus;
    private RadioGroup radioGroupGender;
    private RadioButton radioMale, radioFemale, radioOther;
    private CheckBox checkFullTime, checkPartTime;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        editAddress = findViewById(R.id.editAddress);
        editAge = findViewById(R.id.editAge);
        editEmergencyName = findViewById(R.id.editEmergencyName);
        editEmergencyRelationship = findViewById(R.id.editEmergencyRelationship);
        editEmergencyAddress = findViewById(R.id.editEmergencyAddress);
        editEmergencyPhone = findViewById(R.id.editEmergencyPhone);
        editEmployer = findViewById(R.id.editEmployer);
        spinnerMaritalStatus = findViewById(R.id.spinnerMaritalStatus);

        // Handle date of birth from DatePicker
        DatePicker datePicker = findViewById(R.id.datePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;  // Month is zero-based, so add 1
        int year = datePicker.getYear();
        String dob = day + "/" + month + "/" + year;

        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioOther = findViewById(R.id.radioOther);
        checkFullTime = findViewById(R.id.checkFullTime);
        checkPartTime = findViewById(R.id.checkPartTime);
        ArrayAdapter<CharSequence> maritalStatusAdapter =
                ArrayAdapter.createFromResource(this, R.array.marital_status_array, android.R.layout.simple_spinner_item);
        maritalStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaritalStatus.setAdapter(maritalStatusAdapter);
        // ... (initialize other views)
        Button submitButton = findViewById(R.id.submitButton);
        Button resetButton = findViewById(R.id.resetButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from views
                String name = editName.getText().toString();
                String phone = editPhone.getText().toString();
                String address = editAddress.getText().toString();
                String age = editAge.getText().toString();
                String maritalStatus = spinnerMaritalStatus.getSelectedItem().toString();
                String employer = editEmployer.getText().toString();

                // Handle gender from radio buttons
                String gender;
                int selectedRadioButtonId = radioGroupGender.getCheckedRadioButtonId();
                if (selectedRadioButtonId == R.id.radioMale) {
                    gender = "Male";
                } else if (selectedRadioButtonId == R.id.radioFemale) {
                    gender = "Female";
                } else if (selectedRadioButtonId == R.id.radioOther) {
                    gender = "Other";
                } else {
                    gender = "Not specified";  // Default value if no radio button is selected
                }

                StringBuilder employmentStatusBuilder = new StringBuilder();
                if (checkFullTime.isChecked()) {
                    employmentStatusBuilder.append("Full Time, ");
                }
                if (checkPartTime.isChecked()) {
                    employmentStatusBuilder.append("Part Time, ");
                }

                // Remove the trailing comma and space if the builder is not empty
                String employmentStatus = employmentStatusBuilder.toString();
                if (!employmentStatus.isEmpty()) {
                    employmentStatus = employmentStatus.substring(0, employmentStatus.length() - 2);
                } else {
                    employmentStatus = "Not specified";
                    // Default value if neither checkbox is checked
                }

                String emergencyName = editEmergencyName.getText().toString();
                String emergencyRelationship = editEmergencyRelationship.getText().toString();
                String emergencyAddress = editEmergencyAddress.getText().toString();
                String emergencyPhone = editEmergencyPhone.getText().toString();

                // Create an Intent to start the DisplayDetailsActivity
                Intent intent = new Intent(MainActivity.this, DisplayDetails.class);
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("address", address);
                intent.putExtra("age", age);
                intent.putExtra("dob", dob);
                intent.putExtra("gender", gender);
                intent.putExtra("maritalStatus", maritalStatus);
                intent.putExtra("employer", employer);
                intent.putExtra("employmentStatus", employmentStatus);
                intent.putExtra("emergencyName", emergencyName);
                intent.putExtra("emergencyRelationship", emergencyRelationship);
                intent.putExtra("emergencyAddress", emergencyAddress);
                intent.putExtra("emergencyPhone", emergencyPhone);

//                Put other values in the intent
                startActivity(intent);
            }
        });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all input fields
                editName.getText().clear();
                editPhone.getText().clear();
                editAddress.getText().clear();
                editAge.getText().clear();
                editEmployer.getText().clear();
                editEmergencyName.getText().clear();
                editEmergencyRelationship.getText().clear();
                editEmergencyAddress.getText().clear();
                editEmergencyPhone.getText().clear();

                // Reset Spinner to the first item
                spinnerMaritalStatus.setSelection(0);

                // Reset RadioGroup
                radioGroupGender.clearCheck();

                // Uncheck checkboxes
                checkFullTime.setChecked(false);
                checkPartTime.setChecked(false);

                // Clear DatePicker
                DatePicker datePicker = findViewById(R.id.datePicker);
                datePicker.updateDate(2000, 0, 1);  // Set a default date, adjust as needed
            }
        });


    }

}