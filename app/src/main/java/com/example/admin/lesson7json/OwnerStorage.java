package com.example.admin.lesson7json;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OwnerStorage extends AsyncTask<Void, String, List<Owner>> {
    private String response = "";
    private ProgressDialog mProgressDialog;
    private OnExecuteComplete mListener;
    private Context mContext;

    public OwnerStorage(Context context, OnExecuteComplete listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    protected List<Owner> doInBackground(Void... voids) {
        return getData();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage(mContext.getString(R.string.string_loading));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(List<Owner> owners) {
        super.onPostExecute(owners);
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        mListener.onSuccess(owners);
    }

    private List<Owner> getData() {
        List<Owner> mOwners = new ArrayList<>();
        try {
            URL url = new URL(MainActivity.Url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(MainActivity.METHOD_REQUEST_API);
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            response = convertStreamToString(inputStream);
            JSONArray jsonArray = new JSONArray(response.toString());
            mOwners = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsObject = (JSONObject) jsonArray.get(i);
                String id = jsObject.getString(MainActivity.RESPONSE_ID);
                JSONObject ownerObject = jsObject.getJSONObject(MainActivity.RESPONSE_OWNER);
                String avatarOwner = ownerObject.getString(MainActivity.RESPONSE_AVATAR_URL);
                String typeOwner = ownerObject.getString(MainActivity.RESPONSE_TYPE);
                Owner owner = new Owner(avatarOwner, id, typeOwner);
                mOwners.add(owner);
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return mOwners;
    }

    private String convertStreamToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
