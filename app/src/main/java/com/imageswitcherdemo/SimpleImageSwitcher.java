package com.imageswitcherdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class SimpleImageSwitcher extends AppCompatActivity {

    ImageSwitcher image_switcher;
    float initialX;
    private int position = 0;
    Animation in, out;

    String[] projection = new String[]{
            String.valueOf(R.drawable.iron_man),
            String.valueOf(R.drawable.natasha),
            String.valueOf(R.drawable.tor),
            String.valueOf(R.drawable.avatar_3d),
            String.valueOf(R.drawable.assassins_creed),
            String.valueOf(R.drawable.call_of_duty_black_ops_3),
            String.valueOf(R.drawable.dota_2)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_imgswitcher);

        image_switcher = (ImageSwitcher) findViewById(R.id.image_switcher);

        image_switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {

                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));

                return imageView;
            }
        });

        // Declare in and out animations and load them using AnimationUtils class
        in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        // belwo line to display zero position image
        image_switcher.setImageResource(Integer.parseInt(projection[position]));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                if (initialX > finalX) {

                    position++;
                    if (position > projection.length - 1) {
                        // for looping image we have to add position= 0 instead of below line
                        position = projection.length - 1;
                        Toast.makeText(getApplicationContext(), "No more image available.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        image_switcher.setOutAnimation(out);
                        image_switcher.setImageResource(Integer.parseInt(projection[position]));
                    }
                } else {
                    position = position - 1;

                   /* if (position < 0) {
                        position = projection.length - 1;
                    }
                    image_switcher.setImageResource(Integer.parseInt(projection[position]));*/

                    if (position < 0) {
                        position = 0;
                        Toast.makeText(getApplicationContext(), "No more image available.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        image_switcher.setInAnimation(in);
                        image_switcher.setImageResource(Integer.parseInt(projection[position]));
                    }
                }
                break;
        }
        return false;
    }
}

