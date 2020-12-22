/* Generated By:JavaCC: Do not edit this line. Parser.java */
package parser;
import java.util.*;
import AST.*;
import compile.*;
import exceptions.*;

/** ID lister. */
public class Parser implements ParserConstants {
  /** Main entry point. */
  public static void main(String args [])
  {
    Parser parser = new Parser(System.in);
    ASTNode exp;
    Environment evironment = new Environment(null);
    while (true)
    {
      try
      {
        exp = parser.Start();
        System.out.println(exp.eval(evironment));
        CodeBlock block = new CodeBlock();
        exp.compile(block, evironment);
        block.write("Demo.j");
      }
      catch (Exception e)
      {
        System.out.println("Syntax error");
        e.printStackTrace();
        parser.ReInit(System.in);
      }

    }
  }

  static final public ASTNode Start() throws ParseException {
  ASTNode t;
    //t = Exp() < EL >
      t = Seq();
    jj_consume_token(EL);
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Exp() throws ParseException {
  Token op;
  ASTNode t1, t2;
    t1 = Term();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        op = jj_consume_token(PLUS);
        break;
      case MINUS:
        op = jj_consume_token(MINUS);
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Term();
      if (op.kind == PLUS)
      t1 = new ASTPlus(t1, t2);
      else t1 = new ASTSub(t1, t2);
    }
    {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Term() throws ParseException {
  Token op;
  ASTNode t1, t2;
    t1 = Fact();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASSIGN:
      op = jj_consume_token(ASSIGN);
      t2 = Comp();
      t1 = new ASTAssign(t1, t2);
      break;
    default:
      jj_la1[4] = jj_gen;
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TIMES:
        case DIV:
        case MOD:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_2;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TIMES:
          op = jj_consume_token(TIMES);
          break;
        case DIV:
          op = jj_consume_token(DIV);
          break;
        case MOD:
          op = jj_consume_token(MOD);
          break;
        default:
          jj_la1[3] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        t2 = Fact();
      if (op.kind == TIMES)
      t1 = new ASTMult(t1, t2);
      else if (op.kind == DIV)
      t1 = new ASTDiv(t1, t2);
      else t1 = new ASTMod(t1,t2);
      }
    }
    {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Fact() throws ParseException {
  Token n;
  ArrayList < String > ids = new ArrayList < String > ();
  ArrayList < ASTNode > nodes = new ArrayList < ASTNode > ();
  ASTNode t = null, p, q = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Num:
      n = jj_consume_token(Num);
      t = new ASTNum(Integer.parseInt(n.image));
      break;
    case Id:
      n = jj_consume_token(Id);
      t = new ASTId(n.image);
      break;
    case DEF:
      jj_consume_token(DEF);
      label_3:
      while (true) {
        n = jj_consume_token(Id);
        ids.add(n.image);
        jj_consume_token(EQUALS);
        t = Seq();
        nodes.add(t);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case Id:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_3;
        }
      }
      jj_consume_token(IN);
      p = Seq();
      jj_consume_token(END);
      t = new ASTDef(ids, nodes, p);
      break;
    case MINUS:
      jj_consume_token(MINUS);
      t = new ASTUminus(Fact());
      break;
    case NEW:
      jj_consume_token(NEW);
      t = Fact();
    t = new ASTNew(t);
      break;
    case REF:
      jj_consume_token(REF);
      t = Fact();
      t = new ASTRef(t);
      break;
    case TRUE:
      jj_consume_token(TRUE);
      t = new ASTBool(true);
      break;
    case FALSE:
      jj_consume_token(FALSE);
      t = new ASTBool(false);
      break;
    case NOT:
      jj_consume_token(NOT);
      t = Fact();
      t = new ASTNot(t);
      break;
    case WHILE:
      jj_consume_token(WHILE);
      t = Seq();
      jj_consume_token(DO);
      p = Seq();
      jj_consume_token(END);
      t = new ASTWhile(t, p);
      break;
    case IF:
      jj_consume_token(IF);
      t = Seq();
      jj_consume_token(THEN);
      p = Seq();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ELSE:
        jj_consume_token(ELSE);
        q = Seq();
        break;
      default:
        jj_la1[6] = jj_gen;
        ;
      }
      jj_consume_token(END);
      t = new ASTIf(t, p, q);
      break;
    case PRINT:
      jj_consume_token(PRINT);
      t = Exp();
      t = new ASTPrint(t);
      break;
    case PRINTLN:
      jj_consume_token(PRINTLN);
      t = new ASTPrintln();
      break;
    case LPAR:
      jj_consume_token(LPAR);
      t = Seq();
      jj_consume_token(RPAR);
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Comp() throws ParseException {
  ASTNode t1, t2;
  Token op;
    t1 = Exp();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LESSTHEN:
    case MORETHEN:
    case LESSEQTHEN:
    case MOREEQTHEN:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LESSTHEN:
        op = jj_consume_token(LESSTHEN);
        break;
      case MORETHEN:
        op = jj_consume_token(MORETHEN);
        break;
      case LESSEQTHEN:
        op = jj_consume_token(LESSEQTHEN);
        break;
      case MOREEQTHEN:
        op = jj_consume_token(MOREEQTHEN);
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Exp();
      t1 = new ASTComp(t1, t2, op.image);
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
    {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode CompEquals() throws ParseException {
  ASTNode t1, t2;
    t1 = Comp();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQUALTHEN:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_4;
      }
      jj_consume_token(EQUALTHEN);
      t2 = CompEquals();
      t1 = new ASTComp(t1, t2, "==");
    }
    {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode And() throws ParseException {
  ASTNode t1, t2;
    t1 = CompEquals();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_5;
      }
      jj_consume_token(AND);
      t2 = And();
      t1 = new ASTAnd(t1, t2);
    }
    {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Or() throws ParseException {
  ASTNode t1, t2;
    t1 = And();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_6;
      }
      jj_consume_token(OR);
      t2 = Or();
      t1 = new ASTOr(t1, t2);
    }
    {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Seq() throws ParseException {
  ASTNode t1, t2;
    t1 = Or();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEMICOLON:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_7;
      }
      jj_consume_token(SEMICOLON);
      t2 = Seq();
      t1 = new ASTSeq(t1, t2);
    }
    {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[14];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x60,0x60,0x380,0x380,0x0,0x0,0x0,0x50099450,0x3c00000,0x3c00000,0x200000,0x2000,0x4000,0x20000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x8,0x40,0x1,0x76,0x0,0x0,0x0,0x0,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[39];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 14; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 39; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
