package Classes;

import Core.Entity;

public class Ninja extends Entity{

	public Ninja(String name) {
		
		super("A.I", "Ninja");

		this.setName(name);
		this.getClass().asSubclass(Entity.class);
		
}

	
	
	
	
}
