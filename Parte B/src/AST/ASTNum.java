package AST;

public class ASTNum implements ASTNode {

	int val;

	public int eval(Environment<Integer> e) {
		return val;
	}

	public ASTNum(int n) {
		val = n;
	}

}
