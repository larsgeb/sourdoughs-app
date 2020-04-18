package com.example.swpsourdough;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Snackbar.make(view, "Loading the latest breads...",
                        Snackbar.LENGTH_SHORT).setAction("Action", loadBreads())
                        .show();
            }
        });


        int delay_time_posts_ms =
                getResources().getInteger(R.integer.delay_time_posts_ms);
        Log.d("Lars", String.format("Getting posts in %s seconds",
                ((float) delay_time_posts_ms) / 1000.0));
        new Handler().postDelayed(new Runnable() {
            public void run() {
                getPosts();
            }
        }, delay_time_posts_ms);


    }

    public void getPosts() {


        // Get database url for new posts from resources
        String url = getString(R.string.database_url) +
                getString(R.string.database_post_endpoint);

        // Create a URL request queue
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override public void onResponse(String response) {
                        // enjoy your response
                        Log.d("Lars", "Response from the web!");
                        //                        Log.d("Lars", response);
                        try {
                            JSONObject parsedResponse =
                                    new JSONObject(response);
                            Log.d("Lars", "JSON parsed successfully");

                            JSONArray rowArray =
                                    parsedResponse.getJSONArray("rows");

                            ListView listview = findViewById(R.id.BakersColumn);

                            String[] ListElements = new String[]{};

                            final List<String> ListElementsArrayList =
                                    new ArrayList<>(
                                            Arrays.asList(ListElements));
                            final ArrayAdapter<String> adapter =
                                    new ArrayAdapter<>(MainActivity.this,
                                            android.R.layout.simple_list_item_1,
                                            ListElementsArrayList);
                            listview.setAdapter(adapter);

                            for (int i_row = 0; i_row < rowArray.length();
                                 i_row++) {

                                String baker = rowArray.getJSONObject(i_row)
                                        .getString("baker");

                                baker = new String(baker.getBytes(
                                        StandardCharsets.ISO_8859_1),
                                        StandardCharsets.UTF_8);

                                Log.d("Lars", baker);

                                ListElementsArrayList.add(baker);
                                adapter.notifyDataSetChanged();

                                //                                TextView valueTV =
                                //                                        new TextView(getApplicationContext());
                                //                                valueTV.setText(baker);
                                //                                valueTV.setId(i_row);
                                //                                valueTV.setLayoutParams(
                                //                                        new LinearLayout.LayoutParams(
                                //                                                LinearLayout.LayoutParams.FILL_PARENT,
                                //                                                LinearLayout.LayoutParams.WRAP_CONTENT));
                                //
                                //                                linearLayout.addView(valueTV);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {
                // enjoy your error status
                Log.d("Lars", "No response, but got a nice error code.");
                Log.d("Lars", error.toString());
            }
        });

        queue.add(stringRequest);
    }

    private View.OnClickListener loadBreads() {
        Log.d("Lars", "Clicked the FAB");
        return null;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d("Lars", "Clicked a button, yes!!");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
