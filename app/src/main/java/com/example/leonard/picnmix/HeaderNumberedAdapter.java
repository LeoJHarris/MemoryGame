package com.example.leonard.picnmix;

/**
 * Created by Leonard on 1/07/2015.
 */

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HeaderNumberedAdapter extends RecyclerView.Adapter<HeaderNumberedAdapter.ItemImagesViewHolder> {
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;

    private final View header;
    private final ArrayList<ImageMemorize> items;
    private final Resources resources;

    public HeaderNumberedAdapter(View header, ArrayList<ImageMemorize> Items, Resources R) {
        if (header == null) {
            throw new IllegalArgumentException("header may not be null");
        }
        resources = R;
        this.header = header;
        items = Items;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    @Override
    public ItemImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW_TYPE_HEADER) {
            return new ItemImagesViewHolder(header);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemImagesViewHolder holder, final int position) {
        if (isHeader(position)) {
            return;
        }
        int picId = resources.getIdentifier(items.get(position - 1).getImageName(), "drawable", "com.example.leonard.picnmix");
        holder.itemImage.setImageResource(picId);

        //Clicks for each image
        //holder.itemImage.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //  public void onClick(View v) {
        //      Toast.makeText(
        //              holder.itemImage.getContext(), (items.get(position - 1).getImageName()), Toast.LENGTH_SHORT).show();
        // }
        // });
    }

    @Override
    public int getItemViewType(int position) {
        return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return items.size() + 1;
    }

    public static class ItemImagesViewHolder extends RecyclerView.ViewHolder {
        TextView imageName;
        ImageView itemImage;

        ItemImagesViewHolder(View itemView) {
            super(itemView);
            //imageName = (TextView) itemView.findViewById(R.id.itemName);
            itemImage = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}