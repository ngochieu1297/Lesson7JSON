package com.example.admin.lesson7json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnExecuteComplete {
    public static final String RESPONSE_TYPE = "type";
    public static final String RESPONSE_AVATAR_URL = "avatar_url";
    public static final String RESPONSE_OWNER = "owner";
    public static final String RESPONSE_ID = "id";
    public static String Url = "https://api.github.com/users/google/repos";
    public static String METHOD_REQUEST_API = "GET";
    private List<Owner> mOwners = new ArrayList<>();
    private RecyclerView mRecyclerOwner;
    private OwnerAdapter mOwnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        new OwnerStorage(this, this).execute();
    }

    private void initView() {
        mRecyclerOwner = findViewById(R.id.recycler_owners);
        mOwnerAdapter = new OwnerAdapter(this, mOwners);
        mRecyclerOwner.setAdapter(mOwnerAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerOwner.setLayoutManager(layoutManager);
    }

    @Override
    public void onSuccess(List<Owner> owners) {
        mOwners.addAll(owners);
        mOwnerAdapter.notifyDataSetChanged();
    }
}
