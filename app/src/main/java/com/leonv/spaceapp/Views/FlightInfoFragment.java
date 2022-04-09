package com.leonv.spaceapp.Views;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.leonv.spaceapp.R;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class FlightInfoFragment extends AppCompatActivity {

    private static final String LOGTAG = FlightInfoFragment.class.getName();

    ImageView imagePatch;
    TextView textName, textDetails, textDate, textFlightNumber, textStaticDate,
            textReusedFairings, textWebcast, textArticle, textWikipedia;

    private boolean hasReusedFairings;
    private String webcastLink;
    private String articleLink;
    private String wikipediaLink;
    private String staticFireDateUtc;
    private String launchDetails;
    private int flightNumber;
    private String name;
    private String launchDateUtc;
    private String missionPatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_info_fragment);

        textName = findViewById(R.id.flightPopupName);
        imagePatch = findViewById(R.id.flightPopupImage);
        textDetails = findViewById(R.id.flightPopupDetails);
        textDate = findViewById(R.id.flightPopupDate);
        textFlightNumber = findViewById(R.id.flightPopupFlightNumber);
        textStaticDate = findViewById(R.id.flightPopupStaticDate);
        textReusedFairings = findViewById(R.id.flightPopupReusedFairings);
        textWebcast = findViewById(R.id.flightPopupWebcast);
        textArticle = findViewById(R.id.flightPopupArticle);
        textWikipedia = findViewById(R.id.flightPopupWikipedia);

        getData();
        setData();

    }

    private void getData() {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("details")) {
            name = getIntent().getStringExtra("name");
            hasReusedFairings = getIntent().getBooleanExtra("reusedFairings", false);
            webcastLink = getIntent().getStringExtra("webcast");
            articleLink = getIntent().getStringExtra("article");
            wikipediaLink = getIntent().getStringExtra("wikipedia");
            staticFireDateUtc = getIntent().getStringExtra("staticDate");
            launchDetails = getIntent().getStringExtra("details");
            flightNumber = getIntent().getIntExtra("flightNumber", 0);
            launchDateUtc = getIntent().getStringExtra("launchDate");
            missionPatch = getIntent().getStringExtra("missionPatch");

        } else {
            Log.e(LOGTAG, "No data");
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {

        if (!missionPatch.isEmpty()) Picasso.get().load(missionPatch).into(imagePatch);
        textName.setText(name);
        textReusedFairings.setText(String.format("Reused fairings: %s", hasReusedFairings ? "Yes" : "No"));
        textWebcast.setText(Html.fromHtml("<a href=\"" + webcastLink + "\">Webcast</a>"));
        textWebcast.setMovementMethod(LinkMovementMethod.getInstance());
        textArticle.setText(Html.fromHtml("<a href=\"" + articleLink + "\">Article</a>"));
        textArticle.setMovementMethod(LinkMovementMethod.getInstance());
        textWikipedia.setText(Html.fromHtml("<a href=\"" + wikipediaLink + "\">Wikipedia</a>"));
        textWikipedia.setMovementMethod(LinkMovementMethod.getInstance());
        textStaticDate.setText(String.format("Static fire: %s", staticFireDateUtc));
        textDetails.setText(launchDetails);
        textFlightNumber.setText(String.format("Flight number: %d", flightNumber));
        textDate.setText(String.format("Launch: %s", launchDateUtc));
        Log.i(LOGTAG, "Set data");

    }

}
