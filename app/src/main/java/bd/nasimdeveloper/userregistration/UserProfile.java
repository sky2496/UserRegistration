package bd.nasimdeveloper.userregistration;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UserProfile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    EditText userName, userEmail, verify;
    ImageView profilePicture;
    Boolean Verified;
    String verifyMessage, notVerifyMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userName = findViewById(R.id.UserName);
        userEmail = findViewById(R.id.UserEmail);
        verify = findViewById(R.id.Verify);
        profilePicture = findViewById(R.id.ProfilePicture);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        userName.setText(mUser.getDisplayName().toString());
        userEmail.setText(mUser.getEmail().toString());

        verifyMessage = "Email is Verified";
        notVerifyMessage = "Email is not verified";



        mUser.getEmail();
        mUser.getDisplayName();
        mUser.getPhotoUrl();

        Verified = mUser.isEmailVerified();

        if(Verified){
            verify.setText(verifyMessage);
        }
        else{
            verify.setText(notVerifyMessage);
        }
    }

    public void Update(View view) {
        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(userName.getText().toString())
                .setPhotoUri(Uri.parse("https://www.gettyimages.com/detail/photo/bearded-stylish-man-posing-outdoors-royalty-free-image/642272652"))
                .build();

        mUser.updateProfile(userProfileChangeRequest).addOnCompleteListener(UserProfile.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(UserProfile.this,"Updated Successfully",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(UserProfile.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
}
