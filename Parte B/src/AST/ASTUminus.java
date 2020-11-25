package AST;

public class ASTUminus implements ASTNode {

	ASTNode lhs, rhs;

	public int eval(Environment<Integer> e) {
		int v1 = lhs.eval(e);


		return -v1;
	}

	public ASTUminus(ASTNode l) {
		lhs = l;
	}
}
