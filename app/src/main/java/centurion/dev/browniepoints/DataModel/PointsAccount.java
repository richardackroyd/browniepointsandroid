package centurion.dev.browniepoints.DataModel;

public class PointsAccount {

    Long id;

    int points;

    String name;

    public void setId (Long id) {
        this.id = id;
    }

    public Long getId () {
        return id;
    }

    public void setPoints (int points) {
        this.points = points;
    }

    public int getPoints () {
        return points;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

}
