package com.mohammadkiani.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String SHARED_PREFERENCES_NAME = "username";
    public static final String KEY_NAME = "key_username";

    SharedPreferences sharedPreferences;
    private EditText nameEditText, numberEditText, occupationEditText;
    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // there is class named SharedPreferences
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);

        //write into sharedPreferences
//        sharedPreferences.edit().putString(KEY_NAME, "Mo").apply();

        //read from sharedPreferences
        String userName = sharedPreferences.getString(KEY_NAME, "NA");

        Log.i(TAG, "onCreate: username : " + userName);

        ArrayList<String> names = new ArrayList<>(Arrays.asList("Mo", "Kian", "Na", "Sari"));
        // inorder to write the names
//        sharedPreferences.edit().putStringSet("names", new HashSet<>(names)).apply();

        // read a set from sharedPreferences
        Set<String> retrievedNames = sharedPreferences.getStringSet("names", new HashSet<>());
        Log.i(TAG, "onCreate: names arrayList: " + retrievedNames.toString());

        // writing an object to sharedPreferences - Object serializer
//        try {
//            sharedPreferences.edit().putString("names_serialized", ObjectSerializer.serialize(names)).apply();
//            Log.i(TAG, "onCreate: Serialized: " + ObjectSerializer.serialize(names));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // reading an object from sharedPreferences - Object deserializer
        ArrayList deserializedNames  = new ArrayList<>();
        String receivedSerializedString = sharedPreferences.getString("names_serialized", null);
        try {
            deserializedNames = (ArrayList) ObjectSerializer.deserialize(receivedSerializedString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "onCreate: Deserialize: " + deserializedNames);

        //initializing the edit texts
        nameEditText = findViewById(R.id.name_et);
        numberEditText = findViewById(R.id.number_et);
        occupationEditText = findViewById(R.id.occupation_et);

        contacts = new ArrayList<>();

    }

    public void addContact(View view) {
        getContacts();
        Contact contact = new Contact(nameEditText.getText().toString().trim(),
                Integer.parseInt(numberEditText.getText().toString().trim()),
                nameEditText.getText().toString().trim());

        contacts.add(contact);
        try {
            sharedPreferences.edit().putString("names_serialized", ObjectSerializer.serialize((Serializable) contacts)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Contact c: contacts)
            Log.i(TAG, "onCreate: show contact: " + c.getName());
    }

    private List<Contact> getContacts() {
//        ArrayList receivedContacts  = new ArrayList<>();
        contacts.clear();
        String receivedSerializedString = sharedPreferences.getString("names_serialized", null);
        try {
            contacts = (ArrayList) ObjectSerializer.deserialize(receivedSerializedString);
            if (contacts == null)
                contacts = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return contacts;
    }
}














