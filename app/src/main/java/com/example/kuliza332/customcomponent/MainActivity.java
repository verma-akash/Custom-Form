package com.example.kuliza332.customcomponent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements CustomForm.CustomFormListener {

    private CustomForm customForm;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customForm = findViewById(R.id.custom_form);
        button = findViewById(R.id.button);

        //add editText in sequence and starting from 0....so on
        customForm.addEditTextView(this, 0, "City", "City");
        customForm.addEditTextView(this, 0, "State", "State");
        customForm.addEditTextView(this, 1, "Age", "Age");
        customForm.addEditTextView(this, 2, "Name", "Name");
        customForm.addEditTextView(this, 2, "Address", "Address");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customForm.generateTextMap();
            }
        });
    }

    @Override
    public void returnText(HashMap<String, String> resultTextMap) {
        Toast.makeText(this, resultTextMap.get("State"), Toast.LENGTH_SHORT).show();
    }
}
