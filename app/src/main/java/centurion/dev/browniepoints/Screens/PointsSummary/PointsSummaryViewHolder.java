package centurion.dev.browniepoints.Screens.PointsSummary;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import centurion.dev.browniepoints.R;

/**
 * Created by rich on 23/12/2017.
 */

public class PointsSummaryViewHolder extends RecyclerView.ViewHolder {

    TextView pointsAccountNameText;
    TextView pointsAccountPointsText;
    ImageView pointsAccountAvatarImage;

    public PointsSummaryViewHolder(View itemView) {
        super(itemView);
        this.pointsAccountNameText = (TextView) itemView.findViewById(R.id.pointsAccountNameText);
        this.pointsAccountPointsText = (TextView) itemView.findViewById(R.id.pointsAccountPointsText);
        this.pointsAccountAvatarImage = (ImageView) itemView.findViewById(R.id.pointsAccountAvatarImage);
    }

}
