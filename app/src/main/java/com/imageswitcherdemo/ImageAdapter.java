package com.imageswitcherdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] mImageIds;
    int itemBackground;

    public ImageAdapter(Context c, Integer[] mImageIds) {
        this.mImageIds = mImageIds;
        mContext = c;

        // belwo 3 lines are not working
        TypedArray a = mContext.obtainStyledAttributes(R.styleable.MyGallery);
        itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
        a.recycle();

    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public Object getItem(int pos) {
        return mImageIds[pos];
    }

    @Override
    public long getItemId(int pos) {

        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Adding dynamic image simillarly we had added to Image Switcher
        ImageView i = new ImageView(mContext);
        i.setImageResource(mImageIds[position]);
        i.setAdjustViewBounds(true);
//        i.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        i.setLayoutParams(new Gallery.LayoutParams(mContext.getResources().getDimensionPixelSize(R.dimen.gallery_image_width), mContext.getResources().getDimensionPixelSize(R.dimen.gallery_image_height)));
        i.setScaleType(ImageView.ScaleType.FIT_XY);
//        i.setPadding(10, 10, 10, 10);

        // Setting background resource
//        i.setBackgroundResource(R.color.colorGrey);

        // belwo gets called but dont know not getting selector effect gets display
        if (ImageSwitcherWithGallery.isGalleryUpdate) {
//            i.setBackgroundResource(R.color.colorSelector);
            i.setBackgroundResource(itemBackground);
            ImageSwitcherWithGallery.isGalleryUpdate = false;
        }

        return i;
    }

}