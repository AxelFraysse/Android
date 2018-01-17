package com.example.axel.appli_vinted;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.http.RequestQueue;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import java.util.Queue;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import static android.Manifest.permission.READ_CONTACTS;
import static com.android.volley.toolbox.Volley.newRequestQueue;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    final static String url = "http://localhost:8000";

    // UI references.

    private View mProgressView;
    private View mLoginFormView;

    EditText mUserName = (EditText) findViewById(R.id.txtUserName);
    EditText mPassword = (EditText) findViewById(R.id.txtPassword);
    final TextView mTxtDisplay = (TextView) findViewById(R.id.text_view_id);
    HttpClient client;
    HttpResponse response;

    public class postJSON extends AsyncTask<String, Integer, String>{
        private View mProgressView;
        private View mLoginFormView;
        final static String url = "http://localhost:8000";
        EditText mUserName = (EditText) findViewById(R.id.txtUserName);
        EditText mPassword = (EditText) findViewById(R.id.txtPassword);
        HttpClient client;
        HttpResponse response;


        @Override
        protected String doInBackground(String... strings) {

            try{
                HttpPost post = new HttpPost(url.toString());

                //préparation de la valeur qui sera envoyée au serveur
                List <NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username",""+mUserName));
                nameValuePairs.add(new BasicNameValuePair("password",""+mPassword));

                //précise qu'on est en JSON
                post.setHeader("Accept","application/json"); //vérifier qu'on l'utilise bien comme ça
                post.setHeader("Content-type","application/json ; charset UTF-8");

                post.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));

                //execute the post
                HttpResponse r = client.execute(post);


                //check the status
                int status = r.getStatusLine().getStatusCode();


                if(status == 200){ //càd success
                    return "success";
                }
                else{
                    Log.i("My app","failed to post to the server");
                    return null;
                }

            } catch (Exception ex){
                return null;
            }

        }


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                HttpParams httpParameters = new BasicHttpParams();

                //si la connection de l'utilisateur est très lente
                int timeoutsocket = 20000;
                int timeoutconnection = 20000;

                HttpConnectionParams.setConnectionTimeout(httpParameters,timeoutsocket);
                HttpConnectionParams.setSoTimeout(httpParameters,timeoutconnection);


                client = new DefaultHttpClient(httpParameters);

                try{
                  new postJSON().execute("some value");
                    mTxtDisplay.setText("Response: " + response.toString());


                }catch(Exception ex){
                    Log.i("My app","Error in onCreate .."+ex.toString());
                }

                                    }


        });

    }}
