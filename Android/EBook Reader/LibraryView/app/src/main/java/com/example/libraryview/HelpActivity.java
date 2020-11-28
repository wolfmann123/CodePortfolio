package com.example.libraryview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //help page setup
        TextView help = findViewById(R.id.tv_help);
        help.setTextSize(33);

        help.setMovementMethod(new ScrollingMovementMethod());
        String directions = "You must download a book \n You cannot read a book unless it has been downloaded \n Tapping the 'DOWNLOAD' button will download a book \n " +
                "Tapping the 'Read' button will open a reading screen for that book \n Hold down the 'Download' button to delete a book; this will also delete your line place for that book. \n \n While reading a book, you can jump to any point in the book by " +
                "typing in the line number you wish to jump to in the box at the top-left of the screen. This is also your current line number which is updated as you read and saved when you leave \n The top-right box shows the amount of lines in the book you are reading \n \n Click on the menu button, three dots at the top right of the screen, to access the Help page and the Settings page \n The Settings page shows the last book you read at the top of the page \n You can change the font of the book inside of the Settings page \n" +
                "Tap on the textbox below the current font size to change it, then click the 'UPDATE' button. ONLY 20-50 ARE ALLOWED";
        help.setText(directions);
    }
}
