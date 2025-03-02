import tester.Tester;

//represents a list of integers
interface ILoInt {
	//produce a list of absolute distances from this list of relative distances
	ILoInt relativeToAbsolute();
	
	//add n to every number in this list
	ILoInt addToAll(int n);
}

//represents an empty list of integers
class MtLoInt implements ILoInt {

	/* fields: none
	 * methods:
	 *   this.relativeToAbsolute() ... ILoInt
	 *   this.addToAll(int) ... ILoInt
	 * methods of fields: none  
	 */
	
	//produce a list of absolute distances from this empty list
	public ILoInt relativeToAbsolute() {
		return this;
	}

	//add n to every number in this empty list
	public ILoInt addToAll(int n) {
		return this;
	}
	
}

//represents a non-empty list of integers
class ConsLoInt implements ILoInt {
	int first;
	ILoInt rest;
	
	ConsLoInt(int first, ILoInt rest) {
		this.first = first;
		this.rest = rest;
	}
	
	/* fields:
	 *   this.first ... int
	 *   this.rest ... ILoInt
	 * methods:
	 *   this.relativeToAbsolute() ... ILoInt
	 *   this.addToAll(int) ... ILoInt
	 *   
	 * methods of fields:
	 *   this.rest.relativeToAbsolute() ... ILoInt
	 *   this.rest.addToAll(int) ... ILoInt    
	 * 
	 */

	//produce a list of absolute distances from this non-empty list of relative distances
	public ILoInt relativeToAbsolute() {
		return new ConsLoInt(this.first,
				this.rest.relativeToAbsolute().addToAll(this.first));
	}

	//add n to every number in this non-empty list
	public ILoInt addToAll(int n) {
		return new ConsLoInt(this.first + n, this.rest.addToAll(n));
	}
}

class ExamplesLoInt {
	ILoInt mt = new MtLoInt();
	ILoInt list1 = new ConsLoInt(5, this.mt);
	ILoInt list2 = new ConsLoInt(4, this.list1);
	ILoInt list3 = new ConsLoInt(6, this.list2);
	
	boolean testRelToAbs(Tester t) {
		return
				t.checkExpect(this.mt.relativeToAbsolute(), this.mt)
				&& t.checkExpect(this.list3.relativeToAbsolute(), 
						new ConsLoInt(6, new ConsLoInt(10, new ConsLoInt(15, this.mt))))
				&& t.checkExpect(this.mt.addToAll(2), this.mt)
				&& t.checkExpect(this.list2.addToAll(2), 
						new ConsLoInt(6, new ConsLoInt(7, this.mt)));
	}
	
}





