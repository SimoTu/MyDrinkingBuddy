package com.example.mydrink;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mydrink.ExampleDialog;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ExampleDialog.ExampleDialogListener {
    List<Integer> deckOfCards = new ArrayList();
    LinkedList deckOfRedo = new LinkedList();
    List game_options = new ArrayList();
    int lastPicked = 0;
    int pickedImage = 0;
    int previous_counter = 0;
    Random r = new Random();
    public boolean settings1;
    public boolean settings2;

    public void setSettings1(boolean settings12) {
        this.settings1 = settings12;
    }

    public boolean getSettings1() {
        return this.settings1;
    }

    public void setSettings2(boolean settings22) {
        this.settings2 = settings22;
    }

    public boolean getSettings2() {
        return this.settings2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        this.game_options.add("One game");
        this.game_options.add("Never ending");
        for (int i = 0; i < 65; i++) {
            List<Integer> list = this.deckOfCards;
            Resources resources = getResources();
            list.add(i, Integer.valueOf(resources.getIdentifier("kortti" + (i + 1), "drawable", getPackageName())));
        }
        this.settings1 = false;
        this.settings2 = true;
        ImageView myImageView = (ImageView) findViewById(R.id.imageView);
        myImageView.setOnClickListener(this);
        ((Button) findViewById(R.id.button_idiot)).setOnClickListener(this);
        myImageView.setImageResource(R.drawable.korttitaka);
    }

    public void onYesClicked() {
        startAgain();
    }

    public void onNoClicked() {
        System.out.println("no");
    }

    public void startAgain() {
        for (int i = 0; i < 65; i++) {
            List<Integer> list = this.deckOfCards;
            Resources resources = getResources();
            list.add(i, Integer.valueOf(resources.getIdentifier("kortti" + (i + 1), "drawable", getPackageName())));
        }
        this.deckOfRedo.clear();
        this.pickedImage = 0;
        this.lastPicked = 0;
        ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.korttitaka);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.IPPM) {
            openActivityIPPSlist();
            return true;
        } else if (itemId == R.id.loopgame) {
            Toast.makeText(this, "Selected " + item.toString(), 1).show();
            ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.korttitaka);
            startAgain();
            openActivityShuffle();
            return true;
        } else if (itemId != R.id.onegame) {
            return super.onOptionsItemSelected(item);
        } else {
            Toast.makeText(this, "Selected " + item.toString(), 1).show();
            this.settings2 = true;
            this.settings1 = false;
            startAgain();
            return true;
        }
    }

    public void openDialog() {
        new ExampleDialog().show(getSupportFragmentManager(), "example dialog");
    }

    private void openActivityShuffle() {
        startActivity(new Intent(this, ShuffleActivity.class));
    }

    private void openActivityIPPSlist() {
        new IppsPlayersList();
        IppsPlayersList.players.clear();
        startActivity(new Intent(this, IppsPlayersList.class));
    }

    public void onClick(View v) {
        int nextInt;
        int id = v.getId();
        if (id == R.id.button_idiot) {
            int i = this.previous_counter + 1;
            this.previous_counter = i;
            if (i > 1) {
                ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.x_cat);
            } else if (this.settings1 && this.deckOfRedo.size() > 1) {
                ((ImageView) findViewById(R.id.imageView)).setImageResource(((Integer) this.deckOfRedo.get(1)).intValue());
            } else if (!this.settings2 || this.deckOfRedo.size() <= 1) {
                ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.x_cat);
            } else {
                ((ImageView) findViewById(R.id.imageView)).setImageResource(((Integer) this.deckOfRedo.get(1)).intValue());
            }
        } else if (id == R.id.imageView) {
            if (this.settings1) {
                ImageView myImageView = (ImageView) findViewById(R.id.imageView);
                do {
                    nextInt = this.r.nextInt(this.deckOfCards.size());
                    this.pickedImage = nextInt;
                } while (nextInt == this.lastPicked);
                if (this.deckOfCards.size() > 0) {
                    int index = this.r.nextInt(this.deckOfCards.size());
                    if (this.previous_counter < 1 || this.deckOfRedo.size() <= 0) {
                        int randomCard = this.deckOfCards.get(index).intValue();
                        myImageView.setImageResource(randomCard);
                        this.deckOfRedo.addFirst(Integer.valueOf(randomCard));
                    } else {
                        myImageView.setImageResource(((Integer) this.deckOfRedo.getFirst()).intValue());
                    }
                    this.previous_counter = 0;
                    return;
                }
                myImageView.setImageResource(R.drawable.x_cat);
            } else if (!this.settings2) {
            } else {
                if (this.deckOfCards.size() > 0) {
                    int index2 = this.r.nextInt(this.deckOfCards.size());
                    if (this.previous_counter < 1 || this.deckOfRedo.size() <= 0) {
                        int randomCard2 = this.deckOfCards.get(index2).intValue();
                        ((ImageView) findViewById(R.id.imageView)).setImageResource(randomCard2);
                        this.deckOfRedo.addFirst(Integer.valueOf(randomCard2));
                        this.deckOfCards.remove(index2);
                    } else {
                        ((ImageView) findViewById(R.id.imageView)).setImageResource(((Integer) this.deckOfRedo.getFirst()).intValue());
                    }
                    this.previous_counter = 0;
                    return;
                }
                ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.x_cat);
                openDialog();
            }
        }
    }
}
