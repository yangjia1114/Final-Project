package com.example.yangjia.finalproject;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button button;
    EditText edittext;
    TextView textview;
    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;

    private static final String TAG = "FinalProject:Main";
    private static RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);

        button = (Button) findViewById(R.id.button);
        edittext = (EditText) findViewById(R.id.editText);
        textview = (TextView) findViewById(R.id.textView);
        buttonOnClick();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Catogries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void buttonOnClick() {
        button.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Start API button clicked");
                startAPICall();
                final String text = spinner.getSelectedItem().toString();
                final String text1 = spinner2.getSelectedItem().toString();
                final String text2 = spinner3.getSelectedItem().toString();
                if (text.contentEquals("Length")) {
                    if (text1.contentEquals("cm") && text2.contentEquals("cm")) {
                        double result = Double.parseDouble(edittext.getText().toString());
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("cm") && text2.contentEquals("m")) {
                        double result = Double.parseDouble(edittext.getText().toString()) / 100;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("cm") && text2.contentEquals("km")) {
                        double result = Double.parseDouble(edittext.getText().toString()) / 100000;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("m") && text2.contentEquals("cm")) {
                        double result = Double.parseDouble(edittext.getText().toString()) * 100;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("m") && text2.contentEquals("m")) {
                        double result = Double.parseDouble(edittext.getText().toString());
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("m") && text2.contentEquals("km")) {
                        double result = Double.parseDouble(edittext.getText().toString()) / 1000;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("km") && text2.contentEquals("cm")) {
                        double result = Double.parseDouble(edittext.getText().toString()) * 100000;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("km") && text2.contentEquals("m")) {
                        double result = Double.parseDouble(edittext.getText().toString()) * 1000;
                        textview.setText(Double.toString(result));
                    } else {
                        double result = Double.parseDouble(edittext.getText().toString());
                        textview.setText(Double.toString(result));
                    }
                } else if (text.contentEquals("Time")) {
                    if (text1.contentEquals("s") && text2.contentEquals("s")) {
                        double result = Double.parseDouble(edittext.getText().toString());
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("s") && text2.contentEquals("min")) {
                        double result = Double.parseDouble(edittext.getText().toString()) / 60;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("s") && text2.contentEquals("hr")) {
                        double result = Double.parseDouble(edittext.getText().toString()) / 3600;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("min") && text2.contentEquals("s")) {
                        double result = Double.parseDouble(edittext.getText().toString()) * 60;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("min") && text2.contentEquals("min")) {
                        double result = Double.parseDouble(edittext.getText().toString());
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("min") && text2.contentEquals("hr")) {
                        double result = Double.parseDouble(edittext.getText().toString()) / 60;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("hr") && text2.contentEquals("s")) {
                        double result = Double.parseDouble(edittext.getText().toString()) * 3600;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("hr") && text2.contentEquals("min")) {
                        double result = Double.parseDouble(edittext.getText().toString()) * 60;
                        textview.setText(Double.toString(result));
                    } else {
                        double result = Double.parseDouble(edittext.getText().toString());
                        textview.setText(Double.toString(result));
                    }
                } else if (text.contentEquals("Weight")){
                    if (text1.contentEquals("g") && text2.contentEquals("g")) {
                        double result = Double.parseDouble(edittext.getText().toString());
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("g") && text2.contentEquals("kg")) {
                        double result = Double.parseDouble(edittext.getText().toString()) / 1000;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("g") && text2.contentEquals("t")) {
                        double result = Double.parseDouble(edittext.getText().toString()) / 1000000;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("kg") && text2.contentEquals("g")) {
                        double result = Double.parseDouble(edittext.getText().toString()) * 1000;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("kg") && text2.contentEquals("kg")) {
                        double result = Double.parseDouble(edittext.getText().toString());
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("kg") && text2.contentEquals("t")) {
                        double result = Double.parseDouble(edittext.getText().toString()) / 1000;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("t") && text2.contentEquals("g")) {
                        double result = Double.parseDouble(edittext.getText().toString()) * 1000000;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("t") && text2.contentEquals("kg")) {
                        double result = Double.parseDouble(edittext.getText().toString()) * 1000;
                        textview.setText(Double.toString(result));
                    } else {
                        double result = Double.parseDouble(edittext.getText().toString());
                        textview.setText(Double.toString(result));
                    }
                } else {
                    if (text1.contentEquals("USD") && text2.contentEquals("EUR")) {
                        double result = Double.parseDouble(edittext.getText().toString()) / 1.138246;
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("USD") && text2.contentEquals("USD")) {
                        double result = Double.parseDouble(edittext.getText().toString());
                        textview.setText(Double.toString(result));
                    } else if (text1.contentEquals("EUR") && text2.contentEquals("USD")) {
                        double result = Double.parseDouble(edittext.getText().toString()) * 1.138246;
                        textview.setText(Double.toString(result));
                    } else {
                        double result = Double.parseDouble(edittext.getText().toString());
                        textview.setText(Double.toString(result));
                    }
                }
            }
        });
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        if (text.contentEquals("Length")) {
            List<String> list = new ArrayList<String>();
            list.add("cm");
            list.add("m");
            list.add("km");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            spinner2.setAdapter(dataAdapter);
            spinner3.setAdapter(dataAdapter);
        }
        if (text.contentEquals("Time")) {
            List<String> list = new ArrayList<String>();
            list.add("s");
            list.add("min");
            list.add("hr");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            spinner2.setAdapter(dataAdapter2);
            spinner3.setAdapter(dataAdapter2);
        }
        if (text.contentEquals("Weight")) {
            List<String> list = new ArrayList<String>();
            list.add("g");
            list.add("kg");
            list.add("t");
            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter3.notifyDataSetChanged();
            spinner2.setAdapter(dataAdapter3);
            spinner3.setAdapter(dataAdapter3);
        }
        if (text.contentEquals("Exchangerate")) {
            List<String> list = new ArrayList<String>();
            list.add("EUR");
            list.add("USD");
            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter3.notifyDataSetChanged();
            spinner2.setAdapter(dataAdapter3);
            spinner3.setAdapter(dataAdapter3);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://data.fixer.io/api/latest",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
