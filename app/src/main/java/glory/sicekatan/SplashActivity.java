package glory.sicekatan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Calendar;

import glory.sicekatan.Kelas.SharedVariable;

public class SplashActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Intent i;
    int delay =  3000;
    DatabaseReference ref,refDevice;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fStateListener;
    private static final long time = 3;
    private CountDownTimer mCountDownTimer;
    private long mTimeRemaining;
    private String now;
    String bagian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Firebase.setAndroidContext(this);
        FirebaseApp.initializeApp(SplashActivity.this);
        ref = FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        int versionCode = BuildConfig.VERSION_CODE;
        Calendar calendar = Calendar.getInstance();
        int bulan = calendar.get(Calendar.MONTH)+1;
        now = ""+calendar.get(Calendar.DATE)+"-"+bulan+"-"+calendar.get(Calendar.YEAR);
        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();


        if (fbUser != null) {
            // User already signed in
            // get the FCM token
            String token = FirebaseInstanceId.getInstance().getToken();
            SharedVariable.nama = fAuth.getCurrentUser().getDisplayName();
            SharedVariable.userID = fAuth.getCurrentUser().getUid();

            mCountDownTimer = new CountDownTimer(time * 1000, 50) {
                @Override
                public void onTick(long millisUnitFinished) {
                    mTimeRemaining = ((millisUnitFinished / 1000) + 1);

                }

                @Override
                public void onFinish() {

                        i = new Intent(SplashActivity.this, BerandaActivity.class);
                        startActivity(i);
                }
            };
            mCountDownTimer.start();

            ref.child("users").child(fAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String cek = (String) dataSnapshot.child("check").getValue();
                    String alamat = (String) dataSnapshot.child("alamat").getValue();
                    String pekerjaan = (String) dataSnapshot.child("pekerjaan").getValue();
                    String umur = (String) dataSnapshot.child("umur").getValue();

                    Log.d("cek:",cek);
                    SharedVariable.check = cek;
                    SharedVariable.umur = umur;
                    SharedVariable.pekerjaan = pekerjaan;
                    SharedVariable.alamat = alamat;
                    SharedVariable.userID = fAuth.getCurrentUser().getUid();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } else {

            progressBar.setVisibility(View.GONE);
            i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}
