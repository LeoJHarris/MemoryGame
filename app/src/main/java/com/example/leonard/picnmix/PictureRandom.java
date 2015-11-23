package com.example.leonard.picnmix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonard on 30/06/2015.
 */
class PictureRandom {
    String name;
    int photoId;

    PictureRandom(String name, int imageId) {
        this.name = name;
        this.photoId = imageId;
    }


    private List<PictureRandom> images;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData() {
        images = new ArrayList<>();
        //persons.add(new PictureRandom("Emma Wilson", "23 years old", R.drawable.emma));
        // persons.add(new PictureRandom("Lavery Maiss", "25 years old", R.drawable.lavery));
        // persons.add(new PictureRandom("Lillie Watts", "35 years old", R.drawable.lillie));
    }
}