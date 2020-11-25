
public class ASTUminus implements ASTNode {

	ASTNode lhs, rhs;

    public int eval()
    { 
int v1 = lhs.eval();

    return v1*-1; 
}

    public ASTUminus(ASTNode l)
    {
	lhs = l; 
    }
}

