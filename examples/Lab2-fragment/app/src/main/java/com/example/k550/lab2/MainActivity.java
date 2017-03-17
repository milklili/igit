package com.example.k550.lab2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> myForecastArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FetchWeatherTask fetchWeatherTask = new FetchWeatherTask();
        fetchWeatherTask.execute("http://mpianatra.com/Courses/files/data.json");
        String[] forecastArray = {"Today - Sunny - 55/ 63", "Tomorrow - Foggy - 70/46", "Weds - Cloudy - 72 / 63",
                "Thursday - Rainy - 64 / 51", "Friday - Foggy - 70 / 46", "Saturday - Sunny - 76 / 68"};


        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));


        myForecastArrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.layout_each_item,
                R.id.tv_element_list,
                weekForecast);

        ListView myListView = (ListView) findViewById(R.id.listView_forecast);

        myListView.setAdapter(myForecastArrayAdapter);


    }


    public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();


        @Override
        protected String[] doInBackground(String... params) {

            String link = params[0];

            if (params.length == 0) {
                link = "http://mpianatra.com/Courses/files/data.json";
                return null;
            }


            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String cleanNewsJsonStr = null;


            try {

                final String BASE_URL = link;


                URL url = new URL(BASE_URL);


                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));


                String line;
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                cleanNewsJsonStr = buffer.toString();

            } catch (IOException e) {
                Log.e("TAG", "Error ", e);

                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("TAG", "Error closing stream", e);
                    }
                }
            }

            String[] newsTwentyDaysArray = new String[0];
            try {
                newsTwentyDaysArray = getNewsTitlesDataFromJson(cleanNewsJsonStr, 20);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return newsTwentyDaysArray;
        }


        @Override
        protected void onPostExecute(String[] newsTwentyDaysArray) {

            try {
                Log.e(LOG_TAG, "newsTwentyDaysArray: " + newsTwentyDaysArray);

                for (int i = 0; i < newsTwentyDaysArray.length; i++) {
                    String everyday = newsTwentyDaysArray[i];

                    Log.e(LOG_TAG, "forecastEachDay: " + everyday);

                }

                List<String> stringList = new ArrayList<String>(Arrays.asList(newsTwentyDaysArray));
                myForecastArrayAdapter.clear();
                myForecastArrayAdapter.addAll(stringList);


                super.onPostExecute(newsTwentyDaysArray);
            }catch (Exception Net){}
        }

    }


    private String[] getNewsTitlesDataFromJson(String newsJsonStr, int numNews)
            throws JSONException {


        final String OWM_LIST = "allNews";
        final String OWM_LINK = "link";
        final String OWM_TITLE = "title";

        JSONObject newsJsonObject = new JSONObject(newsJsonStr);
        JSONArray linkAndTitleArray = newsJsonObject.getJSONArray(OWM_LIST);


        String[] resultStrs = new String[numNews];
        for (int i = 0; i < linkAndTitleArray.length(); i++) {

            JSONObject news = linkAndTitleArray.getJSONObject(i);


            String link = (String) news.get(OWM_LINK);
            String title = (String) news.get(OWM_TITLE);

            resultStrs[i] = (i + 1) + " - " + title;
        }

        for (String s : resultStrs) {
            Log.v("LOG_TAG", "Forecast entry: " + s);
        }
        return resultStrs;

    }

}

