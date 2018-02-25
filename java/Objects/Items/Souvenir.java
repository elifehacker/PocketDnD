package Objects.Items;

public class Souvenir extends Item{

    String meta="";

    public Souvenir(int identifier, String name, String desc) {

        super(identifier, name, desc);
    }

    public void setMeta(String mt){
        this.meta = mt;
    }

    public String showmeta(){
        //show who got this on which date
        return meta;
    }

}
