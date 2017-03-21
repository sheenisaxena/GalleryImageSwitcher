package com.imageswitcherdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] mImageIds;
    private static LayoutInflater inflater = null;
    ImageView iv_gallery;
    int CurrPosArg;

    public ImageAdapter(Context c, Integer[] mImageIds, int CurrPos) {
        this.mImageIds = mImageIds;
        mContext = c;
        CurrPosArg = CurrPos;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

	/*	View vi = convertView;

		vi = inflater.inflate(R.layout.item_image_adapter, parent, false);

		iv_gallery = (ImageView) vi.findViewById(R.id.iv_gallery_view);
		iv_gallery.setImageResource(mImageIds[position]);*/
        // Adding dynamic image simillarly we had added to Image Switcher
        ImageView i = new ImageView(mContext);
        i.setImageResource(mImageIds[position]);
        i.setAdjustViewBounds(true);
//        i.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        i.setLayoutParams(new Gallery.LayoutParams(130, 150));
        i.setScaleType(ImageView.ScaleType.FIT_XY);
//        i.setPadding(10, 10, 10, 10);

        // Setting background resource
//        i.setBackgroundResource(mImageIds[0]);
        i.setBackgroundResource(R.color.colorGrey);
        if (CurrPosArg == position)
            i.setBackgroundResource(R.drawable.galleryviewselector);

        return i;
    }

}