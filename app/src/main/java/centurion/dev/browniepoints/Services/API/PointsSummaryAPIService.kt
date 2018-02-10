package centurion.dev.browniepoints.Services.API

import android.content.SharedPreferences
import android.os.AsyncTask

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.util.ArrayList
import java.util.Collections

import javax.net.ssl.HttpsURLConnection
import centurion.dev.browniepoints.DataModel.PointsAccount
import centurion.dev.browniepoints.Screens.PointsSummary.PointsSummaryAdapter
import centurion.dev.browniepoints.Services.SharedPreference.PointsSummarySPService

/**
 * Created by rich on 25/11/2017.
 */

class PointsSummaryAPIService(private val pointsSummaryAdapter: PointsSummaryAdapter, private val sharedPreferences: SharedPreferences) : AsyncTask<Void, Void, ArrayList<PointsAccount>>() {

    private val mURL = "https://mysterious-forest-42652.herokuapp.com/api/points"

    private fun retrieveStream(url: String): InputStream? {

        try {

            val pointsSummaryEndPoint = URL(mURL)

            //TODO update to HTTPClient as more robust
            val myConnection = pointsSummaryEndPoint.openConnection() as HttpsURLConnection

            if (myConnection.responseCode == 200) {

                return myConnection.inputStream

            }

        } catch (e: Exception) {
            println("Exception: " + e)
        }

        return null

    }

    //TODO refactor as too large

    override fun doInBackground(vararg params: Void): ArrayList<PointsAccount>? {

        val source = retrieveStream(mURL)

        val allPointsAccounts = ArrayList<PointsAccount>()

        val pointsSummarySPService = PointsSummarySPService(sharedPreferences)

        //TODO 1 stops crash if no network connection - should replace with local data caching or an error message

        if (source == null) {
            //TODO 1.2 this needs to return the allPointsAccounts array in place of the API call based on last cache
            pointsSummarySPService.runvalues()
            return allPointsAccounts
        }

        val gson = GsonBuilder().create()

        var jsonReader: JsonReader? = null

        try {
            jsonReader = JsonReader(InputStreamReader(source, "UTF-8"))

            jsonReader.beginArray()

            var pointsAccount: PointsAccount

            while (jsonReader.hasNext()) {

                pointsAccount = gson.fromJson(jsonReader, PointsAccount::class.java)

                allPointsAccounts.add(pointsAccount)

            }

            jsonReader.close()

        } catch (e: Exception) {
            println("Exception: " + e)
            return null
        }

        //TODO 1.1 this needs to update the SharedPreferences cache with latest data deseriablised to name:value pairs

        pointsSummarySPService.setValues(allPointsAccounts)

        return allPointsAccounts

    }

    override fun onPostExecute(allPointsAccounts: ArrayList<PointsAccount>) {

        Collections.sort(allPointsAccounts)

        pointsSummaryAdapter.upDateEntries(allPointsAccounts)

    }

}
