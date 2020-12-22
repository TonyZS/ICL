package AST;

public class VInt implements IValue {

	private int value;

	public VInt(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}