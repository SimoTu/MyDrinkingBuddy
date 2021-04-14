package com.example.mydrink;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mydrink.IppsDialog;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity_IPPS extends AppCompatActivity implements View.OnClickListener, IppsDialog.ExampleDialogListener {
    int correctCounter = 0;
    String currentColor = BuildConfig.FLAVOR;
    int currentNumber = 0;
    ArrayList<Integer> deckOfCards_clubs = new ArrayList<>();
    ArrayList<Integer> deckOfCards_diamonds = new ArrayList<>();
    ArrayList<Integer> deckOfCards_hearts = new ArrayList<>();
    ArrayList<Integer> deckOfCards_spades = new ArrayList<>();
    String guessColor = BuildConfig.FLAVOR;
    int guessNumber = 0;
    int guesses_needed = 2;
    int i = 1;
    int index = 0;
    int index2 = 0;
    IppsPlayersList ipl = new IppsPlayersList();
    int lastPicked = 0;
    ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<>();
    int passCounter = 0;
    int pickedImage = 0;
    int player_list_size = IppsPlayersList.players.size();
    TextView player_name;
    Random r = new Random();

    public int getPassCounter() {
        return this.passCounter;
    }

    public void setPassCounter(int passCounter2) {
        this.passCounter = passCounter2;
    }

    public int getCorrectCounter() {
        return this.correctCounter;
    }

    public void setCorrectCounter(int correctCounter2) {
        this.correctCounter = correctCounter2;
    }

    public int getIndex2() {
        return this.index2;
    }

    public void setIndex2(int index22) {
        this.index2 = index22;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index3) {
        this.index = index3;
    }

    public void setCurrentNumber(int currentNumber2) {
        this.currentNumber = currentNumber2;
    }

    public String getCurrentColor() {
        return this.currentColor;
    }

    public void setCurrentColor(String currentColor2) {
        this.currentColor = currentColor2;
    }

    public int getGuessNumber() {
        return this.guessNumber;
    }

    public void setGuessNumber(int guessNumber2) {
        this.guessNumber = guessNumber2;
    }

    public String getGuessColor() {
        return this.guessColor;
    }

    public void setGuessColor(String guessColor2) {
        this.guessColor = guessColor2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_ipps);
        for (int i2 = 0; i2 < 13; i2++) {
            ArrayList<Integer> arrayList = this.deckOfCards_spades;
            Resources resources = getResources();
            arrayList.add(i2, Integer.valueOf(resources.getIdentifier("spades_" + (i2 + 1), "drawable", getPackageName())));
        }
        for (int i3 = 0; i3 < 13; i3++) {
            ArrayList<Integer> arrayList2 = this.deckOfCards_hearts;
            Resources resources2 = getResources();
            arrayList2.add(i3, Integer.valueOf(resources2.getIdentifier("hearts_" + (i3 + 1), "drawable", getPackageName())));
        }
        for (int i4 = 0; i4 < 13; i4++) {
            ArrayList<Integer> arrayList3 = this.deckOfCards_diamonds;
            Resources resources3 = getResources();
            arrayList3.add(i4, Integer.valueOf(resources3.getIdentifier("diamonds_" + (i4 + 1), "drawable", getPackageName())));
        }
        for (int i5 = 0; i5 < 13; i5++) {
            ArrayList<Integer> arrayList4 = this.deckOfCards_clubs;
            Resources resources4 = getResources();
            arrayList4.add(i5, Integer.valueOf(resources4.getIdentifier("clubs_" + (i5 + 1), "drawable", getPackageName())));
        }
        this.listOfLists.add(0, this.deckOfCards_clubs);
        this.listOfLists.add(1, this.deckOfCards_spades);
        this.listOfLists.add(2, this.deckOfCards_hearts);
        this.listOfLists.add(3, this.deckOfCards_diamonds);
        ImageView myImageView = (ImageView) findViewById(R.id.imageView);
        myImageView.setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.nameTextView);
        this.player_name = textView;
        textView.setText(IppsPlayersList.players.get(0));
        ((Button) findViewById(R.id.button_big)).setOnClickListener(this);
        Button btn_pass = (Button) findViewById(R.id.button_pass);
        btn_pass.setOnClickListener(this);
        btn_pass.setEnabled(false);
        ((Button) findViewById(R.id.button_small)).setOnClickListener(this);
        ((Button) findViewById(R.id.button_red)).setOnClickListener(this);
        ((Button) findViewById(R.id.button_black)).setOnClickListener(this);
        setIndex(this.r.nextInt(this.listOfLists.size()));
        setIndex2(this.r.nextInt(this.deckOfCards_hearts.size()));
        if (getIndex() > 1) {
            setCurrentColor("red");
        } else {
            setCurrentColor("black");
        }
        myImageView.setImageResource(((Integer) this.listOfLists.get(getIndex()).get(getIndex2())).intValue());
        setCurrentNumber(this.index2);
        PrintStream printStream = System.out;
        printStream.println("Alkukortin maa on : " + getCurrentColor());
        PrintStream printStream2 = System.out;
        printStream2.println("Alkukortin numero on :" + (getIndex2() + 1));
    }

    public void openDialog() {
        IppsDialog ipps = new IppsDialog();
        ipps.setDrinkCounter(getCorrectCounter());
        ipps.show(getSupportFragmentManager(), "Ipps Dialog");
    }

    public void onYesClicked() {
        restartGame();
    }

    public void onNoClicked() {
        restartGame();
    }

    public void restartGame() {
        ((TextView) findViewById(R.id.correctCounterTextView)).setText("Correct answers: 0");
        setCorrectCounter(0);
        setIndex(this.r.nextInt(this.listOfLists.size()));
        setIndex2(this.r.nextInt(this.deckOfCards_hearts.size()));
        ((Button) findViewById(R.id.button_pass)).setEnabled(false);
        if (getIndex() > 1) {
            setCurrentColor("red");
        } else {
            setCurrentColor("black");
        }
        ((ImageView) findViewById(R.id.imageView)).setImageResource(((Integer) this.listOfLists.get(getIndex()).get(getIndex2())).intValue());
        setCurrentNumber(this.index2 + 1);
        PrintStream printStream = System.out;
        printStream.println("Alkukortin maa on : " + getCurrentColor());
        PrintStream printStream2 = System.out;
        printStream2.println("Alkukortin numero on :" + (getIndex2() + 1));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    private void openActivityOneGame() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void openActivityShuffle() {
        startActivity(new Intent(this, ShuffleActivity.class));
    }

    private void openActivityIPPSlist() {
        new IppsPlayersList();
        IppsPlayersList.players.clear();
        startActivity(new Intent(this, IppsPlayersList.class));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.IPPM) {
            IppsPlayersList.players.clear();
            openActivityIPPSlist();
            return true;
        } else if (itemId == R.id.loopgame) {
            Toast.makeText(this, "Selected " + item.toString(), 1).show();
            openActivityShuffle();
            return true;
        } else if (itemId != R.id.onegame) {
            return super.onOptionsItemSelected(item);
        } else {
            Toast.makeText(this, "Selected " + item.toString(), 1).show();
            openActivityOneGame();
            return true;
        }
    }

    public void onClick(View v) {
        String player;
        Button btn_pass = (Button) findViewById(R.id.button_pass);
        switch (v.getId()) {
            case R.id.button_big /*2131165254*/:
                if (!checkGuess("big") || getPassCounter() <= this.guesses_needed || IppsPlayersList.players.size() <= 1) {
                    btn_pass.setEnabled(false);
                    return;
                } else {
                    btn_pass.setEnabled(true);
                    return;
                }
            case R.id.button_black /*2131165255*/:
                if (!checkGuess("black") || getPassCounter() <= this.guesses_needed || IppsPlayersList.players.size() <= 1) {
                    btn_pass.setEnabled(false);
                    return;
                } else {
                    btn_pass.setEnabled(true);
                    return;
                }
            case R.id.button_pass /*2131165257*/:
                TextView myTextView2 = (TextView) findViewById(R.id.nameTextView);
                if (this.i < this.player_list_size - 1) {
                    player = IppsPlayersList.players.get(this.i);
                    System.out.println("if not null");
                    this.i++;
                } else {
                    System.out.println("if null");
                    player = IppsPlayersList.players.get(this.i);
                    this.i = 0;
                }
                myTextView2.setText(player);
                btn_pass.setEnabled(false);
                setPassCounter(0);
                return;
            case R.id.button_red /*2131165258*/:
                if (!checkGuess("red") || getPassCounter() <= this.guesses_needed || IppsPlayersList.players.size() <= 1) {
                    btn_pass.setEnabled(false);
                    return;
                } else {
                    btn_pass.setEnabled(true);
                    return;
                }
            case R.id.button_small /*2131165259*/:
                if (!checkGuess("small") || getPassCounter() <= this.guesses_needed || IppsPlayersList.players.size() <= 1) {
                    btn_pass.setEnabled(false);
                    return;
                } else {
                    btn_pass.setEnabled(true);
                    return;
                }
            default:
                return;
        }
    }

    public boolean checkGuess(String guess) {
        int nextInt;
        int nextInt2;
        int nextInt3;
        int nextInt4;
        ImageView myImageView = (ImageView) findViewById(R.id.imageView);
        TextView myTextView = (TextView) findViewById(R.id.correctCounterTextView);
        if (guess.equals("big")) {
            do {
                nextInt4 = this.r.nextInt(this.deckOfCards_hearts.size());
                this.pickedImage = nextInt4;
            } while (nextInt4 == this.lastPicked);
            setIndex(this.r.nextInt(this.listOfLists.size()));
            setIndex2(this.pickedImage);
            int randomCard = ((Integer) this.listOfLists.get(getIndex()).get(getIndex2())).intValue();
            setGuessNumber(getIndex2());
            if (getIndex() > 1) {
                setCurrentColor("red");
            } else {
                setCurrentColor("black");
            }
            if (getGuessNumber() > this.currentNumber) {
                PrintStream printStream = System.out;
                printStream.println("Arvatun kortin maa on : " + getCurrentColor());
                PrintStream printStream2 = System.out;
                printStream2.println("Arvatun kortin numero on :" + (getIndex2() + 1));
                myImageView.setImageResource(randomCard);
                setCurrentNumber(getIndex2());
                setPassCounter(getPassCounter() + 1);
                setCorrectCounter(getCorrectCounter() + 1);
                myTextView.setText("Correct answers: " + getCorrectCounter());
                return true;
            }
            PrintStream printStream3 = System.out;
            printStream3.println("Arvatun kortin maa on : " + getCurrentColor());
            PrintStream printStream4 = System.out;
            printStream4.println("Arvatun kortin numero on :" + (getIndex2() + 1));
            myImageView.setImageResource(randomCard);
            setCorrectCounter(getCorrectCounter() + 1);
            setPassCounter(0);
            openDialog();
        }
        if (guess.equals("small")) {
            do {
                nextInt3 = this.r.nextInt(this.listOfLists.size());
                this.pickedImage = nextInt3;
            } while (nextInt3 == this.lastPicked);
            setIndex(this.r.nextInt(this.listOfLists.size()));
            setIndex2(this.r.nextInt(this.deckOfCards_hearts.size()));
            int randomCard2 = ((Integer) this.listOfLists.get(getIndex()).get(getIndex2())).intValue();
            if (getIndex() > 1) {
                setCurrentColor("red");
            } else {
                setCurrentColor("black");
            }
            setGuessNumber(getIndex2());
            if (getGuessNumber() < this.currentNumber) {
                myImageView.setImageResource(randomCard2);
                setCurrentNumber(getIndex2());
                PrintStream printStream5 = System.out;
                printStream5.println("Arvatun kortin maa on : " + getCurrentColor());
                PrintStream printStream6 = System.out;
                printStream6.println("Arvatun kortin numero on :" + (getIndex2() + 1));
                setCorrectCounter(getCorrectCounter() + 1);
                setPassCounter(getPassCounter() + 1);
                myTextView.setText("Correct answers: " + getCorrectCounter());
                return true;
            }
            PrintStream printStream7 = System.out;
            printStream7.println("Arvatun kortin maa on : " + getCurrentColor());
            PrintStream printStream8 = System.out;
            printStream8.println("Arvatun kortin numero on :" + (getIndex2() + 1));
            myImageView.setImageResource(randomCard2);
            setCorrectCounter(getCorrectCounter() + 1);
            setPassCounter(0);
            openDialog();
        }
        if (guess.equals("red") != 0) {
            do {
                nextInt2 = this.r.nextInt(this.listOfLists.size());
                this.pickedImage = nextInt2;
            } while (nextInt2 == this.lastPicked);
            setIndex(this.r.nextInt(this.listOfLists.size()));
            setIndex2(this.r.nextInt(this.deckOfCards_hearts.size()));
            int randomCard3 = ((Integer) this.listOfLists.get(getIndex()).get(getIndex2())).intValue();
            if (getIndex() > 1) {
                setCurrentColor("red");
            } else {
                setCurrentColor("black");
            }
            setGuessColor("red");
            if (getGuessColor() == this.currentColor) {
                myImageView.setImageResource(randomCard3);
                setCorrectCounter(getCorrectCounter() + 1);
                setPassCounter(getPassCounter() + 1);
                PrintStream printStream9 = System.out;
                printStream9.println("Arvatun kortin maa on : " + getCurrentColor());
                PrintStream printStream10 = System.out;
                printStream10.println("Arvatun kortin numero on :" + (getIndex2() + 1));
                myTextView.setText("Correct answers: " + getCorrectCounter());
                return true;
            }
            PrintStream printStream11 = System.out;
            printStream11.println("Arvatun kortin maa on : " + getCurrentColor());
            PrintStream printStream12 = System.out;
            printStream12.println("Arvatun kortin numero on :" + (getIndex2() + 1));
            myImageView.setImageResource(randomCard3);
            setCorrectCounter(getCorrectCounter() + 1);
            setPassCounter(0);
            openDialog();
        }
        if (guess.equals("black") != 0) {
            do {
                nextInt = this.r.nextInt(this.listOfLists.size());
                this.pickedImage = nextInt;
            } while (nextInt == this.lastPicked);
            setIndex(this.r.nextInt(this.listOfLists.size()));
            setIndex2(this.r.nextInt(this.deckOfCards_hearts.size()));
            int randomCard4 = ((Integer) this.listOfLists.get(getIndex()).get(getIndex2())).intValue();
            if (getIndex() > 1) {
                setCurrentColor("red");
            } else {
                setCurrentColor("black");
            }
            setGuessColor("black");
            if (getGuessColor() == this.currentColor) {
                myImageView.setImageResource(randomCard4);
                setCorrectCounter(getCorrectCounter() + 1);
                setPassCounter(getPassCounter() + 1);
                PrintStream printStream13 = System.out;
                printStream13.println("Arvatun kortin maa on : " + getCurrentColor());
                PrintStream printStream14 = System.out;
                printStream14.println("Arvatun kortin numero on :" + (getIndex2() + 1));
                myTextView.setText("Correct answers: " + getCorrectCounter());
                return true;
            }
            PrintStream printStream15 = System.out;
            printStream15.println("Arvatun kortin maa on : " + getCurrentColor());
            PrintStream printStream16 = System.out;
            printStream16.println("Arvatun kortin numero on :" + (getIndex2() + 1));
            myImageView.setImageResource(randomCard4);
            setCorrectCounter(getCorrectCounter() + 1);
            setPassCounter(0);
            openDialog();
        }
        return false;
    }
}
