package com.adityaJSleepFN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.list);


        try {
            JSONObject j = new JSONObject(fromAsset());
            JSONArray a = j.getJSONArray("randomRoomList");
            HashMap<String,String> h;
            ArrayList<HashMap<String,String>> list = new ArrayList<>();
            for (int i =0;i<a.length();i++){
                JSONObject ij = a.getJSONObject(i);
                String name1 = ij.getString("name");
                h=new HashMap<>();
                h.put("name",name1);
                list.add(h);

            }
            ListAdapter la = new SimpleAdapter(this,list,R.layout.list_view, new String[]{"name"}, new int[]{R.id.nameList} );
            lv.setAdapter(la);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
    public String fromAsset() throws IOException {
        String json = null;
        try{
            InputStream i = getAssets().open("randomRoomList.json");
            int sizeOfFile = i.available();
            byte[] b = new byte[sizeOfFile];
            i.read(b);
            i.close();
            json = new String(b, StandardCharsets.UTF_8);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId()==R.id.person_button) {
            startActivity(new Intent(MainActivity.this, AboutMeActivity.class));
            return true;
        }
        else return false;
    }
}
