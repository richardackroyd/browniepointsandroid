package centurion.dev.browniepoints.APIServices;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javax.net.ssl.HttpsURLConnection;
import centurion.dev.browniepoints.DataModel.PointsAccount;
import centurion.dev.browniepoints.Screens.PointsSummary.PointsSummaryAdapter;

/**
 * Created by rich on 25/11/2017.
 */

public class PointsSummaryAPIService extends AsyncTask<Void, Void, ArrayList<PointsAccount>>{

    private final String mURL = "https://mysterious-forest-42652.herokuapp.com/api/points";

    private final PointsSummaryAdapter pointsSummaryAdapter;

    public PointsSummaryAPIService(PointsSummaryAdapter adapter) {

        this.pointsSummaryAdapter = adapter;

    }

    private InputStream retrieveStream(String url) {

        try {

            URL pointsSummaryEndPoint = new URL(mURL);

            //TODO update to HTTPClient as more robust
            HttpsURLConnection myConnection =
                    (HttpsURLConnection) pointsSummaryEndPoint.openConnection();

            if (myConnection.getResponseCode() == 200) {

                InputStream responseBody = myConnection.getInputStream();

                return responseBody;

            }

        } catch (Exception e) {System.out.println("Exception: " + e); }

        return null;

    }

    //TODO refactor as too large

    @Override
    protected ArrayList<PointsAccount> doInBackground(Void... params) {

        InputStream source =  retrieveStream(mURL);

        ArrayList<PointsAccount> allPointsAccounts = new ArrayList<PointsAccount>();

        //TODO stops crash if no network connection - should replace with local data caching or an error message

        if ( source == null ) {
            return allPointsAccounts;
        }

        Gson gson = new GsonBuilder().create();

        JsonReader jsonReader = null;

        try {
            jsonReader = new JsonReader(new InputStreamReader(source, "UTF-8"));

            jsonReader.beginArray();

            PointsAccount pointsAccount;

            while (jsonReader.hasNext()) {

                pointsAccount = gson.fromJson(jsonReader, PointsAccount.class);

                allPointsAccounts.add(pointsAccount);

            }

            jsonReader.close();

        } catch (Exception e){System.out.println("Exception: " + e); return null;}

        return allPointsAccounts;

    }

    protected void onPostExecute(ArrayList<PointsAccount> allPointsAccounts) {

        Collections.sort(allPointsAccounts);

        pointsSummaryAdapter.upDateEntries(allPointsAccounts);

    }

}
