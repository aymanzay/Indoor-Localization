package aymanzeine.locationservice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendActivity extends Activity implements SearchView.OnQueryTextListener {

    private SearchView etSV;
    private ListView etLV;
    private ArrayList<Friend> friends;
    private FriendHelper friendH;
    public String cUser;
    public EditText etStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        etSV = (SearchView) findViewById(R.id.SearchBar);
        etLV = (ListView) findViewById(R.id.LocationList);

        if (savedInstanceState == null) {
            Bundle info = getIntent().getExtras();
            if (info == null) {
                cUser = null;
            } else {
                cUser = info.getString("cUser");
            }
        } else {
            cUser = (String) savedInstanceState.getSerializable("cUser");
        }

        friends = new ArrayList<Friend>();
        addFriends();

        friendH = new FriendHelper(FriendActivity.this, friends);
        etLV.setAdapter(friendH);

        etLV.setTextFilterEnabled(true);
        SVsetup();

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            etLV.clearTextFilter();
        } else {
            etLV.setFilterText(newText);
        }
        return true;
    }

    private void SVsetup() {
        etSV.setIconifiedByDefault(false);
        etSV.setOnQueryTextListener(this);
        etSV.setSubmitButtonEnabled(true);
        etSV.setQueryHint("Enter Friend's Name Here:");
    }

    //Friend and Adapter Classes in order to find friends
    public static class Friend {
        public String name = "default";
        public String lastSeen = "default";
        public String location = "default";

        public Friend(String name, String lastSeen, String location) {
            this.name = name;
            this.lastSeen = lastSeen;
            this.location = location;
        }
    }

    public void addFriends(){
        Connect friend = new Connect(this,etStatus,null,friends,null,null);
        friend.execute("friends", cUser);
    }

    public class FriendHelper extends BaseAdapter implements Filterable {

        public Context context;
        public ArrayList<Friend> friendList;
        public ArrayList<Friend> temp;

        public FriendHelper(Context context, ArrayList<Friend> friendList) {
            super();
            this.context = context;
            this.friendList = friendList;
        }

        public class FriendInfo {
            TextView name;
            TextView seen;
            TextView location;
        }

        public Filter getFilter() {
            return new Filter() {

                @Override
                protected FilterResults performFiltering(CharSequence cSize) {
                    final FilterResults returnR = new FilterResults();
                    final ArrayList<Friend> results = new ArrayList<Friend>();

                    if (temp == null)
                        temp = friendList;
                    if (cSize != null) {
                        if (temp != null && temp.size() > 0) {
                            for (final Friend f : temp) {
                                if (f.name.toLowerCase().contains(cSize.toString()))
                                    results.add(f);
                            }
                        }
                        returnR.values = results;
                    }
                    return returnR;
                }

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence cSize, FilterResults results) {
                    friendList = (ArrayList<Friend>) results.values;
                    notifyDataSetChanged();
                }
            };
        }

        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return friendList.size();
        }

        @Override
        public Object getItem(int position) {
            return friendList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            FriendInfo tempF;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.friend_search, parent, false);
                tempF = new FriendInfo();
                tempF.name = (TextView) convertView.findViewById(R.id.fName);
                tempF.seen = (TextView) convertView.findViewById(R.id.fSeen);
                tempF.location = (TextView) convertView.findViewById(R.id.fLoc);
            } else {
                tempF = (FriendInfo) convertView.getTag();
            }

            tempF.name.setText(friendList.get(position).name);
            tempF.seen.setText("Last Seen: " + friendList.get(position).lastSeen);
            tempF.location.setText(friendList.get(position).location);
            return convertView;
        }

    }
}
