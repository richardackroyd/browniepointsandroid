package centurion.dev.browniepoints.Services.SharedPreference;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import centurion.dev.browniepoints.DataModel.PointsAccount;

/**
 * Created by rich on 13/01/2018.
 */

public class PointsSummarySPService {

    //TODO shared preference wrapper class that can pass in parameter for

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public PointsSummarySPService (SharedPreferences sharedPreferences) {

        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();

    }

    public void runvalues() {

        int retrievedResult;
        retrievedResult = sharedPreferences.getInt("score", 0);
        System.out.println("runvalues: " + retrievedResult);

        ArrayList<PointsAccount> allPointsAccounts = new ArrayList<PointsAccount>();
        PointsAccount pointsAccount1 = new PointsAccount(new Long(1),10,"Rich");
        PointsAccount pointsAccount2 = new PointsAccount(new Long(2),7,"Jim");
        allPointsAccounts.add(pointsAccount1);
        allPointsAccounts.add(pointsAccount2);

        Gson gson = new GsonBuilder().create();

        String jsonAllPointsAccounts = gson.toJson(allPointsAccounts);

        System.out.println("This is the GSON output: " + jsonAllPointsAccounts);

//TODO this test above shows the GSON serialization works and this can potentially be converted to the shared preferences



        String jsonString1 = sharedPreferences.getString("jsondata", "0");

        System.out.println("JSON Data: " + jsonString1);

        //TODO use the GSON example

        try {

            String jsonString = sharedPreferences.getString("jsondata", "0");

            JSONObject cachedPointsAccounts = new JSONObject();

            if (!jsonString.equals("0")) {

                cachedPointsAccounts = new JSONObject(jsonString);

            }


            for (int i = 0; i < cachedPointsAccounts.names().length(); i++) {

                System.out.println(cachedPointsAccounts.names().getString(i) + " "
                        + cachedPointsAccounts.get(cachedPointsAccounts.names().getString(i)));

            }
        } catch (Exception e) {System.out.println("retrieveValues exception: " + e);}


/*            cachedPointsAccounts.

            PointsAccount pointsAccount;

            while (jsonReader.hasNext()) {

                pointsAccount = gson.fromJson(jsonReader, PointsAccount.class);

                allPointsAccounts.add(pointsAccount);

            }

            jsonReader.close();


        } catch (Exception e) {System.out.println("Points Summary Shared Preferences retrieve values: " + e);}

        return allPointsAccounts;
*/
    }

//    public void setValues(JsonReader jsonReader) {
      public void setValues(ArrayList<PointsAccount> allPointsAccounts) {
        editor.putInt("score", 10);

        PointsAccount pointsAccount;

        Gson gson = new GsonBuilder().create();

        String jsonAllPointsAccounts = gson.toJson(allPointsAccounts);

        System.out.println("This is the GSON output: " + jsonAllPointsAccounts);

        int sharedPreferenceIteration = 0;

        //TODO use the GSON example


//        try {

//            System.out.println("json object: " + jsonReader);
  //          System.out.println("json string: " + jsonReader.toString());

    //        editor.putString("jsonadata", jsonReader.toString());

/*            jsonReader.beginArray();

            while (jsonReader.hasNext()) {

                pointsAccount = gson.fromJson(jsonReader, PointsAccount.class);

                editor.putString("name" + sharedPreferenceIteration, pointsAccount.getName());
                editor.putInt("points"+ sharedPreferenceIteration, pointsAccount.getPoints());
                editor.putLong("id"+ sharedPreferenceIteration, pointsAccount.getId());

            }*/

//            jsonReader.close();
//            editor.commit();

//        } catch (Exception e) {System.out.println("Points Summary Shared Preferences setValues error: " + e);}

    }

}
