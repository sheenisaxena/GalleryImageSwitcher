package com.imageswitcherdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_simple_imgsw, btn_imgsw_with_gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_simple_imgsw = (Button) findViewById(R.id.btn_simple_imgsw);
        btn_imgsw_with_gallery = (Button) findViewById(R.id.btn_imgsw_with_gallery);

        btn_simple_imgsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SimpleImageSwitcher.class));
            }
        });

        btn_imgsw_with_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImageSwitcherWithGallery.class));
            }
        });
    }
}

