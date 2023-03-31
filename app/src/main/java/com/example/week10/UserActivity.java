package com.example.week10;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private EditText firstNameET;
    private EditText lastNameET;
    private EditText emailET;
    private RadioGroup studyChoiceRG;
    private Button addUser;
    private Spinner avatarSpinner;

    private CheckBox kandiCB, diCB, tekniikanCB, uimaCB;
    private Context context;
    private ArrayList<String> degrees = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        firstNameET = findViewById(R.id.etunimiText);
        lastNameET = findViewById(R.id.sukunimiText);
        emailET = findViewById(R.id.SahkopostiText);
        studyChoiceRG = findViewById(R.id.rgUser);
        addUser = findViewById(R.id.addUserButton);

        Spinner avatarSpinner = findViewById(R.id.avatarSpinner);
        ArrayList<Integer> avatarList = new ArrayList<>();
        avatarList.add(R.drawable.avatar1);
        avatarList.add(R.drawable.avatar2);
        avatarList.add(R.drawable.avatar3);
        AvatarSpinnerAdapter adapter = new AvatarSpinnerAdapter(this, avatarList);
        avatarSpinner.setAdapter(adapter);


        CheckBox kandiCB = findViewById(R.id.kandidaatinTutkintoCheckBox);
        CheckBox diCB = findViewById(R.id.diTutkintoCheckBox);
        CheckBox tekniikanCB = findViewById(R.id.tekniikanTohtoriTutkintoCheckBox);
        CheckBox uimaCB = findViewById(R.id.uimamestariCheckBox);

        kandiCB.setOnCheckedChangeListener(this::onCheckedChanged);
        diCB.setOnCheckedChangeListener(this::onCheckedChanged);
        tekniikanCB.setOnCheckedChangeListener(this::onCheckedChanged);
        uimaCB.setOnCheckedChangeListener(this::onCheckedChanged);

        context =this;

    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String degree = "";

        switch (buttonView.getId()) {
            case R.id.kandidaatinTutkintoCheckBox:
                degree = "kandidaatin tutkinto";
                break;
            case R.id.diTutkintoCheckBox:
                degree = "Diplomi-insinöörin tutkinto";
                break;
            case R.id.tekniikanTohtoriTutkintoCheckBox:
                degree = "Tekniikan tohtorin tutkinto";
                break;
            case R.id.uimamestariCheckBox:
                degree = "Uimamaisteri";
            default:
                break;
        }

        if (isChecked) {
            degrees.add(degree);
        } else {
            degrees.remove(degree);
        }
    }


    public void addUser(View view) {
        RadioGroup rgUser = findViewById(R.id.rgUser);
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String email = emailET.getText().toString();
        int studyID = studyChoiceRG.getCheckedRadioButtonId();
        avatarSpinner = findViewById(R.id.avatarSpinner);
        int selectedAvatar = avatarSpinner.getSelectedItemPosition();
        String study = "";

        switch (studyID) {
            case R.id.lateButton:
                study = "Laskennallinen tekniikka";
                break;
            case R.id.sahkotekniikkaButton:
                study = "Sähkötekniikka";
                break;
            case R.id.tuotantotalousButton:
                study = "Tuotantotalous";
                break;
            case R.id.tietotekniikkaButton:
                study = "Tietotekniikka";
                break;
        }

        User user = new User(firstName,lastName,email,study, selectedAvatar, degrees);
        UserStorage us = UserStorage.getInstance();
        us.addUser(user, context);

    }
}
