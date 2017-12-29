package centurion.dev.browniepoints.Screens.PointsSummary;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import java.util.ArrayList;

import centurion.dev.browniepoints.APIServices.PointsSummaryAPIService;
import centurion.dev.browniepoints.DataModel.PointsAccount;
import centurion.dev.browniepoints.R;
import centurion.dev.browniepoints.Util.ClickHandler;

public class PointsSummaryViewer extends AppCompatActivity {

    PointsSummaryAdapter pointsSummaryAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.points_summary_cards_container);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.points_summary_cards_recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        pointsSummaryAdapter = new PointsSummaryAdapter(new ClickHandler() {
            @Override
            public void componentClicked(int position) {
                final int pointsAccounts = pointsSummaryAdapter.getItemCount();
                final PointsAccount pointsAccount = pointsSummaryAdapter.getItem(position);
                System.out.println("The number of entried is: " + pointsAccounts);
                System.out.println("The name is: " + pointsAccount.getName());
            }
        });
        recyclerView.setAdapter(pointsSummaryAdapter);
        PointsSummaryAPIService pointsSummaryAPIService = new PointsSummaryAPIService(pointsSummaryAdapter);
        pointsSummaryAPIService.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
