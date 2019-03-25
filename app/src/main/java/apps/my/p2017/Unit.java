package apps.my.p2017;

/**
 * Created by standard on 10/5/2017.
 */

public class Unit {

    String id;
    String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
