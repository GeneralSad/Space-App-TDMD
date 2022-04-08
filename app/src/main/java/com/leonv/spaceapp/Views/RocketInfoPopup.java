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

public class RocketInfoPopup extends AppCompatActivity {

    private static final String LOGTAG = RocketInfoPopup.class.getName();

    ImageView imageView;
    TextView textName, textHeight, textDiameter, textMass, textFS, textSS, textEnginesType, textEnginesLossMax,
            textProp1, textProp2, textTWR, textActive, textStages, textBoosters, textLaunchCost,
            textSuccessRate, textCompany, textWikipedia, textDescription;

    private int height;
    private int diameter;
    private int mass;
    private boolean FSReusable;
    private int FSEngines;
    private int FSFuelInTons;
    private int SSEngines;
    private int SSFuelInTons;
    private String engines_Type;
    private int engineLossMax;
    private String propellant1;
    private String propellant2;
    private double TWR;

    private String name;
    private boolean active;
    private int stages;
    private int boosters;
    private int launchCostDollar;
    private int successRate;
    private String company;
    private String wikipediaLink;
    private String description;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rocket_info_popup);

        imageView = findViewById(R.id.rocketPopupImage);
        textName = findViewById(R.id.rocketPopupName);
        textHeight = findViewById(R.id.rocketPopupHeight);
        textDiameter = findViewById(R.id.rocketPopupDiameter);
        textMass = findViewById(R.id.rocketPopupMass);
        textFS = findViewById(R.id.rocketPopupFS);
        textSS = findViewById(R.id.rocketPopupSS);
        textEnginesType = findViewById(R.id.rocketPopupEngineType);
        textEnginesLossMax = findViewById(R.id.rocketPopupEngineLoss);
        textProp1 = findViewById(R.id.rocketPopupProp1);
        textProp2 = findViewById(R.id.rocketPopupProp2);
        textTWR = findViewById(R.id.rocketPopupTWR);
        textActive = findViewById(R.id.rocketPopupActive);
        textStages = findViewById(R.id.rocketPopupStages);
        textBoosters = findViewById(R.id.rocketPopupBoosters);
        textLaunchCost = findViewById(R.id.rocketPopupLaunchCost);
        textSuccessRate = findViewById(R.id.rocketPopupSuccessRate);
        textCompany = findViewById(R.id.rocketPopupCompany);
        textWikipedia = findViewById(R.id.rocketPopupWikipedia);
        textDescription = findViewById(R.id.rocketPopupDescription);

        getData();
        setData();

    }

    private void getData() {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("description")) {

            name = getIntent().getStringExtra("name");
            height = getIntent().getIntExtra("height", -1);
            diameter = getIntent().getIntExtra("diameter", -1);
            mass = getIntent().getIntExtra("mass", -1);
            FSReusable = getIntent().getBooleanExtra("FSReusable", false);
            FSEngines = getIntent().getIntExtra("FSEngines", -1);
            FSFuelInTons = getIntent().getIntExtra("FSFuelInTons", -1);
            SSEngines = getIntent().getIntExtra("SSEngines", -1);
            SSFuelInTons = getIntent().getIntExtra("SSFuelInTons", -1);
            engines_Type = getIntent().getStringExtra("enginesType");
            engineLossMax = getIntent().getIntExtra("enginesLossMax", -1);
            propellant1 = getIntent().getStringExtra("propellant1");
            propellant2 = getIntent().getStringExtra("propellant2");
            TWR = getIntent().getDoubleExtra("TWR", -1);
            active = getIntent().getBooleanExtra("active", true);
            stages = getIntent().getIntExtra("stages", -1);
            boosters = getIntent().getIntExtra("boosters", -1);
            launchCostDollar = getIntent().getIntExtra("launchCostDollar", -1);
            successRate = getIntent().getIntExtra("successRate", -1);
            company = getIntent().getStringExtra("company");
            wikipediaLink = getIntent().getStringExtra("wikipedia");
            description = getIntent().getStringExtra("description");
            image = getIntent().getStringExtra("image");


        } else {
            Log.e(LOGTAG, "No data");
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {

        Picasso.get().load(image).into(imageView);
        textName.setText(name);
        textHeight.setText("Height: " + Integer.toString(height) + " Meters");
        textDiameter.setText("Length: " + Integer.toString(diameter) + " Meters");
        textMass.setText("Mass: " + Integer.toString(mass) + " Kg");
        textFS.setText("First Stage\n\t\tEngines: " + FSEngines + "\n\t\tFuel: " + FSFuelInTons+ ".000 Kg\n\t\tReusable: " + (FSReusable ? "Yes" : "No"));
        textSS.setText("Second Stage\n\t\tEngines: " + SSEngines + "\n\t\tFuel: " + SSFuelInTons + ".000 Kg");
        textEnginesType.setText("Engine Type: " + engines_Type);
        textEnginesLossMax.setText("Max Engine Loss: " + Integer.toString(engineLossMax) + " engines");
        textProp1.setText("Oxidizer: " + propellant1);
        textProp2.setText("Combustible: " + propellant2);
        textTWR.setText("Thrust to Weight Ratio: " + Double.toString(TWR));
        textActive.setText("In use: " + (active ? "Yes" : "No"));
        textStages.setText("Stages: " + Integer.toString(stages));
        textBoosters.setText("Boosters: " + Integer.toString(boosters));
        textLaunchCost.setText("Launch Cost: " + Double.toString(launchCostDollar / 1000000.0) + " Million USD");
        textSuccessRate.setText("Success Rate: " + Integer.toString(successRate) + "%");
        textCompany.setText("Made by: " + company);
        textWikipedia.setText(Html.fromHtml("<a href=\"" + wikipediaLink + "\">Wikipedia</a>"));
        textWikipedia.setMovementMethod(LinkMovementMethod.getInstance());
        textDescription.setText(description);
        Log.i(LOGTAG, "Set data");

    }



}
