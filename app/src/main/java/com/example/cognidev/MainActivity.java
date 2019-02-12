package com.example.cognidev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cognidev.model.CreateResponse;
import com.example.cognidev.services.CashbackService;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView mLoginText;
    private EditText mUsername;
    private EditText mEmail;
    private Button mSigninButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginText = findViewById(R.id.login_text);
        mUsername = findViewById(R.id.username_edittext);
        mEmail = findViewById(R.id.email_edittext);
        mSigninButton = findViewById(R.id.sign_in_button);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final JsonObject jsonObject = new JsonObject();

                jsonObject.addProperty("name", mUsername.getText().toString());
                jsonObject.addProperty("email", mEmail.getText().toString());

                final CashbackService cashbackService = new CashbackService();
                Call<CreateResponse> call = cashbackService.getLoginResponse(jsonObject);

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
//                call.enqueue(new Callback<CreateResponse>() {
//                    @Override
//                    public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
//                        if (response.code() == 201) {
//                            Log.e("TAG", "response 33: " + response.body().getUser().getUuid());
//
//                            String token = response.headers().get("Token");
//
//
//                            SharedPreferences.Editor editor = sharedPref.edit();
//                            editor.putString("token", token);
//                            editor.commit();
//
//                            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
//                            startActivity(intent);
//
//                        } else {
//                            Toast toast = Toast.makeText(getApplicationContext(), "Please use new username or email", Toast.LENGTH_LONG);
//                            toast.show();
//                            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 200);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<CreateResponse> call, Throwable t) {
//                        Log.e("TAG", "onFailure: "+ t.toString() );
//
//                    }
//                });
            }
        });
    }
}
