package com.example.jesulonimi.preferencemastery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   sp  = PreferenceManager.getDefaultSharedPreferences(this);
        setFont(sp.getBoolean("build",true));
        setColor(sp.getString("thelist","red"));

        sp.registerOnSharedPreferenceChangeListener(this);

    }

    public void setColor(String color){
        TextView tv=(TextView) findViewById(R.id.manText);

        if(color.equals("red")){
            tv.setTextColor(Color.RED);
        }else if(color.equals("blue")){
            tv.setTextColor(Color.BLUE);
        }
    }

    public void setFont(boolean f){
        TextView tv=(TextView) findViewById(R.id.manText);

        if(f){
            tv.setText(Html.fromHtml("<b>"+"hello world"+"</b>"));
        }else{
            tv.setText(Html.fromHtml("<i>"+"hello world"+"</i>"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mySettings){
           Intent i=new Intent(MainActivity.this,SettingsActivity.class);
           startActivity(i);
        }
        return true;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("build")){
            setFont(sharedPreferences.getBoolean("build",true));
        }
        if(key.equals("thelist")){
            setColor(sharedPreferences.getString("thelist","red"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
   PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
}
