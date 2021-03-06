package com.example.test.nuvoco3.lead;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.nuvoco3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.test.nuvoco3.signup.LoginActivity.DATABASE_URL;

public class ViewCustomerActivity extends AppCompatActivity {
    private static final String TAG = "NUVOCO";
    public static ArrayList<Customer> mCustomerArrayList;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefresh;
    String mAddress, mArea, mDistrict, mCategory, mEmail, mPhone, mState, mCreatedBy, mCreatedOn, mUpdatedBy, mUpdatedOn, mId, mName, mStatus;
    CustomerAdapter mAdapter;
    RequestQueue queue;
    SearchView mSearchView;
    String mSearchText = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        queue = Volley.newRequestQueue(this);
        initializeViews();
        mCustomerArrayList = new ArrayList<>();
        readData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.getItemAnimator();
        mAdapter = new CustomerAdapter(this, mCustomerArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(TAG, "onQueryTextSubmit: " + query);
                mCustomerArrayList.clear();
                mSearchText = query;
                readData();

                mRecyclerView.setAdapter(mAdapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSearchText = "";
                readData();
                Log.i(TAG, "onRefresh: " + "test");
                mSwipeRefresh.setRefreshing(false);
            }

        });

    }

    private void initializeViews() {

        mRecyclerView = findViewById(R.id.recyclerView);
        mSwipeRefresh = findViewById(R.id.swipeRefreshLayout);
        mSearchView = findViewById(R.id.searchView);
    }


    private void readData() {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                DATABASE_URL + "/dispCust", null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("lol", "onResponse:  " + response);
                try {
                    JSONArray jsonArray = response.getJSONArray("message");
                    for (int i = 0; i < 20; i++) {

                        JSONObject object = jsonArray.getJSONObject(i);
                        if (object.getString("c_email").toLowerCase().contains(mSearchText.toLowerCase())
                                || object.getString("c_phone").toLowerCase().contains(mSearchText.toLowerCase())
                                || object.getString("c_state").toLowerCase().contains(mSearchText.toLowerCase())
                                || object.getString("name").toLowerCase().contains(mSearchText.toLowerCase())
                                || object.getString("record_id").toLowerCase().contains(mSearchText.toLowerCase())) {
                            mAddress = object.getString("address") + "";
                            mArea = object.getString("c_area") + "";
                            mCategory = object.getString("c_category") + "";
                            mDistrict = object.getString("c_district") + "";
                            mEmail = object.getString("c_email") + "";
                            mPhone = object.getString("c_phone") + "";
                            mState = object.getString("c_state") + "";
                            mName = object.getString("name") + "";
                            mId = object.getString("record_id") + "";
                            mStatus = object.getString("status") + "";
                            mCreatedBy = object.getString("createdBy") + "";
                            mCreatedOn = object.getString("createdOn") + "";
                            mUpdatedBy = object.getString("updatedBy") + "";
                            mUpdatedOn = object.getString("updatedOn") + "";
                            Log.i(TAG, "onResponse: " + mAddress);
                            mCustomerArrayList.add(new Customer(mName, mId, mCategory, mAddress, mArea, mDistrict, mState, mPhone, mEmail, mStatus, mCreatedBy, mCreatedOn, mUpdatedBy, mUpdatedOn));
                            mAdapter.notifyDataSetChanged();
                        }

                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();
                    e1.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("lol", "Error with Internet : " + error.getMessage());
                // hide the progress dialog
            }
        });

        // Adding request to request queue
        queue.add(jsonObjReq);
    }




}
