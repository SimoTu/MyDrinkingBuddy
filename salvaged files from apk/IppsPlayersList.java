package com.example.mydrink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class IppsPlayersList extends Activity {
    public static List<String> players = new ArrayList();
    EditText GetValue;
    public ArrayList<String> ListElementsArrayList = new ArrayList<>();
    Button addButton;
    Button ctnButton;
    ListView listview;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipps_players_list);
        this.listview = (ListView) findViewById(R.id.listView1);
        this.addButton = (Button) findViewById(R.id.button1);
        this.ctnButton = (Button) findViewById(R.id.btn_ctn);
        this.GetValue = (EditText) findViewById(R.id.editText1);
        this.ctnButton.setEnabled(false);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 17367056, this.ListElementsArrayList);
        this.listview.setAdapter(adapter);
        this.addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!IppsPlayersList.this.GetValue.getText().toString().equals(BuildConfig.FLAVOR) && IppsPlayersList.this.GetValue.getText().toString().length() <= 11) {
                    IppsPlayersList.this.ctnButton.setEnabled(true);
                    IppsPlayersList.this.ListElementsArrayList.add(IppsPlayersList.this.GetValue.getText().toString());
                    IppsPlayersList.players.add(IppsPlayersList.this.GetValue.getText().toString());
                    IppsPlayersList.this.GetValue.setText(BuildConfig.FLAVOR);
                    adapter.notifyDataSetChanged();
                } else if (IppsPlayersList.this.GetValue.getText().toString().equals(BuildConfig.FLAVOR)) {
                    Toast.makeText(IppsPlayersList.this, "Enter name...", 0).show();
                } else if (IppsPlayersList.this.GetValue.getText().toString().length() > 11) {
                    Toast.makeText(IppsPlayersList.this, "Max character 12...", 0).show();
                }
            }
        });
        this.listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                SparseBooleanArray deleteValue = IppsPlayersList.this.listview.getCheckedItemPositions();
                for (int item = IppsPlayersList.this.listview.getCount() - 1; item >= 0; item--) {
                    if (deleteValue.get(item)) {
                        adapter.remove(IppsPlayersList.this.ListElementsArrayList.remove(item));
                        IppsPlayersList.players.remove(item);
                    }
                }
                deleteValue.clear();
                if (IppsPlayersList.players.size() < 1) {
                    IppsPlayersList.this.ctnButton.setEnabled(false);
                } else {
                    IppsPlayersList.this.ctnButton.setEnabled(true);
                }
                return false;
            }
        });
        this.ctnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println(IppsPlayersList.players);
                IppsPlayersList.this.openActivityIPPS();
            }
        });
    }

    /* access modifiers changed from: private */
    public void openActivityIPPS() {
        startActivity(new Intent(this, MainActivity_IPPS.class));
    }
}
