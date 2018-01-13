package centurion.dev.browniepoints.Screens.PointsSummary;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import centurion.dev.browniepoints.Services.API.ChangePointsAPIService;
import centurion.dev.browniepoints.DataModel.PointsAccount;
import centurion.dev.browniepoints.DataModel.PointsToChange;
import centurion.dev.browniepoints.R;
import centurion.dev.browniepoints.Util.ClickHandler;

/**
 * Created by rich on 25/11/2017.
 */

public class PointsSummaryAdapter extends RecyclerView.Adapter<PointsSummaryViewHolder> {

    private ArrayList<PointsAccount> pointsAccounts = new ArrayList<PointsAccount>();
    private ClickHandler clickHandler;

    public PointsSummaryAdapter (final ClickHandler clickHandler) {

        super();
        this.clickHandler = clickHandler;

    }

    @Override
    public PointsSummaryViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.points_summary_individual_card_layout, parent, false);

        PointsSummaryViewHolder pointsSummaryViewHolder = new PointsSummaryViewHolder(view);
        return pointsSummaryViewHolder;
    }

    @Override
    public void onBindViewHolder(final PointsSummaryViewHolder pointsSummaryViewHolder, final int listPosition) {

        pointsSummaryViewHolder.pointsAccountNameText.setText(pointsAccounts.get(listPosition).getName());
        pointsSummaryViewHolder.pointsAccountPointsText.setText(Integer.toString(pointsAccounts.get(listPosition).getPoints()));
        pointsSummaryViewHolder.pointsAccountAvatarImage.setImageResource(R.drawable.redsquare);

        pointsSummaryViewHolder.clickHandler = this.clickHandler;
    }

    @Override
    public int getItemCount() {

        return pointsAccounts.size();
    }

    public PointsAccount getItem(int position) {

        return pointsAccounts.get(position);

    }

    //TODO break out the call to the API as a separate function (repeated code in add / remove function)

    public void addPointToAccount(int position) {

        pointsAccounts.get(position).setPoints(pointsAccounts.get(position).getPoints() + 1);

        PointsToChange pointsToChange =
                new PointsToChange(pointsAccounts.get(position).getId(), 1);

        ChangePointsAPIService changePointsAPIService = new ChangePointsAPIService(pointsToChange);
        changePointsAPIService.execute();

        dataSetChanged();

    }

    public void removePointFromAccount(int position) {

        pointsAccounts.get(position).setPoints(pointsAccounts.get(position).getPoints() - 1);

        PointsToChange pointsToChange =
                new PointsToChange(pointsAccounts.get(position).getId(), -1);

        ChangePointsAPIService changePointsAPIService = new ChangePointsAPIService(pointsToChange);
        changePointsAPIService.execute();

        dataSetChanged();

    }

    public void upDateEntries(ArrayList<PointsAccount> pointsAccounts){

        this.pointsAccounts = pointsAccounts;
        dataSetChanged();

    }

    //Broke out as a utility in case wanting to take specific common actions on data set changing
    private void dataSetChanged() {notifyDataSetChanged();}

}
