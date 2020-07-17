package com.example.MeetDinner.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MeetDinner.API.ApiInterface;
import com.example.MeetDinner.Controller;
import com.example.MeetDinner.R;
import com.example.MeetDinner.adaptors.ItemDetailsAdaptor;
import com.example.MeetDinner.modals.ItemDetailsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity6 extends AppCompatActivity implements OtherServiceDialog.AddData {

    RecyclerView recyclerView;
    Button button;
    List<ItemDetailsModel> model;
    TextView itemNumber, restaurantName, restaurantAddress;
    Controller controller;
    SharedPreferences sharedPreferences;
    String getShareVN, getShareVA;
    ImageButton callBtn;
    Switch vegSwitch, eggSwitch;
    String vegNon_veg = "both";
    OtherServiceDialog dialog;
    int size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main6 );
        button = findViewById( R.id.otherServices );
        itemNumber = findViewById( R.id.itemNumber );
        restaurantName = findViewById( R.id.restaurantName );
        restaurantAddress = findViewById( R.id.restaurantAddress );
        callBtn = findViewById( R.id.callBtn );
        vegSwitch = findViewById( R.id.vegSwitch );
        eggSwitch = findViewById( R.id.eggSwitch );
        recyclerView = findViewById( R.id.recycler_items );
        dialog = new OtherServiceDialog(getApplicationContext());
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        sharedPreferences = getSharedPreferences( "data", MODE_PRIVATE );
        if (sharedPreferences.contains( "shareVN" ) && sharedPreferences.contains( "shareVA" )) {
            getShareVN = sharedPreferences.getString( "shareVN", "" );
            getShareVA = sharedPreferences.getString( "shareVA", "" );
            restaurantName.setText( getShareVN );
            restaurantAddress.setText( getShareVA );
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( ApiInterface.BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        ApiInterface apiInterface = retrofit.create( ApiInterface.class );
        Call<List<ItemDetailsModel>> call = apiInterface.getItemDetails( "b98e9093-c784-4e71-8355-56df201a5d2f" );
        call.enqueue( new Callback<List<ItemDetailsModel>>() {
            @Override
            public void onResponse(Call<List<ItemDetailsModel>> call, Response<List<ItemDetailsModel>> response) {
                vegSwitch.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        try {
                            if (vegSwitch.isChecked()) {
                                vegNon_veg = "vegetarian";
                                Toast.makeText( MainActivity6.this, "Vegetarian List", Toast.LENGTH_SHORT ).show();
                                recyclerView.setAdapter( new ItemDetailsAdaptor( getApplicationContext(), response.body(), vegNon_veg ) );
                                List<ItemDetailsModel> list = response.body();
                                size = 0;
                                for (ItemDetailsModel item : list) {
                                    if (item.getCategory().equals( "veg" )) {
                                        size++;
                                    }
                                }
                                itemNumber.setText( String.valueOf( size ) + " items" );
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                       
                    }
                } );
                eggSwitch.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (eggSwitch.isChecked()) {
                            vegNon_veg = "non_vegetarian";
                            Toast.makeText( MainActivity6.this, "Non_Vegetarian List", Toast.LENGTH_SHORT ).show();
                            recyclerView.setAdapter( new ItemDetailsAdaptor( getApplicationContext(), response.body(), vegNon_veg ) );
                            List<ItemDetailsModel> list = response.body();
                            size = 0;
                            for (ItemDetailsModel item : list) {
                                if (item.getCategory().equals( "non veg" )) {
                                    size++;
                                }
                            }
                            itemNumber.setText( String.valueOf( size ) + " items" );

                        }
                    }
                } );
                recyclerView.setAdapter( new ItemDetailsAdaptor( getApplicationContext(), response.body(), vegNon_veg ) );
                List<ItemDetailsModel> list = response.body();
                int size = list.size();
                itemNumber.setText( String.valueOf( size ) + " items" );
                System.out.println( response.body() );

            }

            @Override
            public void onFailure(Call<List<ItemDetailsModel>> call, Throwable t) {
                System.out.println( t.getMessage() );
            }
        } );
        callBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse( "tel:" + "0123-456-789" );
                Intent callIntent = new Intent( Intent.ACTION_DIAL, uri );
                try {
                    startActivity( callIntent );
                } catch (ActivityNotFoundException activityNotFoundException) {
                    Toast.makeText( getApplicationContext(), "Application not found to make a call", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyText();
            }
        } );
    }

    @Override
    public void applyText() {
        dialog.show( getSupportFragmentManager(), "Other Services" );
    }
}