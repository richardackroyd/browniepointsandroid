package centurion.dev.browniepoints.Screens.PointsSummary

import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

import centurion.dev.browniepoints.Services.API.ChangePointsAPIService
import centurion.dev.browniepoints.DataModel.PointsAccount
import centurion.dev.browniepoints.DataModel.PointsToChange
import centurion.dev.browniepoints.R
import centurion.dev.browniepoints.Util.ClickHandler

/**
 * Created by rich on 25/11/2017.
 */

class PointsSummaryAdapter(private val clickHandler: ClickHandler) : RecyclerView.Adapter<PointsSummaryViewHolder>() {

    private var pointsAccounts = ArrayList<PointsAccount>()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PointsSummaryViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.points_summary_individual_card_layout, parent, false)

        return PointsSummaryViewHolder(view)
    }

    override fun onBindViewHolder(pointsSummaryViewHolder: PointsSummaryViewHolder, listPosition: Int) {

        pointsSummaryViewHolder.pointsAccountNameText.text = pointsAccounts[listPosition].name
        pointsSummaryViewHolder.pointsAccountPointsText.text = Integer.toString(pointsAccounts[listPosition].points)
        pointsSummaryViewHolder.pointsAccountAvatarImage.setImageResource(R.drawable.redsquare)

        pointsSummaryViewHolder.clickHandler = this.clickHandler
    }

    override fun getItemCount(): Int {

        return pointsAccounts.size
    }

    fun getItem(position: Int): PointsAccount {

        return pointsAccounts[position]

    }

    //TODO break out the call to the API as a separate function (repeated code in add / remove function)

    fun addPointToAccount(position: Int) {

        pointsAccounts[position].points = pointsAccounts[position].points + 1

        val pointsToChange = PointsToChange(pointsAccounts[position].id, 1)

        val changePointsAPIService = ChangePointsAPIService(pointsToChange)
        changePointsAPIService.execute()

        dataSetChanged()

    }

    fun removePointFromAccount(position: Int) {

        pointsAccounts[position].points = pointsAccounts[position].points - 1

        val pointsToChange = PointsToChange(pointsAccounts[position].id, -1)

        val changePointsAPIService = ChangePointsAPIService(pointsToChange)
        changePointsAPIService.execute()

        dataSetChanged()

    }

    fun upDateEntries(pointsAccounts: ArrayList<PointsAccount>) {

        this.pointsAccounts = pointsAccounts
        dataSetChanged()

    }

    //Broke out as a utility in case wanting to take specific common actions on data set changing
    private fun dataSetChanged() {
        notifyDataSetChanged()
    }

}
