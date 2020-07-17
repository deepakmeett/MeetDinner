package com.example.MeetDinner.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MeetDinner.API.ApiInterface;
import com.example.MeetDinner.R;
import com.example.MeetDinner.modals.VendorDetailsModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity5 extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    String r1;
    Button continueButton;
    EditText name, tableNumber;
    TextView vendorName, vendorAddress;
    SharedPreferences sharedPreferences;
    String putShareVN, putShareVA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main5 );
        radioGroup = findViewById( R.id.toggle );
        radioButton1 = findViewById( R.id.dineInRadio );
        radioButton2 = findViewById( R.id.takeAwayRadio );
        continueButton = findViewById( R.id.continue_button );
        vendorName = findViewById( R.id.vendorName );
        vendorAddress = findViewById( R.id.vendorAddress );
        name = findViewById( R.id.name );
        tableNumber = findViewById( R.id.tableNumber );

        sharedPreferences = getSharedPreferences( "data", MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( ApiInterface.BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        ApiInterface apiInterface = retrofit.create( ApiInterface.class );
        JsonObject object = new JsonObject();
        object.addProperty( "data", "b98e9093-c784-4e71-8355-56df201a5d2f" );
        Call<VendorDetailsModel> call = apiInterface.getVendorDetails( object );
        call.enqueue( new Callback<VendorDetailsModel>() {
            @Override
            public void onResponse(Call<VendorDetailsModel> call, Response<VendorDetailsModel> response) {
                VendorDetailsModel list = response.body();
                if (list != null && !list.equals( "" )) {
                    putShareVN = list.getVendorName();
                    putShareVA = list.getAddress();
                    editor.putString( "shareVN", putShareVN );
                    editor.putString( "shareVA", putShareVA );
                    
                    vendorName.setText( putShareVN );
                    vendorAddress.setText( putShareVA );
                }
            }

            @Override
            public void onFailure(Call<VendorDetailsModel> call, Throwable t) {
                Toast.makeText( MainActivity5.this, t.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
        name.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Drawable drawable = getResources().getDrawable( R.drawable.ic_baseline_person_24 );
                if (hasFocus) {
                    drawable = DrawableCompat.wrap( drawable );
                    DrawableCompat.setTint( drawable, getResources().getColor( R.color.colorAccent ) );
                    name.setCompoundDrawablesWithIntrinsicBounds( null, null, drawable, null );
                } else {
                    drawable = DrawableCompat.wrap( drawable );
                    DrawableCompat.setTint( drawable, getResources().getColor( R.color.iconTint ) );
                    name.setCompoundDrawablesWithIntrinsicBounds( null, null, drawable, null );
                }
                if (!name.getText().toString().equals( "" )) {
                    drawable = DrawableCompat.wrap( drawable );
                    DrawableCompat.setTint( drawable, getResources().getColor( R.color.colorAccent ) );
                    name.setCompoundDrawablesWithIntrinsicBounds( null, null, drawable, null );
                }

            }
        } );
        tableNumber.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Drawable drawable = getResources().getDrawable( R.drawable.table );
                if (hasFocus) {
                    drawable = DrawableCompat.wrap( drawable );
                    DrawableCompat.setTint( drawable, getResources().getColor( R.color.colorAccent ) );
                    tableNumber.setCompoundDrawablesWithIntrinsicBounds( null, null, drawable, null );
                } else {
                    drawable = DrawableCompat.wrap( drawable );
                    DrawableCompat.setTint( drawable, getResources().getColor( R.color.iconTint ) );
                    tableNumber.setCompoundDrawablesWithIntrinsicBounds( null, null, drawable, null );
                }
                if (!tableNumber.getText().toString().equals( "" )) {
                    drawable = DrawableCompat.wrap( drawable );
                    DrawableCompat.setTint( drawable, getResources().getColor( R.color.colorAccent ) );
                    tableNumber.setCompoundDrawablesWithIntrinsicBounds( null, null, drawable, null );
                }

            }
        } );
        radioButton1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton1.setTextColor( getResources().getColor( android.R.color.white ) );
                radioButton2.setTextColor( getResources().getColor( R.color.colorAccent ) );
                radioButton1.setBackgroundColor( getResources().getColor( R.color.colorAccent ) );
                radioButton2.setBackgroundColor( getResources().getColor( android.R.color.white ) );
            }
        } );
        radioButton2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton2.setTextColor( getResources().getColor( android.R.color.white ) );
                radioButton1.setTextColor( getResources().getColor( R.color.colorAccent ) );
                radioButton2.setBackgroundColor( getResources().getColor( R.color.colorAccent ) );
                radioButton1.setBackgroundColor( getResources().getColor( android.R.color.white ) );
            }
        } );
        continueButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName = name.getText().toString();
                String table = tableNumber.getText().toString();
                if (customerName.isEmpty() && customerName.equalsIgnoreCase( "" )) {
                    name.setError( "Please add your name" );
                } else if (table.isEmpty() && table.equalsIgnoreCase( "" )) {
                    tableNumber.setError( "Please add table number" );
                } else {
                    editor.putString( "shareCN", customerName );
                    editor.putString( "shareTable", table );
                    editor.putString( "shareRB1", radioButton1.getText().toString());
                    editor.putString( "shareRB2", radioButton2.getText().toString());
                    editor.apply();


                    int r = radioGroup.getCheckedRadioButtonId();
                    if (r == R.id.dineInRadio) {
                        r1 = radioButton1.getText().toString();
                        
                    } else if (r == R.id.takeAwayRadio) {
                        r1 = radioButton2.getText().toString();

                    }
                    if (r1 == null) {
                        Toast.makeText( MainActivity5.this, radioButton2.getText().toString(), Toast.LENGTH_SHORT ).show();
                    } else {
                        Toast.makeText( getApplicationContext(), r1, Toast.LENGTH_SHORT ).show();
                    }
                    Intent intent = new Intent( getApplicationContext(), MainActivity6.class );
                    startActivity( intent );
                }
            }
        } );
    }
}