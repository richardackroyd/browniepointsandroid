package centurion.dev.browniepoints.APIServices;

import android.graphics.Point;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import centurion.dev.browniepoints.DataModel.PointsAccount;
import centurion.dev.browniepoints.DataModel.PointsToChange;
import centurion.dev.browniepoints.Screens.PointsSummary.PointsSummaryAdapter;

/**
 * Created by rich on 31/12/2017.
 */

public class ChangePointsAPIService extends AsyncTask<Void, Void, String> {

    private String mURL = "https://mysterious-forest-42652.herokuapp.com/api/addPoint/";
    private PointsToChange pointsToChange;

    //TODO need to move this to the doinbackground so can reuse object or open and close effectively
    public ChangePointsAPIService(PointsToChange pointsToChange) {

        this.pointsToChange = pointsToChange;
        mURL = mURL + pointsToChange.pointsAccountID;

    }


    //TODO large function - refactor

    @Override
    protected String doInBackground(Void... params) {

        try {
            URL pointsSummaryEndPoint = new URL(mURL);

            JSONObject postDataParams = new JSONObject();
            postDataParams.put("changeByPoints", pointsToChange.pointsToChangeBy);

            HttpsURLConnection conn = (HttpsURLConnection) pointsSummaryEndPoint.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("PUT");
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("Accept", "application/json;charset=utf-8");

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(postDataParams.toString());

            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                return sb.toString();

            }
            else {
                return new String("false : "+responseCode);
            }
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }

    }

}
