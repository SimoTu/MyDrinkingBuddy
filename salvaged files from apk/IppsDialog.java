package com.example.mydrink;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class IppsDialog extends AppCompatDialogFragment {
    int drinkCounter = 0;
    /* access modifiers changed from: private */
    public ExampleDialogListener listener;

    public interface ExampleDialogListener {
        void onNoClicked();

        void onYesClicked();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    public int getDrinkCounter() {
        return this.drinkCounter;
    }

    public void setDrinkCounter(int drinkCounter2) {
        this.drinkCounter = drinkCounter2;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        new MainActivity_IPPS();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog.Builder title = builder.setTitle((CharSequence) "Keep on going...");
        title.setMessage((CharSequence) "Drink " + getDrinkCounter() + "!").setPositiveButton((CharSequence) "Continue", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                IppsDialog.this.listener.onYesClicked();
            }
        }).setNegativeButton((CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                IppsDialog.this.listener.onNoClicked();
            }
        });
        return builder.create();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }
}
