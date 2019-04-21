package com.droidverine.hdds.hdds.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.droidverine.hdds.hdds.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login, submit_forgot;
    TextView txtforgot,txtregister;
    String email_entered, password_entered;
    int id, event_id;
    RelativeLayout login_layout, forgot_layout;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtregister=findViewById(R.id.txtregister);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_btn);
        login_layout = (RelativeLayout) findViewById(R.id.login_layout);
        txtforgot = findViewById(R.id.txtforgotpass);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);
        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        txtforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),ForgotActivity.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();
        if (mAuth == null) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        } else
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    email_entered = email.getText().toString();
                    password_entered = password.getText().toString();
                    if (email_entered.length() == 0) {
                        // email not entered
                        email.setError("Please enter your email");
                    } else if (password_entered.length() == 0) {
                        // password not entered
                        Snackbar.make(findViewById(R.id.activity_login), "Please enter your password.", Snackbar.LENGTH_SHORT).show();
                    } else {
                        // both are entered
                        signIn(email_entered, password_entered);
                    }

                }
            });

    }

    private void signIn(String email, String password) {
        Log.d("sign", "signIn:" + email);


        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("sigin", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("signin", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "INVALID INPUT", Toast.LENGTH_LONG).show();
                        }

                        // [START_EXCLUDE]

                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    @Override
    public void onBackPressed() {
        if (forgot_layout.getVisibility() == View.VISIBLE) {
            forgot_layout.setVisibility(View.GONE);
            login_layout.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }

    public void updateUI(FirebaseUser user) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
    }
}
