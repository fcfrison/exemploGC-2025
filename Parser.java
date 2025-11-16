//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 "exemploGC.y"
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
//#line 21 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ID=257;
public final static short INT=258;
public final static short FLOAT=259;
public final static short BOOL=260;
public final static short NUM=261;
public final static short LIT=262;
public final static short VOID=263;
public final static short MAIN=264;
public final static short READ=265;
public final static short WRITE=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short WHILE=269;
public final static short TRUE=270;
public final static short FALSE=271;
public final static short EQ=272;
public final static short LEQ=273;
public final static short GEQ=274;
public final static short NEQ=275;
public final static short AND=276;
public final static short OR=277;
public final static short MAISMAIS=278;
public final static short MENOSMENOS=279;
public final static short MAISIGUAL=280;
public final static short FOR=281;
public final static short BREAK=282;
public final static short CONTINUE=283;
public final static short DO=284;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    3,    0,    5,    7,    4,    2,    2,    8,    1,    1,
    1,    6,    6,    9,    9,    9,   11,    9,    9,   12,
   13,    9,   14,    9,   16,   17,    9,   18,   19,   20,
   21,   22,    9,   23,   24,   25,    9,    9,    9,   26,
   15,   15,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,   27,   28,   10,
};
final static short yylen[] = {                            2,
    0,    3,    0,    0,    9,    2,    0,    3,    1,    1,
    1,    2,    0,    2,    3,    5,    0,    8,    5,    0,
    0,    7,    0,    7,    0,    0,   11,    0,    0,    0,
    0,    0,   16,    0,    0,    0,   11,    2,    2,    0,
    3,    0,    1,    1,    1,    1,    3,    2,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    2,    2,    2,    2,    3,    0,    0,    7,
};
final static short yydefred[] = {                         1,
    0,    0,    9,   10,   11,    0,    0,    0,    0,    0,
    2,    6,    8,    0,    0,    3,    0,   13,    0,    0,
   43,    0,    0,    0,   20,   44,   45,    0,    0,    0,
    0,    0,    0,    0,    0,   13,    0,   12,    0,   64,
   66,    0,    0,    0,    0,    0,    0,   63,   65,    0,
   38,   39,   25,   48,    0,    0,    5,    0,    0,    0,
    0,    0,    0,   68,    0,    0,    0,    0,    0,    0,
    0,   14,    0,    0,    0,    0,    0,    0,    0,    0,
   13,   47,   15,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   52,   53,   54,    0,    0,    0,
    0,    0,    0,   28,    0,    0,   19,   16,    0,    0,
   21,   34,    0,    0,   69,    0,    0,    0,    0,    0,
    0,    0,    0,   40,   24,   22,   35,   29,    0,    0,
   18,    0,   13,    0,    0,   41,    0,    0,    0,    0,
   30,    0,   37,    0,   27,   31,   13,    0,    0,   33,
};
final static short yydgoto[] = {                          1,
    6,    7,    2,   11,   17,   19,   37,    8,   38,   39,
  100,   47,  118,  101,  125,   81,  139,  113,  134,  144,
  147,  149,  119,  133,  140,  132,   90,  122,
};
final static short yysindex[] = {                         0,
    0, -220,    0,    0,    0, -254, -258, -220,  -50, -247,
    0,    0,    0,  -20,  -11,    0,  -89,    0,   51,  -53,
    0,    8,    9,   11,    0,    0,    0, -207, -203,   18,
    1,    2,  -64,   81,   81,    0,  -63,    0,   98,    0,
    0,   81,   81, -193, -197,   81,   26,    0,    0,   66,
    0,    0,    0,    0,  122,  -33,    0,   81,   81,   81,
   81,   81,   81,    0,   81,   81,   81,   81,   81,   81,
   81,    0,  428,  428,   27,   28,  428,   81,   12,  133,
    0,    0,    0,   10,   10,   10,   10,  -31,  435,   81,
   10,   10,  -24,  -24,    0,    0,    0,   13,   14,   30,
   29,  157,   34,    0,   -5,  377,    0,    0,   81,   51,
    0,    0,   81, -202,    0,  340, -192,   51,  -46,  399,
   38,   81,   20,    0,    0,    0,    0,    0,   81,  428,
    0,   51,    0,   81,  428,    0,   51,  406,   39,  -44,
    0,   24,    0,  -41,    0,    0,    0,   51,  -40,    0,
};
final static short yyrindex[] = {                         0,
    0, -177,    0,    0,    0,    0,    0, -177,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -38,   91,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -26,  -16,    0,   44,   48,    0,    0,    0,
    0,    0,    0,  460,  466,  474,  480,  -37,  -22,    0,
  486,  494,  440,  451,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   23,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -14,
    0,    0,    0,    0,   52,    0,  -30,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -29,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   86,    0,    0,    0,  -35,    0,    0, -108,  533,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=771;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
   56,  117,    9,   62,   10,   71,   35,   43,   13,  126,
   69,   67,   71,   68,   67,   70,   14,   69,   61,   15,
   62,   62,   70,  136,   49,   62,   70,   34,   66,   16,
   65,   67,   67,   18,   35,   61,   61,    3,    4,    5,
   61,   49,   49,   70,   70,  105,   71,   44,   45,   48,
   46,   69,   67,   49,   68,   42,   70,   50,   53,   51,
   52,   57,   42,   75,   76,   78,  121,   98,   99,  110,
  103,  107,  108,  109,  112,  124,  127,  129,  131,  142,
  143,  146,  145,   34,  150,    7,    4,   17,   23,   36,
   35,   83,   26,   12,   36,   32,    0,  137,   34,    0,
    0,    0,    0,    0,    0,   35,    0,    0,    0,    0,
    0,  148,    0,   34,    0,    0,    0,   36,    0,  114,
   35,    0,    0,    0,   79,    0,    0,   46,    0,    0,
    0,   46,   46,   46,   71,   46,    0,   46,    0,   69,
   67,    0,   68,    0,   70,   42,    0,   42,   46,   46,
   46,    0,   46,   46,    0,    0,   72,   66,   71,   65,
   64,    0,   82,   69,   67,    0,   68,    0,   70,   71,
    0,    0,    0,   36,   69,   67,    0,   68,    0,   70,
    0,   66,    0,   65,   64,    0,    0,    0,    0,    0,
    0,  104,   66,   71,   65,   64,    0,  111,   69,   67,
    0,   68,    0,   70,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   66,    0,   65,   64,
    0,    0,    0,   20,   40,   41,   42,   21,    0,    0,
    0,   22,   23,   24,    0,   25,   26,   27,   62,   62,
   58,   59,   60,   61,   28,   29,    0,   30,   31,   32,
   33,   20,    0,    0,   61,   21,    0,    0,    0,   22,
   23,   24,    0,   25,   26,   27,    0,    0,    0,    0,
    0,    0,   28,   29,    0,   30,   31,   32,   33,   42,
    0,    0,    0,   42,    0,    0,    0,   42,   42,   42,
    0,   42,   42,   42,    0,    0,    0,    0,    0,    0,
   42,   42,    0,   42,   42,   42,   42,   20,    0,    0,
    0,   21,    0,    0,    0,   22,   23,   24,    0,   25,
   26,   27,   20,    0,    0,    0,   21,    0,   28,   29,
    0,   30,   31,   32,   33,   26,   27,   20,    0,    0,
    0,   21,    0,   28,   29,    0,    0,    0,    0,    0,
   26,   27,    0,    0,    0,    0,    0,    0,   28,   29,
    0,    0,   46,   46,   46,   46,   46,   46,    0,   58,
   59,   60,   61,   62,   63,    0,   71,    0,    0,    0,
  123,   69,   67,    0,   68,    0,   70,    0,    0,    0,
    0,    0,    0,   58,   59,   60,   61,   62,   63,   66,
    0,   65,   64,    0,   58,   59,   60,   61,   62,   63,
    0,    0,    0,   71,    0,    0,    0,    0,   69,   67,
    0,   68,    0,   70,    0,    0,    0,    0,   58,   59,
   60,   61,   62,   63,  115,   71,   66,    0,   65,   64,
   69,   67,   71,   68,    0,   70,  141,   69,   67,    0,
   68,    0,   70,    0,    0,    0,    0,  128,   66,    0,
   65,   64,    0,    0,   71,   66,    0,   65,   64,   69,
   67,   71,   68,    0,   70,    0,   69,   67,    0,   68,
   50,   70,   50,    0,   50,    0,    0,   66,    0,   65,
   64,   51,    0,   51,   66,   51,   65,   50,   50,   50,
   57,   50,   50,    0,    0,    0,   58,    0,   51,   51,
   51,    0,   51,   51,   59,    0,    0,   57,   57,   57,
   60,   57,   57,   58,   58,   58,   55,   58,   58,    0,
    0,   59,   59,   59,   56,   59,   59,   60,   60,   60,
    0,   60,   60,   55,   55,   55,    0,   55,   55,    0,
    0,   56,   56,   56,    0,   56,   56,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   54,   55,    0,    0,
    0,    0,    0,    0,   73,   74,    0,    0,   77,    0,
    0,    0,   80,    0,    0,    0,    0,    0,    0,    0,
   84,   85,   86,   87,   88,   89,    0,   91,   92,   93,
   94,   95,   96,   97,    0,    0,    0,    0,    0,    0,
  102,   58,   59,   60,   61,   62,   63,    0,    0,    0,
    0,    0,  106,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  116,    0,    0,    0,  120,    0,    0,   58,   59,
   60,   61,   62,   63,  130,    0,    0,    0,    0,    0,
    0,  135,    0,    0,    0,    0,  138,    0,    0,    0,
   58,   59,   60,   61,   62,   63,    0,   58,   59,   60,
   61,   62,   63,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   58,
   59,   60,   61,   62,   63,    0,   58,   59,   60,   61,
   62,   50,   50,   50,   50,   50,   50,    0,    0,    0,
    0,    0,   51,   51,   51,   51,   51,   51,    0,    0,
    0,   57,   57,   57,   57,   57,   57,   58,   58,   58,
   58,   58,   58,    0,    0,   59,   59,   59,   59,   59,
   59,   60,   60,   60,   60,   60,   60,   55,   55,   55,
   55,   55,   55,    0,    0,   56,   56,   56,   56,   56,
   56,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   36,  110,  257,   41,  263,   37,   40,   61,   59,  118,
   42,   43,   37,   45,   41,   47,  264,   42,   41,   40,
   58,   59,   47,  132,   41,   63,   41,   33,   60,   41,
   62,   58,   59,  123,   40,   58,   59,  258,  259,  260,
   63,   58,   59,   58,   59,   81,   37,   40,   40,  257,
   40,   42,   43,  257,   45,   33,   47,   40,  123,   59,
   59,  125,   40,  257,  262,   40,  269,   41,   41,   41,
   59,   59,   59,   44,   41,  268,  123,   40,   59,   41,
  125,  123,   59,   33,  125,  263,  125,   44,   41,  123,
   40,  125,   41,    8,  125,  125,   -1,  133,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,
   -1,  147,   -1,   33,   -1,   -1,   -1,  123,   -1,  125,
   40,   -1,   -1,   -1,   59,   -1,   -1,   37,   -1,   -1,
   -1,   41,   42,   43,   37,   45,   -1,   47,   -1,   42,
   43,   -1,   45,   -1,   47,  123,   -1,  125,   58,   59,
   60,   -1,   62,   63,   -1,   -1,   59,   60,   37,   62,
   63,   -1,   41,   42,   43,   -1,   45,   -1,   47,   37,
   -1,   -1,   -1,  123,   42,   43,   -1,   45,   -1,   47,
   -1,   60,   -1,   62,   63,   -1,   -1,   -1,   -1,   -1,
   -1,   59,   60,   37,   62,   63,   -1,   41,   42,   43,
   -1,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   60,   -1,   62,   63,
   -1,   -1,   -1,  257,  278,  279,  280,  261,   -1,   -1,
   -1,  265,  266,  267,   -1,  269,  270,  271,  276,  277,
  272,  273,  274,  275,  278,  279,   -1,  281,  282,  283,
  284,  257,   -1,   -1,  277,  261,   -1,   -1,   -1,  265,
  266,  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,
   -1,   -1,  278,  279,   -1,  281,  282,  283,  284,  257,
   -1,   -1,   -1,  261,   -1,   -1,   -1,  265,  266,  267,
   -1,  269,  270,  271,   -1,   -1,   -1,   -1,   -1,   -1,
  278,  279,   -1,  281,  282,  283,  284,  257,   -1,   -1,
   -1,  261,   -1,   -1,   -1,  265,  266,  267,   -1,  269,
  270,  271,  257,   -1,   -1,   -1,  261,   -1,  278,  279,
   -1,  281,  282,  283,  284,  270,  271,  257,   -1,   -1,
   -1,  261,   -1,  278,  279,   -1,   -1,   -1,   -1,   -1,
  270,  271,   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,
   -1,   -1,  272,  273,  274,  275,  276,  277,   -1,  272,
  273,  274,  275,  276,  277,   -1,   37,   -1,   -1,   -1,
   41,   42,   43,   -1,   45,   -1,   47,   -1,   -1,   -1,
   -1,   -1,   -1,  272,  273,  274,  275,  276,  277,   60,
   -1,   62,   63,   -1,  272,  273,  274,  275,  276,  277,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   -1,   47,   -1,   -1,   -1,   -1,  272,  273,
  274,  275,  276,  277,   58,   37,   60,   -1,   62,   63,
   42,   43,   37,   45,   -1,   47,   41,   42,   43,   -1,
   45,   -1,   47,   -1,   -1,   -1,   -1,   59,   60,   -1,
   62,   63,   -1,   -1,   37,   60,   -1,   62,   63,   42,
   43,   37,   45,   -1,   47,   -1,   42,   43,   -1,   45,
   41,   47,   43,   -1,   45,   -1,   -1,   60,   -1,   62,
   63,   41,   -1,   43,   60,   45,   62,   58,   59,   60,
   41,   62,   63,   -1,   -1,   -1,   41,   -1,   58,   59,
   60,   -1,   62,   63,   41,   -1,   -1,   58,   59,   60,
   41,   62,   63,   58,   59,   60,   41,   62,   63,   -1,
   -1,   58,   59,   60,   41,   62,   63,   58,   59,   60,
   -1,   62,   63,   58,   59,   60,   -1,   62,   63,   -1,
   -1,   58,   59,   60,   -1,   62,   63,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   34,   35,   -1,   -1,
   -1,   -1,   -1,   -1,   42,   43,   -1,   -1,   46,   -1,
   -1,   -1,   50,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   58,   59,   60,   61,   62,   63,   -1,   65,   66,   67,
   68,   69,   70,   71,   -1,   -1,   -1,   -1,   -1,   -1,
   78,  272,  273,  274,  275,  276,  277,   -1,   -1,   -1,
   -1,   -1,   90,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  109,   -1,   -1,   -1,  113,   -1,   -1,  272,  273,
  274,  275,  276,  277,  122,   -1,   -1,   -1,   -1,   -1,
   -1,  129,   -1,   -1,   -1,   -1,  134,   -1,   -1,   -1,
  272,  273,  274,  275,  276,  277,   -1,  272,  273,  274,
  275,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,
  273,  274,  275,  276,  277,   -1,  272,  273,  274,  275,
  276,  272,  273,  274,  275,  276,  277,   -1,   -1,   -1,
   -1,   -1,  272,  273,  274,  275,  276,  277,   -1,   -1,
   -1,  272,  273,  274,  275,  276,  277,  272,  273,  274,
  275,  276,  277,   -1,   -1,  272,  273,  274,  275,  276,
  277,  272,  273,  274,  275,  276,  277,  272,  273,  274,
  275,  276,  277,   -1,   -1,  272,  273,  274,  275,  276,
  277,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=284;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'","'?'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"ID","INT","FLOAT","BOOL","NUM","LIT",
"VOID","MAIN","READ","WRITE","IF","ELSE","WHILE","TRUE","FALSE","EQ","LEQ",
"GEQ","NEQ","AND","OR","MAISMAIS","MENOSMENOS","MAISIGUAL","FOR","BREAK",
"CONTINUE","DO",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 dList mainF",
"$$2 :",
"$$3 :",
"mainF : VOID MAIN '(' ')' $$2 '{' lcmd $$3 '}'",
"dList : decl dList",
"dList :",
"decl : type ID ';'",
"type : INT",
"type : FLOAT",
"type : BOOL",
"lcmd : lcmd cmd",
"lcmd :",
"cmd : exp ';'",
"cmd : '{' lcmd '}'",
"cmd : WRITE '(' LIT ')' ';'",
"$$4 :",
"cmd : WRITE '(' LIT $$4 ',' exp ')' ';'",
"cmd : READ '(' ID ')' ';'",
"$$5 :",
"$$6 :",
"cmd : WHILE $$5 '(' exp ')' $$6 cmd",
"$$7 :",
"cmd : IF '(' exp $$7 ')' cmd restoIf",
"$$8 :",
"$$9 :",
"cmd : DO '{' $$8 lcmd '}' WHILE '(' exp $$9 ')' ';'",
"$$10 :",
"$$11 :",
"$$12 :",
"$$13 :",
"$$14 :",
"cmd : FOR '(' exp ';' $$10 exp ';' $$11 exp ')' $$12 '{' $$13 lcmd $$14 '}'",
"$$15 :",
"$$16 :",
"$$17 :",
"cmd : FOR '(' ';' ';' ')' $$15 '{' $$16 lcmd $$17 '}'",
"cmd : BREAK ';'",
"cmd : CONTINUE ';'",
"$$18 :",
"restoIf : ELSE $$18 cmd",
"restoIf :",
"exp : NUM",
"exp : TRUE",
"exp : FALSE",
"exp : ID",
"exp : '(' exp ')'",
"exp : '!' exp",
"exp : ID '=' exp",
"exp : exp '+' exp",
"exp : exp '-' exp",
"exp : exp '*' exp",
"exp : exp '/' exp",
"exp : exp '%' exp",
"exp : exp '>' exp",
"exp : exp '<' exp",
"exp : exp EQ exp",
"exp : exp LEQ exp",
"exp : exp GEQ exp",
"exp : exp NEQ exp",
"exp : exp OR exp",
"exp : exp AND exp",
"exp : MAISMAIS ID",
"exp : ID MAISMAIS",
"exp : MENOSMENOS ID",
"exp : ID MENOSMENOS",
"exp : ID MAISIGUAL exp",
"$$19 :",
"$$20 :",
"exp : exp '?' $$19 exp ':' $$20 exp",
};

//#line 315 "exemploGC.y"

  private Yylex lexer;

  private TabSimb ts = new TabSimb();

  private int strCount = 0;
  private ArrayList<String> strTab = new ArrayList<String>();

  private Stack<Integer> pRot = new Stack<Integer>();
  private Stack<Integer> pBreak = new Stack<Integer>();
  private Stack<Integer> pContinue = new Stack<Integer>();
  private int proxRot = 1;


  public static int ARRAY = 100;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error + "  linha: " + lexer.getLine());
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
      yyparser.yyparse();
      // yyparser.listarTS();

    }
    else {
      // interactive mode
      System.out.println("\n\tFormato: java Parser entrada.cmm >entrada.s\n");
    }

  }

							
		void gcExpArit(int oparit) {
		System.out.println("\tPOPL %EBX");
		System.out.println("\tPOPL %EAX");

   		switch (oparit) {
     		case '+' : System.out.println("\tADDL %EBX, %EAX" ); break;
     		case '-' : System.out.println("\tSUBL %EBX, %EAX" ); break;
     		case '*' : System.out.println("\tIMULL %EBX, %EAX" ); break;

    		case '/': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     break;
     		case '%': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     System.out.println("\tMOVL %EDX, %EAX");
           		     break;
    		}
   		System.out.println("\tPUSHL %EAX");
		}

	public void gcExpRel(int oprel) {

    System.out.println("\tPOPL %EAX");
    System.out.println("\tPOPL %EDX");
    System.out.println("\tCMPL %EAX, %EDX");
    System.out.println("\tMOVL $0, %EAX");
    
    switch (oprel) {
       case '<':  			System.out.println("\tSETL  %AL"); break;
       case '>':  			System.out.println("\tSETG  %AL"); break;
       case Parser.EQ:  System.out.println("\tSETE  %AL"); break;
       case Parser.GEQ: System.out.println("\tSETGE %AL"); break;
       case Parser.LEQ: System.out.println("\tSETLE %AL"); break;
       case Parser.NEQ: System.out.println("\tSETNE %AL"); break;
       }
    
    System.out.println("\tPUSHL %EAX");

	}


	public void gcExpLog(int oplog) {

	   	System.out.println("\tPOPL %EDX");
 		 	System.out.println("\tPOPL %EAX");

  	 	System.out.println("\tCMPL $0, %EAX");
 		  System.out.println("\tMOVL $0, %EAX");
   		System.out.println("\tSETNE %AL");
   		System.out.println("\tCMPL $0, %EDX");
   		System.out.println("\tMOVL $0, %EDX");
   		System.out.println("\tSETNE %DL");

   		switch (oplog) {
    			case Parser.OR:  System.out.println("\tORL  %EDX, %EAX");  break;
    			case Parser.AND: System.out.println("\tANDL  %EDX, %EAX"); break;
       }

    	System.out.println("\tPUSHL %EAX");
	}

	public void gcExpNot(){

  	 System.out.println("\tPOPL %EAX" );
 	   System.out.println("	\tNEGL %EAX" );
  	 System.out.println("	\tPUSHL %EAX");
	}

   private void geraInicio() {
			System.out.println(".text\n\n#\t nome COMPLETO e matricula dos componentes do grupo...\n#\n"); 
			System.out.println(".GLOBL _start\n\n");  
   }

   private void geraFinal(){
	
			System.out.println("\n\n");
			System.out.println("#");
			System.out.println("# devolve o controle para o SO (final da main)");
			System.out.println("#");
			System.out.println("\tmov $0, %ebx");
			System.out.println("\tmov $1, %eax");
			System.out.println("\tint $0x80");
	
			System.out.println("\n");
			System.out.println("#");
			System.out.println("# Funcoes da biblioteca (IO)");
			System.out.println("#");
			System.out.println("\n");
			System.out.println("_writeln:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $10, (%ECX)");
			System.out.println("\tMOVL $1, %EDX");
			System.out.println("\tJMP _writeLit");
			System.out.println("_write:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJGE _write3");
			System.out.println("\tNEGL %EAX");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("_write3:");
			System.out.println("\tPUSHL %EBX");
			System.out.println("\tMOVL $10, %EBX");
			System.out.println("_divide:");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tIDIVL %EBX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tADD $48, %DL");
			System.out.println("\tMOVB %DL, (%ECX)");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJNE _divide");
			System.out.println("\tPOPL %EBX");
			System.out.println("\tCMPL $0, %EBX");
			System.out.println("\tJE _print");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $'-', (%ECX)");
			System.out.println("_print:");
			System.out.println("\tMOVL $__fim_msg, %EDX");
			System.out.println("\tSUBL %ECX, %EDX");
			System.out.println("_writeLit:");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("\tMOVL $4, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tRET");
			System.out.println("_read:");
			System.out.println("\tMOVL $15, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $3, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tMOVL $0, %EAX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tCMPB $'-', (%ECX)");
			System.out.println("\tJNE _reading");
			System.out.println("\tINCL %ECX");
			System.out.println("\tINC %BL");
			System.out.println("_reading:");
			System.out.println("\tMOVB (%ECX), %DL");
			System.out.println("\tCMP $10, %DL");
			System.out.println("\tJE _fimread");
			System.out.println("\tSUB $48, %DL");
			System.out.println("\tIMULL $10, %EAX");
			System.out.println("\tADDL %EDX, %EAX");
			System.out.println("\tINCL %ECX");
			System.out.println("\tJMP _reading");
			System.out.println("_fimread:");
			System.out.println("\tCMPB $1, %BL");
			System.out.println("\tJNE _fimread2");
			System.out.println("\tNEGL %EAX");
			System.out.println("_fimread2:");
			System.out.println("\tRET");
			System.out.println("\n");
     }

     private void geraAreaDados(){
			System.out.println("");		
			System.out.println("#");
			System.out.println("# area de dados");
			System.out.println("#");
			System.out.println(".data");
			System.out.println("#");
			System.out.println("# variaveis globais");
			System.out.println("#");
			ts.geraGlobais();	
			System.out.println("");
	
    }

     private void geraAreaLiterais() { 

         System.out.println("#\n# area de literais\n#");
         System.out.println("__msg:");
	       System.out.println("\t.zero 30");
	       System.out.println("__fim_msg:");
	       System.out.println("\t.byte 0");
	       System.out.println("\n");

         for (int i = 0; i<strTab.size(); i++ ) {
             System.out.println("_str_"+i+":");
             System.out.println("\t .ascii \""+strTab.get(i)+"\""); 
	           System.out.println("_str_"+i+"Len = . - _str_"+i);  
	      }		
   }
   
//#line 718 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 35 "exemploGC.y"
{ geraInicio(); }
break;
case 2:
//#line 35 "exemploGC.y"
{ geraAreaDados(); geraAreaLiterais(); }
break;
case 3:
//#line 37 "exemploGC.y"
{ System.out.println("_start:"); }
break;
case 4:
//#line 38 "exemploGC.y"
{ geraFinal(); }
break;
case 8:
//#line 43 "exemploGC.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                if (nodo != null) 
                            yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                        else ts.insert(new TS_entry(val_peek(1).sval, val_peek(2).ival)); }
break;
case 9:
//#line 49 "exemploGC.y"
{ yyval.ival = INT; }
break;
case 10:
//#line 50 "exemploGC.y"
{ yyval.ival = FLOAT; }
break;
case 11:
//#line 51 "exemploGC.y"
{ yyval.ival = BOOL; }
break;
case 14:
//#line 58 "exemploGC.y"
{ System.out.println("\tPOPL %EDX"); }
break;
case 15:
//#line 59 "exemploGC.y"
{ System.out.println("\t\t# terminou o bloco..."); }
break;
case 16:
//#line 60 "exemploGC.y"
{ strTab.add(val_peek(2).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				System.out.println("\tCALL _writeln"); 
                                strCount++;
				}
break;
case 17:
//#line 69 "exemploGC.y"
{ strTab.add(val_peek(0).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				strCount++;
				}
break;
case 18:
//#line 77 "exemploGC.y"
{ 
			 System.out.println("\tPOPL %EAX"); 
			 System.out.println("\tCALL _write");	
			 System.out.println("\tCALL _writeln"); 
                        }
break;
case 19:
//#line 84 "exemploGC.y"
{
									System.out.println("\tPUSHL $_"+val_peek(2).sval);
									System.out.println("\tCALL _read");
									System.out.println("\tPOPL %EDX");
									System.out.println("\tMOVL %EAX, (%EDX)");
									
								}
break;
case 20:
//#line 92 "exemploGC.y"
{
					pRot.push(proxRot);
					pBreak.push(proxRot+1);
					pContinue.push(proxRot);
					proxRot += 2;
					System.out.printf("rot_%02d:\n",pRot.peek());
				  }
break;
case 21:
//#line 99 "exemploGC.y"
{
							System.out.println("\tPOPL %EAX   # desvia se falso...");
							System.out.println("\tCMPL $0, %EAX");
							System.out.printf("\tJE rot_%02d\n", (int)pRot.peek()+1);
						}
break;
case 22:
//#line 104 "exemploGC.y"
{
				  			System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRot.peek());
							System.out.printf("rot_%02d:\n",(int)pRot.peek()+1);
							pRot.pop();
							pBreak.pop();
							pContinue.pop();
						}
break;
case 23:
//#line 112 "exemploGC.y"
{	
				pRot.push(proxRot);
				proxRot += 2;
				System.out.println("\tPOPL %EAX\n");
				System.out.println("\tCMPL $0, %EAX\n");
				System.out.printf("\tJE rot_%02d\n", pRot.peek());
			}
break;
case 24:
//#line 121 "exemploGC.y"
{
						System.out.printf("rot_%02d:\n",pRot.peek()+1);
						pRot.pop();
					}
break;
case 25:
//#line 125 "exemploGC.y"
{
		pRot.push(proxRot);
		pBreak.push(proxRot + 1);
		pContinue.push(proxRot);
		proxRot += 2;
		System.out.printf("rot_%02d:\n", pRot.peek());
	}
break;
case 26:
//#line 134 "exemploGC.y"
{
					System.out.println("\tPOPL %EAX");
					System.out.println("\tCMPL $0 , %EAX");
					System.out.printf("\tJE rot_%02d\n", pRot.peek() + 1);
					System.out.printf("\tJMP rot_%02d\n", pRot.peek());
					System.out.printf("rot_%02d:\n", pRot.peek() + 1);
					pRot.pop();
					pBreak.pop();
					pContinue.pop();
				}
break;
case 28:
//#line 146 "exemploGC.y"
{
				pRot.push(proxRot);
				pBreak.push(proxRot + 3); /* pula para o fim*/
				pContinue.push(pRot.peek() + 2); /* pula para o incremento*/
				proxRot +=4;
				System.out.printf("rot_%02d:\n", pRot.peek());
			}
break;
case 29:
//#line 153 "exemploGC.y"
{
				System.out.println("\tPOPL %EAX");
				System.out.println("\tCMPL $0 , %EAX");
				System.out.printf("\tJE rot_%02d\n", pRot.peek() + 3);
				System.out.printf("\tJMP rot_%02d\n", pRot.peek() + 1);
				System.out.printf("rot_%02d:\n", pRot.peek() + 2);
			}
break;
case 30:
//#line 160 "exemploGC.y"
{
				System.out.printf("\tJMP rot_%02d\n", pRot.peek());
			}
break;
case 31:
//#line 163 "exemploGC.y"
{
						System.out.printf("rot_%02d:\n", pRot.peek() + 1);
					}
break;
case 32:
//#line 167 "exemploGC.y"
{
						System.out.printf("\tJMP rot_%02d\n", pRot.peek() + 2);
						System.out.printf("rot_%02d:\n", pRot.peek() + 3);
						pRot.pop();
						pBreak.pop();
						pContinue.pop();
					}
break;
case 34:
//#line 176 "exemploGC.y"
{
                pRot.push(proxRot);
				pBreak.push(proxRot + 2);
				pContinue.push(proxRot);
                proxRot += 3;
                System.out.printf("rot_%02d:\n", pRot.peek());
                System.out.printf("\tJMP rot_%02d\n", pRot.peek() + 1);
            }
break;
case 35:
//#line 185 "exemploGC.y"
{ System.out.printf("rot_%02d:\n", pRot.peek() + 1); }
break;
case 36:
//#line 189 "exemploGC.y"
{
				System.out.printf("\tJMP rot_%02d\n", pRot.peek());
				System.out.printf("rot_%02d:\n", pRot.peek() + 2 );
				pRot.pop();
				pBreak.pop();
				pContinue.pop();
			}
break;
case 38:
//#line 198 "exemploGC.y"
{
        if (pBreak.isEmpty()) {
            yyerror("break fora de um loop");
        } else {
            System.out.printf("\tJMP rot_%02d\n", pBreak.peek());
        }
    }
break;
case 39:
//#line 205 "exemploGC.y"
{
		if (pContinue.isEmpty()){
			yyerror("continue fora de um loop");
		} else {
			System.out.printf("\tJMP rot_%02d\n", pContinue.peek());
		}
	}
break;
case 40:
//#line 215 "exemploGC.y"
{
				System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
				System.out.printf("rot_%02d:\n",pRot.peek());
	
			}
break;
case 42:
//#line 223 "exemploGC.y"
{
		    System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
				System.out.printf("rot_%02d:\n",pRot.peek());
				}
break;
case 43:
//#line 230 "exemploGC.y"
{ System.out.println("\tPUSHL $"+val_peek(0).sval); }
break;
case 44:
//#line 231 "exemploGC.y"
{ System.out.println("\tPUSHL $1"); }
break;
case 45:
//#line 232 "exemploGC.y"
{ System.out.println("\tPUSHL $0"); }
break;
case 46:
//#line 233 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(0).sval); }
break;
case 48:
//#line 235 "exemploGC.y"
{ gcExpNot(); }
break;
case 49:
//#line 236 "exemploGC.y"
{  System.out.println("\tPOPL %EDX");
  						   System.out.println("\tMOVL %EDX, _"+val_peek(2).sval);
						   System.out.println("\tPUSHL  %EDX");
					        }
break;
case 50:
//#line 240 "exemploGC.y"
{ gcExpArit('+'); }
break;
case 51:
//#line 241 "exemploGC.y"
{ gcExpArit('-'); }
break;
case 52:
//#line 242 "exemploGC.y"
{ gcExpArit('*'); }
break;
case 53:
//#line 243 "exemploGC.y"
{ gcExpArit('/'); }
break;
case 54:
//#line 244 "exemploGC.y"
{ gcExpArit('%'); }
break;
case 55:
//#line 246 "exemploGC.y"
{ gcExpRel('>'); }
break;
case 56:
//#line 247 "exemploGC.y"
{ gcExpRel('<'); }
break;
case 57:
//#line 248 "exemploGC.y"
{ gcExpRel(EQ); }
break;
case 58:
//#line 249 "exemploGC.y"
{ gcExpRel(LEQ); }
break;
case 59:
//#line 250 "exemploGC.y"
{ gcExpRel(GEQ); }
break;
case 60:
//#line 251 "exemploGC.y"
{ gcExpRel(NEQ); }
break;
case 61:
//#line 253 "exemploGC.y"
{ gcExpLog(OR); }
break;
case 62:
//#line 254 "exemploGC.y"
{ gcExpLog(AND); }
break;
case 63:
//#line 255 "exemploGC.y"
{ 
								System.out.println("\tPUSHL _"+val_peek(0).sval);
								System.out.println("\tPUSHL $1");
								gcExpArit('+');
								System.out.println("\tPOPL %EAX");
								System.out.println("\tMOVL %EAX, _" + val_peek(0).sval);
								System.out.println("\tPUSHL _"+val_peek(0).sval);
							}
break;
case 64:
//#line 263 "exemploGC.y"
{
								System.out.println("\tPUSHL _"+val_peek(1).sval);
								System.out.println("\tPUSHL _"+val_peek(1).sval);
								System.out.println("\tPUSHL $1");
								gcExpArit('+');
								System.out.println("\tPOPL %EAX");
								System.out.println("\tMOVL %EAX, _" + val_peek(1).sval);
							}
break;
case 65:
//#line 271 "exemploGC.y"
{ 
								System.out.println("\tPUSHL _"+val_peek(0).sval);
								System.out.println("\tPUSHL $1");
								gcExpArit('-');
								System.out.println("\tPOPL %EAX");
								System.out.println("\tMOVL %EAX, _" + val_peek(0).sval);
								System.out.println("\tPUSHL _"+val_peek(0).sval);
							}
break;
case 66:
//#line 279 "exemploGC.y"
{
								System.out.println("\tPUSHL _"+val_peek(1).sval);
								System.out.println("\tPUSHL _"+val_peek(1).sval);
								System.out.println("\tPUSHL $1");
								gcExpArit('-');
								System.out.println("\tPOPL %EAX");
								System.out.println("\tMOVL %EAX, _" + val_peek(1).sval);
							}
break;
case 67:
//#line 287 "exemploGC.y"
{
								System.out.println("\tPUSHL _" + val_peek(2).sval);
								gcExpArit('+');
								System.out.println("\tPOPL %EAX");
								System.out.println("\tMOVL %EAX, _" + val_peek(2).sval);
								System.out.println("\tPUSHL %EAX");
							}
break;
case 68:
//#line 294 "exemploGC.y"
{	
				pRot.push(proxRot);
				proxRot += 2;
				System.out.println("\tPOPL %EAX");
				System.out.println("\tCMPL $0, %EAX");
				System.out.printf("\tJE rot_%02d\n", pRot.peek());
			}
break;
case 69:
//#line 302 "exemploGC.y"
{
				System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
				System.out.printf("rot_%02d:\n",pRot.peek());
			}
break;
case 70:
//#line 307 "exemploGC.y"
{
				System.out.printf("rot_%02d:\n",pRot.peek()+1);
				pRot.pop();
			}
break;
//#line 1274 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
