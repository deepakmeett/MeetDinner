package com.example.MeetDinner.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MeetDinner.R;
public class MainActivity11 extends AppCompatActivity {
    Button chef;
    TextView normalPrice, totalPriceText, sub_total, gstprice, totalPay, addRemoveNumber;
    ImageView add, remove;

    int num = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main11 );
        chef = findViewById( R.id.payDonedialogDone );
        addRemoveNumber = findViewById( R.id.addRemoveNumber );
        add = findViewById( R.id.add );
        remove = findViewById( R.id.remove );
        normalPrice = findViewById( R.id.normalPrice );
        totalPriceText = findViewById( R.id.totalPrice );
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

        chef.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( MainActivity11.this, "This is the last screen", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}