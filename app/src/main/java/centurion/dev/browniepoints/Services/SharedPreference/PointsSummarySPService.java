package centurion.dev.browniepoints.Services.SharedPreference;

import android.content.SharedPreferences;

/**
 * Created by rich on 13/01/2018.
 */

public class PointsSummarySPService {

    private SharedPreferences sharedPreferences;

    public PointsSummarySPService (SharedPreferences sharedPreferences) {

        this.sharedPreferences = sharedPreferences;

    }

    public void runvalues() {

        System.out.println("runvalues");

    }

}
