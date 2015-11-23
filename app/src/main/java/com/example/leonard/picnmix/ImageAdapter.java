package com.example.leonard.picnmix;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<ImageMemorize> ImageValues;
    private Resources r;

    public ImageAdapter(Context context, ArrayList<ImageMemorize> ImageValues, Resources R) {
        this.context = context;
        this.ImageValues = ImageValues;
        r = R;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from Image.xml
            gridView = inflater.inflate(R.layout.image_items, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(ImageValues.get(position).getImageName());

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);


            int picId = r.getIdentifier(ImageValues.get(position).getImageName(), "drawable", "com.example.leonard.picnmix");

            imageView.setImageResource(picId);

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return ImageValues.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}