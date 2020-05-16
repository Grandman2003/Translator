package com.example.translator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.translator.Request;
import com.example.translator.YaTransTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    static public HashMap<String, String> langs;
    static public Spinner langt;
    TextView tb;
    static public EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = findViewById(R.id.tobe);
        langs = new HashMap<>();
        et = findViewById(R.id.text);
        createHashLangs();
        dispLangs();
    }

    private void createHashLangs() {
        for (int i = 0; i < getResources().getStringArray(R.array.langs).length; i++) {
            langs.put(getResources().getStringArray(R.array.laungs)[i],getResources().getStringArray(R.array.langs)[i]);
        }
    }

    public void dispLangs() {
        langt = findViewById(R.id.tospin);
        langt.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.laungs)));
    }


    public void displayResult(String text) {
        TextView tv = findViewById(R.id.resultation);
        Log.d("TransResult", text);
        tv.setText(text);
    }

    public void onClick(View v) throws ExecutionException, InterruptedException {
        if(tb.getText().toString().equals("")) {
            String txt = et.getText().toString();
            Request req = new Request(txt, langs.get("Шведский") + "-" + langs.get(langt.getSelectedItem().toString()));
            YaTransTask task = new YaTransTask(this);
            task.execute(req);
        }
    }
}
