/**
 * Created by abdulkhaled on 17/09/2017.
 */
public class Location {


    private Boolean isVisitedLocation = false;
    private int locationValue;

    public Location(int locationValue) {
        this.locationValue = locationValue;
    }

    public void setLocationVisited() {
        this.isVisitedLocation = true;
    }

    public Boolean getVisitedLocation() {
        return isVisitedLocation;
    }

    public int getLocationValue() {
        return locationValue;
    }

    public Boolean isTraversablePassage() {
        return locationValue == 0 && !isVisitedLocation;
    }

}
