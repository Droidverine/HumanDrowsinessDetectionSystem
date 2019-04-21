package com.droidverine.hdds.hdds.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.droidverine.hdds.hdds.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {
    Button btnforgot;
    TextView btnbacklg;
    FirebaseAuth auth;
    String email;
    EditText edtemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        btnforgot=findViewById(R.id.resetbtn);
        auth=FirebaseAuth.getInstance();
        edtemail=findViewById(R.id.edtforgotemail);
        btnforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            email=edtemail.getText().toString();
            forgotpass();
            }
        });

    }
    public void forgotpass()
    {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ForgotActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                        // progressBar.setVisibility(View.GONE);
                    }
                });
    }
}
