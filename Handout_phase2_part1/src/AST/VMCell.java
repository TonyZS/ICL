package AST;

public class VMCell implements IValue {

	IValue v;
	
	public VMCell(IValue value){
		this.v = value;
	}
	
	public IValue getValue() {
		return v;
	}
	
	public void set(IValue value) {
		this.v = value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(v);
	}
}
