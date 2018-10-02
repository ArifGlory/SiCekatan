package glory.sicekatan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JantungActivity extends AppCompatActivity {

    TextView txtDetak;
    LinearLayout lineData;
    Button btnReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jantung);

        txtDetak = (TextView) findViewById(R.id.txtDetak);
        txtDetak.setVisibility(View.VISIBLE);
        lineData = (LinearLayout) findViewById(R.id.lineData);
        lineData.setVisibility(View.GONE);

        btnReport = (Button) findViewById(R.id.btnReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDetak.setVisibility(View.GONE);
                lineData.setVisibility(View.VISIBLE);
                btnReport.setVisibility(View.GONE);
            }
        });
    }
}
