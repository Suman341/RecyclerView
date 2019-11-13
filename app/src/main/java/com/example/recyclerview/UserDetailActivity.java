package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetailActivity extends AppCompatActivity {
    TextView textviewName,textviewGender,textviewdob,textviewcountry,textviewphone,textviewemail;
    ImageView individualUserImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        individualUserImage= findViewById(R.id.ivUserImg);


        textviewName= findViewById(R.id.tvName);
        textviewGender= findViewById(R.id.tvGender);
        textviewdob= findViewById(R.id.tvDob);
        textviewcountry= findViewById(R.id.tvCountry);
        textviewphone= findViewById(R.id.tvPhone);
        textviewemail= findViewById(R.id.tvEmail);

        Intent intent = getIntent();
        String name= intent.getStringExtra("name");
        String gender= intent.getStringExtra("gender");
        String dob = intent.getStringExtra("dob");
        String country= intent.getStringExtra("country");
        String phone= intent.getStringExtra("phone");
        String email= intent.getStringExtra("email");
        String image= intent.getStringExtra("image");


        String uri = "@drawable/" + image;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        individualUserImage.setImageResource(imageResource);


        textviewName.setText("Name :"+name);
        textviewGender.setText("Gender :"+gender);
        textviewdob.setText("DOB :"+dob);
        textviewcountry.setText("Country :"+country);
        textviewphone.setText("Phone :"+phone);
        textviewemail.setText("Email :"+email);
    }
}
