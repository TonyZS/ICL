package AST;

public class ASTMult implements ASTNode {

	ASTNode lhs, rhs;

	public int eval(Environment<Integer> e) {
		int v1 = lhs.eval(e);
		int v2 = rhs.eval(e);
		return v1 * v2;
	}

	public ASTMult(ASTNode l, ASTNode r) {
		lhs = l;
		rhs = r;
	}
}