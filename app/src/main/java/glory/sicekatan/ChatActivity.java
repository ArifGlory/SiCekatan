package glory.sicekatan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import glory.sicekatan.Adapter.RecycleAdapteraChat;

public class ChatActivity extends AppCompatActivity {


    RecyclerView recycler_chat;
    RecycleAdapteraChat adapter;

    Button btn_kirim;
    EditText et_pesan;
    String time,pesan;
    String from_id,namaLayanan;
    TextView txtLayanan;
    public  static String to_id;
    Intent i;

    DatabaseReference ref;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Firebase.setAndroidContext(this);
        FirebaseApp.initializeApp(ChatActivity.this);
        ref = FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();

        recycler_chat = (RecyclerView) findViewById(R.id.recycler_chat);
        adapter = new RecycleAdapteraChat(this);
        recycler_chat.setAdapter(adapter);
        recycler_chat.setLayoutManager(new LinearLayoutManager(this));

        btn_kirim = (Button) findViewById(R.id.btn_send);
    }
}
