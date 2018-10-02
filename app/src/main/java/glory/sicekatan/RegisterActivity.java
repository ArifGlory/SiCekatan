package glory.sicekatan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import glory.sicekatan.Kelas.UserModel;
import glory.sicekatan.Kelas.Utils;

public class RegisterActivity extends AppCompatActivity {

    EditText fullName, emailId,
            password, confirmPassword,etUmur,etDiagnosa,etJantung,etDarah,etSuhu,etAlamat,etPekerjaan;
    TextView login;
    Button signUpButton;
    CheckBox terms_conditions;
    Intent i;
    DatabaseReference ref;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fStateListener;
    ProgressBar progressBar;
    UserModel userDaftarModel;
    private String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firebase.setAndroidContext(this);
        FirebaseApp.initializeApp(RegisterActivity.this);
        ref = FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();

        fullName = (EditText) findViewById(R.id.fullName);
        emailId = (EditText) findViewById(R.id.userEmailId);

        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        signUpButton = (Button) findViewById(R.id.signUpBtn);
        login = (TextView) findViewById(R.id.already_user);
        terms_conditions = (CheckBox) findViewById(R.id.terms_conditions);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        etUmur = (EditText) findViewById(R.id.etUmur);
        etJantung = (EditText) findViewById(R.id.etJantung);
        etDiagnosa = (EditText) findViewById(R.id.etDiagnosa);
        etDarah = (EditText) findViewById(R.id.etDarah);
        etSuhu = (EditText) findViewById(R.id.etSuhu);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        etPekerjaan = (EditText) findViewById(R.id.etPekerjaan);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });
        fStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = fAuth.getCurrentUser();
                if (user != null ){
                    //user sedang login
                    Log.d("Fauth : ","onAuthStateChanged:signed_in:" + user.getUid());
                }
                //user sedang logout
                Log.d("Fauth : ","onAuthStateChanged:signed_out");
            }
        };
    }


    private void checkValidation() {

        matikanKomponen();

        // Get all edittext texts
        String getFullName = fullName.getText().toString();
        String getEmailId = emailId.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();
        String getUmur = etUmur.getText().toString();
        String getDiagnosa = etDiagnosa.getText().toString();
        String getDarah = etDarah.getText().toString();
        String getJantung = etJantung.getText().toString();
        String getSuhu = etSuhu.getText().toString();
        String getAlamat = etAlamat.getText().toString();
        String getPekerjaan = etPekerjaan.getText().toString();

        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        // Check if all strings are null or not
        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("") || getConfirmPassword.length() == 0
                || getUmur.equals("") || getUmur.length() == 0
                || getDiagnosa.equals("") || getDiagnosa.length() == 0
                || getDarah.equals("") || getDarah.length() == 0
                || getJantung.equals("") || getJantung.length() == 0
                || getSuhu.equals("") || getSuhu.length() == 0
                || getAlamat.equals("") || getAlamat.length() == 0
                || getPekerjaan.equals("") || getPekerjaan.length() == 0
                ) {

           customToast("Semua Field harus diisi");
            hidupkanKomponen();
        }
        //check valid email
        else if (!m.find()) {
            customToast("Email anda tidak valid");
            hidupkanKomponen();
        }
        // Check if both password should be equal
        else if (!getConfirmPassword.equals(getPassword)) {
           customToast("Konfirmasi password  tidak sesuai");
            hidupkanKomponen();
        }
        // Make sure user should check Terms and Conditions checkbox
        else if (!terms_conditions.isChecked()) {
            customToast("Terms and Condition belum di ceklis");
            hidupkanKomponen();
        }
        // Else do signup or do your stuff
        else
            signUp(getEmailId,getPassword);

    }

    private void signUp(final String email, String passwordUser){

        fAuth.createUserWithEmailAndPassword(email,passwordUser).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Log.d("Fauth :","createUserWithEmail:onComplete: " + task.isSuccessful());
                /**
                 * Jika sign in gagal, tampilkan pesan ke user. Jika sign in sukses
                 * maka auth state listener akan dipanggil dan logic untuk menghandle
                 * signed in user bisa dihandle di listener.
                 */

                if (!task.isSuccessful()){
                    Log.e("Eror gagal daftar ",task.getException().toString());
                    Toast.makeText(RegisterActivity.this,"Proses Pendaftaran gagal"+task.getException().toString(),Toast.LENGTH_LONG).show();

                    hidupkanKomponen();
                }else {
                    FirebaseUser user = fAuth.getCurrentUser();
                    String userID =  fAuth.getCurrentUser().getUid();
                    String token  = FirebaseInstanceId.getInstance().getToken();
                    //cek tanggal skrg
                    Calendar calendar = Calendar.getInstance();
                    int bulan = calendar.get(Calendar.MONTH)+1;
                    time = ""+calendar.get(Calendar.DATE)+"-"+bulan+"-"+calendar.get(Calendar.YEAR);

                    //ganti nama
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                            .setDisplayName(fullName.getText().toString()).build();
                    user.updateProfile(profileChangeRequest);


                    userDaftarModel = new UserModel(userID,
                            fullName.getText().toString(),
                            token,
                            time,
                            "1",
                            etUmur.getText().toString(),
                            etDiagnosa.getText().toString(),
                            etJantung.getText().toString(),
                            etDarah.getText().toString(),
                            etSuhu.getText().toString(),
                            etAlamat.getText().toString(),
                            etPekerjaan.getText().toString());

                    ref = ref.child("users");
                    ref.child(userID).setValue(userDaftarModel);

                    Toast.makeText(RegisterActivity.this,"Register Succes ! Please go to Login page \n" +
                            "Email : "+email,Toast.LENGTH_LONG).show();

                    progressBar.setVisibility(View.GONE);
                    hidupkanKomponen();
                    fullName.setText("");
                    emailId.setText("");
                    password.setText("");
                    confirmPassword.setText("");
                    etSuhu.setText("");
                    etJantung.setText("");
                    etUmur.setText("");
                    etDiagnosa.setText("");
                    etDarah.setText("");
                    etAlamat.setText("");
                    etPekerjaan.setText("");


                    //  i = new Intent(getApplicationContext(),LoginActivity.class);
                    // startActivity(i);
                }
            }
        });
    }

    private void matikanKomponen(){
        progressBar.setVisibility(View.VISIBLE);
        fullName.setEnabled(false);
        emailId.setEnabled(false);
        password.setEnabled(false);
        etUmur.setEnabled(false);
        etJantung.setEnabled(false);
        etDarah.setEnabled(false);
        etSuhu.setEnabled(false);
        etDiagnosa.setEnabled(false);
        etDarah.setEnabled(false);
        confirmPassword.setEnabled(false);
        terms_conditions.setEnabled(false);
    }

    private void hidupkanKomponen(){
        progressBar.setVisibility(View.GONE);
        fullName.setEnabled(true);
        emailId.setEnabled(true);
        etUmur.setEnabled(true);
        etJantung.setEnabled(true);
        etDarah.setEnabled(true);
        etSuhu.setEnabled(true);
        etDiagnosa.setEnabled(true);
        etDarah.setEnabled(true);
        password.setEnabled(true);
        confirmPassword.setEnabled(true);
        terms_conditions.setEnabled(true);
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
