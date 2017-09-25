package aymanzeine.locationservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String cUser, cFullN, cInterests, cFreq;
    TextView username, fullname, interests, frequency;
    Button bConfig, bNews, bFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Bundle info = getIntent().getExtras();
            if (info == null) {
                cUser = null;
                cFullN = null;
                cInterests = null;
                cFreq = null;
            } else {
                cUser = info.getString("logged_in_usr");
                cFullN = info.getString("fullname_usr");
                cInterests = info.getString("interests_usr");
                cFreq = info.getString("frequency_usr");
            }
        } else {
            cUser = (String) savedInstanceState.getSerializable("logged_in_usr");
            cFullN = (String) savedInstanceState.getSerializable("fullname_usr");
            cInterests = (String) savedInstanceState.getSerializable("interests_usr");
            cFreq = (String) savedInstanceState.getSerializable("frequency_usr");
        }

        username = (TextView) findViewById(R.id.userN);
        fullname = (TextView) findViewById(R.id.fullN);
        interests = (TextView) findViewById(R.id.interest);
        frequency = (TextView) findViewById(R.id.freq);
        bConfig = (Button) findViewById(R.id.confB);
        bFriends = (Button) findViewById(R.id.locB);
        bNews = (Button) findViewById(R.id.newsB);

        username.setText("Username: " + cUser);
        fullname.setText("Full Name: " + cFullN);
        interests.setText("Interests: " + cInterests);
        frequency.setText("Frequency: " + cFreq);

    }
    public void config(View v){
        Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
        intent.putExtra("logged_in_usr", cUser);
        intent.putExtra("fullname_usr", cFullN);
        intent.putExtra("interests_usr", cInterests);
        intent.putExtra("frequency_usr", cFreq);
        startActivity(intent);
    }


    public void news(View v){
            Intent intent = new Intent(this, NewsActivity.class);
            intent.putExtra("logged_in_usr", cUser);
            intent.putExtra("fullname_usr", cFullN);
            intent.putExtra("interests_usr", cInterests);
            intent.putExtra("frequency_usr", cFreq);
            startActivity(intent);
    }


    public void locate(View v){
        Intent intent = new Intent(MainActivity.this, FriendActivity.class);
        intent.putExtra("logged_in_usr", cUser);
        intent.putExtra("fullname_usr", cFullN);
        intent.putExtra("interests_usr", cInterests);
        intent.putExtra("frequency_usr", cFreq);
        startActivity(intent);
    }
}
