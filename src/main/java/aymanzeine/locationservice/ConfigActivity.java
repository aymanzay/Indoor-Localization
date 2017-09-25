package aymanzeine.locationservice;

import android.content.DialogInterface;
import android.support.annotation.InterpolatorRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ConfigActivity extends AppCompatActivity {

    public String cUser, cfullName, cInterests, cFreq;
    EditText etuserN, etfullName;
    Spinner frequency;
    TextView etStatus;

    Button bInterests, updateV;
    boolean[] interestArr;

    //Interest List
    private class InterestList{
        private String field;
        private boolean select;

        public String getName(){
            return field;
        }

        public void setField(String field){
            this.field = field;
        }

        public boolean isSelect(){
            return select;
        }

        public void setSelect(boolean select){
            this.select = select;
        }
    }

    //Update Values
    public void updateValues(View v){
        cUser = etuserN.getText().toString();
        cfullName = etfullName.getText().toString();
        cFreq = frequency.getSelectedItem().toString().split(" ")[0];
        if(Integer.parseInt(cFreq) == 1)
            cFreq = "60";
        else if(Integer.parseInt(cFreq) == 15)
            cFreq = "900";
        else if(Integer.parseInt(cFreq) == 30)
            cFreq = "1800";
        else if(Integer.parseInt(cFreq) == 60)
            cFreq = "3600";
        else
            cFreq = "10";

        cInterests = "";
        if(interestArr[0] == true)
            cInterests += (cInterests.length() > 1)? "_" : "";
        cInterests = "Sciences";

        if(interestArr[1] == true){
            cInterests += (cInterests.length()>1)? "_" : "";
            cInterests += "Business";
        }

        if(interestArr[2] == true){
            cInterests += (cInterests.length()>1)? "_" : "";
            cInterests += "Computers";
        }

        if(interestArr[3] == true){
            cInterests += (cInterests.length()>1)? "_" : "";
            cInterests += "Education";
        }



        Connect update = new Connect(this, etStatus, null, null, null, null);
        update.execute("update", cUser, cfullName, cInterests, cFreq);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        if(savedInstanceState == null){
            Bundle info = getIntent().getExtras();
            if(info == null){
                cUser = null;
                cfullName = null;
                cInterests = null;
                cFreq = null;
            } else {
                cUser = info.getString("logged_in_usr");
                cfullName = info.getString("fullname_usr");
                cInterests = info.getString("interests_usr");
                cFreq = info.getString("frequency_usr");
            }
        } else {
            cUser = (String) savedInstanceState.getSerializable("logged_in_usr");
            cfullName = (String) savedInstanceState.getSerializable("fullname_usr");
            cInterests = (String) savedInstanceState.getSerializable("interests_usr");
            cFreq = (String) savedInstanceState.getSerializable("frequency_usr");
        }

        etuserN = (EditText) findViewById(R.id.userconf);
        etfullName = (EditText) findViewById(R.id.fullName);
        frequency = (Spinner) findViewById(R.id.freqSpin);
        etStatus = (TextView) findViewById(R.id.Confstat);
        bInterests = (Button) findViewById(R.id.bInterest);
        updateV = (Button) findViewById(R.id.confUpdate);

        final ArrayList<InterestList> interestL = new ArrayList<InterestList>();
        final String[] choices = new String[]{"Sciences","Business","Computers","Education"};
        final boolean[] checked = new boolean[]{(cInterests.contains(choices[0])),
                (cInterests.contains(choices[1])),
                (cInterests.contains(choices[2])),
                (cInterests.contains(choices[3]))};
        interestArr = checked;

        //interests button to prompt list
        bInterests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(ConfigActivity.this);

                for(int i = 0; i < choices.length; i++){
                    InterestList menu = new InterestList();
                    menu.setField(choices[i]);
                    menu.setSelect(checked[i]);
                    interestL.add(menu);
                }

                builder.setMultiChoiceItems(choices, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int option, boolean chosen){
                        interestL.get(option).setSelect(chosen);
                        Toast.makeText(getApplicationContext(), interestL.get(option).getName() + " " + chosen, Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setCancelable(false);
                builder.setTitle("Please select your interests");
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<InterestList> selected = new ArrayList<>();
                        for(int i = 0; i < interestL.size(); i++){
                            InterestList menu = interestL.get(i);
                            choices[i] = menu.getName();
                            checked[i] = menu.isSelect();
                            if(menu.isSelect()){
                                selected.add(menu);
                            }
                        }

                        interestL.clear();
                    }
                });

                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        interestL.clear();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        etuserN.setText(cUser);
        if(cfullName.equals("Unknown")){
            etfullName.setHint("Enter your full name");
        } else {
            etfullName.setText(cfullName);
        }

        int position = 0;
        if(cFreq.equals("60"))
            position = 1;
        if(cFreq.equals("900"))
            position = 2;
        if(cFreq.equals("1800"))
            position = 3;
        if(cFreq.equals("3600"))
            position = 4;

        final int tempPos = position;
        frequency.post(new Runnable() {
            @Override
            public void run() {
                frequency.setSelection(tempPos);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequency.setAdapter(adapter);
    }
}
