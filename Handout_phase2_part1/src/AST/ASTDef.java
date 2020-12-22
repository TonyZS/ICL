package AST;

import java.util.List;

import compile.CodeBlock;
import compile.Frame;
import exceptions.ValueErrorException;

public class ASTDef implements ASTNode {

	private List<String> ids;
	private List<ASTNode> values;
	private ASTNode node;

	public ASTDef(List<String> ids, List<ASTNode> values, ASTNode node) {
		this.ids = ids;
		this.values = values;
		this.node = node;
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {

		Environment<Pair<Integer, String>> newEnv = e.beginScope();

		Frame frame = block.createFrame();
		int i = 0;
		while (i < ids.size()) {
			String field = frame.addField("I");
			block.addinstruction("aload_1");

			values.get(i).compile(block, newEnv);
			block.addinstruction(String.format("putfield %s/%s I", frame.getId(), field));
			newEnv.assoc(ids.get(i), new Pair<Integer, String>(newEnv.getDepth(), field));
			i++;
		}

		node.compile(block, newEnv);
		block.pop();

		newEnv = newEnv.endScope();
	}

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		Environment<IValue> newEnv = e.beginScope();
		int i = 0;
		while (i < ids.size()) {
			newEnv.assoc(ids.get(i), values.get(i).eval(newEnv));
			i++;
		}

		IValue pValue = node.eval(newEnv);
		newEnv = newEnv.endScope();

		return pValue;
	}

}
