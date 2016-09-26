package com.esmael.interviewapp;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONObject;

import java.util.List;

import Adapters.DataAdapter;
import HTTPConnection.HttpDataRequest;
import HelperClasses.GlobalVariables;
import HelperClasses.StatusValues;
import JSONHandler.JsonHandler;
import Models.Data;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

public class ScrollingActivity extends AppCompatActivity {
    public RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.data_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        JSONObject jsonObject = new JSONObject();
    Toast.makeText(this,
            "Data Loading please wait"
            ,Toast.LENGTH_LONG).show();
        //Fetch data
        new GetDataAsyncTask().execute(jsonObject);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "hi, Thanks for your consideration", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //Async task to send a request for data
    private class GetDataAsyncTask extends AsyncTask<JSONObject, Integer, String> {
        JsonHandler jsonHandler = new JsonHandler();

        @Override
        protected String doInBackground(JSONObject... params) {
            try {
                JSONObject login_json = params[0];
                //Provide the URL where the data is fetched
                String response = new HttpDataRequest().GetJSONDataFromAPI(GlobalVariables.API_ACCESS_URL);
                return response;
            } catch (Exception exc) {
                Toast.makeText(ScrollingActivity.this, "Error Getting Data, please try again", Toast.LENGTH_LONG).show();
                System.out.println("Scrolling activity#GetDataAsyncTask#doInBackground exc : " + exc.getMessage());
                exc.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String result) {
            try {

                if (result != null) {
                    List<Data> data = new JsonHandler().getDataFromJsonString(result.trim());
                    if (data != null) {

                        mRecyclerView.setAdapter(new EasyRecyclerAdapter<Data>(
                                ScrollingActivity.this,
                                DataAdapter.class,
                                data));


                    } else {
                        Toast.makeText(getApplicationContext(), StatusValues.FAILED_ATTEMPT_TO_RETRIEVE_DATA.getName(), Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), StatusValues.FAILED_ATTEMPT_TO_RETRIEVE_DATA.getName(), Toast.LENGTH_LONG).show();

                }


            } catch (Exception exc) {

                Toast.makeText(getApplicationContext(), StatusValues.FAILED_ATTEMPT_TO_RETRIEVE_DATA.getName(), Toast.LENGTH_LONG).show();
                exc.printStackTrace();
            }
        }

    }
}
