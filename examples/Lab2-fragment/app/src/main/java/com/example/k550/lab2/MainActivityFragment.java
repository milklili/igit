package com.example.k550.lab2;

import android.app.DownloadManager;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by K550 on 2017/3/17.
 */

public class MainActivityFragment extends Fragment  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //Create a root view for the fragment
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
/*
        String[] forecastArray = {
                "Today - Sunny - 55/ 63",
                "Tomorrow - Foggy - 70/46",
                "Saturday - Cloudy - 72 / 63",
                "Sunday - Rainy - 64 / 51",
                "Monday - Foggy - 70 / 46",
                "Tuesday - Sunny - 76 / 68"};

        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));
        ArrayAdapter myForecastArrayAdapter = new ArrayAdapter<String>(
                //The current context
                getActivity(),
                //ID of the list item layout
                R.layout.layout_each_item,
                // ID of the textView to populate
                R.id.tv_element_list,
                //Forecast data
                weekForecast);
        //Reference to the listView
        ListView myListView = (ListView) rootView.findViewById(R.id.listView_forecast);
        //Set array adapter on the listView
        myListView.setAdapter(myForecastArrayAdapter);
        */

        return rootView;


    }







}
