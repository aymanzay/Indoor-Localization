package aymanzeine.locationservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    EditText etUN;
    EditText etPass;
    TextView etStat;
    TextView registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUN = (EditText) findViewById(R.id.logname);
        etPass = (EditText) findViewById(R.id.passlog);
        Button bLogin = (Button) findViewById(R.id.bLogin);
        registerLink = (TextView) findViewById(R.id.regButton);
        etStat = (TextView) findViewById(R.id.statusV);
    }

    public void Login(View v){
        Connect login = new Connect(this, etStat, null, null, null, null);
        login.execute("login", etUN.getText().toString(), etPass.getText().toString());
    }

    public void Register(View v){
        etStat.setText("");
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}

