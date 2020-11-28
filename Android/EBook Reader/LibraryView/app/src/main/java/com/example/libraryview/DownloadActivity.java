package com.example.libraryview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadActivity extends AppCompatActivity {
    String site, filename;
    TextView status;
    //urls to grab txt files from
    String[] urls = {
            "http://styere.xyz/class/TimeMachine.txt",
            "http://styere.xyz/class/ChristmasCarol.txt",
            "https://www.gutenberg.org/files/76/76-0.txt",
            "https://www.gutenberg.org/files/1661/1661-0.txt",
            "https://www.gutenberg.org/files/74/74-0.txt",
            "https://www.gutenberg.org/files/11/11-0.txt",
            "https://www.gutenberg.org/files/1998/1998-0.txt",
            "https://www.gutenberg.org/ebooks/375.txt",
            "https://www.gutenberg.org/files/45/45-0.txt",
            "https://www.gutenberg.org/ebooks/1250.txt",
            "https://www.gutenberg.org/ebooks/20203.txt",
            "https://www.gutenberg.org/files/160/160-0.txt",
            "https://www.gutenberg.org/ebooks/16328.txt",
            "https://www.gutenberg.org/ebooks/4363.txt",
            "https://www.gutenberg.org/files/28054/28054-0.txt",
            "https://www.gutenberg.org/ebooks/19942.txt",
            "https://www.gutenberg.org/files/60559/60559-0.txt",
            "https://www.gutenberg.org/files/46/46-0.txt",
            "https://www.gutenberg.org/ebooks/147.txt",
            "https://www.gutenberg.org/files/100/100-0.txt",
            "https://www.gutenberg.org/ebooks/3296.txt",
            "https://www.gutenberg.org/files/1184/1184-0.txt",
            "https://www.gutenberg.org/files/2554/2554-0.txt",
            "https://www.gutenberg.org/files/60553/60553-0.txt",
            "https://www.gutenberg.org/ebooks/815.txt",
            "https://www.gutenberg.org/ebooks/972.txt",
            "https://www.gutenberg.org/ebooks/1001.txt",
            "https://www.gutenberg.org/ebooks/2542.txt",
            "https://www.gutenberg.org/ebooks/345.txt",
            "https://www.gutenberg.org/files/2814/2814-0.txt",
            "https://www.gutenberg.org/files/158/158-0.txt",
            "https://www.gutenberg.org/ebooks/16643.txt",
            "https://www.gutenberg.org/files/3600/3600-0.txt",
            "https://www.gutenberg.org/files/4517/4517-0.txt",
            "https://www.gutenberg.org/files/84/84-0.txt",
            "https://www.gutenberg.org/files/1400/1400-0.txt",
            "https://www.gutenberg.org/files/2591/2591-0.txt",
            "https://www.gutenberg.org/files/829/829-0.txt",
            "https://www.gutenberg.org/files/219/219-0.txt",
            "https://www.gutenberg.org/files/2852/2852-0.txt",
            "https://www.gutenberg.org/ebooks/45502.txt",
            "https://www.gutenberg.org/ebooks/6130.txt",
            "https://www.gutenberg.org/ebooks/844.txt",
            "https://www.gutenberg.org/ebooks/11030.txt",
            "https://www.gutenberg.org/ebooks/15399.txt",
            "https://www.gutenberg.org/ebooks/1635.txt",
            "https://www.gutenberg.org/ebooks/1260.txt",
            "https://www.gutenberg.org/files/140/140-0.txt",
            "https://www.gutenberg.org/ebooks/27827.txt",
            "https://www.gutenberg.org/files/1251/1251-0.txt",
            "https://www.gutenberg.org/files/1322/1322-0.txt",
            "https://www.gutenberg.org/files/41/41-0.txt",
            "https://www.gutenberg.org/files/135/135-0.txt",
            "https://www.gutenberg.org/ebooks/3207.txt",
            "https://www.gutenberg.org/files/521/521-0.txt",
            "https://www.gutenberg.org/ebooks/514.txt",
            "https://www.gutenberg.org/ebooks/5200.txt",
            "https://www.gutenberg.org/files/2701/2701-0.txt",
            "https://www.gutenberg.org/files/59328/59328-0.txt",
            "https://www.gutenberg.org/files/1080/1080-0.txt",
            "https://www.gutenberg.org/ebooks/851.txt",
            "https://www.gutenberg.org/ebooks/23.txt",
            "https://www.gutenberg.org/ebooks/34901.txt",
            "https://www.gutenberg.org/files/60555/60555-0.txt",
            "https://www.gutenberg.org/files/16/16-0.txt",
            "https://www.gutenberg.org/ebooks/174.txt",
            "https://www.gutenberg.org/files/1342/1342-0.txt",
            "https://www.gutenberg.org/ebooks/1232.txt",
            "https://www.gutenberg.org/ebooks/5827.txt",
            "https://www.gutenberg.org/files/58585/58585-0.txt",
            "https://www.gutenberg.org/ebooks/3825.txt",
            "https://www.gutenberg.org/ebooks/1497.txt",
            "https://www.gutenberg.org/files/25344/25344-0.txt",
            "https://www.gutenberg.org/ebooks/7370.txt",
            "https://www.gutenberg.org/ebooks/161.txt",
            "https://www.gutenberg.org/ebooks/2500.txt",
            "https://www.gutenberg.org/files/1934/1934-0.txt",
            "https://www.gutenberg.org/ebooks/408.txt",
            "https://www.gutenberg.org/files/43/43-0.txt",
            "https://www.gutenberg.org/files/244/244-0.txt",
            "https://www.gutenberg.org/files/98/98-0.txt",
            "https://www.gutenberg.org/ebooks/833.txt",
            "https://www.gutenberg.org/files/35/35-0.txt",
            "https://www.gutenberg.org/ebooks/779.txt",
            "https://www.gutenberg.org/files/120/120-0.txt",
            "https://www.gutenberg.org/files/209/209-0.txt",
            "https://www.gutenberg.org/files/4300/4300-0.txt",
            "https://www.gutenberg.org/files/203/203-0.txt",
            "https://www.gutenberg.org/ebooks/60545.txt",
            "https://www.gutenberg.org/files/205/205-0.txt",
            "https://www.gutenberg.org/files/2600/2600-0.txt",
            "https://www.gutenberg.org/files/36/36-0.txt",
            "https://www.gutenberg.org/ebooks/55.txt",
            "https://www.gutenberg.org/files/2148/2148-0.txt",
            "https://www.gutenberg.org/ebooks/25525.txt",
            "https://www.gutenberg.org/ebooks/768.txt",
            "https://www.gutenberg.org/files/1952/1952-0.txt"
    };


    //array for titles to save files with
    String[] titles = {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        //initialize
        ActionBar actionBar = getSupportActionBar();
        status = findViewById(R.id.tv_pro);

        //get intent
        Intent startIntent = getIntent();
        int pos = startIntent.getIntExtra("POSITION", 0);
        //define writing and text-fetching vars
        site = urls[pos];
        filename = titles[pos];
        new webgo().execute(site);
        //feedback text
        status.setText("Downloading...");

    }

    public class webgo extends AsyncTask<String, Long, String> {


        @Override
        protected String doInBackground(String... strings) {


            try {

                //Reading from Internet
                // assemble the string and the search request
                StringBuilder response = new StringBuilder();
                URL url = new URL(site);

                // make the connection
                HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();

                // did it do ok?
                if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader input = new BufferedReader(
                            new InputStreamReader(httpconn.getInputStream()), 8192);
                    String strLine = null;
                    while ((strLine = input.readLine()) != null) {
                        // have more data
                        response.append(strLine);
                        response.append("\n");
                    }
                    input.close();
                    httpconn.disconnect();


                    //writing a file
                    OutputStream out = openFileOutput(filename+".txt", Context.MODE_PRIVATE);
                    out.write(response.toString().getBytes());
                    out.close();

                    //send as a string
                    return response.toString();
                }
            } catch (IOException e) {


                return "error";
            }
            return "error";
        }

        protected void onPostExecute(String result) {
            //status.setText("File Written");
            //new Read().execute();
            //handling errors
            if(result == "error"){
                status.setText("There was an error downloading the eBook. Please check your network connection and try again.");
                Toast.makeText(getApplicationContext(), "There was an error downloading the eBook. Please check your network connection and try again.", Toast.LENGTH_LONG).show();

            } else {
                status.setText("Download Complete");
                Toast.makeText(getApplicationContext(), "Download Complete", Toast.LENGTH_LONG).show();
            }
            //go back to previous
            finish();


        }


    }
}
