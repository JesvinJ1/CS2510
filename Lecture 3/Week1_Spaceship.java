import tester.Tester;

//represents a spaceship in the space invaders game
class Spaceship {
	Location loc;
	String color;
	int speed; //measured in meters per second
	
	Spaceship(Location loc, String c, int speed) {
		this.loc = loc;
		this.color = c;
		this.speed = speed;
	}
	
	/*
	 * fields:
	 * this.loc ... Location
	 * this.color ... String
	 * this.speed ... int
	 * methods:
	 *  this.reducedSpeed(int) ... int
	 *  this.move(int, int) ... Spaceship
	 *  this.sameSpaceship(Spaceship) ... boolean
	 * methods of fields:
	 * 	this.loc.moveLocation(int, int) ... Location
	 *  this.loc.sameLocation(Location) ... boolean
	 */
	
	//compute a reduced speed for this Spaceship 
	int reducedSpeed(int rate) {
		return this.speed - this.speed * rate / 100;
	}
	
	//creates a new spaceship that is shifted by the given values
	Spaceship move(int dx, int dy) {
		return new Spaceship(this.loc.moveLocation(dx, dy), 
				this.color, this.speed);
	}
	
	//is this spaceship the same as the given one?
	boolean sameSpaceship(Spaceship other) {
		return this.loc.sameLocation(other.loc)
				&& this.color.equals(other.color)
				&& this.speed == other.speed;
	}
}


//represents a point on the Cartesian plane
class Location {
	int x;
	int y;
	
	Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/* fields : 
	 * this.x ... int
	 * this.y ... int
	 * methods:
	 *  this.moveLocation(int, int) ... Location
	 *  this.sameLocation(Location) ... bolean
	 */
	
	//creates a new location that is shifted by the given increments
	Location moveLocation(int x, int y) {
		return new Location (this.x + x, this.y + y);
	}
	
	// is this location the same as the given one
	boolean sameLocation(Location other) {
		return this.x == other.x &&
				this.y == other.y;
	}
	
}

class ExamplesGamePieces {
	Location loc30_120 = new Location(30, 120);
	Location loc80_650 = new Location(80, 650);
	Spaceship ship1 = new Spaceship(this.loc30_120, "red", 30);
	Spaceship ship2 = new Spaceship(this.loc80_650, "green", 100);
	
	// Purpose Statement: tests for reducedSpeeds
	boolean testReduce(Tester t) {
		return t.checkExpect(this.ship2.reducedSpeed(20), 80); 
	}
	
	// tests for move
	boolean testMove (Tester t) {
		return t.checkExpect(this.ship1.move(10, -5), 
				new Spaceship(new Location(40, 115), "red", 30)) &&
				t.checkExpect(this.ship1.move(100, 20), 
						new Spaceship(new Location(130, 140), "red", 30)) &&
				t.checkExpect(this.loc30_120.moveLocation(1, 5), 
						new Location(31, 125));
	}
	
	boolean testSameSpaceship(Tester t) {
		return t.checkExpect(this.ship1.sameSpaceship(this.ship2), false) &&
				t.checkExpect(this.ship1.sameSpaceship(this.ship1), true) &&
				t.checkExpect(this.ship1.sameSpaceship(
						new Spaceship(this.loc30_120, "red", 30)), true);
		}
	}












