package com.example.toure;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText Username, Password;
    Button login;
    TextView register;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = findViewById(R.id.loginusername);
        Password = findViewById(R.id.loginpassword);
        login = findViewById(R.id.LoginButton);
        register = findViewById(R.id.lnkRegister);
        progressBar = findViewById(R.id.progress_circular);

        //check user is present or not
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String username, password;

                username = String.valueOf(Username.getText());
                password = String.valueOf(Password.getText());

                if (!username.equals(" ") && !password.equals(" ")) {
                    progressBar.setVisibility(View.VISIBLE);
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];

                            field[0] = "username";
                            field[1] = "password";

                            //Creating array for data
                            String[] data = new String[2];

                            data[0] = username;
                            data[1] = password;

                            PutData putData = new PutData("http://192.168.0.107/LoginRegister/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Log.i("PutData", result);
                                    if (result.equals("Login Success")) {
                                        Intent intent = new Intent(MainActivity.this, HomePage.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });


                } else {
                    Toast.makeText(getApplicationContext(), "please enter correct information ", Toast.LENGTH_LONG).show();
                }

            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                finish();
            }
        });


    }
}