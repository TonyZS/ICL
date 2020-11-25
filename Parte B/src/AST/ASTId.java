package AST;

public class ASTId implements ASTNode {

	String id;

	public int eval(Environment<Integer> e) {
		return e.find(id);
	}

	public ASTId(String value) {
		id = value;
	}

}