package com.example.MeetDinner.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MeetDinner.R;
public class Payment_Activity extends AppCompatActivity {

    Button button;
    RadioButton googlePayButton, paytmButton, phonePeButton, bhimButton, cashButton;
    TextView orderRupees;
    String payBy = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.payment_activity );
        button = findViewById( R.id.dialogDone );
        googlePayButton = findViewById( R.id.googlePayButton );
        paytmButton = findViewById( R.id.paytmButton );
        phonePeButton = findViewById( R.id.phonePeButton );
        bhimButton = findViewById( R.id.bhimButton );
        cashButton = findViewById( R.id.cashButton );
        orderRupees = findViewById( R.id.orderRupees );

        orderRupees.setText( "₹000");


        googlePayButton.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean hasChecked) {
                if (hasChecked){
                    payBy = "Google Pay";
                    googlePayButton.setChecked( true );
                    paytmButton.setChecked( false );
                    phonePeButton.setChecked( false );
                    bhimButton.setChecked( false );
                    cashButton.setChecked( false );
                }
               
            }
        } );

        paytmButton.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean hasChecked) {
                if (hasChecked){
                    payBy = "Paytm";
                    googlePayButton.setChecked( false );
                    paytmButton.setChecked( true );
                    phonePeButton.setChecked( false );
                    bhimButton.setChecked( false );
                    cashButton.setChecked( false );
                }
                
            }
        } );
        phonePeButton.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean hasChecked) {
                if (hasChecked) {
                    payBy = "PhonePe";
                    googlePayButton.setChecked( false );
                    paytmButton.setChecked( false );
                    phonePeButton.setChecked( true );
                    bhimButton.setChecked( false );
                    cashButton.setChecked( false );
                }
            }
        } );
        bhimButton.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean hasChecked) {
                if (hasChecked) {
                    payBy = "BHIM UPI";
                    googlePayButton.setChecked( false );
                    paytmButton.setChecked( false );
                    phonePeButton.setChecked( false );
                    bhimButton.setChecked( true );
                    cashButton.setChecked( false );
                }
            }
        } );
        cashButton.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean hasChecked) {
                if (hasChecked) {
                    payBy = "Cash On counter";
                    googlePayButton.setChecked( false );
                    paytmButton.setChecked( false );
                    phonePeButton.setChecked( false );
                    bhimButton.setChecked( false );
                    cashButton.setChecked( true );
                }
            }
        } );


        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( Payment_Activity.this, payBy, Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent( getApplicationContext(), MainActivity10.class );
                startActivity( intent );
            }
        } );
    }
}