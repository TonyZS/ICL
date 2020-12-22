package AST;
import compile.*;
import exceptions.ValueErrorException;

public interface ASTNode {

	public IValue eval(Environment<IValue> e)throws ValueErrorException;

	 public void compile(CodeBlock block, Environment<Pair<Integer, String>> e) ;

}
