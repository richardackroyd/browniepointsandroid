package centurion.dev.browniepoints.Screens.PointsSummary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import centurion.dev.browniepoints.DataModel.PointsAccount;
import centurion.dev.browniepoints.R;

/**
 * Created by rich on 25/11/2017.
 */

public class PointsSummaryAdapter extends RecyclerView.Adapter<PointsSummaryViewHolder> {

    private ArrayList<PointsAccount> pointsAccounts = new ArrayList<PointsAccount>();

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

        TextView pointsAccountNameText = pointsSummaryViewHolder.pointsAccountNameText;
        TextView pointsAccountPointsText = pointsSummaryViewHolder.pointsAccountPointsText;
        ImageView pointsAccountAvatarImage = pointsSummaryViewHolder.pointsAccountAvatarImage;

        pointsAccountNameText.setText(pointsAccounts.get(listPosition).getName());
        pointsAccountPointsText.setText(Integer.toString(pointsAccounts.get(listPosition).getPoints()));
        pointsAccountAvatarImage.setImageResource(R.drawable.redsquare);
    }

    @Override
    public int getItemCount() {

        return pointsAccounts.size();
    }

    public void upDateEntries(ArrayList<PointsAccount> pointsAccounts){

        this.pointsAccounts = pointsAccounts;
        notifyDataSetChanged();

    }

}
