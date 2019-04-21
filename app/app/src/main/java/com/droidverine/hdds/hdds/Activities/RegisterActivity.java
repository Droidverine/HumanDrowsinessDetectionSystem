package com.droidverine.hdds.hdds.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.droidverine.hdds.hdds.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    EditText emailedt,passwordedt,cardmodeledt,carnoedt;
    Button signupbtn;
    TextView existsacc;
    String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth=FirebaseAuth.getInstance();
        emailedt=findViewById(R.id.signupemail);
        passwordedt=findViewById(R.id.signuppassword);
        signupbtn=findViewById(R.id.signup_btn);
        existsacc=findViewById(R.id.existacctxt);
        cardmodeledt=findViewById(R.id.cardmodeledt);
        carnoedt=findViewById(R.id.carnumberedt);
        existsacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=emailedt.getText().toString();
                password=passwordedt.getText().toString();
                createuser();
            }
        });
    }
    public void createuser(){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        // progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            updateData(cardmodeledt.getText().toString(),carnoedt.getText().toString());
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            // finish();
                        }
                    }
                });
    }
    private void updateData(final String carmodel, final String carnumber) {
        database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference();
        myRef.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".","_")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataSnapshot.getRef().child("Carmodel").setValue(carmodel);
                dataSnapshot.getRef().child("Carno").setValue(carnumber);
                dataSnapshot.getRef().child("status").setValue("Uknown");





            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("User", databaseError.getMessage());
            }
        });
    }
}
