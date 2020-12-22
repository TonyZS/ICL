package AST;

import compile.CodeBlock;
import exceptions.ValueErrorException;

public class ASTComp implements ASTNode {

	private ASTNode lhs, rhs;
	private String comp;
	

	public ASTComp(ASTNode left, ASTNode right, String comp) {
		this.lhs = left;
		this.rhs = right;
		this.comp = comp;
	}

	@Override
	public IValue eval(Environment<IValue> e) throws ValueErrorException {
		IValue v1 = lhs.eval(e);
		IValue v2 = rhs.eval(e);
		
		switch (comp) {
		case "<":
			if (v1 instanceof VInt && v2 instanceof VInt)
				return new VBoolean(((VInt) v1).getValue() < ((VInt) v2).getValue());
			break;
		case "<=":
			if (v1 instanceof VInt && v2 instanceof VInt)
				return new VBoolean(((VInt) v1).getValue() <= ((VInt) v2).getValue());
			break;
		case ">":
			if (v1 instanceof VInt && v2 instanceof VInt)
				return new VBoolean(((VInt) v1).getValue() > ((VInt) v2).getValue());
			break;
		case ">=":
			if (v1 instanceof VInt && v2 instanceof VInt)
				return new VBoolean(((VInt) v1).getValue() >= ((VInt) v2).getValue());
			break;
		case "==":
			if (v1 instanceof VInt && v2 instanceof VInt) {
				return new VBoolean(((VInt) v1).getValue() == ((VInt) v2).getValue());

			} else if (v1 instanceof VBoolean && v2 instanceof VBoolean)
				return new VBoolean(((VBoolean) v1).getValue() == ((VBoolean) v2).getValue());
			break;
		}
		throw new ValueErrorException("Invalid argument to " + comp + " operator");

	}
	@Override
	public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) {
		lhs.compile(block, e);
		rhs.compile(block, e);
		block.addinstruction("isub\n");
		int flag1 = block.getCurrFlag();
		int flag2 = block.getCurrFlag();

		switch (comp) {
		case "<":
			block.addinstruction("iflt L" + flag1);
			break;
		case "<=":
			block.addinstruction("ifle L" + flag1);
			break;
		case ">":
			block.addinstruction("ifgt L" + flag1);
			break;
		case ">=":
			block.addinstruction("ifge L" + flag1);
			break;
		case "==":
			block.addinstruction("ifeq L" + flag1);
			break;
		default:
			break;
		}
		block.addinstruction("sipush 0");
		block.addinstruction("goto L" + flag2);
		block.addinstruction("L" + flag1 + ":");
		block.addinstruction("sipush 1");
		block.addinstruction("L" + flag2 + ":");
	}


}
