package com.example.leonard.picnmix;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

public class GameMemorizeImages extends Activity {

    int numberItems;
    ImageButton imageButton;
    ArrayList<ImageMemorize> listImagesMemorize = new ArrayList<>();
    MediaPlayer mp;
    Boolean ticking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_memorize_images);

        Intent intent = getIntent();

        numberItems = intent.getIntExtra("ItemValues", numberItems);

        ticking = intent.getBooleanExtra("Ticking", ticking);

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(nextImageHandler);

        Field[] drawables = R.drawable.class.getFields();
        for (Field f : drawables) {
            try {
                if (!(f.getName().toString().contains("_"))) {
                    String pathDirectoryImage = "R.drawable." + f.getName();

                    // Populate the List Array with Images
                    ImageMemorize m = new ImageMemorize(pathDirectoryImage, f.getName());
                    listImagesMemorize.add(m);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // End populating the list of images


        Collections.shuffle(listImagesMemorize);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_memorize_images, menu);
        return true;
    }

    View.OnClickListener nextImageHandler = new View.OnClickListener() {

        int i = 0;

        public void onClick(View v) {

            if (i < numberItems) {
                Resources r = getResources();
                int picId = r.getIdentifier(listImagesMemorize.get(i).getImageName(), "drawable", "com.example.leonard.picnmix");


                if (ticking == true)

                    if (mp != null) {
                        if (!mp.isPlaying()) {
                            mp = MediaPlayer.create(getApplicationContext(), R.raw.clock);
                            mp.start();
                            mp.setLooping(true);
                        }
                    }
                imageButton.setBackgroundResource(picId);

                //TODO NOT WORKING!
                //Toast.makeText(GameMemorizeImages.this, listImagesMemorize.get(i).getImageName(), Toast.LENGTH_LONG);

                i++;
            } else {
                if (mp != null) {
                    if (mp.isPlaying()) {
                        mp.stop();
                    }
                }
                Intent intent = new Intent(GameMemorizeImages.this, ImagesInputOrder.class);

                intent.putParcelableArrayListExtra("listArrayImages", listImagesMemorize);
                intent.putExtra("numberItems", numberItems);
                startActivity(intent);
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
