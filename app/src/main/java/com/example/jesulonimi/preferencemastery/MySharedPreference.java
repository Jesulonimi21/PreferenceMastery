package com.example.jesulonimi.preferencemastery;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;



public class MySharedPreference extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    SharedPreferences sharedPreferences;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.xml);

      sharedPreferences =getPreferenceScreen().getSharedPreferences();
        android.support.v7.preference.PreferenceScreen preferenceScreen=getPreferenceScreen();
        int prefCount=preferenceScreen.getPreferenceCount();


        for(int i=0;i<prefCount;i++){
            Preference preference=preferenceScreen.getPreference(i);
              if(!(preference instanceof CheckBoxPreference)){
                  String value=sharedPreferences.getString(preference.getKey(),"");
                  setSummary(preference,value);
              }


        }

    }

    public void setSummary(Preference p,String value){
        if(p!=null&&p instanceof ListPreference){
            ListPreference listPreference=(ListPreference)p;
            int prefIndex=listPreference.findIndexOfValue(value);
            if(prefIndex>=0){
            listPreference.setSummary(listPreference.getEntries()[prefIndex]);
        }}
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference=findPreference(key);
        if(preference instanceof ListPreference){
            String value=sharedPreferences.getString(preference.getKey(),"");
            setSummary(preference,value);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
