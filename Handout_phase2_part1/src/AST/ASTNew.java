/**
 * 
 */
package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;


public class ASTNew implements ASTNode {

	ASTNode rhs;
	
	public ASTNew(ASTNode r) {
		this.rhs = r;
	}
	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		IValue v1 = rhs.eval(e);
		return new VMCell(v1);
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		// TODO Auto-generated method stub

	}

}
