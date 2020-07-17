package com.example.MeetDinner.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MeetDinner.R;
public class MainActivity8 extends AppCompatActivity {

    RadioGroup rg1;
    RadioButton rb1, rb2;
    String r1;
    Button b3;
    TextView normalPrice, totalPriceText, sub_total, gstprice, totalPay, addRemoveNumber;
    ImageView add, remove;

    int num = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main8 );
        rg1 = findViewById( R.id.payRG );
        rb1 = findViewById( R.id.payNowRadio );
        rb2 = findViewById( R.id.payLaterRadio );
        b3 = findViewById( R.id.next_button );
        addRemoveNumber = findViewById( R.id.addRemoveNumber );
        add = findViewById( R.id.add );
        remove = findViewById( R.id.remove );
        normalPrice = findViewById( R.id.normalPrice );
        totalPriceText = findViewById( R.id.totalPriceText );
        sub_total = findViewById( R.id.sub_total );
        gstprice = findViewById( R.id.gstprice );
        totalPay = findViewById( R.id.totalPay );
        normalPrice.setText( "₹00" );
        totalPriceText.setText( "₹00" );
        sub_total.setText( "₹00" );
        gstprice.setText( "₹00" );
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
        
        rb1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb1.setTextColor( getResources().getColor( android.R.color.white ) );
                rb2.setTextColor( getResources().getColor( R.color.colorAccent ) );
                rb1.setBackgroundColor( getResources().getColor( R.color.colorAccent ) );
                rb2.setBackgroundColor( getResources().getColor( android.R.color.white ) );
            }
        } );
        rb2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb2.setTextColor( getResources().getColor( android.R.color.white ) );
                rb1.setTextColor( getResources().getColor( R.color.colorAccent ) );
                rb2.setBackgroundColor( getResources().getColor( R.color.colorAccent ) );
                rb1.setBackgroundColor( getResources().getColor( android.R.color.white ) );
            }
        } );
        b3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int r = rg1.getCheckedRadioButtonId();
                if (r == R.id.dineInRadio) {
                    r1 = rb1.getText().toString();

                } else if (r == R.id.takeAwayRadio) {
                    r1 = rb2.getText().toString();

                }
                if (r1 == null) {
                    Toast.makeText( getApplicationContext(), rb2.getText().toString(), Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( getApplicationContext(), r1, Toast.LENGTH_SHORT ).show();
                }
                Intent intent = new Intent( getApplicationContext(), Payment_Activity.class );
                startActivity( intent );
            }
        } );

    }
}