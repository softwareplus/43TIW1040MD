package com.example.belisolbokregistratieapp;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {
    EditText name, address, phone, opmerking, id;
    String Name, Address, Phone, Opmerking,Id;
    Button button;
    Boolean valid = true;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //Bespreking met Ben : 2020-01-21
        //nu enkel opmeking updaten
        //In tweede fase, als we met rechten gaan werken,
        //moeten we alle velden kunnen updaten

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.phone);
        opmerking = (EditText) findViewById(R.id.opmerking);
        id = (EditText) findViewById(R.id.id);
        progressDialog = new ProgressDialog(this);
        button = (Button) findViewById(R.id.button);

        Id = getIntent().getStringExtra("id");

        name.setText(getIntent().getStringExtra("name"));
        address.setText(getIntent().getStringExtra("address"));
        phone.setText(getIntent().getStringExtra("phone"));
        opmerking.setText(getIntent().getStringExtra("opmerking"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name = name.getText().toString();
                Address = address.getText().toString();
                Phone = phone.getText().toString();
                Opmerking = opmerking.getText().toString();


                valid=true;


                /*
                IN VOLGENDE FASE UID MEENEMEN EN OPVANGEN

                if(TextUtils.isEmpty(Uid)){
                    uid.setError("UID Cannot be Empty");
                    valid = false;
                }
                 */

                if(valid){
                    progressDialog.setMessage("Loading");
                    progressDialog.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_UPDATE, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(EditActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                if(jsonObject.getString("message").equals("Edit Data Successful")){
                                    ListActivity.ma.refresh_list();
                                    //finish();
                                    finishAffinity();
                                }
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(EditActivity.this, "Failed",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        protected Map<String , String> getParams() throws AuthFailureError {
                            Map<String , String> params = new HashMap<>();
                            params.put("id", Id);
                            params.put("opmerking", Opmerking);
                            return params;
                        }
                    };
                    RequestHandler.getInstance(EditActivity.this).addToRequestQueue(stringRequest);

                }
            }
        });
    }
}
