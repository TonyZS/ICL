/**
 * 
 */
package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTAssign implements ASTNode {

	ASTNode lhs, rhs;

	public ASTAssign(ASTNode l, ASTNode r) {
		lhs = l;
		rhs = r;	
	}

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		IValue v1 = lhs.eval(e);
		
		if (v1 instanceof VMCell) {
			IValue v2 = rhs.eval(e);
			((VMCell) v1).set(v2);
			return v2;
		} else {
			throw new ValueErrorException("Invalid argument to := operator");
		}
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		// TODO Auto-generated method stub

	}

}
