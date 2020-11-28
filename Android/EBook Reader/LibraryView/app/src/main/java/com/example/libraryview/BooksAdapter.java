package com.example.libraryview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.savedstate.SavedStateRegistry;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;

// Create the adapter extending from RecyclerView.Adapter
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private List<Books> mBooks;

    // Pass in the book list into the constructor
    public BooksAdapter(List<Books> books) {
        super();
        mBooks = books;
    }

    Context context;

    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View bookView = inflater.inflate(R.layout.item_book, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(bookView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final BooksAdapter.ViewHolder viewHolder, final int position) {
        // Get the data based on position
        Books book = mBooks.get(position);
        final String title = book.getTitle();
        // Set item views
        final TextView textView = viewHolder.nameTextView;
        textView.setText(book.getTitle());
        SharedPreferences settings = context.getSharedPreferences("mysettings", Context.MODE_PRIVATE);




        //is the book available to be downloaded or is it already on the device?
        viewHolder.downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBooks.set(position, new Books(title, false));
                Button button = viewHolder.downloadButton;
                //start the download activity
                Intent downloadIntent = new Intent(
                        context, DownloadActivity.class);
                downloadIntent.putExtra("POSITION", position);
                context.startActivity(downloadIntent);
                //after successful download the button is used to take the user to the read activity
            }
        });

        //Read Button for when read is clicked
        viewHolder.readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = context.getApplicationContext().getFilesDir().getAbsolutePath();
                path += "/" + ReadActivity.titles[position] + ".txt";
                File file = new File(path + "");
                SharedPreferences settings = context.getSharedPreferences("mysettings", Context.MODE_PRIVATE);



                //Allowed to open Read activity
                if (file.exists()) {

                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("LAST_BOOK", title);
                    editor.commit();



                    Intent readIntent = new Intent(
                            context, ReadActivity.class);
                    readIntent.putExtra("POSITION", position);
                    context.startActivity(readIntent);

                } else {
                    Toast.makeText(context.getApplicationContext(), "Must Download First", Toast.LENGTH_LONG).show();
                    return;
                }

            }
        });

        //Download button is clicked
        viewHolder.downloadButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //delete the book on long click
                mBooks.set(position, new Books(title, true));
                Button button = viewHolder.downloadButton;
                button.setEnabled(true);
                context.deleteFile(ReadActivity.titles[position] + ".txt");
                /*Shared Preferences Deleting the Line Number*/
                SharedPreferences settings = context.getSharedPreferences("mysettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                String filename = ReadActivity.titles[position];
                editor.remove(filename);
                editor.commit();

                Toast.makeText(context, "Book Deleted", Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    // Provide a direct reference to each of the views within a data item
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button downloadButton;
        public Button readButton;

        // Constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.book_name);
            downloadButton = itemView.findViewById(R.id.download_button);
            readButton = itemView.findViewById(R.id.read_button);

        }


    }
}
