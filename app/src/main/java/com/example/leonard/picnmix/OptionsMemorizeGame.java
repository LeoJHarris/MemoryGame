package com.example.leonard.picnmix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

public class OptionsMemorizeGame extends Activity {

    Button buttonStartGame;
    TextView textItemsVal;
    Boolean ticking = false;
    CheckBox checkBoxString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_memorize_game);

        checkBoxString = (CheckBox) findViewById(R.id.checkBoxTicking);
        buttonStartGame = (Button) findViewById(R.id.buttonStartGame);
        buttonStartGame.setOnClickListener(startGameHandler);

        final SeekBar sk = (SeekBar) findViewById(R.id.seekBarItemsVal);
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub

                textItemsVal.setText(String.valueOf(progress + 5));
            }
        });
        textItemsVal = (TextView) findViewById(R.id.textViewValItems);
    }

    View.OnClickListener startGameHandler = new View.OnClickListener() {
        public void onClick(View v) {
            Intent gameImageIntent = new Intent(OptionsMemorizeGame.this, GameMemorizeImages.class);

            gameImageIntent.putExtra("ItemValues", Integer.parseInt(textItemsVal.getText().toString()));
            gameImageIntent.putExtra("Ticking", ticking);
            startActivity(gameImageIntent);
        }
    };

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkBoxTicking:
                if (checked) {
                    ticking = true;
                    checkBoxString.setText("Clock Sound On");
                } else {
                    ticking = false;
                    checkBoxString.setText("Clock Sound Off");
                }

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options_memorize_game, menu);
        return true;
    }

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
