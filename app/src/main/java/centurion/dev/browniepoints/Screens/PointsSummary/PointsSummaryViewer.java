package centurion.dev.browniepoints.Screens.PointsSummary;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import java.util.ArrayList;
import centurion.dev.browniepoints.DataModel.PointsAccount;
import centurion.dev.browniepoints.R;

public class PointsSummaryViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.points_summary_cards_container);

        ArrayList<PointsAccount> data = new ArrayList<PointsAccount>();
        PointsAccount pointsAccount = new PointsAccount();
        pointsAccount.setId(new Long(1));
        pointsAccount.setName("Alex");
        pointsAccount.setPoints(23);
        data.add(pointsAccount);
        PointsAccount pointsAccount2 = new PointsAccount();
        pointsAccount2.setId(new Long(2));
        pointsAccount2.setName("Emily");
        pointsAccount2.setPoints(12);
        data.add(pointsAccount2);
        PointsAccount pointsAccount3 = new PointsAccount();
        pointsAccount3.setId(new Long(2));
        pointsAccount3.setName("Hannah");
        pointsAccount3.setPoints(10);
        data.add(pointsAccount3);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.points_summary_cards_recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        PointsSummaryAdapter adapter = new PointsSummaryAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
