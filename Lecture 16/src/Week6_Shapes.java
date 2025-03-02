import tester.Tester;

//represents a shape
interface IShape { 
	
}

//represents a circle shape
class Circle implements IShape {
	int x;
	int y;
	int radius;
	String color;

	Circle(int x, int y, int r, String color) {
		this.x = x;
		this.y = y;
		this.radius = r;
		this.color = color;
	}

	
}

//represents a rectangle shape
class Rect implements IShape {
	int x; 
	int y; 
	int w; 
	int h;
	String color;

	Rect(int x, int y, int w, int h, String color) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.color = color;
	}
	
	
}


class ExamplesShapes {
	IShape c1 = new Circle(50, 50, 10, "red");
	IShape c2 = new Circle(50, 50, 30, "red");
	IShape c3 = new Circle(30, 100, 30, "blue");

	IShape r1 = new Rect(50, 50, 30, 20, "red");
	IShape r2 = new Rect(50, 50, 50, 40, "red");
	IShape r3 = new Rect(20, 40, 10, 20, "green");

	IList<IShape> mtShape = new MtList<IShape>();
	IList<IShape> shapes = new ConsList<IShape>(this.c1, 
			new ConsList<IShape>(this.r1, this.mtShape));

	
	boolean testShapeAreas(Tester t) {
		return t.checkExpect(this.shapes.map(new ShapeArea()), 
				new ConsList<Double>(100 * Math.PI, 
						new ConsList<Double>(600.0, new MtList<Double>())));
	}
}