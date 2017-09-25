package aymanzeine.locationservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/*
 * Created by AymanZAY on 2/5/17.
 */

public class Connect extends AsyncTask<String,Void,String>{

    private TextView etStatus;
    private Context context;
    private int method;
    private List<String> list;
    private ArrayList<FriendActivity.Friend> fList;
    String userN, updateN, updateI, updateF, interests;
    FriendActivity.FriendHelper friendH;
    View view;


    //Constructor
    public Connect(Context context, TextView etStatus, List<String> list, ArrayList<FriendActivity.Friend> fList, FriendActivity.FriendHelper friendH, View view){
        this.context = context;
        this.etStatus = etStatus;
        method = 8888;
        this.list = list;
        this.fList = fList;
        this.userN = "default";
        this.friendH = friendH;
        this.view = view;
    }

    @Override
    protected String doInBackground(String... params) {

        if(params[0].equals("login"))
            method = 0;
        else if(params[0].equals("confirm"))
            method = 1;
        else if(params[0].equals("register"))
            method = 2;
        else if(params[0].equals("update"))
            method = 3;
        else if(params[0].equals("friends"))
            method = 4;
        else if(params[0].equals("news"))
            method = 5;
        else if(params[0].equals("locate"))
            method = 6;

        String username, password, data, link, line;
        URL url;
        URLConnection conn;
        OutputStreamWriter wr;
        BufferedReader reader;
        StringBuilder sb;

        //Post method
        try {
            switch (method) {
                case 0: //Login
                    username = (String) params[1];
                    password = (String) params[2];

                    data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("login", "UTF-8");
                    data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                    link = "http://10.0.2.2:8889/demo/index.php";
                    url = new URL(link);
                    conn = url.openConnection();
                    conn.setDoOutput(true);

                    wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    sb = new StringBuilder();
                    line = null;

                    //Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    return sb.toString();

                case 1: //Confirm
                    username = (String) params[1];

                    data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("confirm", "UTF-8");
                    data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

                    link = "http://10.0.2.2:8889/demo/index.php";
                    url = new URL(link);
                    conn = url.openConnection();
                    conn.setDoOutput(true);

                    wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    sb = new StringBuilder();
                    line = null;

                    //Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    //Log.e("result: ", sb.toString());
                    return sb.toString();


                case 2: //Register
                    username = (String) params[1];
                    password = (String) params[2];

                    data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("register", "UTF-8");
                    data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                    link = "http://10.0.2.2:8889/demo/index.php";
                    url = new URL(link);
                    conn = url.openConnection();
                    conn.setDoOutput(true);

                    wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    sb = new StringBuilder();
                    line = null;

                    //Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    return sb.toString();

                case 3: //Update
                    username = (String) params[1];
                    updateN = (String) params[2];
                    updateI = (String) params[3];
                    updateF = (String) params[4];

                    data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("update", "UTF-8");
                    data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                    data += "&" + URLEncoder.encode("fullname", "UTF-8") + "=" + URLEncoder.encode(updateN, "UTF-8");
                    data += "&" + URLEncoder.encode("interests", "UTF-8") + "=" + URLEncoder.encode(updateI, "UTF-8");
                    data += "&" + URLEncoder.encode("frequency", "UTF-8") + "=" + URLEncoder.encode(updateF, "UTF-8");

                    link = "http://10.0.2.2:8889/demo/index.php";
                    url = new URL(link);
                    conn = url.openConnection();
                    conn.setDoOutput(true);

                    wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    sb = new StringBuilder();
                    line = null;

                    //Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    return sb.toString();

                case 4: //Friends
                    username = (String) params[1];
                    data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("friends", "UTF-8");
                    data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

                    link = "http://10.0.2.2:8889/demo/index.php";
                    url = new URL(link);
                    conn = url.openConnection();
                    conn.setDoOutput(true);

                    wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    sb = new StringBuilder();
                    line = null;

                    //Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    //Log.e("About to locate", sb.toString());
                    return sb.toString();

                case 5: //News
                    interests = (String) params[1];
                    data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("news", "UTF-8");
                    data += "&" + URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode((String)params[1], "UTF-8");

                    link = "http://10.0.2.2:8889/demo/index.php";
                    url = new URL(link);
                    conn = url.openConnection();
                    conn.setDoOutput(true);

                    wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    sb = new StringBuilder();
                    line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    return sb.toString();

                case 6: //Locate
                    String mac = (String) params[1];
                    data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("locate", "UTF-8");
                    data += "&" + URLEncoder.encode("mac", "UTF-8") + "=" + URLEncoder.encode(mac, "UTF-8");

                    link = "http://10.0.2.2:8889/demo/index.php";
                    url = new URL(link);
                    conn = url.openConnection();
                    conn.setDoOutput(true);

                    wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    sb = new StringBuilder();
                    line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    return sb.toString();
                default:
                    return null;
            }
        }
        catch(Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
        switch(method) {
            case 0: //Login
                if (result.contains("Succeed")) {
                    this.etStatus.setText("Logged in");
                    String[] results = result.split(";");
                    Intent intent = new Intent(context, MainActivity.class);

                    intent.putExtra("logged_in_usr", results[1]);
                    intent.putExtra("fullname_usr", results[2]);
                    intent.putExtra("interests_usr", results[3]);
                    intent.putExtra("frequency_usr", results[4]);
                    context.startActivity(intent);
                    return;
                } else if (result.contains("Failed")) {
                    this.etStatus.setText("Invalid Login, try again.");
                    return;
                } else {
                    this.etStatus.setText(result);
                    return;
                }

            case 1: //Register
                if (result.contains("True")) {
                    View view = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
                    EditText username = (EditText) view.findViewById(R.id.reguser);
                    EditText password = (EditText) view.findViewById(R.id.regpass);
                    Connect register = new Connect(this.context, this.etStatus, null, null, null, null);
                    register.execute("register", username.getText().toString(), password.getText().toString());
                    return;
                } else {
                    this.etStatus.setText("Username taken, try a different one. " + result);
                    return;
                }

            case 2: //Confirm
                if (result.contains("Error")) {
                    this.etStatus.setText("Error registering: " + result);
                    return;
                } else {
                    this.etStatus.setText("Successful register");
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    return;
                }

            case 3: //Update
                if (result.contains("Error")) {
                    this.etStatus.setText("Error updating: " + result);
                    return;
                } else {
                    Intent intent = new Intent(context, MainActivity.class);
                    this.etStatus.setText("Successful update" + result);
                    intent.putExtra("logged_in_usr", userN);
                    intent.putExtra("fullname_usr", updateN);
                    intent.putExtra("interests_usr", updateI);
                    intent.putExtra("frequency_usr", updateF);
                    context.startActivity(intent);
                    return;
                }

            case 4: //Friends
                String[] results = result.split("\"");
                //Log.e("Friends ", results[0]);
                String name = "default";
                String seen = "default";
                String location = "default";
                for (int i = 0; i < results.length; i++) {
                    if (results[i].equals("full_name"))
                        name = results[i + 2];

                    if (results[i].equals("connected_mac")) {
                        location = "unknown";
                        if (location.equals("online")) {
                            location = "unknown";
                        } else {
                            Connect locate = new Connect(this.context, this.etStatus, null, fList, null, null);
                            //Log.e("About to locate", results[i]);
                            locate.execute("locate", location, name);
                        }
                    }

                    if (results[i].equals("online")) {
                        seen = results[i + 2];
                        fList.add(new FriendActivity.Friend(name, seen, "Last seen: " + location));
                    }
                }
                return;

            case 5: //News
                String category = result.split("\"category\":\"")[1].split("\",\"URL\"")[0];
                String[] urls = result.split("URL\":\"");

                for (int i = 1; i < urls.length; i++) {
                    String url = urls[i].split("\"")[0];
                    String url2 = url.replace("\\", "");
                    list.add(url2);
                }
                return;

            case 6: //Locate
                if (result.contains("Failed")) {
                    for (FriendActivity.Friend f : fList) {
                        if (f.name.contains(userN)) {
                            f.location = "offline";
                        }
                    }
                } else {
                    String locResult = result.split(";")[1] + " " + result.split(";")[2];
                    for (FriendActivity.Friend f : fList) {
                        if (f.name.contains(userN)) {
                            f.location = locResult;
                        }
                    }
                }

            default:
                break;
        }
    }

}
