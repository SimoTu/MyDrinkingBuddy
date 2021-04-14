package com.example.mydrink;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ShuffleActivity extends AppCompatActivity implements View.OnClickListener {
    List<Integer> deckOfCards = new ArrayList();
    LinkedList deckOfRedo = new LinkedList();
    int lastPicked = 0;
    int pickedImage = 0;
    int previous_counter = 0;
    Random r = new Random();
    boolean settings1 = true;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.shuffle_activity);
        for (int i = 0; i < 65; i++) {
            List<Integer> list = this.deckOfCards;
            Resources resources = getResources();
            list.add(i, Integer.valueOf(resources.getIdentifier("kortti" + (i + 1), "drawable", getPackageName())));
        }
        ImageView myImageView = (ImageView) findViewById(R.id.imageView);
        myImageView.setOnClickListener(this);
        ((Button) findViewById(R.id.button_idiot)).setOnClickListener(this);
        myImageView.setImageResource(R.drawable.korttitaka);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    private void openActivityIPPSlist() {
        new IppsPlayersList();
        IppsPlayersList.players.clear();
        startActivity(new Intent(this, IppsPlayersList.class));
    }

    private void openActivityOneGame() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.IPPM) {
            openActivityIPPSlist();
            return true;
        } else if (itemId == R.id.loopgame) {
            ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.korttitaka);
            return true;
        } else if (itemId != R.id.onegame) {
            return super.onOptionsItemSelected(item);
        } else {
            openActivityOneGame();
            return true;
        }
    }

    public void onClick(View v) {
        int nextInt;
        int id = v.getId();
        if (id == R.id.button_idiot) {
            int i = this.previous_counter + 1;
            this.previous_counter = i;
            if (i > 1) {
                ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.x_cat);
            } else if (!this.settings1 || this.deckOfRedo.size() <= 1) {
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
                return;
            }
            ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.x_cat);
        }
    }
}
