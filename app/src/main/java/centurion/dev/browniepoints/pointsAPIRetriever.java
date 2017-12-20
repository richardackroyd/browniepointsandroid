package centurion.dev.browniepoints;

import android.graphics.Point;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by rich on 25/11/2017.
 */

public class pointsAPIRetriever extends AsyncTask<Void, Void, ArrayList<PointsAccount>>{

    private final String mURL = "https://mysterious-forest-42652.herokuapp.com/api/points";

    private final pointsAdapter mAdapter;

    public pointsAPIRetriever (pointsAdapter adapter) {

        this.mAdapter = adapter;

    }

    private InputStream retrieveStream(String url) {

        try {

            URL pointsSummaryEndPoint = new URL(mURL);

            HttpsURLConnection myConnection =
                    (HttpsURLConnection) pointsSummaryEndPoint.openConnection();

            if (myConnection.getResponseCode() == 200) {

                InputStream responseBody = myConnection.getInputStream();

                return responseBody;

            }

        } catch (Exception e) {System.out.println("Exception: " + e); }

        return null;

    }

    @Override
    protected ArrayList<PointsAccount> doInBackground(Void... params) {

        InputStream source =  retrieveStream(mURL);

        Gson gson = new GsonBuilder().create();

        ArrayList<PointsAccount> allPointsAccounts = new ArrayList<PointsAccount>();

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

        mAdapter.upDateEntries(allPointsAccounts);

    }

}
