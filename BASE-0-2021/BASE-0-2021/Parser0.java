/* Generated By:JavaCC: Do not edit this line. Parser0.java */
import java.util.List;
import java.util.ArrayList;

/** ID lister. */
public class Parser0 implements Parser0Constants {

  static final public ASTNode Start() throws ParseException {
ASTNode t1;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Num:
    case MINUS:
    case DEREF:
    case NOPE:
    case LPAR:
    case DEF:
    case NEW:
    case PRINT:
    case PRINTLN:
    case TRUE:
    case FALSE:
    case Id:
      t1 = ExpSeq();
      jj_consume_token(DSEMI);
      break;
    case 0:
      jj_consume_token(0);
                t1 = null;
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
          {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode ExpSeq() throws ParseException {
  ASTNode t1, t2;
    t1 = ExpComp();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEQ:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      jj_consume_token(SEQ);
      t2 = ExpComp();
                                         t1 = new ASTSeq(t1, t2);
    }
     {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode ExpComp() throws ParseException {
  Token tok;
  ASTNode t1, t2;
    t1 = Exp();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASS:
    case BEQ:
    case GT:
    case GTE:
    case LT:
    case LTE:
    case AND:
    case OR:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BEQ:
        tok = jj_consume_token(BEQ);
        break;
      case ASS:
        tok = jj_consume_token(ASS);
        break;
      case GT:
        tok = jj_consume_token(GT);
        break;
      case GTE:
        tok = jj_consume_token(GTE);
        break;
      case LT:
        tok = jj_consume_token(LT);
        break;
      case LTE:
        tok = jj_consume_token(LTE);
        break;
      case AND:
        tok = jj_consume_token(AND);
        break;
      case OR:
        tok = jj_consume_token(OR);
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Exp();
                    if(tok.kind == ASS)
                        t1 = new ASTAssign(t1, t2);
                    else if(tok.kind == AND || tok.kind == OR)
                        t1 = new ASTCompareBool(t1, t2, tok.image);
                    else
                        t1 = new ASTCompare(t1, t2, tok.image);
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
   {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Exp() throws ParseException {
ASTNode t1,t2;
Token tok;
    t1 = Term();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        tok = jj_consume_token(PLUS);
        break;
      case MINUS:
        tok = jj_consume_token(MINUS);
        break;
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Term();
                   if (tok.kind == PLUS)
                        t1 = new ASTAdd(t1,t2);
                   else
                        t1 = new ASTSub(t1,t2);
    }
       {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Term() throws ParseException {
ASTNode t1,t2;
Token token;
    t1 = Fact();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MUL:
      case DIV:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MUL:
        token = jj_consume_token(MUL);
        break;
      case DIV:
        token = jj_consume_token(DIV);
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Fact();
       if(token.kind == MUL)
        t1 = new ASTMul(t1,t2);
        else t1 = new ASTDiv(t1,t2);
    }
       {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Fact() throws ParseException {
  ASTNode t1, t2;
 Token tok;
 List<Bind> binds = new ArrayList<Bind>();
 String id;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Num:
      tok = jj_consume_token(Num);
                 t1 = new ASTNum(Integer.parseInt(tok.image));
      break;
    case TRUE:
    case FALSE:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TRUE:
        tok = jj_consume_token(TRUE);
        break;
      case FALSE:
        tok = jj_consume_token(FALSE);
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
                                   t1 = new ASTBool(n.image.equals("true"));
      break;
    case NEW:
      jj_consume_token(NEW);
              t1 = new ASTRef(Fact());
      break;
    case DEREF:
      jj_consume_token(DEREF);
              t1 = new ASTDeref(Fact());
      break;
    case LPAR:
      jj_consume_token(LPAR);
      t1 = Exp();
      jj_consume_token(RPAR);
      break;
    case MINUS:
      jj_consume_token(MINUS);
              t1 = new ASTMinus(Fact());
      break;
    case NOPE:
      jj_consume_token(NOPE);
                t=new ASTNot(ExpSeq());
      break;
    case DEF:
      jj_consume_token(DEF);
      label_4:
      while (true) {
        tok = jj_consume_token(Id);
                           id = tok.image;
        jj_consume_token(EQUALS);
                                                      t2 = Exp(); binds.add(new Bind(id, t2));
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case Id:
          ;
          break;
        default:
          jj_la1[9] = jj_gen;
          break label_4;
        }
      }
      jj_consume_token(IN);
                                                                                                          t1 = new ASTDef(binds, Exp());
      jj_consume_token(END);
      break;
    case Id:
      tok = jj_consume_token(Id);
                  t1 = new ASTId(tok.image);
      break;
    case PRINT:
      jj_consume_token(PRINT);
                t1= new ASTPrintln(Fact(), false);
      break;
    case PRINTLN:
      jj_consume_token(PRINTLN);
                  t1 = new ASTPrintln(Fact(), true);
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public Parser0TokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[11];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x12c008a1,0x4000,0x3fa000,0x3fa000,0xc0,0xc0,0x300,0x300,0x0,0x0,0x12c008a0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x58c,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x180,0x400,0x58c,};
   }

  /** Constructor with InputStream. */
  public Parser0(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser0(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new Parser0TokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser0(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new Parser0TokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser0(Parser0TokenManager tm) {
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
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(Parser0TokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
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
    boolean[] la1tokens = new boolean[43];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 11; i++) {
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
    for (int i = 0; i < 43; i++) {
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
