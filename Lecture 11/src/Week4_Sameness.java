import tester.Tester;


// to represent a geometric shape
interface IShape {
	//is this IShape the same as the given IShape
	boolean sameShape(IShape that);
	//is this IShape the same as the given Circle?
	boolean sameCircle(Circle c);

}

//represents an abstract shape
abstract class AShape implements IShape {
	CartPt location;
	String color;

	AShape(CartPt loc, String color) {
		this.location = loc;
		this.color = color;
	}
	
	//is this Rect the same as the given Circle?
	public boolean sameCircle(Circle c) {
		return false;
	}

}

// to represent a circle
class Circle extends AShape {
	int radius;

	Circle(CartPt center, int radius, String color) {
		super(center, color);
		this.radius = radius;
	}

	/*  TEMPLATE 
     Fields:
     ... this.location ...        -- CartPt
     ... this.radius ...             -- int
     ... this.color ...           -- String
     Methods:
     ... this.sameCircle(Circle) ... boolean
     ... this.sameShape(IShape) ... boolean
     Methods for fields:
     ... this.location.distTo0() ...           -- double 
     ... this.location.distTo(CartPt) ...      -- double 
     ... this.location.sameLocation(Location) ... boolean

	 */

	//is this Circle the same as the given one?
	public boolean sameCircle(Circle c) {
		/* Everything in the class template plus:
		 * fields of c:
    	    ... c.location ...        -- CartPt
     		... c.radius ...             -- int
     		... c.color ...           -- String
     		Methods:
     		... c.sameCircle(Circle) ... boolean
     		Methods for fields of c:
     		... c.location.distTo0() ...           -- double 
     		... c.location.distTo(CartPt) ...      -- double 
     		... c.location.sameLocation(Location) ... boolean 
		 */
		return this.location.sameLocation(c.location) &&
				this.color.equals(c.color) &&
				this.radius == c.radius;
	}

	//is this Circle the same as the given IShape?
	public boolean sameShape(IShape that) {
		/* fields of that: none
		 * methods of that: that.sameShape(IShape)
		 *                  that.sameCircle(Circle)
		 * 
		 */
//		if (that instanceof Circle) {
//			return this.sameCircle((Circle)that);
//		}
//		else {
//			return false;
//		}
		return that.sameCircle(this);
	}

}

// to represent a square
class Square extends Rect {

	Square(CartPt nw, int size, String color) {
		super(nw, size, size, color);
	}

	/*  TEMPLATE 
     Fields:
     ... this.location ...              -- CartPt
     ... this.width ...            -- int
     ... this.height ...           -- int
     ... this.color ...           -- String
     Methods:
     ... this.sameSquare(Square) ... boolean
     Methods for fields:
     ... this.location.distTo0() ...            -- double 
     ... this.location.distTo(CartPt) ...       -- double 
     ... this.location.sameLocation(Location) ... boolean
	 */

	//is this Square the same as the given one?
	boolean sameSquare(Square s) {
		/* Everything in the class template plus:
		 * fields of s:
	    ... s.location ...        -- CartPt
 		... s.width ...             -- int
 		... s.height ...            -- int
 		... s.color ...           -- String
 		Methods:
 		... s.sameSquare(Square) ... boolean
 		Methods for fields of c:
 		... s.location.distTo0() ...           -- double 
 		... s.location.distTo(CartPt) ...      -- double 
 		... s.location.sameLocation(Location) ... boolean 
		 */
		return this.location.sameLocation(s.location) &&
				this.color.equals(s.color) &&
				this.height == s.height;
	}

	//is this Rect the same as the given IShape?
	public boolean sameShape(IShape that) {
		/* fields of that: none
		 * methods of that: that.sameShape(IShape)
		 * 
		 */
		if (that instanceof Square) {
			return this.sameSquare((Square)that);
		}
		else {
			return false;
		}
	}

}

// to represent a rectangle
class Rect extends AShape {
	int width;
	int height;

	Rect(CartPt nw, int width, int height, String color) {
		super(nw, color);
		this.width = width;
		this.height = height;
	}

	/* TEMPLATE
     FIELDS
     ... this.location ...              -- CartPt
     ... this.width ...           -- int
     ... this.height ...          -- int
     ... this.color ...           -- String
     METHODS
     ... this.sameRect(Rect) ... boolean
     METHODS FOR FIELDS:
     ... this.location.distTo0() ...        -- double
     ... this.location.distTo(CartPt) ...   -- double
     ... this.location.sameLocation(Location) ... boolean
	 */

	//is this Rectangle the same as the given one?
	boolean sameRect(Rect r) {
		/* Everything in the class template plus:
		 * fields of r:
	    ... r.location ...        -- CartPt
 		... r.width ...             -- int
 		... r.height ...            -- int
 		... r.color ...           -- String
 		Methods:
 		... r.sameSquare(Square) ... boolean
 		Methods for fields of c:
 		... r.location.distTo0() ...           -- double 
 		... r.location.distTo(CartPt) ...      -- double 
 		... r.location.sameLocation(Location) ... boolean 
		 */
		return this.location.sameLocation(r.location) &&
				this.color.equals(r.color) &&
				this.height == r.height &&
				this.width == r.width;
	}

	//is this Rect the same as the given IShape?
	public boolean sameShape(IShape that) {
		/* fields of that: none
		 * methods of that: that.sameShape(IShape)
		 * 
		 */
		if (that instanceof Rect) {
			return this.sameRect((Rect)that);
		}
		else {
			return false;
		}
	}

	

}


class Combo implements IShape {
	IShape top;
	IShape bottom;

	Combo(IShape top, IShape bottom) {
		this.top = top;
		this.bottom = bottom;
	}

	@Override
	public boolean sameShape(IShape that) {
		// TODO Auto-generated method stub
		return false;
	}

	/* fields:
	 *   this.top ... IShape
	 *   this.bottom ... IShape
	 * methods:
	 * 
	 * methods for fields:  
	 * 
	 */

	//is this Combo the same as the given Circle?
	public boolean sameCircle(Circle c) {
		return false;
	}

}


// to represent a Cartesian point
class CartPt {
	int x;
	int y;

	CartPt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/* TEMPLATE
     FIELDS
     ... this.x ...          -- int
     ... this.y ...          -- int
     METHODS
     ... this.distTo0() ...        -- double
     ... this.distTo(CartPt) ...   -- double
     ... this.sameLocation(Location) ... boolean
	 */

	// to compute the distance form this point to the origin
	public double distTo0(){
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}

	// to compute the distance form this point to the given point
	public double distTo(CartPt pt){
		/* Everything in the class template plus:
        FIELDS of pt
        ... pt.x ...          -- int
        ... pt.y ...          -- int
        METHODS of pt
        ... pt.distTo0() ...        -- double
        ... pt.distTo(CartPt) ...   -- double
        ... pt.sameLocation(Location) ... boolean
		 */
		return Math.sqrt((this.x - pt.x) * (this.x - pt.x) + 
				(this.y - pt.y) * (this.y - pt.y));
	}

	//is this location the same as the given one?
	boolean sameLocation(CartPt that) {
		/* Everything in the class template plus:
        FIELDS of pt
        ... pt.x ...          -- int
        ... pt.y ...          -- int
        METHODS of pt
        ... pt.distTo0() ...        -- double
        ... pt.distTo(CartPt) ...   -- double
        ... pt.sameLocation(Location) ... boolean
		 */
		return this.x == that.x && this.y == that.y;
	}
}

class ExamplesShapes {
	ExamplesShapes() {}

	CartPt pt1 = new CartPt(0, 0);
	CartPt pt2 = new CartPt(3, 4);
	CartPt pt3 = new CartPt(7, 1);

	IShape c1 = new Circle(new CartPt(50, 50), 10, "red");
	IShape c2 = new Circle(new CartPt(50, 50), 30, "red");
	IShape c3 = new Circle(new CartPt(30, 100), 30, "blue");

	IShape s1 = new Square(new CartPt(50, 50), 30, "red");
	IShape s2 = new Square(new CartPt(50, 50), 50, "red");
	IShape s3 = new Square(new CartPt(20, 40), 10, "green");

	IShape r1 = new Rect(new CartPt(50, 50), 30, 30, "red");
	IShape r2 = new Rect(new CartPt(50, 50), 50, 40, "red");
	IShape r3 = new Rect(new CartPt(20, 40), 10, 20, "green");

	Circle c4 = new Circle(this.pt1, 4, "pink");
	Circle c5 = new Circle(this.pt1, 5, "yellow");
	Circle c6 = new Circle(this.pt1, 4, "pink");
	Rect r4 = new Rect(this.pt2, 4, 5, "black");
	Rect r5 = new Rect(this.pt1, 5, 6, "red");
	Rect r6 = new Rect(this.pt2, 4, 5, "black");

	Square s4 = new Square(this.pt1, 5, "red");
	Square s5 = new Square(this.pt2, 4, "black");
	Square s6 = new Square(this.pt1, 5, "red");


	boolean testSameCircRectSquare(Tester t) {
		return
				t.checkExpect(c4.sameCircle(c5), false)
				&& t.checkExpect(c4.sameCircle(c6), true)


				&& t.checkExpect(r4.sameRect(r5), false)
				&& t.checkExpect(r4.sameRect(r6), true)

				&& t.checkExpect(s4.sameSquare(s5), false)
				&& t.checkExpect(s4.sameSquare(s6), true);
	}

	boolean testSameShape(Tester t) {
		return t.checkExpect(c1.sameShape(c1), true)
				&& t.checkExpect(c1.sameShape(r1), false)
				&& t.checkExpect(this.r1.sameShape(c1), false)
				&& t.checkExpect(this.r1.sameShape(c1), false)
				&& t.checkExpect(this.s1.sameShape(r1), false)
				&& t.checkExpect(this.r1.sameShape(s1), false)
				;
	}

	// test the method distTo0 in the class CartPt
	boolean testDistTo0(Tester t) { 
		return
				t.checkInexact(this.pt1.distTo0(), 0.0, 0.001) &&
				t.checkInexact(this.pt2.distTo0(), 5.0, 0.001);
	}

	// test the method distTo in the class CartPt
	boolean testDistTo(Tester t) { 
		return
				t.checkInexact(this.pt1.distTo(this.pt2), 5.0, 0.001) &&
				t.checkInexact(this.pt2.distTo(this.pt3), 5.0, 0.001);
	}


}
