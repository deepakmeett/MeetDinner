package com.example.MeetDinner.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MeetDinner.R;
public class SignUpActivity extends AppCompatActivity {
    Button button, facebookBtn, gmailBtn;
    TextView backToLogin;
    EditText username, registerEmail, registerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sign_up_activity );
        button = findViewById( R.id.signUp_in_btn );

        facebookBtn = findViewById( R.id.facebookBtn );
        gmailBtn = findViewById( R.id.gmailBtn );
        username = findViewById( R.id.username);
        registerEmail = findViewById( R.id.registerEmail );
        registerPassword = findViewById( R.id.registerPassword );
        backToLogin = findViewById( R.id.backToLogin );
        
        
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username.getText().toString();
                String registerEmail = username.getText().toString();
                String registerPass = username.getText().toString();
                
                Intent intent = new Intent( SignUpActivity.this, MainActivity3.class );
                startActivity( intent );
            }
        } );

        facebookBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( SignUpActivity.this, "Facebook", Toast.LENGTH_SHORT ).show();
            }
        } );
        
        gmailBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( SignUpActivity.this, "Gmail", Toast.LENGTH_SHORT ).show();
            }
        } );

        backToLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        } );

    }
}