package com.example.swpsourdough;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class mainFeedFragment extends Fragment {

  LocalBroadcastManager localBroadcastManager;
  int lastShownPost = 99999999;
  private BreadListItemAdapter adapter;
  private APIConnection breadGetter;
  private ArrayList<BreadListItem> InitialBreadListItemsArray;
  private LinearLayoutManager mLayoutManager;
  private volatile boolean loading;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    // Create database connection
    breadGetter = new APIConnection();

    breadGetter.getBreads();

    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.main_feed_fragment_alt, container, false);
  }

  @SuppressLint("SetTextI18n")
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    InitialBreadListItemsArray = new ArrayList<>();

    // set up the RecyclerView
    RecyclerView recyclerView = view.findViewById(R.id.rvBreads);

    mLayoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(mLayoutManager);

    adapter = new BreadListItemAdapter(getContext(), InitialBreadListItemsArray);

    recyclerView.setAdapter(adapter);

    View button = view.findViewById(R.id.loadMoreButton);

    button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        breadGetter.getBreads(lastShownPost);
      }
    });
  }


  public class APIConnection {

    // Get database url for new posts from resources
    String url = getString(R.string.database_url) +
        getString(R.string.database_post_endpoint);

    // Create a URL request queue
    RequestQueue queue;

    APIConnection() {

      // Create request queue
      this.queue = Volley.newRequestQueue(requireContext());

    }

    void getBreads(int startIndex) {

      while (loading) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      startIndex = lastShownPost;

      String queryURL = url + "?last_shown_post=" + startIndex;
      _getBreads(queryURL);
    }

    void getBreads() {

      _getBreads(url);

    }

    void _getBreads(String queryURL) {
      loading = true;
      StringRequest stringRequest = new StringRequest(Request.Method.GET, queryURL,
          new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
              // Handle the response

              // Try to parse JSONObject
              JSONArray breadEntries;
              try {
                breadEntries = (new JSONObject(response)).getJSONArray("rows");
              } catch (JSONException e) {
                e.printStackTrace();
                loading = false;
                return;
              }
              Log.d("Lars", "JSON parsed successfully, rows extracted successfully");

              // Try to add each entry to adapter
              for (int iBreadEntry = 0; iBreadEntry < breadEntries.length(); iBreadEntry++) {
                try {

                  JSONObject currentJSONEntry = breadEntries.getJSONObject(iBreadEntry);

                  Log.d("Lars", currentJSONEntry.toString());

                  // Create bread object
                  BreadListItem breadListItem = new BreadListItem(
                      currentJSONEntry.getInt("id"),
                      new String(currentJSONEntry.getString("baker").getBytes(
                          StandardCharsets.ISO_8859_1),
                          StandardCharsets.UTF_8),
                      currentJSONEntry.getInt("taste"),
                      currentJSONEntry.getInt("crunch"),
                      currentJSONEntry.getInt("fluff"),
                      currentJSONEntry.getInt("pretty"),
                      currentJSONEntry.getInt("hydration"),
                      currentJSONEntry.getString("flour"),
                      currentJSONEntry.getString("creation_time"),
                      currentJSONEntry.getString("starter"),
                      currentJSONEntry.getString("image_url")
                  );
                  InitialBreadListItemsArray.add(breadListItem);

                  lastShownPost = currentJSONEntry.getInt("id");

                } catch (JSONException e) {
                  e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
              }
              loading = false;
            }
          },
          new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              // Handle the error response
              Log.e("Lars", error.toString());
              loading = false;
            }
          });

      this.queue.add(stringRequest);
    }


  }
}
