package com.example.khuinkhujik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ShowJobActivity extends AppCompatActivity {
    private static ArrayList<ArrayList<String>> jobList = new ArrayList<ArrayList<String>>();
    private static TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_job);
        textView = (TextView) findViewById(R.id.textView);
        try {
            readDataFromCsv();
            textView.setText("d");
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public void readDataFromCsv() throws IOException, CsvValidationException {
        InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.csv1));
        BufferedReader reader = new BufferedReader(is);
        CSVReader read = new CSVReader(reader);
        String[] record = null;
        while ((record = read.readNext()) != null){
            textView.setText(record[0]);
        }
    }
}