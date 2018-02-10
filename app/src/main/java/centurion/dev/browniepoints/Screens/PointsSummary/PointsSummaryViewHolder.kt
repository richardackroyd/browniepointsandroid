package centurion.dev.browniepoints.Screens.PointsSummary

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import centurion.dev.browniepoints.R
import centurion.dev.browniepoints.Util.ClickHandler

/**
 * Created by rich on 23/12/2017.
 */

//public class PointsSummaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
class PointsSummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    internal var pointsAccountNameText: TextView
    internal var pointsAccountPointsText: TextView
    internal var pointsAccountAvatarImage: ImageView
    internal var addPoints: TextView
    internal var removePoints: TextView
    internal var clickHandler: ClickHandler? = null

    init {

        this.pointsAccountNameText = itemView.findViewById(R.id.pointsAccountNameText) as TextView
        this.pointsAccountPointsText = itemView.findViewById(R.id.pointsAccountPointsText) as TextView
        this.pointsAccountAvatarImage = itemView.findViewById(R.id.pointsAccountAvatarImage) as ImageView
        this.addPoints = itemView.findViewById(R.id.addPoint) as TextView
        this.removePoints = itemView.findViewById(R.id.removePoint) as TextView

        addPoints.setOnClickListener { clickHandler!!.componentClicked(adapterPosition, 1) }

        removePoints.setOnClickListener { clickHandler!!.componentClicked(adapterPosition, 0) }

    }

    /*    @Override
    public void onClick(final View view) {
        if (clickHandler != null) {
            clickHandler.componentClicked(getAdapterPosition());
        }
    }*/


}
