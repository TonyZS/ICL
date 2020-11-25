package AST;

import java.util.*;


public class ASTDef implements ASTNode {

	private List<String> ids;
	private List<ASTNode> values;
	private ASTNode node;

	@Override
	public int eval(Environment<Integer> e) {

		List<Integer> evals = new ArrayList<Integer>();

		for (ASTNode node : values) {
			evals.add(node.eval(e));
		}

		Environment<Integer> newEnv = e.beginScope();
		int i = 0;
		while (i < evals.size()) {
			newEnv.assoc(ids.get(i), evals.get(i));
			i++;
		}

		int pValue = node.eval(newEnv);
		newEnv = newEnv.endScope();

		return pValue;
	}
	
	public ASTDef(List<String> ids, List<ASTNode> values, ASTNode node) {
		this.ids = ids;
		this.values = values;
		this.node = node;
	}

}
