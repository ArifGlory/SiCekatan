package glory.sicekatan;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import glory.sicekatan.Kelas.SharedVariable;

public class ProfilActivity extends AppCompatActivity {

    Button loginBtn;
    Intent i;
    TextView txtNama,txtUmur,txtPekerjaan,txtAlamat;
    DatabaseReference ref;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fStateListener;
    ProgressBar progressBar;
    DialogInterface.OnClickListener listener;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Firebase.setAndroidContext(this);
        FirebaseApp.initializeApp(ProfilActivity.this);
        ref = FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();

        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();

        txtAlamat = (TextView) findViewById(R.id.txtAlamat);
        txtPekerjaan = (TextView) findViewById(R.id.txtPekerjaan);
        txtUmur = (TextView) findViewById(R.id.txtUmur);
        txtNama = (TextView) findViewById(R.id.txtNama);

        txtAlamat.setText(SharedVariable.alamat);
        txtUmur.setText(SharedVariable.umur+" tahun");
        txtNama.setText(SharedVariable.nama);
        txtPekerjaan.setText(SharedVariable.pekerjaan);
    }
}
