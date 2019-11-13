package com.example.recyclerview;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recyclerview.Model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener{


    EditText editTextN,editTextE,editTextD,editTextP;
    RadioGroup radioG;
    Spinner spin;
    Button buttonsubmit,buttonView;
    String name,gender,dob,country,email,image,phone;
    AutoCompleteTextView autoCompleteTextView;

    ArrayList<User> usersList = new ArrayList<>();


    String[] imagesuggests = {"profile1", "profile2"};

    Calendar calendardata = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener mydatepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendardata.set(Calendar.YEAR,i);
            calendardata.set(Calendar.MONTH,i1);
            calendardata.set(Calendar.DAY_OF_MONTH,i2);

            String mydateFormat ="dd-MM-y";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mydateFormat, Locale.getDefault());
            editTextD.setText(simpleDateFormat.format(calendardata.getTime()));


        }

    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        autoCompleteTextView=findViewById(R.id.tvAutoComplete);
        editTextN=findViewById(R.id.name);
        editTextE=findViewById(R.id.email);
        editTextD=findViewById(R.id.date);
        editTextP=findViewById(R.id.phone);
        radioG=findViewById(R.id.gender);
        spin=findViewById(R.id.spCountry);

        buttonsubmit=findViewById(R.id.btnsubmit);
        buttonView=findViewById(R.id.btnview);

        List<String> countries = new ArrayList<>();


        countries.add(0,"Choose Your Country");
        countries.add("Nepal");
        countries.add("China");
        countries.add("Pakistan");
        countries.add("Bhutan");

        ArrayAdapter<String> adapter =new ArrayAdapter(this,R.layout.spinner_values,countries);
        spin.setAdapter(adapter);

        ArrayAdapter<String> dummyimageadapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, imagesuggests);
        autoCompleteTextView = findViewById(R.id.tvAutoComplete);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(dummyimageadapter);



        radioG.setOnCheckedChangeListener(this);
        buttonsubmit.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        editTextD.setOnClickListener(this);

        setSpinnerValue();
    }

    @Override
    public void onClick(View view) {
        name = editTextN.getText().toString();
        dob=editTextD.getText().toString();
        image=autoCompleteTextView.getText().toString();
        email=editTextE.getText().toString();
        phone = editTextP.getText().toString();



        if(view.getId()==R.id.btnsubmit)
        {
            if(validate())
            {
                usersList.add(new User(name,gender,dob,country,phone,email,image));

            }




        }

        if(view.getId()==R.id.date)
        {
            new DatePickerDialog(this,mydatepicker,calendardata.get(Calendar.YEAR),calendardata.get(Calendar.MONTH),
                    calendardata.get(Calendar.DAY_OF_MONTH)).show();
        }

        if(view.getId()==R.id.btnview)
        {


            Intent intent = new Intent(this, RecycleViewDetails.class);

            intent.putExtra("userlist",usersList);


            startActivity(intent);

        }


    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i== R.id.rmale)
        {
            gender="Male";

        }
        if(i == R.id.rfemale)
        {
            gender = "Female";

        }
        if(i== R.id.rother)
        {
            gender ="Other";

        }
    }

    private boolean validate(){
        if(TextUtils.isEmpty(name))
        {
            editTextN.setError("Enter A Name");
            editTextN.requestFocus();
            editTextN.setHint("Please Enter a Name");
            return false;
        }
        if(TextUtils.isEmpty(gender))
        {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(dob))
        {
            editTextD.setError("Enter  DOB");
            editTextD.requestFocus();
            editTextD.setHint("Please Enter  DateOfBirth");
            return false;
        }

        if(TextUtils.isEmpty(phone))
        {
            editTextP.setError("Phone number is Empty");
            editTextP.requestFocus();
            editTextP.setHint("Please Enter a Phone");
            return false;
        }

        if(!TextUtils.isDigitsOnly(phone))
        {
            editTextP.setError("Phone Number is invalid");
            editTextP.requestFocus();
            editTextP.setHint("Please Enter a Phone");
            return false;
        }

        if(TextUtils.isEmpty(email))
        {
            editTextE.setError("Enter A Email");
            editTextE.requestFocus();
            editTextE.setHint("Please Enter a Email");
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextE.setError("Email is invalid");
            editTextE.requestFocus();
            editTextE.setHint("Please Enter a Email");
            return false;
        }

        if(TextUtils.isEmpty(image))
        {
            autoCompleteTextView.setError("Enter an Image name");
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.setHint("Please enter an image name");
            return false;
        }



        if(TextUtils.isEmpty(country))
        {
            Toast.makeText(this, "Please select a country", Toast.LENGTH_SHORT).show();
            return false;
        }





        return  true;
    }


    private void setSpinnerValue(){

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(adapterView.getItemAtPosition(i).equals("Choose your Country"))
                {
                    Toast.makeText(getApplicationContext(),"Please Select any one option",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    country = adapterView.getSelectedItem().toString();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

}
