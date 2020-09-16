package com.example.newdoc2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.example.newdoc2.ElementMain.devicehigh;
public class Chat_activity extends AppCompatActivity {
EditText editText;
    MessageAdapter adap;
    ListView simpleList;
   static int high_chat,width_chat;
    String BASE_URL = "https://reqres.in";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activity);

        //to chang high dynamic
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) this).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //if you need three fix imageview in width
        width_chat = displaymetrics.widthPixels / 12;
        high_chat=displaymetrics.heightPixels / 22;


        editText = (EditText) findViewById(R.id.editText);
        simpleList = (ListView)findViewById(R.id.messages_view);
        adap=new MessageAdapter(this);
        simpleList.setAdapter(adap);
        Message mass=new Message("مرحبا نيفو","samer",false,false);
        adap.add(mass);
        Message mass2=new Message("كيف حالك انا آنا","samer",false,false);
        adap.add(mass2);
        Message mass3=new Message("سنقضي وقتا ممتعاً لتحسين مزاجك","samer",false,false);

        adap.add(mass3);

    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            //scaledrone.publish("observable-room", message);
           Message mass=new Message(message,"i",true,false);
            adap.add(mass);
            simpleList.setSelection(simpleList.getCount() - 1);
            editText.getText().clear();
            Message masss=new Message("","",false,true);
            adap.add(masss);
        }
    }
    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof NetworkError) {
                Toast.makeText(getApplicationContext(), "No network available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };
    public  void GETStringRequest()
    {

        VolleyLog.DEBUG = true;
        RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        String uri_page_one = String.format(BASE_URL + "/api/users?page=%1$s");
       // final String uri_page_two = String.format(BASE_URL + "/api/users?page=%1$s", page_2);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri_page_one, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            /*    VolleyLog.wtf(response, "utf-8");
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                UserList userList = mGson.fromJson(response, UserList.class);
                mUserDataList.addAll(userList.userDataList);
                ++numberOfRequestsCompleted;*/

            }
        }, errorListener) {

            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }

        };

        queue.add(stringRequest);





        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {

            @Override
            public void onRequestFinished(Request<Object> request) {
                try {
                    if (request.getCacheEntry() != null) {
                        String cacheValue = new String(request.getCacheEntry().data, "UTF-8");
                        VolleyLog.d(request.getCacheKey() + " " + cacheValue);

                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        });

    }
    public void GETSJSONRequest(){

            VolleyLog.DEBUG = true;
            RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
           // String uri_page_one = String.format(BASE_URL + "/api/users?page=%1$s", page_1);
            final String uri_page_two = String.format(BASE_URL + "/api/users?page=%1$s");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(uri_page_two, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                VolleyLog.wtf(response.toString(), "utf-8");
           /*     GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();

                UserList userList = mGson.fromJson(response.toString(), UserList.class);
                mUserDataList.addAll(userList.userDataList);
                ++numberOfRequestsCompleted;*/

            }
        }, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };

        queue.add(jsonObjectRequest);

        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {

            @Override
            public void onRequestFinished(Request<Object> request) {
                try {
                    if (request.getCacheEntry() != null) {
                        String cacheValue = new String(request.getCacheEntry().data, "UTF-8");
                        VolleyLog.d(request.getCacheKey() + " " + cacheValue);

                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        });
    }


    private void POSTStringequest() {

        RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();

        VolleyLog.DEBUG = true;
        String uri = BASE_URL + "/api/users";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                VolleyLog.wtf(response);
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

            }
        }, errorListener) {
            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }

            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
             //parm to send
               /* params.put("name", "Anupam");
                params.put("job", "Android Developer");*/


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                return headers;
            }

        };




        StringRequest stringRequestPOSTJSON = new StringRequest(Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                VolleyLog.wtf(response);
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

            }
        }, errorListener) {
            @Override
            public Priority getPriority() {
                return Priority.HIGH;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name", "Android Tutorials");
                    jsonObject.put("job", "To implement Volley in an Android Application.");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String requestBody = jsonObject.toString();


                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }


        };

        queue.add(stringRequest);
        queue.add(stringRequestPOSTJSON);


    }

    private void POSTJSONRequest() {

        RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();

        VolleyLog.DEBUG = true;
        String uri = BASE_URL + "/api/users";




        JSONObject jsonObject = new JSONObject();
       /* try {
            jsonObject.put("name", "JournalDev.com");
            jsonObject.put("job", "To teach you the best");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(uri, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.wtf(response.toString());
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

            }
        }, errorListener) {

            @Override
            public int getMethod() {
                return Method.POST;
            }

            @Override
            public Priority getPriority() {
                return Priority.NORMAL;
            }
        };


        StringRequest stringRequestPOSTJSON = new StringRequest(Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                VolleyLog.wtf(response);
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

            }
        }, errorListener) {
            @Override
            public Priority getPriority() {
                return Priority.HIGH;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name", "Android Tutorials");
                    jsonObject.put("job", "To implement Volley in an Android Application.");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String requestBody = jsonObject.toString();


                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }


        };

        queue.add(jsonObjectRequest);
        queue.add(stringRequestPOSTJSON);


    }
}
