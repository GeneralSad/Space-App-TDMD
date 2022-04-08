package com.leonv.spaceapp.Views;

import android.os.Bundle;
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
            textProp1, textProp2, textTWR, textType, textActive, textStages, textBoosters, textLaunchCost,
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
    private String type;
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
        textType = findViewById(R.id.rocketPopupType);
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
            TWR = getIntent().getIntExtra("TWR", -1);
            type = getIntent().getStringExtra("type");
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
        textHeight.setText(Integer.toString(height));
        textDiameter.setText(Integer.toString(diameter));
        textMass.setText(Integer.toString(mass));
        textFS.setText(FSEngines + " : " + FSReusable + " : " + FSFuelInTons);
        textSS.setText(SSEngines + " : " + SSFuelInTons);
        textEnginesType.setText(engines_Type);
        textEnginesLossMax.setText(Integer.toString(engineLossMax));
        textProp1.setText(propellant1);
        textProp2.setText(propellant2);
        textTWR.setText(Double.toString(TWR));
        textType.setText(type);
        textActive.setText(Boolean.toString(active));
        textStages.setText(Integer.toString(stages));
        textBoosters.setText(Integer.toString(boosters));
        textLaunchCost.setText(Integer.toString(launchCostDollar));
        textSuccessRate.setText(Integer.toString(successRate));
        textCompany.setText(company);
        textWikipedia.setText(wikipediaLink);
        textDescription.setText(description);
        Log.i(LOGTAG, "Set data");

    }



}
