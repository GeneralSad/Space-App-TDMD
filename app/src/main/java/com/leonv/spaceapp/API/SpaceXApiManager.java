package com.leonv.spaceapp.API;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.Landpad;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.Models.Payload;
import com.leonv.spaceapp.Models.PayloadWeight;
import com.leonv.spaceapp.Models.Rocket;
import com.leonv.spaceapp.Models.RocketFlightCore;
import com.leonv.spaceapp.OnItemClickListener;
import com.leonv.spaceapp.Viewmodels.MapViewModel;
import com.leonv.spaceapp.Viewmodels.RocketsViewModel;
import com.leonv.spaceapp.Viewmodels.UpcomingViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SpaceXApiManager {

    private static final String LOGTAG = SpaceXApiManager.class.getName();

    private static SpaceXApiManager instance;

    private RequestQueue queue;
    private ArrayList<SpaceXApiListener> listeners = new ArrayList<>();

    public SpaceXApiManager(Context context) {
        this.queue = Volley.newRequestQueue(context);
    }

    public static SpaceXApiManager getInstance(final Context context){
        if(instance == null){
            synchronized (SpaceXApiManager.class){
                if(instance == null){
                    instance = new SpaceXApiManager(context);
                }
            }
        }
        return instance;
    }

    public void addListener(SpaceXApiListener listener) {
        listeners.add(listener);
    }

    //TODO: Every get...Data method is repeating code, maybe find a way to do this better

    public void getRocketsData() {
        final String url = "https://api.spacexdata.com/v4/rockets";

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d(LOGTAG, "Volley response: " + response.toString());

                    try {
                        ArrayList<Rocket> rockets = new ArrayList<>(response.length());
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonRocket = response.getJSONObject(i);
                            Rocket rocket = createRocket(jsonRocket);
                            rockets.add(rocket);
                        }

                        for (SpaceXApiListener listener : listeners) {
                            listener.onRocketsAvailable(rockets);
                        }
                    } catch (JSONException exception) {
                        Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
                    }
                },
                error -> {
                    Log.e(LOGTAG, error.getLocalizedMessage());
                    listeners.forEach(listener -> listener.onDataError(new Error(error.getLocalizedMessage())));
                }
        );
        this.queue.add(request);
    }

    public void getLaunchPadData(String id) {
        final String url = "https://api.spacexdata.com/v4/launchpads/" + id;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d(LOGTAG, "Volley response: " + response.toString());

                    Launchpad launchpad = createLaunchpad(response);

                    for (SpaceXApiListener listener : listeners) {
                        listener.onLaunchpadAvailable(launchpad);
                    }
                },
                error -> {
                    Log.e(LOGTAG, error.getLocalizedMessage());
                    listeners.forEach(listener -> listener.onDataError(new Error(error.getLocalizedMessage())));
                }
        );
        this.queue.add(request);
    }

    public void getLaunchPadsData() {
        final String url = "https://api.spacexdata.com/v4/launchpads/";

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d(LOGTAG, "Volley response: " + response.toString());

                    try {
                        ArrayList<Launchpad> launchpads = new ArrayList<>(response.length());
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonFlight = response.getJSONObject(i);
                            Launchpad launchpad = createLaunchpad(jsonFlight);
                            launchpads.add(launchpad);
                        }

                        for (SpaceXApiListener listener : listeners) {
                            listener.onLaunchpadsAvailable(launchpads);
                        }
                    } catch (JSONException exception) {
                        Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
                    }
                },
                error -> {
                    Log.e(LOGTAG, error.getLocalizedMessage());
                    listeners.forEach(listener -> listener.onDataError(new Error(error.getLocalizedMessage())));
                }
        );
        this.queue.add(request);
    }

    public void getLandPadData(String id) {
        final String url = "https://api.spacexdata.com/v4/landpads/" + id;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d(LOGTAG, "Volley response: " + response.toString());

                    Landpad landpad = createLandpad(response);

                    for (SpaceXApiListener listener : listeners) {
                        listener.onLandpadAvailable(landpad);
                    }

                },
                error -> {
                    Log.e(LOGTAG, error.getLocalizedMessage());
                    listeners.forEach(listener -> listener.onDataError(new Error(error.getLocalizedMessage())));
                }
        );
        this.queue.add(request);
    }

    public void getPayloadData(String id) {

        final String url = "https://api.spacexdata.com/v4/payloads/" + id;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d(LOGTAG, "Volley response: " + response.toString());

                    Payload payload = createPayload(response);

                    for (SpaceXApiListener listener : listeners) {
                        listener.onPayloadAvailable(payload);
                    }

                },
                error -> {
                    Log.e(LOGTAG, error.getLocalizedMessage());
                    listeners.forEach(listener -> listener.onDataError(new Error(error.getLocalizedMessage())));
                }
        );
        this.queue.add(request);
    }

    public void getRocketData(String id) {
        final String url = "https://api.spacexdata.com/v4/rockets/" + id;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d(LOGTAG, "Volley response: " + response.toString());

                    Rocket rocket = createRocket(response);

                    for (SpaceXApiListener listener : listeners) {
                        listener.onRocketAvailable(rocket);
                    }

                },
                error -> {
                    Log.e(LOGTAG, error.getLocalizedMessage());
                    listeners.forEach(listener -> listener.onDataError(new Error(error.getLocalizedMessage())));
                }
        );
        this.queue.add(request);
    }

    public void getFlightData(String id) {
        final String url = "https://api.spacexdata.com/v4/launches/" + id;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d(LOGTAG, "Volley response: " + response.toString());

                    Flight flight = createFlight(response);

                    for (SpaceXApiListener listener : listeners) {
                        listener.onFlightAvailable(flight);
                    }

                },
                error -> {
                    Log.e(LOGTAG, error.getLocalizedMessage());
                    listeners.forEach(listener -> listener.onDataError(new Error(error.getLocalizedMessage())));
                }
        );
        this.queue.add(request);
    }

    public void getFlightsData(String pathName) {
        final String url = "https://api.spacexdata.com/v4/launches/" + pathName;

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d(LOGTAG, "Volley response: " + response.toString());
                    try {

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonFlight = response.getJSONObject(i);
                            Flight flight = createFlight(jsonFlight);

                            for (SpaceXApiListener listener : listeners) {
                                listener.onFlightAvailable(flight);
                            }

                        }

                    } catch (JSONException exception) {
                        Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
                    }
                },
                error -> {
                    Log.e(LOGTAG, error.getLocalizedMessage());
                    listeners.forEach(listener -> listener.onDataError(new Error(error.getLocalizedMessage())));
                }
        );
        this.queue.add(request);
    }

    private Launchpad createLaunchpad(JSONObject jsonLaunchpad) {

        try {

            String name = !jsonLaunchpad.isNull("name") ? jsonLaunchpad.getString("name") : "N/A";
            String fullName = !jsonLaunchpad.isNull("full_name") ? jsonLaunchpad.getString("full_name") : "N/A";
            String locality = !jsonLaunchpad.isNull("locality") ? jsonLaunchpad.getString("locality") : "N/A";
            String region = !jsonLaunchpad.isNull("region") ? jsonLaunchpad.getString("region") : "N/A";

            double latitude = !jsonLaunchpad.isNull("latitude") ? jsonLaunchpad.getDouble("latitude") : 0.0;
            double longitude = !jsonLaunchpad.isNull("longitude") ? jsonLaunchpad.getDouble("longitude") : 0.0;

            int launchAttempts = jsonLaunchpad.getInt("launch_attempts");
            int launchSuccesses = jsonLaunchpad.getInt("launch_successes");

            JSONArray jsonRocketIds = jsonLaunchpad.getJSONArray("rockets");
            ArrayList<String> rocketIds = new ArrayList<>();
            for (int i = 0; i < jsonRocketIds.length(); i++) {
                rocketIds.add(jsonRocketIds.getString(i));
            }

            String timezone = !jsonLaunchpad.isNull("timezone") ? jsonLaunchpad.getString("timezone") : "N/A";

            JSONArray jsonFlightIds = jsonLaunchpad.getJSONArray("launches");
            ArrayList<String> flightIds = new ArrayList<>();
            for (int i = 0; i < jsonFlightIds.length(); i++) {
                flightIds.add(jsonFlightIds.getString(i));
            }

            String status = jsonLaunchpad.getString("status");

            String details = !jsonLaunchpad.isNull("details") ? jsonLaunchpad.getString("details") : "N/A";
            String launchpadId = jsonLaunchpad.getString("id");

            Log.d(LOGTAG, "Creating launchpad");
            Launchpad launchpad = new Launchpad(name, fullName, locality, region, launchAttempts,
                    launchSuccesses, rocketIds, timezone, latitude, longitude, flightIds, status,
                    details, launchpadId);
            return launchpad;

        } catch (Exception exception) {
            Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
            return null;
        }

    }

    private Landpad createLandpad(JSONObject jsonLandpad) {

        try {

            String name = !jsonLandpad.isNull("name") ? jsonLandpad.getString("name") : "N/A";
            String fullName = !jsonLandpad.isNull("full_name") ? jsonLandpad.getString("full_name") : "N/A";

            String status = jsonLandpad.getString("status");
            String type = !jsonLandpad.isNull("type") ? jsonLandpad.getString("type") : "N/A";

            String locality = !jsonLandpad.isNull("locality") ? jsonLandpad.getString("locality") : "N/A";
            String region = !jsonLandpad.isNull("region") ? jsonLandpad.getString("region") : "N/A";

            double latitude = !jsonLandpad.isNull("latitude") ? jsonLandpad.getDouble("latitude") : 0;
            double longitude = !jsonLandpad.isNull("longitude") ? jsonLandpad.getDouble("longitude") : 0;

            int landingAttempts = jsonLandpad.getInt("landing_attempts");
            int landingSuccesses = jsonLandpad.getInt("landing_successes");

            String wikipedia = !jsonLandpad.isNull("wikipedia") ? jsonLandpad.getString("wikipedia") : "N/A";
            String details = !jsonLandpad.isNull("details") ? jsonLandpad.getString("details") : "N/A";

            JSONArray jsonFlightIds = jsonLandpad.getJSONArray("launches");
            ArrayList<String> flightIds = new ArrayList<>();
            for (int i = 0; i < jsonFlightIds.length(); i++) {
                flightIds.add(jsonFlightIds.getString(i));
            }

            String landpadId = jsonLandpad.getString("id");


            Log.d(LOGTAG, "Creating landpad");
            Landpad landpad = new Landpad(name, fullName, status, type, locality, region,
                    latitude, longitude, landingAttempts, landingSuccesses, wikipedia,
                    details, flightIds, landpadId);
            return landpad;

        } catch (Exception exception) {
            Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
            return null;
        }

    }

    private Payload createPayload(JSONObject jsonPayload) {

        try {

            //TODO: Maybe add launchId
            String name = !jsonPayload.isNull("name") ? jsonPayload.getString("name") : "N/A";
            String type = !jsonPayload.isNull("type") ? jsonPayload.getString("type") : "N/A";

            //TODO: Check if this won't crash when json is empty
            JSONArray jsonCustomers = jsonPayload.getJSONArray("customers");
            ArrayList<String> customers = new ArrayList<>();
            for (int i = 0; i < jsonCustomers.length(); i++) {
                customers.add(jsonCustomers.getString(i));
            }

            JSONArray jsonNationalities = jsonPayload.getJSONArray("nationalities");
            ArrayList<String> nationalities = new ArrayList<>();
            for (int i = 0; i < jsonNationalities.length(); i++) {
                nationalities.add(jsonNationalities.getString(i));
            }

            JSONArray jsonManufacturers = jsonPayload.getJSONArray("manufacturers");
            ArrayList<String> manufacturers = new ArrayList<>();
            for (int i = 0; i < jsonManufacturers.length(); i++) {
                manufacturers.add(jsonManufacturers.getString(i));
            }

            int mass = !jsonPayload.isNull("mass_kg") ? jsonPayload.getInt("mass_kg") : -1;
            String orbitType = !jsonPayload.isNull("orbit") ? jsonPayload.getString("orbit") : "N/A";
            int lifeSpan = !jsonPayload.isNull("lifespan_years") ? jsonPayload.getInt("lifespan_years") : -1;

            String payloadId = jsonPayload.getString("id");

            Log.d(LOGTAG, "Creating payload");
            Payload payload = new Payload(name, type, customers, nationalities, manufacturers,
                    mass, orbitType, lifeSpan, payloadId);
            return payload;

        } catch (JSONException exception) {
            Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
            return null;
        }

    }

    private Rocket createRocket(JSONObject jsonRocket) {

        try {

            JSONObject jsonHeight = jsonRocket.getJSONObject("height");
            int height = jsonHeight.getInt("meters");

            JSONObject jsonDiameter = jsonRocket.getJSONObject("diameter");
            int diameter = jsonDiameter.getInt("meters");

            JSONObject jsonMass = jsonRocket.getJSONObject("mass");
            int mass = jsonMass.getInt("kg");

            JSONObject jsonFS = jsonRocket.getJSONObject("first_stage");
            boolean FS_reusable = jsonFS.getBoolean("reusable");
            int FS_Engines = jsonFS.getInt("engines");
            int FS_FuelInTons = jsonFS.getInt("fuel_amount_tons");

            JSONObject jsonSS = jsonRocket.getJSONObject("second_stage");
            int SS_Engines = jsonSS.getInt("engines");
            int SS_FuelInTons = jsonSS.getInt("fuel_amount_tons");

            JSONObject jsonEngines = jsonRocket.getJSONObject("engines");
            String engines_Type = jsonEngines.getString("type");
            int engines_EngineLossMax = !jsonEngines.isNull("engine_loss_max") ? jsonEngines.getInt("engine_loss_max") : 0;
            String propellant1 = jsonEngines.getString("propellant_1");
            String propellant2 = jsonEngines.getString("propellant_2");
            int TWR = jsonEngines.getInt("thrust_to_weight");

            JSONArray jsonPayloadWeights = jsonRocket.getJSONArray("payload_weights");
            ArrayList<PayloadWeight> payload_weights = new ArrayList<>();
            for (int i = 0; i < jsonPayloadWeights.length(); i++) {
                JSONObject payloadWeight = jsonPayloadWeights.getJSONObject(i);
                String payloadWeightId = payloadWeight.getString("id");
                String payloadWeightName = payloadWeight.getString("name");
                int payloadWeightMass = payloadWeight.getInt("kg");
                payload_weights.add(new PayloadWeight(payloadWeightId, payloadWeightName, payloadWeightMass));
            }

            String name = jsonRocket.getString("name");
            String type = jsonRocket.getString("type");
            boolean active = jsonRocket.getBoolean("active");
            int stages = jsonRocket.getInt("stages");
            int boosters = jsonRocket.getInt("boosters");
            int launchCostDollar = jsonRocket.getInt("cost_per_launch");
            int succesRate = jsonRocket.getInt("success_rate_pct");
            String company = jsonRocket.getString("company");
            String wikipediaLink = jsonRocket.getString("wikipedia");
            String description = jsonRocket.getString("description");
            String rocketId = jsonRocket.getString("id");

            JSONArray jsonImages = jsonRocket.getJSONArray("flickr_images");
            String image = !jsonImages.isNull(0) ? jsonImages.getString(0) : "";

            Log.d(LOGTAG, "Creating rocket");
            Rocket rocket = new Rocket(height, diameter, mass, FS_reusable, FS_Engines, FS_FuelInTons,
                    SS_Engines, SS_FuelInTons, engines_Type, engines_EngineLossMax, propellant1,
                    propellant2, TWR, payload_weights, name, type, active, stages, boosters, launchCostDollar,
                    succesRate, company, wikipediaLink, description, rocketId, image);
            return rocket;

        } catch (JSONException exception) {
            Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
            return null;
        }

    }

    private Flight createFlight(JSONObject jsonObject) {

        try {

            boolean hasReusedFairings;

            if (!jsonObject.isNull("fairings")) {
                JSONObject fairings = jsonObject.getJSONObject("fairings");
                hasReusedFairings= (!fairings.isNull("reused") && fairings.getBoolean("reused"));
            } else  {
                hasReusedFairings = false;
            }


            JSONObject links = jsonObject.getJSONObject("links");
            String webcastLink = !links.isNull("webcast") ? links.getString("webcast") : "";
            String articleLink = !links.isNull("article") ? links.getString("article") : "";
            String wikipediaLink = !links.isNull("wikipedia") ? links.getString("wikipedia") : "";

            JSONObject jsonPatch = links.getJSONObject("patch");
            String missionPatch = "";
            if (!jsonPatch.isNull("original")) {
                missionPatch = jsonPatch.getString("original");
            } else if (!jsonPatch.isNull("small")) {
                missionPatch = jsonPatch.getString("small");
            }

            String staticFireDateUtc = !jsonObject.isNull("static_fire_date_utc") ? jsonObject.getString("static_fire_date_utc") : "";
            boolean isTBD = (!jsonObject.isNull("tbd") && jsonObject.getBoolean("tbd"));
            boolean isNET = (!jsonObject.isNull("net") && jsonObject.getBoolean("net"));

            String rocketId = !jsonObject.isNull("rocket") ? jsonObject.getString("rocket") : "";
            String launchDetails = !jsonObject.isNull("details") ? jsonObject.getString("details") : "";

            JSONArray payloads = jsonObject.getJSONArray("payloads");
            ArrayList<String> payloadIds = new ArrayList<>();
            for (int i = 0; i < payloads.length(); i++) {
                payloadIds.add(payloads.getString(i));
            }

            String launchpadId = !jsonObject.isNull("launchpad") ? jsonObject.getString("launchpad") : "";
            int flightNumber = jsonObject.getInt("flight_number");
            String name = jsonObject.getString("name");
            String launchDateUtc = jsonObject.getString("date_utc");
            String datePrecision =  jsonObject.getString("date_precision");

            JSONArray jsonCores = jsonObject.getJSONArray("cores");
            ArrayList<RocketFlightCore> cores = new ArrayList<>();
            for (int i = 0; i < jsonCores.length(); i++) {
                JSONObject rocketCore = jsonCores.getJSONObject(i);
                int coreFlightNumber = !rocketCore.isNull("flight") ? rocketCore.getInt("flight") : -1;
                boolean isReused = (!rocketCore.isNull("reused") && rocketCore.getBoolean("reused"));
                String landingType = !rocketCore.isNull("landing_type") ? rocketCore.getString("landing_type") : "";
                String landpadId = !rocketCore.isNull("landpad") ? rocketCore.getString("landpad") : "";

                RocketFlightCore core = new RocketFlightCore(coreFlightNumber, isReused, landingType, landpadId);
                cores.add(core);
            }

            String flightId = jsonObject.getString("id");

            Log.d(LOGTAG, "Creating flight");
            Flight flight = new Flight(hasReusedFairings, webcastLink, articleLink, wikipediaLink,
                    staticFireDateUtc, isTBD, isNET, rocketId, launchDetails, payloadIds, launchpadId,
                    flightNumber, name, launchDateUtc, datePrecision, cores, flightId, missionPatch);

            return flight;

        } catch (JSONException exception) {
            Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
            return null;
        }
    }

}
