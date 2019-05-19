package bd.nasimdeveloper.userregistration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText emailID, passwordId;
    Button signupButton;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailID = findViewById(R.id.email);
        passwordId = findViewById(R.id.password);
        signupButton = findViewById(R.id.signUp);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }



    public void SignUp(View view) {

        email = emailID.getText().toString();
        password = passwordId.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Sign up Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SignIn(View view) {

        email = emailID.getText().toString();
        password = passwordId.getText().toString();

        if(mAuth!=null){
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(MainActivity.this, UserProfile.class);
                        startActivity(intent);
                    }
                }
            }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }


    }
}
