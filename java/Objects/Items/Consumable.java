package Objects.Items;

public class Consumable extends Item{

    String effect;

    public Consumable(int identifier, String name, String desc, String ef) {

        super(identifier, name, desc);
        this.effect = ef;
    }

    public String getEffect(){
        return this.effect;
    }

    public String getEffectType(){
        return this.getEffect().split("@")[0];
    }

    public String getEffectAttribute(){
        return this.getEffect().split("@")[1];
    }

    public String getEffectMagnitude(){
        return this.getEffect().split("@")[2];
    }

    public void use(){
        //update owner stat base on id;
    }

}
