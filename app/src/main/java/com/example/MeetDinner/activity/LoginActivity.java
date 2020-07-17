package com.example.MeetDinner.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MeetDinner.R;
public class LoginActivity extends AppCompatActivity {
    Button button;
    TextView signUp;
    EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.login_activity );

        if (ContextCompat.checkSelfPermission( getApplicationContext(),
                                               android.Manifest.permission.CAMERA )
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions( this, new String[]
                    {Manifest.permission.CAMERA,}, 101 );

        }
        button = findViewById( R.id.log_in_btn );
        signUp = findViewById( R.id.signUp );
        emailEditText = findViewById( R.id.email );
        passwordEditText = findViewById( R.id.password );
        
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                Toast.makeText( LoginActivity.this, email + " " + password, Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent( LoginActivity.this, SignUpActivity.class );
                startActivity( intent );
            }
        } );

        signUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginActivity.this, SignUpActivity.class );
                startActivity( intent );
            }
        } );
    }
}