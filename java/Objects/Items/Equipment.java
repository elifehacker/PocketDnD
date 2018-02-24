package Objects.Items;

public class Equipment extends Item{

	int type;

	public Equipment(int identifier, String desc, int t) {
		super(identifier, desc);
		type = t;
	}

	public void equip(){
		//update owner stat base on id;
	}

}
