package com.example.libraryview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadActivity extends AppCompatActivity {

    //array for names to save files and preferences with
    public static String[] titles = {
            "TheTimeMachine",
            "AChristmasCarol",
            "AdventuresofHuckleberryFinn",
            "TheAdventuresofSherlockHolmes",
            "TheAdventuresofTomSawyer",
            "AlicesAdventuresinWonderland",
            "AlsosprachZarathustraEnglish",
            "AnOccurrenceatOwlCreekBridge",
            "AnneofGreenGables",
            "Anthem",
            "AutobiographyofBenjaminFranklin",
            "TheAwakeningandSelectedShortStories",
            "BeowulfAnAngloSaxonEpicPoem",
            "BeyondGoodandEvil",
            "TheBrothersKaramazov",
            "Candide",
            "TheChaldeanAccountofGenesis",
            "AChristmasCarolinProseBeingaGhostStoryofChristmas",
            "CommonSense",
            "TheCompleteWorksofWilliamShakespeare",
            "TheConfessionsofStAugustine",
            "TheCountofMonteCristo",
            "CrimeandPunishment",
            "TheCriticismoftheFourthGospel",
            "DemocracyinAmericaVolume1",
            "TheDevilsDictionary",
            "DivineComedyLongfellowsTranslationHell",
            "ADollsHouseaplay",
            "Dracula",
            "Dubliners",
            "Emma",
            "EssaysbyRalphWaldoEmerson",
            "EssaysofMicheldeMontaigneComplete",
            "EthanFrome",
            "Frankenstein",
            "OrTheModernPrometheus",
            "GreatExpectations",
            "GrimmsFairyTales",
            "GulliversTravelsintoSeveralRemoteNationsoftheWorld",
            "HeartofDarkness",
            "TheHoundoftheBaskervilles",
            "HowtheOtherHalfLivesStudiesAmongtheTenementsofNewYork",
            "TheIliad",
            "TheImportanceofBeingEarnestATrivialComedyforSeriousPeople",
            "IncidentsintheLifeofaSlaveGirlWrittenbyHerself",
            "TheInterestingNarrativeoftheLifeofOlaudahEquianoOrGustavusVassaTheAfrican",
            "Ion",
            "JaneEyreAnAutobiography",
            "TheJungle",
            "TheKamaSutraofVatsyayana",
            "LeMortedArthurVolume1",
            "LeavesofGrass",
            "TheLegendofSleepyHollow",
            "LesMisérables",
            "Leviathan",
            "TheLifeandAdventuresofRobinsonCrusoe",
            "LittleWomen",
            "Metamorphosis",
            "MobyDick",
            "OrTheWhale",
            "ModernCopperSmelting",
            "AModestProposal",
            "NarrativeoftheCaptivityandRestorationofMrsMaryRowlandson",
            "NarrativeoftheLifeofFrederickDouglassanAmericanSlave",
            "OnLiberty",
            "TheParochialHistoryofCornwallVolume1",
            "PeterPan",
            "ThePictureofDorianGray",
            "PrideandPrejudice",
            "ThePrince",
            "TheProblemsofPhilosophy",
            "TheProphet",
            "Pygmalion",
            "TheRepublic",
            "TheScarletLetter",
            "SecondTreatiseofGovernment",
            "SenseandSensibility",
            "Siddhartha",
            "SongsofInnocenceandSongsofExperience",
            "TheSoulsofBlackFolk",
            "TheStrangeCaseofDrJekyllandMrHyde",
            "AStudyinScarlet",
            "ATaleofTwoCities",
            "TheTheoryoftheLeisureClass",
            "TheTimeMachine",
            "TheTragicalHistoryofDoctorFaustus",
            "TreasureIsland",
            "TheTurnoftheScrew",
            "Ulysses",
            "UncleTomsCabin",
            "TheUsedPeopleLot",
            "WaldenandOnTheDutyOfCivilDisobedience",
            "WarandPeace",
            "TheWaroftheWorlds",
            "TheWonderfulWizardofOz",
            "TheWorksofEdgarAllanPoeVolume2",
            "TheWorksofEdgarAllanPoeTheRavenEdition",
            "WutheringHeights",
            "TheYellowWallpaper"
    };
    String filename;


    TextView page;
    EditText current;
    TextView total;
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity);

        //initialize and get intent
        Intent startIntent = getIntent();
        int pos = startIntent.getIntExtra("POSITION", 0);
        filename = titles[pos];

        page = findViewById(R.id.page);

        current = findViewById(R.id.et_current);
        total = findViewById(R.id.tv_length);


        //set texview for scroll
        page.setMovementMethod(new ScrollingMovementMethod());


        //set font size
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        int fontsize = settings.getInt("FONT", 20);
        page.setTextSize(fontsize);

        //current lines and scrolls to last read position
        int lines = settings.getInt(filename + "", 0);
        current.setText(lines + "");
        page.scrollTo(0, lines * page.getLineHeight());

        //the read async, gets the text from the downloaded text file
        try {
            new Read().execute();
        } catch (Exception e) {
            page.setText(e.getMessage() + "");
        }

        //get line count
        total.setText(page.getLineCount() + "");


        //clicking on the current text line, and only jumps to that line if the submit on the keyboard is pressed
        current.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //input number
                    int going = Integer.parseInt(current.getText().toString());
                    //handling number values larger than the total lines of a book, and then subtracts some so the text is visible on the screen
                    if (going > page.getLineCount()) {
                        going = page.getLineCount() - 13;
                    }
                    //scrolls to desired line
                    page.scrollTo(0, going * page.getLineHeight());
                    return true;
                }
                return false;
            }
        });


        //handles updating the current lines as the user reads
        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = (int) (page.getScrollY() / (double) page.getLineHeight());
                current.setText(position + "");


            }
        });


    }

    //on resume, scroll to the last read line from previous session and set font size
    @Override
    protected void onResume() {
        super.onResume();

        //set new font size
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        int fontsize = settings.getInt("FONT", 20);
        page.setTextSize(fontsize);

        //scroll to last line read
        int lines = settings.getInt(filename + "", 0);
        current.setText(lines + "");
        page.scrollTo(0, lines * page.getLineHeight());


    }


    //to save things on pause in case of interruption
    @Override
    protected void onPause() {
        super.onPause();
        save();

    }


    //save the preferences for the current line
    //uses filename to be dynamic for all the books
    public void save() {
        settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        editor = settings.edit();
        page.performClick();
        editor.putInt(filename + "", Integer.parseInt(current.getText().toString()));
        editor.commit();
    }

    // AsyncTask to read the file in the background
    class Read extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... data) {


            //reading through a bufferedreader
            try {
                String path = getApplicationContext().getFilesDir().getAbsolutePath();
                path += "/" + filename + ".txt";

                BufferedReader reader;
                reader = new BufferedReader(new FileReader(path));

                StringBuilder response = new StringBuilder();
                String temp = reader.readLine();
                //reads as many lines as there are and builds a stringbuilder out of it
                while (temp != null) {
                    response.append(temp);
                    response.append("\n");
                    // read next temp
                    temp = reader.readLine();
                }
                //close reader
                reader.close();

                //return the response
                return response.toString();
            } catch (IOException e) {
                return (e.getMessage());
            }


        }

        protected void onPostExecute(String result) {
            //set the page display to the book file String that was read
            page.setText(result);
            //set the total line count
            total.setText(page.getLineCount() + "");
        }
    }

}


