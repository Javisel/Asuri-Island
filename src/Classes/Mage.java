package Classes;

import Core.Entity;

public class Mage extends Entity{

	public Mage(String name) {
		
		super("Roxane", "Mage");
		this.setEntityclass("Mage");
		this.setName(name);
		this.fillstats();
		this.getClass().asSubclass(Entity.class);
}

	
	
	
	
}
