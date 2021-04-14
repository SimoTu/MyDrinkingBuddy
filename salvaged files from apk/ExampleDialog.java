package com.example.mydrink;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog extends AppCompatDialogFragment {
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

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle((CharSequence) "Play a new game ?").setCancelable(false).setMessage((CharSequence) "You sick fuck...").setPositiveButton((CharSequence) "Hell yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ExampleDialog.this.listener.onYesClicked();
            }
        }).setNegativeButton((CharSequence) "I am a loser", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ExampleDialog.this.listener.onNoClicked();
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
