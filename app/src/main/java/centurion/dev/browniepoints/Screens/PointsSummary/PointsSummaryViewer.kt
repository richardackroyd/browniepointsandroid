package centurion.dev.browniepoints.Screens.PointsSummary

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.app.AppCompatActivity
import android.view.Menu

import centurion.dev.browniepoints.Services.API.PointsSummaryAPIService
import centurion.dev.browniepoints.R
import centurion.dev.browniepoints.Util.ClickHandler

class PointsSummaryViewer : AppCompatActivity() {

    internal var pointsSummaryAdapter: PointsSummaryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.points_summary_cards_container)

        val recyclerView = findViewById(R.id.points_summary_cards_recyclerview) as RecyclerView
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        //TODO sort out the decision making for actions - try to move closer to the button and not have the logic here
        pointsSummaryAdapter = PointsSummaryAdapter(ClickHandler { position, actionToTake ->
            when (actionToTake) {
                0 -> pointsSummaryAdapter!!.removePointFromAccount(position)
                1 -> pointsSummaryAdapter!!.addPointToAccount(position)
            }
        })

        recyclerView.adapter = pointsSummaryAdapter
        //TODO add context in here to draw shared preference capability for offline data store
        val sharedPreferences = this.getSharedPreferences("myfile", 0)
        val pointsSummaryAPIService = PointsSummaryAPIService(pointsSummaryAdapter!!, sharedPreferences)
        pointsSummaryAPIService.execute()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}
