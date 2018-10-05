package glory.sicekatan;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import glory.sicekatan.Kelas.SharedVariable;
import glory.sicekatan.Kelas.UserPreference;
import glory.sicekatan.Kelas.Utils;

public class LoginDokter extends AppCompatActivity {

    Intent i;
    EditText emailid, password;
    Button loginButton;
    TextView forgotPassword, signUp,loginPasien;
    DatabaseReference ref;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fStateListener;
    ProgressBar progressBar;
    DialogInterface.OnClickListener listener;
    private String userID;
    UserPreference mUserPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dokter);

        Firebase.setAndroidContext(this);
        FirebaseApp.initializeApp(LoginDokter.this);
        ref = FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        mUserPref = new UserPreference(this);

        emailid = (EditText) findViewById(R.id.login_emailid);
        signUp = (TextView) findViewById(R.id.createAccount);
        password = (EditText) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.loginBtn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loginPasien = (TextView) findViewById(R.id.loginPasien);
        loginPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });
    }

    private void doLogin(final String email,String passwordUser){
            String emailDokter = mUserPref.getEmailDokter();
            String passDokter = mUserPref.getPassDokter();

            if (email.equals(emailDokter) && passwordUser.equals(passDokter)){
                SharedVariable.userID = "dokter";
                SharedVariable.nama = "Dokter";
                i = new Intent(getApplicationContext(),BerandaDokterActivity.class);
                startActivity(i);
            }else {
                customToast("Email atau password anda salah");
                hidupkanKomponen();
            }
    }

    private void checkValidation() {
        String getEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();
        matikanKomponen();

        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {

            customToast("Email dan password harus diisi");
            hidupkanKomponen();
        } else if (!m.find()) {
            customToast("Email anda tidak valid");
            hidupkanKomponen();
        } else {
            doLogin(getEmailId, getPassword);

        }
    }

    private void matikanKomponen(){
        emailid.setEnabled(false);
        password.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        loginButton.setEnabled(false);
    }

    private void hidupkanKomponen(){
        emailid.setEnabled(true);
        password.setEnabled(true);
        progressBar.setVisibility(View.GONE);
        loginButton.setEnabled(true);
    }

    public  void customToast(String s){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.toast_root));

        TextView text = (TextView) layout.findViewById(R.id.toast_error);
        text.setText(s);
        Toast toast = new Toast(getApplicationContext());// Get Toast Context
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);// Set
        toast.setDuration(Toast.LENGTH_SHORT);// Set Duration
        toast.setView(layout); // Set Custom View over toast
        toast.show();// Finally show toast
    }
}
