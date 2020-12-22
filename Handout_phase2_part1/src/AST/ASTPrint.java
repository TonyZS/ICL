package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTPrint implements ASTNode {

	private ASTNode node;

	public ASTPrint(ASTNode node) {
		this.node = node;
	}

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {

		IValue vNode = node.eval(e);
		if (vNode instanceof IValue) {
			System.out.print(vNode.getValue());
			return new VString("");
		} else
			throw new ValueErrorException("s");
	}

	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		node.compile(block, e);
		// tenho uma duvida
		block.addinstruction("convert to String;");
		block.addinstruction("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
		block.addinstruction("call print");
		block.addinstruction("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");

	}

}
