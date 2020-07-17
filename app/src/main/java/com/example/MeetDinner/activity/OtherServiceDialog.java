package com.example.MeetDinner.activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.MeetDinner.API.ApiInterface;
import com.example.MeetDinner.R;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
public class OtherServiceDialog extends AppCompatDialogFragment implements View.OnClickListener {

    Button doneButton, okayButton;
    TextView dialogOtherService, cutlery, water, tissue, pickle, others, thankYou, requestId;
    String cutleryString = "false";
    String waterString = "false";
    String tissueString = "false";
    String pickleString = "false";
    RadioButton cutlerySelect, waterSelect, tissueSelect, pickleSelect, othersSelect;
    EditText dialogET;
    private AddData listener;
    SharedPreferences sharedPreferences;
    String orderID, tableNo, otherText = null;
    Context context;

    public OtherServiceDialog(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate( R.layout.layout_dialog, null );
        builder.setView( view );
        findView( view );
        sharedPreferences = context.getSharedPreferences( "data", MODE_PRIVATE );
//        cutleryCheck = view.findViewById( R.id.cutleryCheck );
//        waterCheck = view.findViewById( R.id.waterCheck );
//        tissueCheck = view.findViewById( R.id.tissueCheck );
//        pickleCheck = view.findViewById( R.id.pickleCheck );
//        othersCheck = view.findViewById( R.id.othersCheck );
        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        retrofitCode();
        return super.onCreateView( inflater, container, savedInstanceState );
    }

    private void findView(View view) {
        okayButton = view.findViewById( R.id.dialogOkay );
        doneButton = view.findViewById( R.id.dialogDone );
        dialogOtherService = view.findViewById( R.id.dialogOtherService );
        cutlery = view.findViewById( R.id.cutlery );
        water = view.findViewById( R.id.water );
        tissue = view.findViewById( R.id.tissue );
        pickle = view.findViewById( R.id.pickle );
        others = view.findViewById( R.id.others );
        dialogET = view.findViewById( R.id.dialogET );
        cutlerySelect = view.findViewById( R.id.cutlerySelect );
        waterSelect = view.findViewById( R.id.waterSelect );
        tissueSelect = view.findViewById( R.id.tissueSelect );
        pickleSelect = view.findViewById( R.id.pickleSelect );
        othersSelect = view.findViewById( R.id.othersSelect );
        thankYou = view.findViewById( R.id.thankYou );
        requestId = view.findViewById( R.id.requestId );
        doneButton.setOnClickListener( this );
        okayButton.setOnClickListener( this );
        cutlerySelect.setOnClickListener( this );
        waterSelect.setOnClickListener( this );
        tissueSelect.setOnClickListener( this );
        pickleSelect.setOnClickListener( this );
        othersSelect.setOnClickListener( this );

    }

    @Override
    public void onClick(View view) {
        if (view == cutlerySelect) {
            if (cutlerySelect.isChecked()) {
                cutleryString = "true";
            }
        } else if (view == waterSelect) {
            if (waterSelect.isChecked()) {
                waterString = "true";
            }
        } else if (view == tissueSelect) {
            if (tissueSelect.isChecked()) {
                tissueString = "true";
            }
        } else if (view == pickleSelect) {
            if (pickleSelect.isChecked()) {
                pickleString = "true";
            }
        } else if (view == othersSelect) {
            if (othersSelect.isChecked()) {
                dialogET.setEnabled( true );
            }
        } else if (view == doneButton) {
            dialogOtherService.setVisibility( View.GONE );
            cutlery.setVisibility( View.GONE );
            water.setVisibility( View.GONE );
            tissue.setVisibility( View.GONE );
            pickle.setVisibility( View.GONE );
            others.setVisibility( View.GONE );
            dialogET.setVisibility( View.GONE );
            cutlerySelect.setVisibility( View.GONE );
            waterSelect.setVisibility( View.GONE );
            tissueSelect.setVisibility( View.GONE );
            pickleSelect.setVisibility( View.GONE );
            othersSelect.setVisibility( View.GONE );
            doneButton.setVisibility( View.GONE );
            thankYou.setVisibility( View.VISIBLE );
            requestId.setVisibility( View.VISIBLE );
            okayButton.setVisibility( View.VISIBLE );
            requestId.setText( "Your request ID is #" + orderID );
        } else if (view == okayButton) {
            Intent intent = new Intent( getActivity(), MainActivity8.class );
            startActivity( intent );
            dismiss();
        }
    }

    private void retrofitCode() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( ApiInterface.BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        ApiInterface apiInterface = retrofit.create( ApiInterface.class );
        if (sharedPreferences.contains( "shareTable" )) {
            tableNo = sharedPreferences.getString( "shareTable", "" );
        }
        otherText = dialogET.getText().toString();
        try {
            JsonObject object = new JsonObject();
            object.addProperty( "vendor_id", "b98e9093-c784-4e71-8355-56df201a5d2f" );
            object.addProperty( "table_no", tableNo );
            object.addProperty( "cutlery", cutleryString );
            object.addProperty( "water", waterString );
            object.addProperty( "tissue", tissueString );
            object.addProperty( "pickle", pickleString );
            object.addProperty( "others", otherText );
            System.out.println( object );
            Call<String> call = apiInterface.getDialogData( object );
            call.enqueue( new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    orderID = response.body();
                    System.out.println( response.body() + "   rrrrrrrrrrrrr" );
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_SHORT ).show();
                }
            } );

        } catch (JsonIOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach( context );
        try {
            listener = (AddData) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "implement priceShowListener" );
        }
    }

    public interface AddData {

        void applyText();
    }
}
