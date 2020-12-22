package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTRef implements ASTNode {
	private ASTNode ref;

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		IValue eRef = ref.eval(e);
		if (eRef instanceof VMCell)
			return ((VMCell) (eRef)).getValue();
		else
			throw new ValueErrorException("Invalid argument to ! operator");
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		// TODO Auto-generated method stub

	}

	public ASTRef(ASTNode ref) {
		this.ref = ref;
	}

}
