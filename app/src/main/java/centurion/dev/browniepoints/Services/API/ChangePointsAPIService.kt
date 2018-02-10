package centurion.dev.browniepoints.Services.API

import android.os.AsyncTask

import org.json.JSONObject

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.URL

import javax.net.ssl.HttpsURLConnection

import centurion.dev.browniepoints.DataModel.PointsToChange

/**
 * Created by rich on 31/12/2017.
 */

//TODO need to move this to the doinbackground so can reuse object or open and close effectively

class ChangePointsAPIService (private val pointsToChange: PointsToChange) : AsyncTask<Void, Void, String>() {

    private var mURL = "https://mysterious-forest-42652.herokuapp.com/api/addPoint/"

    init {
        mURL = mURL + pointsToChange.pointsAccountID!!

    }


    //TODO large function - refactor

    override fun doInBackground(vararg params: Void): String {

        try {
            val pointsSummaryEndPoint = URL(mURL)

            val postDataParams = JSONObject()
            postDataParams.put("changeByPoints", pointsToChange.pointsToChangeBy)

            val conn = pointsSummaryEndPoint.openConnection() as HttpsURLConnection
            conn.readTimeout = 15000
            conn.connectTimeout = 15000
            conn.requestMethod = "PUT"
            conn.doInput = true
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8")
            conn.setRequestProperty("Accept", "application/json;charset=utf-8")

            val os = conn.outputStream
            val writer = BufferedWriter(
                    OutputStreamWriter(os, "UTF-8"))
            writer.write(postDataParams.toString())

            writer.flush()
            writer.close()
            os.close()

            val responseCode = conn.responseCode

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                val responseBuffer = BufferedReader(
                        InputStreamReader(
                                conn.inputStream))
                val sb = StringBuffer("")
                val responseAsText:List<String> = responseBuffer.readLines();

                for (line in responseAsText) sb.append(line)

                responseBuffer.close()
                return sb.toString()

            } else return "false : " + responseCode

        } catch (e: Exception) { return "Exception: " + e.message }

    }

}
