package AST;

import compile.CodeBlock;
import compile.Frame;
import exceptions.ValueErrorException;

public class ASTId implements ASTNode {

	String id;

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		
		return e.find(id);
	}


	public ASTId(String value) {
		id = value;
	}

	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		
		Frame currFrame = block.getCurrFrame();

		Pair<Integer, String> cenas = e.find(id);

		int ref = e.getDepth() - cenas.getX();

		block.addinstruction("aload_1");
		while (ref > 0) {
			block.addinstruction(String.format("getfield %s/sl %s", currFrame.getId(), currFrame.getType("sl")));
			currFrame = currFrame.getFather();
			ref--;
		}

		block.addinstruction(
				String.format("getfield %s/%s %s", currFrame.getId(), cenas.getY(), currFrame.getType(cenas.getY())));
		
	}

	
	
}