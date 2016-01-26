package edu.uw.intentdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "**DEMO**";

    private static final int REQUEST_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View launchButton = findViewById(R.id.btnLaunch);
        launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Launch button pressed");


                //Explicit Intent
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("edu.uw.intentdemo.message", "Hello number 2!");

                startActivity(intent);


            }
        });


        View callButton = findViewById(R.id.btnDial);
        callButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Call button pressed");

                //implicit intent
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:206-685-1622"));

                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);

            }
        });


        View cameraButton = findViewById(R.id.btnPicture);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Camera button pressed");

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, REQUEST_PICTURE);


            }
        });


        View messageButton = findViewById(R.id.btnMessage);
        messageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Message button pressed");


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if camera responds with a "yup!"
        if (requestCode == REQUEST_PICTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap)extras.get("data");

            ((ImageView)findViewById(R.id.imgThumbnail)).setImageBitmap(bitmap);
        }

    }
}
