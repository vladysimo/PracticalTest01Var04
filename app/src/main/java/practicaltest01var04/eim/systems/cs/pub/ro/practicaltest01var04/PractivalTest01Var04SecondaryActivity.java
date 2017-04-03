package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PractivalTest01Var04SecondaryActivity extends AppCompatActivity {

    private Button verifyButton, cancelButton;
    private EditText sablonEditText;

    private class MySecondaryListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ok_button:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practival_test01_var04_secondary);

        verifyButton = (Button)findViewById(R.id.ok_button);
        cancelButton = (Button)findViewById(R.id.cancel_button);
        sablonEditText = (EditText)findViewById(R.id.sablon_edit_text);
        View.OnClickListener myListener = new MySecondaryListener();

        verifyButton.setOnClickListener(myListener);
        cancelButton.setOnClickListener(myListener);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            String sablon = bundle.getString("sablon_key");
            sablonEditText.setText(sablon);
        }
    }
}

