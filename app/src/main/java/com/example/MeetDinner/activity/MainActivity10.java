package com.example.MeetDinner.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.MeetDinner.R;
public class MainActivity10 extends AppCompatActivity {
    ImageView chef;
    TextView normalPrice, totalPriceText, totalPay, addRemoveNumber;
    ImageView add, remove;

    int num = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main10 );
        chef = findViewById( R.id.chef );
        addRemoveNumber = findViewById( R.id.addRemoveNumber );
        add = findViewById( R.id.add );
        remove = findViewById( R.id.remove );
        normalPrice = findViewById( R.id.normalPrice );
        totalPriceText = findViewById( R.id.totalPrice );
        totalPay = findViewById( R.id.totalPay );
        normalPrice.setText( "₹00" );
        totalPriceText.setText( "₹00" );
        totalPay.setText( "₹00" );


        add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                addRemoveNumber.setText( String.valueOf(num) );
            }
        } );

        remove.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num>=1){
                    num--;
                }
                addRemoveNumber.setText( String.valueOf(num) );
                if (num <= 0){
                    addRemoveNumber.setText( "0" );
                }
            }
        } );
        
        chef.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), MainActivity11.class );
                startActivity( intent );
            }
        } );
    }
}