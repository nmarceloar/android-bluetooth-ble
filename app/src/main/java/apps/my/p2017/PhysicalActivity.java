package apps.my.p2017;

import io.reactivex.Observable;

/**
 * Created by standard on 10/3/2017.
 */

public class PhysicalActivity {
    public String getId() {
        return id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public Float getMets() {
        return mets;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getEspecifica() {
        return especifica;
    }

    public String getIcon() {
        return icon;
    }

    public Float getSpeed() {
        return speed;
    }

    public Float getSpeedMin() {
        return speedMin;
    }

    public Float getSpeedMax() {
        return speedMax;
    }

    String id;
    Long codigo;
    Float mets;
    String categoria;
    String especifica;
    String icon;

    Float speed;
    Float speedMin;
    Float speedMax;

    public boolean hasSpeed() {

        return speed != null || speedMin != null || speedMax != null;

    }

}
