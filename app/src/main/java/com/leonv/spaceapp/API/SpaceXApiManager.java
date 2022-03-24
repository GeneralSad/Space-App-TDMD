package com.leonv.spaceapp.API;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.Landpad;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.Models.Payload;
import com.leonv.spaceapp.Models.PayloadWeight;
import com.leonv.spaceapp.Models.Rocket;
import com.leonv.spaceapp.Models.RocketFlightCore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SpaceXApiManager {
    private static final String LOGTAG = SpaceXApiManager.class.getName();

    private Context appContext;
    private RequestQueue queue;
    private SpaceXApiListener listener;

    public SpaceXApiManager(Context context, SpaceXApiListener listener) {
        this.appContext = context;
        this.listener = listener;
        this.queue = Volley.newRequestQueue(this.appContext);
    }

    //TODO: LANDPAD, LAUNCHPAD, SPECIFIC FLIGHT

    public void getLaunchPadData(String id) {
        final String url = "https://api.spacexdata.com/v4/launchpads/" + id;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(LOGTAG, "Volley response: " + response.toString());
                        try {

                            String name = response.getString("name");
                            String fullName = response.getString("full_name");
                            String locality = response.getString("locality");
                            String region = response.getString("region");
                            int landingAttempts = response.getInt("launch_attempts");
                            int landingSuccesses = response.getInt("launch_successes");

                            JSONArray jsonRocketIds = response.getJSONArray("rockets");
                            ArrayList<String> rocketIds = new ArrayList<>();
                            for (int i = 0; i < jsonRocketIds.length(); i++) {
                                rocketIds.add(jsonRocketIds.getString(i));
                            }

                            String timezone = response.getString("timezone");

                            JSONArray jsonFlightIds = response.getJSONArray("launches");
                            ArrayList<String> flightIds = new ArrayList<>();
                            for (int i = 0; i < jsonFlightIds.length(); i++) {
                                flightIds.add(jsonFlightIds.getString(i));
                            }

                            String status = response.getString("status");

                            String details = response.getString("details");
                            String id = response.getString("id");

                            Log.d(LOGTAG, "Adding payload");
                            Launchpad launchpad = new Launchpad(name, fullName, locality, region, landingAttempts, landingSuccesses, rocketIds, timezone, flightIds, status, details, id);
                            listener.onLaunchpadAvailable(launchpad);

                        } catch (JSONException exception) {
                            Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(LOGTAG, error.getLocalizedMessage());
                        listener.onDataError(new Error(error.getLocalizedMessage()));
                    }
                }
        );

        this.queue.add(request);

    }

    public void getLandPadData(String id) {
        final String url = "https://api.spacexdata.com/v4/landpads/" + id;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(LOGTAG, "Volley response: " + response.toString());
                        try {

                            String name = response.getString("name");
                            String fullName = response.getString("full_name");
                            String status = response.getString("status");
                            String type = response.getString("type");
                            String locality = response.getString("locality");
                            String region = response.getString("region");
                            int landingAttempts = response.getInt("landing_attempts");
                            int landingSuccesses = response.getInt("landing_successes");
                            String wikipedia = response.getString("wikipedia");
                            String details = response.getString("details");

                            JSONArray jsonFlightIds = response.getJSONArray("launches");
                            ArrayList<String> flightIds = new ArrayList<>();
                            for (int i = 0; i < jsonFlightIds.length(); i++) {
                                flightIds.add(jsonFlightIds.getString(i));
                            }

                            String id = response.getString("id");

                            Log.d(LOGTAG, "Adding payload");
                            Landpad landpad = new Landpad(name, fullName, status, type, locality, region, landingAttempts, landingSuccesses, wikipedia, details, flightIds, id);
                            listener.onLandpadAvailable(landpad);

                        } catch (JSONException exception) {
                            Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(LOGTAG, error.getLocalizedMessage());
                        listener.onDataError(new Error(error.getLocalizedMessage()));
                    }
                }
        );

        this.queue.add(request);

    }

    public void getPayloadData(String id) {

        final String url = "https://api.spacexdata.com/v4/payloads/" + id;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(LOGTAG, "Volley response: " + response.toString());
                        try {

                            String name = response.getString("name");
                            String type = response.getString("type");

                            JSONArray jsonCustomers = response.getJSONArray("customers");
                            ArrayList<String> customers = new ArrayList<>();
                            for (int i = 0; i < jsonCustomers.length(); i++) {
                                customers.add(jsonCustomers.getString(i));
                            }

                            JSONArray jsonNationalities = response.getJSONArray("nationalities");
                            ArrayList<String> nationalities = new ArrayList<>();
                            for (int i = 0; i < jsonNationalities.length(); i++) {
                               nationalities.add(jsonNationalities.getString(i));
                            }

                            JSONArray jsonManufacturers = response.getJSONArray("manufacturers");
                            ArrayList<String> manufacturers = new ArrayList<>();
                            for (int i = 0; i < jsonManufacturers.length(); i++) {
                                manufacturers.add(jsonManufacturers.getString(i));
                            }

                            int mass = response.getInt("mass_kg");
                            String orbitType = response.getString("orbit");
                            int lifeSpan = !response.isNull("lifespan_years") ? response.getInt("lifespan_years") : -1;

                            String payloadId = response.getString("id");

                            Log.d(LOGTAG, "Adding payload");
                            Payload payload = new Payload(name, type, customers, nationalities, manufacturers, mass, orbitType, lifeSpan, payloadId);
                            listener.onPayloadAvailable(payload);

                        } catch (JSONException exception) {
                            Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(LOGTAG, error.getLocalizedMessage());
                        listener.onDataError(new Error(error.getLocalizedMessage()));
                    }
                }
        );

        this.queue.add(request);



    }

    public void getRocketData(String id) {
        final String url = "https://api.spacexdata.com/v4/rockets/" + id;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(LOGTAG, "Volley response: " + response.toString());
                        try {

                            JSONObject jsonHeight = response.getJSONObject("height");
                            int height = jsonHeight.getInt("meters");

                            JSONObject jsonDiameter = response.getJSONObject("diameter");
                            int diameter = jsonDiameter.getInt("meters");

                            JSONObject jsonMass = response.getJSONObject("mass");
                            int mass = jsonMass.getInt("kg");

                            JSONObject jsonFS = response.getJSONObject("first_stage");
                            boolean FS_reusable = jsonFS.getBoolean("reusable");
                            int FS_Engines = jsonFS.getInt("engines");
                            int FS_FuelInTons = jsonFS.getInt("fuel_amount_tons");

                            JSONObject jsonSS = response.getJSONObject("second_stage");
                            int SS_Engines = jsonSS.getInt("engines");
                            int SS_FuelInTons = jsonSS.getInt("fuel_amount_tons");

                            JSONObject jsonEngines = response.getJSONObject("engines");
                            String engines_Type = jsonEngines.getString("type");
                            int engines_EngineLossMax = jsonEngines.getInt("engine_loss_max");
                            String propellant1 = jsonEngines.getString("propellant_1");
                            String propellant2 = jsonEngines.getString("propellant_2");
                            int TWR = jsonEngines.getInt("thrust_to_weight");

                            JSONArray jsonPayloadWeights = response.getJSONArray("payload_weights");
                            ArrayList<PayloadWeight> payload_weights = new ArrayList<>();
                            for (int i = 0; i < jsonPayloadWeights.length(); i++) {
                                JSONObject payloadWeight = jsonPayloadWeights.getJSONObject(i);
                                String payloadWeightId = payloadWeight.getString("id");
                                String payloadWeightName = payloadWeight.getString("name");
                                int payloadWeightMass = payloadWeight.getInt("kg");
                                payload_weights.add(new PayloadWeight(payloadWeightId, payloadWeightName, payloadWeightMass));
                            }

                            String name = response.getString("name");
                            String type = response.getString("type");
                            boolean active = response.getBoolean("active");
                            int stages = response.getInt("stages");
                            int boosters = response.getInt("boosters");
                            int launchCostDollar = response.getInt("cost_per_launch");
                            int succesRate = response.getInt("success_rate_pct");
                            String company = response.getString("company");
                            String wikipediaLink = response.getString("wikipedia");
                            String description = response.getString("description");
                            String rocketId = response.getString("id");

                            Rocket rocket = new Rocket(height, diameter, mass, FS_reusable, FS_Engines, FS_FuelInTons, SS_Engines, SS_FuelInTons, engines_Type, engines_EngineLossMax, propellant1, propellant2, TWR, payload_weights, name, type, active, stages, boosters, launchCostDollar, succesRate, company, wikipediaLink, description, rocketId);
                            Log.d(LOGTAG, "Adding rocket");
                            listener.onRocketAvailable(rocket);

                        } catch (JSONException exception) {
                            Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(LOGTAG, error.getLocalizedMessage());
                        listener.onDataError(new Error(error.getLocalizedMessage()));
                    }
                }
        );

        this.queue.add(request);
    }

    public void getNextFlightData(String id) {
        final String url = "https://api.spacexdata.com/v4/launches/" + id;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(LOGTAG, "Volley response: " + response.toString());
                        try {

                            JSONObject fairings = response.getJSONObject("fairings");
                            boolean areReusedFairings = (!fairings.isNull("reused") && fairings.getBoolean("reused"));

                            JSONObject links = response.getJSONObject("links");
                            String webcastLink = links.getString("webcast");
                            String articleLink = links.getString("article");
                            String wikipediaLink = links.getString("wikipedia");

                            String staticFireDataUtc = response.getString("static_fire_date_utc");
                            boolean isTBD = (!response.isNull("tbd") && response.getBoolean("tbd"));
                            boolean isNET = (!response.isNull("net") && response.getBoolean("net"));

                            String rocketId = response.getString("rocket");
                            String launchDetails = response.getString("details");

                            JSONArray payloads = response.getJSONArray("payloads");
                            ArrayList<String> payloadIds = new ArrayList<>();
                            for (int i = 0; i < payloads.length(); i++) {
                                payloadIds.add(payloads.getString(i));
                            }

                            String launchpadId = response.getString("launchpad");
                            int flightNumber = response.getInt("flight_number");
                            String name = response.getString("name");
                            String launchDateUtc = response.getString("date_utc");
                            String datePrecision = response.getString("date_precision");

                            JSONArray jsonCores = response.getJSONArray("cores");
                            ArrayList<RocketFlightCore> cores = new ArrayList<>();
                            for (int i = 0; i < jsonCores.length(); i++) {
                                JSONObject rocketCore = jsonCores.getJSONObject(i);
                                int coreFlightNumber = rocketCore.getInt("flight");
                                boolean isReused = (!rocketCore.isNull("reused") && rocketCore.getBoolean("reused"));
                                String landingType = rocketCore.getString("landing_type");
                                String landpadId = rocketCore.getString("landpad");

                                RocketFlightCore core = new RocketFlightCore(coreFlightNumber, isReused, landingType, landpadId);
                                cores.add(core);
                            }

                            String flightId = response.getString("id");

                            Flight flight = new Flight(areReusedFairings, webcastLink, articleLink, wikipediaLink, staticFireDataUtc, isTBD, isNET, rocketId, launchDetails, payloadIds, launchpadId, flightNumber, name, launchDateUtc, datePrecision, cores, flightId);
                            Log.d(LOGTAG, "Adding flight");
                            listener.onFlightAvailable(flight);

                        } catch (JSONException exception) {
                            Log.e(LOGTAG, "Error while parsing JSON data: " + exception.getLocalizedMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(LOGTAG, error.getLocalizedMessage());
                        listener.onDataError(new Error(error.getLocalizedMessage()));
                    }
                }
        );

        this.queue.add(request);
    }
}
