package Objects.Items;

public class Consumable extends Item{

    String effect;

    public Consumable(int identifier, String name, String desc, String ef) {

        super(identifier, name, desc);
        this.effect = ef;
    }

    public void use(){
        //update owner stat base on id;
    }

}
