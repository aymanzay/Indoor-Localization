package aymanzeine.locationservice;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class RegisterActivity extends AppCompatActivity {

    EditText etUN;
    EditText etPass;
    EditText etConf;
    TextView etStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUN = (EditText) findViewById(R.id.reguser);
        etPass = (EditText) findViewById(R.id.regpass);
        etConf = (EditText) findViewById(R.id.confpass);
        etStat = (TextView) findViewById(R.id.statusV);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
    }

    public void Register(View v) {
        String user = etUN.getText().toString();
        String pass = etPass.getText().toString();

        if (user.equals(null)) {
            etStat.setText("Enter a username");
        } else if (!pass.equals(etConf.getText().toString())) {
            etStat.setText("Passwords do not match");
        } else {
            Connect confirm = new Connect(this, etStat, null, null, null, null); //changed from confirm to register
            confirm.execute("confirm", etUN.getText().toString());
        }
        //Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        //startActivity(intent);
    }

}
