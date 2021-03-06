package com.example.test.nuvoco3.lead;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.test.nuvoco3.R;

import static com.example.test.nuvoco3.lead.ViewCustomerActivity.mCustomerArrayList;

public class CustomerDetailsActivity extends AppCompatActivity {
    TextView mName, mType, mCategory, mStatus, mPhone, mEmail, mArea, mDistrict, mState, mAddress, mCreatedBy, mCreatedOn, mUpdatedBy, mUpdatedOn;
    TextView mNewContact, mNewComplaint, mNewCampaign, mNewPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Customer Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initializeViews();
        displayData();


    }

    //  Displays details of a particular adapter from the ID received from the recycler view
    private void displayData() {

        int position = getIntent().getIntExtra("position", -1);
        mName.append(mCustomerArrayList.get(position).getCustomerName());
        mType.setText(mCustomerArrayList.get(position).getCustomerId());
        mCategory.append(mCustomerArrayList.get(position).getCustomerCategory());
        mStatus.append(mCustomerArrayList.get(position).getCustomerStatus());
        mPhone.append(mCustomerArrayList.get(position).getCustomerPhoneno());
        mEmail.append(mCustomerArrayList.get(position).getCustomerEmailId());
        mArea.append(mCustomerArrayList.get(position).getCustomerArea());
        mDistrict.append(mCustomerArrayList.get(position).getCustomerDistrict());
        mState.append(mCustomerArrayList.get(position).getCustomerState());
        mAddress.append(mCustomerArrayList.get(position).getCustomerAddress());
        mCreatedBy.append(mCustomerArrayList.get(position).getCustomerCreatedBy());
        mCreatedOn.append(mCustomerArrayList.get(position).getCustomerCreatedOn());
        mUpdatedBy.append(mCustomerArrayList.get(position).getCustomerUpdatedBy());
        mUpdatedOn.append(mCustomerArrayList.get(position).getCustomerUpdatedOn());
    }

    //  Initialize Views
    private void initializeViews() {
        mName = findViewById(R.id.textViewName);
        mType = findViewById(R.id.textViewType);
        mCategory = findViewById(R.id.textViewCategory);
        mStatus = findViewById(R.id.textViewStatus);
        mPhone = findViewById(R.id.textViewPhone);
        mEmail = findViewById(R.id.textViewEmail);
        mArea = findViewById(R.id.textViewArea);
        mDistrict = findViewById(R.id.textViewDistrict);
        mState = findViewById(R.id.textViewState);
        mAddress = findViewById(R.id.textViewAddress);
        mCreatedBy = findViewById(R.id.textViewCreatedBy);
        mCreatedOn = findViewById(R.id.textViewCreatedOn);
        mUpdatedBy = findViewById(R.id.textViewUpdatedBy);
        mUpdatedOn = findViewById(R.id.textViewUpdatedOn);
        mNewContact = findViewById(R.id.textViewContact);
        mNewComplaint = findViewById(R.id.textViewComplaint);
        mNewCampaign = findViewById(R.id.textViewCampaign);
        mNewPrice = findViewById(R.id.textViewPrice);
    }
}
