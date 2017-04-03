package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04.Constants.REQUEST_CODE;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    private Button top_left, top_right, center, bottom_left, bottom_right, navigate;
    private EditText edit_text;
    private int total;
    private int serviceStatus = 0;

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    private IntentFilter intentFilter = new IntentFilter();

    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bottom_left_button:
                    if (edit_text.getText().toString().isEmpty())
                        edit_text.setText(edit_text.getText().toString() + "Bottom Left");
                    else
                        edit_text.setText(edit_text.getText().toString() + ", Bottom Left");
                    break;
                case R.id.bottom_right_button:
                    if (edit_text.getText().toString().isEmpty())
                        edit_text.setText(edit_text.getText().toString() + "Bottom Right");
                    else
                        edit_text.setText(edit_text.getText().toString() + ", Bottom Right");
                    break;
                case R.id.top_left_button:
                    if (edit_text.getText().toString().isEmpty())
                        edit_text.setText(edit_text.getText().toString() + "Top Left");
                    else
                        edit_text.setText(edit_text.getText().toString() + ", Top Left");
                    break;
                case R.id.top_right_button:
                    if (edit_text.getText().toString().isEmpty())
                        edit_text.setText(edit_text.getText().toString() + "Top Right");
                    else
                        edit_text.setText(edit_text.getText().toString() + ", Top Right");
                    break;
                case R.id.center_button:
                    if (edit_text.getText().toString().isEmpty())
                        edit_text.setText(edit_text.getText().toString() + "Center");
                    else
                        edit_text.setText(edit_text.getText().toString() + ", Center");
                    break;
                case R.id.navigate_button:
                    Intent intent = new Intent(getApplicationContext(), PractivalTest01Var04SecondaryActivity.class);
                    intent.putExtra("sablon_key", edit_text.getText().toString());
                    startActivityForResult(intent, REQUEST_CODE);
                    break;
            }
            total++;
            Log.d("MyActivity", String.valueOf(total));

            if (total > 3
                    && serviceStatus == 0) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
                intent.putExtra("sablon_service_value", edit_text.getText().toString());
                getApplicationContext().startService(intent);
                serviceStatus = 1;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);
        total = 0;
        serviceStatus = 0;

        top_left = (Button)findViewById(R.id.top_left_button);
        top_right = (Button)findViewById(R.id.top_right_button);
        center = (Button)findViewById(R.id.center_button);
        bottom_left = (Button)findViewById(R.id.bottom_left_button);
        bottom_right = (Button)findViewById(R.id.bottom_right_button);
        navigate = (Button)findViewById(R.id.navigate_button);
        edit_text = (EditText)findViewById(R.id.edit_text);
        View.OnClickListener myListener = new MyListener();

        top_left.setOnClickListener(myListener);
        top_right.setOnClickListener(myListener);
        center.setOnClickListener(myListener);
        bottom_left.setOnClickListener(myListener);
        bottom_right.setOnClickListener(myListener);
        navigate.setOnClickListener(myListener);

        intentFilter.addAction(Constants.ACTION);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("total_key", String.valueOf(total));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        String totalString = savedInstanceState.getString("total_key");
        total = Integer.parseInt(totalString);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE) {
            edit_text.setText("");
            Log.d("MyActivity", String.valueOf(resultCode));
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var04Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
