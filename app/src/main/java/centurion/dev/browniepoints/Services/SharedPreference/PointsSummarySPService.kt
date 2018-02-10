package centurion.dev.browniepoints.Services.SharedPreference

import android.content.SharedPreferences

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader

import org.json.JSONObject

import java.io.InputStreamReader
import java.util.ArrayList

import centurion.dev.browniepoints.DataModel.PointsAccount

/**
 * Created by rich on 13/01/2018.
 */

class PointsSummarySPService(
        //TODO shared preference wrapper class that can pass in parameter for

        private val sharedPreferences: SharedPreferences) {
    private val editor: SharedPreferences.Editor

    init {
        editor = sharedPreferences.edit()

    }

    fun runvalues() {

        val retrievedResult: Int
        retrievedResult = sharedPreferences.getInt("score", 0)
        println("runvalues: " + retrievedResult)

        val allPointsAccounts = ArrayList<PointsAccount>()
        val pointsAccount1 = PointsAccount(1, 10, "Rich")
        val pointsAccount2 = PointsAccount(2, 7, "Jim")
        allPointsAccounts.add(pointsAccount1)
        allPointsAccounts.add(pointsAccount2)

        val gson = GsonBuilder().create()

        val jsonAllPointsAccounts = gson.toJson(allPointsAccounts)

        println("This is the GSON output: " + jsonAllPointsAccounts)

        //TODO this test above shows the GSON serialization works and this can potentially be converted to the shared preferences


        val jsonString1 = sharedPreferences.getString("jsondata", "0")

        println("JSON Data: " + jsonString1!!)

        //TODO use the GSON example

        try {

            val jsonString = sharedPreferences.getString("jsondata", "0")

            var cachedPointsAccounts = JSONObject()

            if (jsonString != "0") {

                cachedPointsAccounts = JSONObject(jsonString)

            }


            for (i in 0 until cachedPointsAccounts.names().length()) {

                println(cachedPointsAccounts.names().getString(i) + " "
                        + cachedPointsAccounts.get(cachedPointsAccounts.names().getString(i)))

            }
        } catch (e: Exception) {
            println("retrieveValues exception: " + e)
        }


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
    fun setValues(allPointsAccounts: ArrayList<PointsAccount>) {
        editor.putInt("score", 10)

        val pointsAccount: PointsAccount

        val gson = GsonBuilder().create()

        val jsonAllPointsAccounts = gson.toJson(allPointsAccounts)

        println("This is the GSON output: " + jsonAllPointsAccounts)

        val sharedPreferenceIteration = 0

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
