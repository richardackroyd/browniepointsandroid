package centurion.dev.browniepoints.DataModel

class PointsAccount(var id: Long?, var points: Int, var name: String) : Comparable<PointsAccount> {

    override fun compareTo(pointsAccount: PointsAccount): Int {
        val compareID = pointsAccount.id!!
        val delta = this.id!! - compareID
        return delta.toInt()
    }

}
