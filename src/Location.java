/**
 * Created by abdulkhaled on 17/09/2017.
 */
public class Location {

    private Boolean isNewLocation = true;
    private int locationValue;

    public Location(int locationValue) {
        this.locationValue = locationValue;
    }

    public void setLocation() {
        this.isNewLocation = false;
    }

    public Boolean isTraversablePassage() {

        return locationValue == 0 && isNewLocation;
    }

}
