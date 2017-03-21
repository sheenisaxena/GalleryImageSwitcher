package com.imageswitcherdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class ImageSwitcherWithGallery extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        ViewSwitcher.ViewFactory {

    public static boolean isGalleryUpdate;
    float initialX;
    GestureDetector detector;
    Gallery g;
    boolean booleanswitch;
    ImageAdapter adapter;
    private ImageSwitcher mswitcher;
    private int positionArg = 0;
    //All Images stored im integer array
    private Integer[] mImageIds = {R.drawable.tor, R.drawable.natasha, R.drawable.iron_man,
            R.drawable.assassins_creed, R.drawable.avatar_3d, R.drawable.call_of_duty_black_ops_3, R.drawable.dota_2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting no title on window, it should be written before setting cotent view
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.imgswitcher_with_gallery);

        //Getting id for Image switcher and it is used to switch between images
        mswitcher = (ImageSwitcher) findViewById(R.id.image);
        mswitcher.setFactory(this);

        //Gallery for placing images
        g = (Gallery) findViewById(R.id.gallery);

        //Setting adapter over gallery
        adapter = new ImageAdapter(ImageSwitcherWithGallery.this, mImageIds);
        g.setAdapter(adapter);

        //Implementing itemselected listener over gallery
        g.setOnItemSelectedListener(ImageSwitcherWithGallery.this);

        mswitcher.setImageResource(mImageIds[positionArg]);

        detector = new GestureDetector(this, new GestureTap());

    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
                               long arg3) {

        //On item selected from gallery the image switcher will get the position and set it to background
        mswitcher.setImageResource(mImageIds[position]);
        positionArg = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    @Override
    public View makeView() {
        //Adding dynamic image
        ImageView i = new ImageView(getApplicationContext());

        //Setting background color
        i.setBackgroundColor(0xFFFFFFFF);

        //Setting scale type
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);

        //Now setting layout parameters for image view
        i.setLayoutParams(new ImageSwitcher.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT));

        // below code is required if user want touchimageview for zoom effect & swiping effect from left to right or vice versa.
//        i.setOnTouchListener(this);

        //Returning imageview
        return i;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                if (initialX > finalX) {

                    positionArg++;
                    if (positionArg > mImageIds.length - 1) {
                        // for looping image we have to add position= 0 instead of below line
                        positionArg = mImageIds.length - 1;
                        Toast.makeText(getApplicationContext(), "No more image available.",
                                Toast.LENGTH_LONG).show();
                    } else {
//                        mswitcher.invalidate();
                        mswitcher.setImageResource(mImageIds[positionArg]);
                    }
                } else {
                    positionArg = positionArg - 1;

                   /* if (position < 0) {
                        position = projection.length - 1;
                    }
                    image_switcher.setImageResource(Integer.parseInt(projection[position]));*/

                    if (positionArg < 0) {
                        positionArg = 0;
                        Toast.makeText(getApplicationContext(), "No more image available.",
                                Toast.LENGTH_LONG).show();
                    } else {
//                        mswitcher.invalidate();
                        mswitcher.setImageResource(mImageIds[positionArg]);
                    }
                }

                // this is for display selected item in gallery view
                g.setSelection(positionArg);
                isGalleryUpdate = true;
                adapter.notifyDataSetChanged();

                break;
        }
        return detector.onTouchEvent(event);
    }

    public class GestureTap extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("onDoubleTap :", "" + e.getAction());
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("onSingleTap :", "" + e.getAction());
            if (!booleanswitch) {
                booleanswitch = true;
                g.setVisibility(View.GONE);
            } else {
                booleanswitch = false;
                g.setVisibility(View.VISIBLE);
            }

            return true;
        }
    }
}

