package com.example.swpsourdough;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mainFeedFragment extends Fragment {

    BreadListItemView adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater
                .inflate(R.layout.main_feed_fragment_alt, container, false);
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Foldable items with callbacks ===============================================

        //        final TextView descriptionText =
        //                (TextView) view.findViewById(R.id.detail_description_content);
        //        final TextView showAll = (TextView) view.findViewById(R.id.detail_read_all);
        //
        //        descriptionText.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //
        //                if (showAll.getVisibility() == View.INVISIBLE) {
        //                    showAll.setVisibility(View.VISIBLE);
        //
        //                } else if (showAll.getVisibility() == View.VISIBLE) {
        //                    showAll.setVisibility(View.INVISIBLE);
        //                }
        //
        //
        //                descriptionText.setMaxLines(Integer.MAX_VALUE);
        //            }
        //        });

        // Programmatically populating lists with custom views =========================

        //        // Get current activity
        //        Activity currentActivity = getActivity();
        //        assert currentActivity != null;
        //        Log.d("Lars", currentActivity.toString()); // Echo the name
        ////
        ////
        //        // Get reference to the list into which we want to add breads
        //        ListView breadListView =
        //                (ListView) view.findViewById(R.id.BakersColumnPopulate);
        //
        //        // Create an initial List from String Array elements
        //        final List<String> fruits_list =
        //                new ArrayList<String>(Collections.<String>emptyList());
        //
        //
        //        final ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>
        //                (currentActivity, R.layout.bread_list_item, R.id.breadListBaker,
        //                        fruits_list);
        //
        //        breadListView.setAdapter(mArrayAdapter);
        //
        //        for (int i = 0; i < 30; i++) {
        //            fruits_list.add(0, "#" + Integer.toString(i));
        //        }
        //
        //        breadListView.setSelectionFromTop(0, 0);


        // Recyclerview ================================================================

        ArrayList<BreadListItem> InitialBreadListItemsArray = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            InitialBreadListItemsArray.add(new BreadListItem(i));

        }

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rvAnimals);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter =
                new BreadListItemView(getContext(), InitialBreadListItemsArray);

        recyclerView.setAdapter(adapter);

    }

    public void delayStuff() {

        //        int delay_time_posts_ms =
        //                getResources().getInteger(R.integer.delay_time_posts_ms);
        //        Log.d("Lars", String.format("Getting posts in %s seconds",
        //                ((float) delay_time_posts_ms) / 1000.0));
        //        new Handler().postDelayed(new Runnable() {
        //            public void run() {
        //                getPosts();
        //            }
        //        }, delay_time_posts_ms);

    }


    public void getPosts() {

        //
        //        // Get database url for new posts from resources
        //                String url = getString(R.string.database_url) +
        //                        getString(R.string.database_post_endpoint);
        //
        //        // Create a URL request queue
        //        RequestQueue queue = Volley.newRequestQueue(getActivity());
        //
        //        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        //                new Response.Listener<String>() {
        //                    @Override
        //                    public void onResponse(String response) {
        //                        // enjoy your response
        //                        Log.d("Lars", "Response from the web!");
        //                        //                        Log.d("Lars", response);
        //                        try {
        //                            JSONObject parsedResponse =
        //                                    new JSONObject(response);
        //                            Log.d("Lars", "JSON parsed successfully");
        //
        //                            JSONArray rowArray =
        //                                    parsedResponse.getJSONArray("rows");
        //
        //                            ListView listview = findViewById(R.id.BakersColumn);
        //
        //                            String[] ListElements = new String[]{};
        //
        //                            final List<String> ListElementsArrayList =
        //                                    new ArrayList<>(
        //                                            Arrays.asList(ListElements));
        //                            final ArrayAdapter<String> adapter =
        //                                    new ArrayAdapter<>(getActivity(),
        //                                            android.R.layout.simple_list_item_1,
        //                                            ListElementsArrayList);
        //                            listview.setAdapter(adapter);
        //
        //                            for (int i_row = 0; i_row < rowArray.length();
        //                                 i_row++) {
        //
        //                                String baker = rowArray.getJSONObject(i_row)
        //                                        .getString("baker");
        //
        //                                baker = new String(baker.getBytes(
        //                                        StandardCharsets.ISO_8859_1),
        //                                        StandardCharsets.UTF_8);
        //
        //                                Log.d("Lars", baker);
        //
        //                                ListElementsArrayList.add(baker);
        //                                adapter.notifyDataSetChanged();
        //                            }
        //
        //                        } catch (JSONException e) {
        //                            e.printStackTrace();
        //                        }
        //                    }
        //                }, new Response.ErrorListener() {
        //            @Override
        //            public void onErrorResponse(VolleyError error) {
        //                // enjoy your error status
        //                Log.d("Lars", "No response, but got a nice error code.");
        //                Log.d("Lars", error.toString());
        //            }
        //        });
        //
        //        queue.add(stringRequest);
    }

    public void createSnackbar() {
        //        Log.d("Lars", "Resumed the app");
        //
        //
        //        // Get relevant view (main coordinator is a CoordinatorView in which everything lives)
        //        View view = findViewById(R.id.main_coordinator);
        //
        //        // Create the snackbar
        //        Snackbar welcome_back_snackbar =
        //                Snackbar.make(view, "Hello again", Snackbar.LENGTH_SHORT);
        //
        ////        welcome_back_snackbar.setAction();
        //
        //        // Show the snackbar
        //        welcome_back_snackbar.show();

    }


}
