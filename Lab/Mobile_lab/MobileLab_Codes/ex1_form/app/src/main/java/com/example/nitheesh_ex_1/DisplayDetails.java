package com.example.nitheesh_ex_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nitheesh_ex_1.R;
public class DisplayDetails extends AppCompatActivity {

    @Override
    protected void onCreate( @Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
// Initialize TextViews to display details
        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        TextView phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        TextView addressTextView = (TextView) findViewById(R.id.addressTextView);
        TextView maritalStatusTextView = (TextView) findViewById(R.id.maritalStatusTextView);
        TextView ageTextView = (TextView) findViewById(R.id.ageTextView);
        TextView dobTextView = (TextView) findViewById(R.id.dobTextView);
        TextView genderTextView = (TextView) findViewById(R.id.genderTextView);
        TextView employerTextView = (TextView) findViewById(R.id.employerTextView);
        TextView employmentStatusTextView = (TextView) findViewById(R.id.employmentStatusTextView);
// Initialize more TextViews for other details
        TextView emergencyNameTextView =
                (TextView) findViewById(R.id.emergencyNameTextView);
        TextView emergencyRelationshipTextView =
                (TextView) findViewById(R.id.emergencyRelationshipTextView);
        TextView emergencyAddressTextView =
                (TextView) findViewById(R.id.emergencyAddressTextView);
        TextView emergencyPhoneTextView =
                (TextView) findViewById(R.id.emergencyPhoneTextView);
// Initialize more TextViews for other emergency contact details
// Get intent data
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String phone = intent.getStringExtra("phone");
            String address = intent.getStringExtra("address");
            String maritalStatus = intent.getStringExtra("maritalStatus");
            String age = intent.getStringExtra("age");
            String dob = intent.getStringExtra("dob");
            String gender = intent.getStringExtra("gender");
            String employer = intent.getStringExtra("employer");
            String employmentStatus = intent.getStringExtra("employmentStatus");
// Get more data for other TextViews
            String emergencyName = intent.getStringExtra("emergencyName");
            String emergencyRelationship =
                    intent.getStringExtra("emergencyRelationship");
            String emergencyAddress =
                    intent.getStringExtra("emergencyAddress");
            String emergencyPhone = intent.getStringExtra("emergencyPhone");
// Get more data for other emergency contact TextViews
// Display data in TextViews
            nameTextView.setText("Name: " + name);
            phoneTextView.setText("Phone: " + phone);
            addressTextView.setText("Address: " + address);
            maritalStatusTextView.setText("Marital Status: " + maritalStatus);
            ageTextView.setText("Age: " + age);
            dobTextView.setText("Date of Birth: " + dob);
            genderTextView.setText("Gender: " + gender);
            employerTextView.setText("Employer: " + employer);
            employmentStatusTextView.setText("Employment Status: " +
                    employmentStatus);
// Display more data in other TextViews
            emergencyNameTextView.setText("Emergency Contact Name: " +
                    emergencyName);
            emergencyRelationshipTextView.setText("Emergency Contact Relationship: " + emergencyRelationship);
            emergencyAddressTextView.setText("Emergency Contact Address: " +
                    emergencyAddress);
            emergencyPhoneTextView.setText("Emergency Contact Phone: " +
                    emergencyPhone);
// Display more emergency contact data in other TextViews
        }
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // This will simulate pressing the hardware back button
            }
        });
    }
}
