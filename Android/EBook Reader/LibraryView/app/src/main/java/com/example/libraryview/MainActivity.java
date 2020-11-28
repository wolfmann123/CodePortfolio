/*
Authors: Thomas Morris & John Booker
Date: 12/5/19
Desc: Group Project eReader
Important: There is an error with multiple runs of different asyncs. So clicking on Read multiple times or download will make
            Android Studio crash the emulator. It may be associated to RAM. It was the really long error with no real message
            that we showed you in class.


*/


package com.example.libraryview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Books> book;
    RecyclerView rvBooks;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("eBook Reader");
        rvBooks = (RecyclerView) findViewById(R.id.rvBooks);
        book = Books.createBooksList(98);
        // Create adapter passing in the book data
        BooksAdapter adapter = new BooksAdapter(book);
        // Attach the adapter to the recyclerview to populate items
        rvBooks.setAdapter(adapter);
        // Set layout manager to position the items
        rvBooks.setLayoutManager(new LinearLayoutManager(this));

    }




    // respond to a menu item click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // which item did they click?
        switch (item.getItemId()) {
            case R.id.menu_help:
                // want to see the help/instructions
                Intent helpIntent = new Intent(
                        getApplicationContext(), HelpActivity.class);
                startActivity(helpIntent);
                return true;

            case R.id.menu_settings:
                // want to change a setting
                Intent settingsIntent = new Intent(
                        getApplicationContext(), SettingsActivity.class);
                // start the activity, getting a response
                startActivity(settingsIntent);
                return true;

            default:
                // unknown item
                return false;
        }
    }








}
