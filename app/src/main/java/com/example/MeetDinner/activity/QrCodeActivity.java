package com.example.MeetDinner.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.MeetDinner.R;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import static android.Manifest.permission.CAMERA;
public class QrCodeActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 1;
    SurfaceView surfaceView;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;
    TextView textView;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.qr_code_activity );
        surfaceView = findViewById( R.id.surfaceView );
        textView = findViewById( R.id.collectedData );
        layout = findViewById( R.id.scannerLL );

        layout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), MainActivity5.class );
                startActivity( intent );
            }
        } );
        
        barcodeDetector = new BarcodeDetector.Builder( this )
                .setBarcodeFormats( Barcode.QR_CODE ).build();
        cameraSource = new CameraSource.Builder( this, barcodeDetector )
                .setRequestedPreviewSize( 640, 480 ).build();
        surfaceView.getHolder().addCallback( new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission( getApplicationContext(), Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                try {
                    cameraSource.start( surfaceHolder );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        } );
        barcodeDetector.setProcessor( new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qr_code = detections.getDetectedItems();
                if (qr_code.size() != 0) {
                    textView.post( new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService( Context.VIBRATOR_SERVICE );
                            vibrator.vibrate( 500 );
                            final String codeData = (qr_code.valueAt( 0 ).displayValue);
                            textView.setText( codeData );

                            textView.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( codeData ) );
                                    startActivity( intent );
                                    textView.setText( "ALIGN TO SCAN AREA" );
                                }
                            } );

                        }
                    } );
                }
            }
        } );
    }

    

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
            } else {
                requestPermission();
            }
        }
    }
    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission( this, CAMERA )
                == PackageManager.PERMISSION_GRANTED);
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.CAMERA,}, 101 );
    }

}