package centurion.dev.browniepoints.Screens.PointsSummary;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import centurion.dev.browniepoints.APIServices.PointsSummaryAPIService;
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

        //TODO sort out the decision making for actions - try to move closer to the button and not have the logic here
        pointsSummaryAdapter = new PointsSummaryAdapter(new ClickHandler() {
            @Override
            public void componentClicked(int position, int actionToTake) {

                switch(actionToTake) {
                    case 0: pointsSummaryAdapter.removePointFromAccount(position);
                            break;
                    case 1: pointsSummaryAdapter.addPointToAccount(position);
                            break;
                }
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
