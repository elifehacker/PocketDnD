package Objects.Items;

/**
 * Created by stevwang on 24/3/18.
 */

public class Effect {

    private int attribute;
    private int magnitude;

    public Effect(int attribute, int magnitude) {
        this.attribute = attribute;
        this.magnitude = magnitude;
    }

    public int getAttribute() {
        return attribute;
    }

    public int getMagnitude() {
        return magnitude;
    }
}
