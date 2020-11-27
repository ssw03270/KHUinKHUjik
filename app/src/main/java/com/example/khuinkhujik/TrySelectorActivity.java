package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TrySelectorActivity extends AppCompatActivity {

    Button videoButton, documentButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_selector);

        videoButton = (Button)findViewById(R.id.buttonVideo);
        documentButton = (Button)findViewById(R.id.buttonDocument);
    }

    public void videoButton(View v){
        Intent intent = new Intent(getApplication(), ReadyActivity.class);
        intent.putExtra("tryType","video");
        startActivity(intent);
        overridePendingTransition(0, 0);
        TrySelectorActivity.this.finish();
    }
    public void documentButton(View v){
        Intent intent = new Intent(getApplication(), ReadyActivity.class);
        intent.putExtra("tryType","document");
        startActivity(intent);
        overridePendingTransition(0, 0);
        TrySelectorActivity.this.finish();
    }
}