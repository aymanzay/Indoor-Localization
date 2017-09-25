package aymanzeine.locationservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    ExpandableListView eLV;
    ExpandableListAdapter aLV;
    List<String> dataList;
    HashMap<String, List<String>> lChild;
    String uInterest;
    TextView etStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        eLV = (ExpandableListView) findViewById(R.id.listV);
        etStatus = (TextView) findViewById(R.id.Ntitle);

        compileNewsList();

        aLV = new ExpandableListAdapter(this, dataList, lChild);
        eLV.setAdapter(aLV);

        if (savedInstanceState == null) {
            Bundle info = getIntent().getExtras();
            if(info == null) {
                uInterest= " ";
            } else {
                uInterest= info.getString("interests_usr");
            }
        } else {
            uInterest= (String) savedInstanceState.getSerializable("interests_usr");
        }

    }

    private void compileNewsList() {
        dataList = new ArrayList<String>();
        lChild = new HashMap<String, List<String>>();

        dataList.add("Sciences");
        dataList.add("Business");
        dataList.add("Computers");
        dataList.add("Education");

        List<String> Sci = new ArrayList<String>();
        List<String> Bus = new ArrayList<String>();
        List<String> Com = new ArrayList<String>();
        List<String> Edu = new ArrayList<String>();

        lChild.put(dataList.get(0), Sci);
        lChild.put(dataList.get(1), Bus);
        lChild.put(dataList.get(2), Com);
        lChild.put(dataList.get(3), Edu);

        Connect Snews = new Connect(this, etStatus, Sci, null, null, null);
        Snews.execute("news", "Sciences");
        Connect Bnews = new Connect(this, etStatus, Bus, null, null, null);
        Bnews.execute("news", "Business");
        Connect Cnews = new Connect(this, etStatus, Com, null, null, null);
        Cnews.execute("news", "Computers");
        Connect Enews = new Connect(this, etStatus, Edu, null, null, null);
        Enews.execute("news", "Education");

    }

}
