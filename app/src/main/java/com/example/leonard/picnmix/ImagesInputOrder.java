package com.example.leonard.picnmix;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ImagesInputOrder extends Activity {

    int numberItems;

    private ArrayList<ImageMemorize> listImagesMemorizeOrdered = new ArrayList<>();
    private List<ImageMemorize> listImagesMemorizeRadomized = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        Intent intent = getIntent();
        final ArrayList<ImageMemorize> listImagesMemorize = intent.getParcelableArrayListExtra("listArrayImages");
        numberItems = intent.getIntExtra("numberItems", numberItems);

        Resources r = getResources();

        int numberItemsRemoval = listImagesMemorize.size() - numberItems;
        // numberItemsRemoval = listImagesMemorize.size() - numberItemsRemoval;

        for (int i = listImagesMemorize.size() - 1; i >= (numberItems); i--) {
            listImagesMemorize.remove(i);
        }

        listImagesMemorizeOrdered = listImagesMemorize;

        final ArrayList<ImageMemorize> l;
        l = (ArrayList) listImagesMemorize.clone();

        // for (int y = 0; y < listImagesMemorize.size(); y++)
        // {
        //     listImagesMemorizeRadomized.add(y,listImagesMemorize.get(y));
        // }

        //TODO Make it shuffle
        Collections.shuffle(l);

        final AutofitRecyclerView recyclerView = (AutofitRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new MarginDecoration(this));

        final GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);

        final View header = LayoutInflater.from(this).inflate(R.layout.header, recyclerView, false);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), R.string.grid_layout_header, Toast.LENGTH_SHORT).show();
            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            int i = 0;
            int attempts = 0;
            final int amountItems = listImagesMemorizeOrdered.size();
            int correctlyAnswered = 1;

            @Override
            public void onItemClick(View view, int position) {

                if (attempts < 3) {

                    String imageValSelected = l.get(position - 1).getImageName();

                    if (listImagesMemorizeOrdered.get(i).getImageName() == imageValSelected) {

                        if (attempts == 2) {
                            Toast.makeText(ImagesInputOrder.this, "Yay! Attempts reset. " + correctlyAnswered + "/"
                                    + amountItems + " ordered correctly", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ImagesInputOrder.this, correctlyAnswered + "/"
                                    + amountItems + " ordered correctly", Toast.LENGTH_SHORT).show();
                        }
                        correctlyAnswered++;

                        attempts = 0;
                        //recyclerView.removeView(view);
                        // recyclerView.removeViewAt(position);

                        recyclerView.setAdapter(null);

                        listImagesMemorizeOrdered.remove(0);

                        l.remove(position - 1);

                        Resources r = getResources();
                        final HeaderNumberedAdapter adapter = new HeaderNumberedAdapter(header, l, r);
                        recyclerView.setAdapter(adapter);


                        if (listImagesMemorizeOrdered.isEmpty()) {
                            //Empty list no more, user wins.
                        }

                    } else if (attempts == 0) {
                        attempts++;
                        Toast.makeText(ImagesInputOrder.this, "2 Attempts left!", Toast.LENGTH_SHORT).show();
                    } else if (attempts == 1) {
                        attempts++;
                        Toast.makeText(getApplicationContext(), "1 Attempt left!", Toast.LENGTH_SHORT).show();
                    } else if (attempts == 2) {
                        attempts++;
                        Toast.makeText(getApplicationContext(), "No attempts left! Try again", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No attempts left! Try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }

        ));

        final HeaderNumberedAdapter adapter = new HeaderNumberedAdapter(header, l, r);

        recyclerView.setAdapter(adapter);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()

                                  {
                                      @Override
                                      public int getSpanSize(int position) {
                                          return adapter.isHeader(position) ? manager.getSpanCount() : 1;
                                      }
                                  }
        );
    }
}
