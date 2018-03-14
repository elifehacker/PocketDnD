package Objects.Items;

public class Equipment extends Item{

	String effect;

	public Equipment(int identifier, String name, String desc, String eft) {
		super(identifier, name, desc);
		this.effect= eft;
	}

	public void equip(){
		//update owner stat base on id;
	}
	public String getEffect(){
		return effect;
	}
}
