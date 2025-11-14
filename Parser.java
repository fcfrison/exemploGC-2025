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
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    3,    0,    5,    7,    4,    2,    2,    8,    1,    1,
    1,    6,    6,    9,    9,    9,   11,    9,    9,   12,
   13,    9,   14,    9,   16,   17,   18,   19,   20,    9,
   21,   22,   23,    9,   24,   15,   15,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   25,   26,   10,
};
final static short yylen[] = {                            2,
    0,    3,    0,    0,    9,    2,    0,    3,    1,    1,
    1,    2,    0,    2,    3,    5,    0,    8,    5,    0,
    0,    7,    0,    7,    0,    0,    0,    0,    0,   16,
    0,    0,    0,   11,    0,    3,    0,    1,    1,    1,
    1,    3,    2,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    2,    2,    2,
    2,    3,    0,    0,    7,
};
final static short yydefred[] = {                         1,
    0,    0,    9,   10,   11,    0,    0,    0,    0,    0,
    2,    6,    8,    0,    0,    3,    0,   13,    0,    0,
   38,    0,    0,    0,   20,   39,   40,    0,    0,    0,
    0,    0,   13,    0,   12,    0,   59,   61,    0,    0,
    0,    0,    0,    0,   58,   60,    0,   43,    0,    0,
    5,    0,    0,    0,    0,    0,    0,   63,    0,    0,
    0,    0,    0,    0,    0,   14,    0,    0,    0,    0,
    0,    0,    0,    0,   42,   15,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   47,   48,   49,
    0,    0,    0,    0,    0,    0,   25,    0,   19,   16,
    0,    0,   21,   31,    0,   64,    0,    0,    0,    0,
    0,    0,    0,   35,   24,   22,   32,   26,    0,   18,
    0,   13,    0,   36,    0,    0,    0,   27,   34,    0,
   28,   13,    0,    0,   30,
};
final static short yydgoto[] = {                          1,
    6,    7,    2,   11,   17,   19,   34,    8,   35,   36,
   93,   44,  109,   94,  115,  105,  123,  130,  132,  134,
  110,  122,  127,  121,   83,  112,
};
final static short yysindex[] = {                         0,
    0, -248,    0,    0,    0, -255, -257, -248,  -51, -250,
    0,    0,    0,  -22,  -17,    0,  -94,    0,    5,  -60,
    0,   -3,    3,    8,    0,    0,    0, -218, -217,    9,
   31,   31,    0,  -75,    0,   63,    0,    0,   31,   31,
 -206, -208,   31,   15,    0,    0,   20,    0,   90,  -33,
    0,   31,   31,   31,   31,   31,   31,    0,   31,   31,
   31,   31,   31,   31,   31,    0,  281,  281,   17,   18,
  281,   31,    2,   97,    0,    0,   -1,   -1,   -1,   -1,
  409,  385,   31,   -1,   -1,   10,   10,    0,    0,    0,
    4,    6,   12,   21,  104,   25,    0,  126,    0,    0,
   31,    5,    0,    0,   31,    0,  133, -201,    5,  -55,
  140,   31,   11,    0,    0,    0,    0,    0,  281,    0,
    5,    0,   31,    0,    5,  167,  -56,    0,    0,  -50,
    0,    0,    5,  -53,    0,
};
final static short yyrindex[] = {                         0,
    0, -189,    0,    0,    0,    0,    0, -189,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -48,   39,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -28,  -26,    0,   34,
   42,    0,    0,    0,    0,    0,  327,  334,  420,  426,
  -36,  -38,    0,  434,  440,  288,  414,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -14,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -24,    0,
    0,    0,    0,    0,  -40,    0,    0,    0,    0,    0,
    0,    0,  -37,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   67,    0,    0,    0,  -29,    0,    0,  -93,  687,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=810;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         31,
   40,    9,   56,   50,   57,   10,   32,   13,  108,    3,
    4,    5,   62,   14,   44,  116,   65,   15,   37,   56,
   56,   57,   57,   16,   56,   37,   57,  124,   18,   62,
   62,   44,   44,   65,   65,   65,   41,   31,   45,   46,
   63,   61,   42,   62,   32,   64,   65,   43,   47,   51,
   69,   63,   31,   70,   72,  101,   64,   91,   92,   32,
   96,  102,   99,   31,  100,  104,  114,  117,  129,  120,
   32,  135,  131,    7,   12,   41,    4,   17,   73,   41,
   41,   41,   23,   41,   33,   41,    0,   29,    0,   33,
    0,   76,  125,    0,    0,    0,   41,   41,   41,   65,
   41,   41,  133,    0,   63,   61,    0,   62,   37,   64,
   37,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   66,   60,    0,   59,   58,   65,   33,    0,    0,
   75,   63,   61,   65,   62,    0,   64,    0,   63,   61,
   65,   62,    0,   64,  103,   63,   61,    0,   62,   60,
   64,   59,   58,    0,    0,   97,   60,    0,   59,   58,
    0,    0,   65,   60,    0,   59,   58,   63,   61,   65,
   62,    0,   64,  113,   63,   61,   65,   62,    0,   64,
    0,   63,   61,  106,   62,   60,   64,   59,   58,    0,
    0,    0,   60,    0,   59,   58,    0,    0,  118,   60,
    0,   59,   58,   65,    0,    0,    0,  128,   63,   61,
    0,   62,    0,   64,    0,    0,    0,   37,   38,   39,
    0,    0,    0,   20,    0,    0,   60,   21,   59,   58,
    0,   22,   23,   24,    0,   25,   26,   27,   56,   57,
   57,    0,   37,    0,   28,   29,   37,   30,    0,    0,
   37,   37,   37,    0,   37,   37,   37,    0,    0,    0,
    0,   20,    0,   37,   37,   21,   37,    0,    0,   22,
   23,   24,    0,   25,   26,   27,   20,    0,    0,    0,
   21,    0,   28,   29,    0,   30,    0,   20,    0,   26,
   27,   21,    0,    0,    0,    0,    0,   28,   29,    0,
   26,   27,    0,    0,    0,    0,    0,    0,   28,   29,
   41,   41,   41,   41,   41,   41,    0,   65,    0,    0,
    0,    0,   63,   61,    0,   62,    0,   64,   45,    0,
   45,    0,   45,    0,   52,   53,   54,   55,   56,   57,
   60,    0,   59,   58,    0,   45,   45,   45,    0,   45,
   45,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   52,   53,   54,   55,   56,   57,   52,   52,   53,
   54,   55,   56,   57,   53,   52,   53,   54,   55,   56,
   57,    0,    0,    0,   52,   52,   52,    0,   52,   52,
    0,   53,   53,   53,    0,   53,   53,   52,   53,   54,
   55,   56,   57,    0,   52,   53,   54,   55,   56,   57,
    0,   52,   53,   54,   55,   56,   57,    0,    0,    0,
    0,   65,    0,    0,    0,    0,   63,   61,    0,   62,
    0,   64,    0,    0,    0,    0,    0,    0,   52,   53,
   54,   55,   56,   57,   60,   65,   59,    0,    0,    0,
   63,   61,    0,   62,   46,   64,   46,    0,   46,    0,
   54,    0,    0,    0,    0,    0,   55,    0,   60,    0,
   59,   46,   46,   46,   50,   46,   46,   54,   54,   54,
   51,   54,   54,   55,   55,   55,    0,   55,   55,    0,
    0,   50,   50,   50,    0,   50,   50,   51,   51,   51,
    0,   51,   51,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   52,   53,   54,   55,   56,   57,    0,   45,
   45,   45,   45,   45,   45,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   52,   52,
   52,   52,   52,   52,    0,   53,   53,   53,   53,   53,
   53,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   52,   53,   54,   55,
   56,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   52,   53,   54,   55,    0,   46,   46,   46,   46,   46,
   46,   54,   54,   54,   54,   54,   54,   55,   55,   55,
   55,   55,   55,    0,    0,   50,   50,   50,   50,   50,
   50,   51,   51,   51,   51,   51,   51,   48,   49,    0,
    0,    0,    0,    0,    0,   67,   68,    0,    0,   71,
    0,    0,    0,   74,    0,    0,    0,    0,   77,   78,
   79,   80,   81,   82,    0,   84,   85,   86,   87,   88,
   89,   90,    0,    0,    0,    0,    0,    0,   95,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   98,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  107,    0,    0,
    0,  111,    0,    0,    0,    0,    0,    0,  119,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  126,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   61,  257,   41,   33,   41,  263,   40,   59,  102,  258,
  259,  260,   41,  264,   41,  109,   41,   40,   33,   58,
   59,   58,   59,   41,   63,   40,   63,  121,  123,   58,
   59,   58,   59,   58,   59,   37,   40,   33,  257,  257,
   42,   43,   40,   45,   40,   47,   37,   40,   40,  125,
  257,   42,   33,  262,   40,   44,   47,   41,   41,   40,
   59,   41,   59,   33,   59,   41,  268,  123,  125,   59,
   40,  125,  123,  263,    8,   37,  125,   44,   59,   41,
   42,   43,   41,   45,  125,   47,   -1,  125,   -1,  123,
   -1,  125,  122,   -1,   -1,   -1,   58,   59,   60,   37,
   62,   63,  132,   -1,   42,   43,   -1,   45,  123,   47,
  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   59,   60,   -1,   62,   63,   37,  123,   -1,   -1,
   41,   42,   43,   37,   45,   -1,   47,   -1,   42,   43,
   37,   45,   -1,   47,   41,   42,   43,   -1,   45,   60,
   47,   62,   63,   -1,   -1,   59,   60,   -1,   62,   63,
   -1,   -1,   37,   60,   -1,   62,   63,   42,   43,   37,
   45,   -1,   47,   41,   42,   43,   37,   45,   -1,   47,
   -1,   42,   43,   58,   45,   60,   47,   62,   63,   -1,
   -1,   -1,   60,   -1,   62,   63,   -1,   -1,   59,   60,
   -1,   62,   63,   37,   -1,   -1,   -1,   41,   42,   43,
   -1,   45,   -1,   47,   -1,   -1,   -1,  278,  279,  280,
   -1,   -1,   -1,  257,   -1,   -1,   60,  261,   62,   63,
   -1,  265,  266,  267,   -1,  269,  270,  271,  277,  276,
  277,   -1,  257,   -1,  278,  279,  261,  281,   -1,   -1,
  265,  266,  267,   -1,  269,  270,  271,   -1,   -1,   -1,
   -1,  257,   -1,  278,  279,  261,  281,   -1,   -1,  265,
  266,  267,   -1,  269,  270,  271,  257,   -1,   -1,   -1,
  261,   -1,  278,  279,   -1,  281,   -1,  257,   -1,  270,
  271,  261,   -1,   -1,   -1,   -1,   -1,  278,  279,   -1,
  270,  271,   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,
  272,  273,  274,  275,  276,  277,   -1,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   -1,   47,   41,   -1,
   43,   -1,   45,   -1,  272,  273,  274,  275,  276,  277,
   60,   -1,   62,   63,   -1,   58,   59,   60,   -1,   62,
   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  272,  273,  274,  275,  276,  277,   41,  272,  273,
  274,  275,  276,  277,   41,  272,  273,  274,  275,  276,
  277,   -1,   -1,   -1,   58,   59,   60,   -1,   62,   63,
   -1,   58,   59,   60,   -1,   62,   63,  272,  273,  274,
  275,  276,  277,   -1,  272,  273,  274,  275,  276,  277,
   -1,  272,  273,  274,  275,  276,  277,   -1,   -1,   -1,
   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,
  274,  275,  276,  277,   60,   37,   62,   -1,   -1,   -1,
   42,   43,   -1,   45,   41,   47,   43,   -1,   45,   -1,
   41,   -1,   -1,   -1,   -1,   -1,   41,   -1,   60,   -1,
   62,   58,   59,   60,   41,   62,   63,   58,   59,   60,
   41,   62,   63,   58,   59,   60,   -1,   62,   63,   -1,
   -1,   58,   59,   60,   -1,   62,   63,   58,   59,   60,
   -1,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  272,  273,  274,  275,  276,  277,   -1,  272,
  273,  274,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,
  274,  275,  276,  277,   -1,  272,  273,  274,  275,  276,
  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,  274,  275,
  276,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  272,  273,  274,  275,   -1,  272,  273,  274,  275,  276,
  277,  272,  273,  274,  275,  276,  277,  272,  273,  274,
  275,  276,  277,   -1,   -1,  272,  273,  274,  275,  276,
  277,  272,  273,  274,  275,  276,  277,   31,   32,   -1,
   -1,   -1,   -1,   -1,   -1,   39,   40,   -1,   -1,   43,
   -1,   -1,   -1,   47,   -1,   -1,   -1,   -1,   52,   53,
   54,   55,   56,   57,   -1,   59,   60,   61,   62,   63,
   64,   65,   -1,   -1,   -1,   -1,   -1,   -1,   72,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   83,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  101,   -1,   -1,
   -1,  105,   -1,   -1,   -1,   -1,   -1,   -1,  112,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=281;
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
"GEQ","NEQ","AND","OR","MAISMAIS","MENOSMENOS","MAISIGUAL","FOR",
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
"$$10 :",
"$$11 :",
"$$12 :",
"cmd : FOR '(' exp ';' $$8 exp ';' $$9 exp ')' $$10 '{' $$11 lcmd $$12 '}'",
"$$13 :",
"$$14 :",
"$$15 :",
"cmd : FOR '(' ';' ';' ')' $$13 '{' $$14 lcmd $$15 '}'",
"$$16 :",
"restoIf : ELSE $$16 cmd",
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
"$$17 :",
"$$18 :",
"exp : exp '?' $$17 exp ':' $$18 exp",
};

//#line 266 "exemploGC.y"

  private Yylex lexer;

  private TabSimb ts = new TabSimb();

  private int strCount = 0;
  private ArrayList<String> strTab = new ArrayList<String>();

  private Stack<Integer> pRot = new Stack<Integer>();
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
   
//#line 710 "Parser.java"
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
					proxRot += 2;
					System.out.printf("rot_%02d:\n",pRot.peek());
				  }
break;
case 21:
//#line 97 "exemploGC.y"
{
							System.out.println("\tPOPL %EAX   # desvia se falso...");
							System.out.println("\tCMPL $0, %EAX");
							System.out.printf("\tJE rot_%02d\n", (int)pRot.peek()+1);
						}
break;
case 22:
//#line 102 "exemploGC.y"
{
				  			System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRot.peek());
							System.out.printf("rot_%02d:\n",(int)pRot.peek()+1);
							pRot.pop();
						}
break;
case 23:
//#line 108 "exemploGC.y"
{	
				pRot.push(proxRot);
				proxRot += 2;
				System.out.println("\tPOPL %EAX\n");
				System.out.println("\tCMPL $0, %EAX\n");
				System.out.printf("\tJE rot_%02d\n", pRot.peek());
			}
break;
case 24:
//#line 117 "exemploGC.y"
{
						System.out.printf("rot_%02d:\n",pRot.peek()+1);
						pRot.pop();
					}
break;
case 25:
//#line 122 "exemploGC.y"
{
				pRot.push(proxRot);
				proxRot +=4;
				System.out.printf("rot_%02d:\n", pRot.peek());
			}
break;
case 26:
//#line 127 "exemploGC.y"
{
				System.out.println("\tPOPL %EAX");
				System.out.println("\tCMPL $0 , %EAX");
				System.out.printf("\tJE rot_%02d\n", pRot.peek() + 3);
				System.out.printf("\tJMP rot_%02d\n", pRot.peek() + 1);
				System.out.printf("rot_%02d:\n", pRot.peek() + 2);
			}
break;
case 27:
//#line 134 "exemploGC.y"
{
				System.out.printf("\tJMP rot_%02d\n", pRot.peek());
			}
break;
case 28:
//#line 137 "exemploGC.y"
{
						System.out.printf("rot_%02d:\n", pRot.peek() + 1);
					}
break;
case 29:
//#line 141 "exemploGC.y"
{
						System.out.printf("\tJMP rot_%02d\n", pRot.peek() + 2);
						System.out.printf("rot_%02d:\n", pRot.peek() + 3);
						pRot.pop();
					}
break;
case 31:
//#line 148 "exemploGC.y"
{
                pRot.push(proxRot);
                proxRot += 2;
                System.out.printf("rot_%02d:\n", pRot.peek());
                System.out.printf("\tJMP rot_%02d\n", pRot.peek() + 1);
            }
break;
case 32:
//#line 155 "exemploGC.y"
{ System.out.printf("rot_%02d:\n", pRot.peek() + 1); }
break;
case 33:
//#line 157 "exemploGC.y"
{
				System.out.printf("\tJMP rot_%02d\n", pRot.peek());
				pRot.pop();
			}
break;
case 35:
//#line 166 "exemploGC.y"
{
				System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
				System.out.printf("rot_%02d:\n",pRot.peek());
	
			}
break;
case 37:
//#line 174 "exemploGC.y"
{
		    System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
				System.out.printf("rot_%02d:\n",pRot.peek());
				}
break;
case 38:
//#line 181 "exemploGC.y"
{ System.out.println("\tPUSHL $"+val_peek(0).sval); }
break;
case 39:
//#line 182 "exemploGC.y"
{ System.out.println("\tPUSHL $1"); }
break;
case 40:
//#line 183 "exemploGC.y"
{ System.out.println("\tPUSHL $0"); }
break;
case 41:
//#line 184 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(0).sval); }
break;
case 43:
//#line 186 "exemploGC.y"
{ gcExpNot(); }
break;
case 44:
//#line 187 "exemploGC.y"
{  System.out.println("\tPOPL %EDX");
  						   System.out.println("\tMOVL %EDX, _"+val_peek(2).sval);
						   System.out.println("\tPUSHL  %EDX");
					        }
break;
case 45:
//#line 191 "exemploGC.y"
{ gcExpArit('+'); }
break;
case 46:
//#line 192 "exemploGC.y"
{ gcExpArit('-'); }
break;
case 47:
//#line 193 "exemploGC.y"
{ gcExpArit('*'); }
break;
case 48:
//#line 194 "exemploGC.y"
{ gcExpArit('/'); }
break;
case 49:
//#line 195 "exemploGC.y"
{ gcExpArit('%'); }
break;
case 50:
//#line 197 "exemploGC.y"
{ gcExpRel('>'); }
break;
case 51:
//#line 198 "exemploGC.y"
{ gcExpRel('<'); }
break;
case 52:
//#line 199 "exemploGC.y"
{ gcExpRel(EQ); }
break;
case 53:
//#line 200 "exemploGC.y"
{ gcExpRel(LEQ); }
break;
case 54:
//#line 201 "exemploGC.y"
{ gcExpRel(GEQ); }
break;
case 55:
//#line 202 "exemploGC.y"
{ gcExpRel(NEQ); }
break;
case 56:
//#line 204 "exemploGC.y"
{ gcExpLog(OR); }
break;
case 57:
//#line 205 "exemploGC.y"
{ gcExpLog(AND); }
break;
case 58:
//#line 206 "exemploGC.y"
{ 
								System.out.println("\tPUSHL _"+val_peek(0).sval);
								System.out.println("\tPUSHL $1");
								gcExpArit('+');
								System.out.println("\tPOPL %EAX");
								System.out.println("\tMOVL %EAX, _" + val_peek(0).sval);
								System.out.println("\tPUSHL _"+val_peek(0).sval);
							}
break;
case 59:
//#line 214 "exemploGC.y"
{
								System.out.println("\tPUSHL _"+val_peek(1).sval);
								System.out.println("\tPUSHL _"+val_peek(1).sval);
								System.out.println("\tPUSHL $1");
								gcExpArit('+');
								System.out.println("\tPOPL %EAX");
								System.out.println("\tMOVL %EAX, _" + val_peek(1).sval);
							}
break;
case 60:
//#line 222 "exemploGC.y"
{ 
								System.out.println("\tPUSHL _"+val_peek(0).sval);
								System.out.println("\tPUSHL $1");
								gcExpArit('-');
								System.out.println("\tPOPL %EAX");
								System.out.println("\tMOVL %EAX, _" + val_peek(0).sval);
								System.out.println("\tPUSHL _"+val_peek(0).sval);
							}
break;
case 61:
//#line 230 "exemploGC.y"
{
								System.out.println("\tPUSHL _"+val_peek(1).sval);
								System.out.println("\tPUSHL _"+val_peek(1).sval);
								System.out.println("\tPUSHL $1");
								gcExpArit('-');
								System.out.println("\tPOPL %EAX");
								System.out.println("\tMOVL %EAX, _" + val_peek(1).sval);
							}
break;
case 62:
//#line 238 "exemploGC.y"
{
								System.out.println("\tPUSHL _" + val_peek(2).sval);
								gcExpArit('+');
								System.out.println("\tPOPL %EAX");
								System.out.println("\tMOVL %EAX, _" + val_peek(2).sval);
								System.out.println("\tPUSHL %EAX");
							}
break;
case 63:
//#line 245 "exemploGC.y"
{	
				pRot.push(proxRot);
				proxRot += 2;
				System.out.println("\tPOPL %EAX");
				System.out.println("\tCMPL $0, %EAX");
				System.out.printf("\tJE rot_%02d\n", pRot.peek());
			}
break;
case 64:
//#line 253 "exemploGC.y"
{
				System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
				System.out.printf("rot_%02d:\n",pRot.peek());
			}
break;
case 65:
//#line 258 "exemploGC.y"
{
				System.out.printf("rot_%02d:\n",pRot.peek()+1);
				pRot.pop();
			}
break;
//#line 1210 "Parser.java"
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
