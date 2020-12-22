/**
 * 
 */
package AST;


public class VBoolean implements IValue {
	
	private boolean value;

	public VBoolean(boolean value) {
		this.value = value;
	}
	
	public Boolean getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}