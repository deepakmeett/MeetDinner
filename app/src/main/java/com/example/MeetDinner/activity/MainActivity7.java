package com.example.MeetDinner.activity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.MeetDinner.R;
import com.example.MeetDinner.adaptors.VegDishesAdapter;
import com.example.MeetDinner.modals.ItemDetailsModel;

import java.util.List;
public class MainActivity7 extends AppCompatActivity implements OtherServiceDialog.AddData{
    
    RecyclerView recycler_horizontal, recycler_items_One;
    Button button;
    OtherServiceDialog dialog;
    List<ItemDetailsModel> model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main7 );
        button = findViewById( R.id.otherServicesOne );
        dialog = new OtherServiceDialog(getApplicationContext());
        
        recycler_horizontal = findViewById( R.id.recycler_horizontal );
        recycler_horizontal.setHasFixedSize( true );
        recycler_horizontal.setLayoutManager( new LinearLayoutManager( this, RecyclerView.HORIZONTAL, false ) );
        recycler_horizontal.setAdapter( new VegDishesAdapter( this ) );


        recycler_items_One = findViewById( R.id.recycler_items_One );
        recycler_items_One.setHasFixedSize( true );
        recycler_items_One.setLayoutManager( new LinearLayoutManager( this ) );
//        recycler_items_One.setAdapter( new DishesAdaptor( this, model ) );
        
        
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    applyText();
            }
        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        button.setText( "OTHER SERVICES" );
        super.onActivityResult( requestCode, resultCode, data );
    }

    @Override
    public void applyText() {
     dialog.show( getSupportFragmentManager(), "Other Services" );   
    }
}