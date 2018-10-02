package glory.sicekatan;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PemberitahuanActivity extends AppCompatActivity {

    Intent i;
    RecyclerView recycler_listNotif;
    public static ProgressBar progressBar;
    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemberitahuan);


        txtInfo = (TextView) findViewById(R.id.txtInfo);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recycler_listNotif= (RecyclerView) findViewById(R.id.recycler_listlevel);

        txtInfo.setText("Belum ada pemberitahuan");
        txtInfo.setVisibility(View.VISIBLE);
    }
}
