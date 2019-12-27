package com.example.internal_files_yodalef;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author Dvir Dadon
 * @since 24/12/2019
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {
    EditText ET;
    TextView tV;
    String st="",line="",strrd="";
    /**
     * @since 24/12/2019
     * @param savedInstanceState
     * The onCreate check the last data the user wrote on the text
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ET = findViewById(R.id.ET);
        tV= findViewById(R.id.tV);
        try {
            FileInputStream fis= openFileInput("String.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            line = br.readLine();
            while (line != null) {
                sb.append(line+'\n');
                line = br.readLine();
            }
            strrd=sb.toString();
            isr.close();
            tV.setText("Text:"+strrd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @since 24/12/2019
     * @param view
     * Writing to a text file String.txt from the text the user wrote
     */

    public void Save(View view) {
        st=st+ET.getText().toString();
        tV.setText("Text:"+strrd+st);
        try {
            FileOutputStream fos = openFileOutput("String.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(st);
            bw.write(strrd);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @since 24/12/2019
     * @param view
     * reset the file test.txt to null
     */

    public void Reset(View view) {
        try {
            FileOutputStream fos = openFileOutput("test.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ET.setText("");
        st="";
        strrd="";
        tV.setText("Text:");
    }

    /**
     * @since 24/12/2019
     * @param view
     * Writing to a text file String.txt from the text the user wrote and exit the application
     */

    public void Exit(View view) {
        try {
            FileOutputStream fos = openFileOutput("String.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(st);
            bw.write(strrd);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
    }


}
