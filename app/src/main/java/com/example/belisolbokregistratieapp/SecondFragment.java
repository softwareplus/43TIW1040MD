package com.example.belisolbokregistratieapp;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class SecondFragment extends Fragment
{
    View view;
    Button btnRaamBok;
    ProgressBar progressBar;

    EditText txtOrderNumber, txtColliQty, txtRegistrationDate;

    String OrderNumber,ColliQty,RegistrationDate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_second, container, false);
        // get the reference of Button
        btnRaamBok = (Button) view.findViewById(R.id.btnRaamBok);


        progressBar     = view.findViewById(R.id.progressBar);

        txtOrderNumber = view.findViewById(R.id.txtOrderNumber);
        txtColliQty = view.findViewById(R.id.txtColliQty);
        txtRegistrationDate = view.findViewById(R.id.txtDate);


        // perform setOnClickListener on first Button
        btnRaamBok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // display a message by using a Toast
                OrderNumber = txtOrderNumber.getText().toString();
                ColliQty = txtColliQty.getText().toString();
                RegistrationDate = txtRegistrationDate.getText().toString();

                //controleer input and stuur door
                if(TextUtils.isEmpty(OrderNumber))
                {
                    txtOrderNumber.setError("Email is verplicht!");
                    return;
                }

                //check date and colli quantity
                //if all valid, save

                progressBar.setVisibility(View.VISIBLE);




                Toast.makeText(getActivity(), "test insert", Toast.LENGTH_SHORT).show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_ADD, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.getString("message").equals("Edit Data Successful")){
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //toon foutmelding
                    }
                }){
                    protected Map<String , String> getParams() throws AuthFailureError {
                        Map<String , String> params = new HashMap<>();
                        params.put("OrderNumber", OrderNumber);
                        params.put("ColliQty", ColliQty);
                        params.put("RegistrationDate", RegistrationDate);
                        return params;
                    }
                };



                RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);


                progressBar.setVisibility(View.INVISIBLE);
                getActivity().getFragmentManager().beginTransaction().remove(SecondFragment.this).commit();

            }
        });
        return view;
    }
}
