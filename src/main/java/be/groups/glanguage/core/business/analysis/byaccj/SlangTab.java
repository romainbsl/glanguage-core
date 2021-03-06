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






//#line 2 "slang.y"
package be.groups.glanguage.core.business.analysis.byaccj;

import be.groups.glanguage.core.business.action.SemanticalAction;
import be.groups.glanguage.core.business.analysis.IdentifierParameterList;
import be.groups.glanguage.core.business.evaluation.PlanEvaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.parser.ParserInnerError;
import be.groups.glanguage.core.error.parser.ParserUnableToParseTextInnerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
//#line 34 "SlangTab.java"




public class SlangTab
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
//## **user defined:SemanticType
String   yytext;//user variable to return contextual strings
SemanticType yyval; //used to return semantic vals from action routines
SemanticType yylval;//the 'lval' (result) I got from yylex()
SemanticType valstk[] = new SemanticType[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new SemanticType();
  yylval=new SemanticType();
  valptr=-1;
}
final void val_push(SemanticType val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    SemanticType[] newstack = new SemanticType[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final SemanticType val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final SemanticType val_peek(int relative)
{
  return valstk[valptr-relative];
}
final SemanticType dup_yyval(SemanticType val)
{
  return val;
}
//#### end semantic value section ####
public final static short T_OR=257;
public final static short T_AND=258;
public final static short T_DIFFERENT=259;
public final static short T_SMALLER_EQ=260;
public final static short T_GREATER_EQ=261;
public final static short T_INTEGER_DIV=262;
public final static short T_MODULO=263;
public final static short UNARY_OP=264;
public final static short T_NOT=265;
public final static short ATOMIC_CALL=266;
public final static short T_APPLIC=267;
public final static short T_BOOLEAN=268;
public final static short T_DATE=269;
public final static short T_DURATION=270;
public final static short T_ELSE=271;
public final static short T_ELSEIF=272;
public final static short T_END=273;
public final static short T_FALSE=274;
public final static short T_IF=275;
public final static short T_IN=276;
public final static short T_INTEGER=277;
public final static short T_NUMERIC=278;
public final static short T_SEP=279;
public final static short T_STRING=280;
public final static short T_THEN=281;
public final static short T_TIMESPAN=282;
public final static short T_TRUE=283;
public final static short T_UNKNOWN=284;
public final static short T_FORMULA=285;
public final static short T_JANUARY=286;
public final static short T_FEBRUARY=287;
public final static short T_MARCH=288;
public final static short T_APRIL=289;
public final static short T_MAY=290;
public final static short T_JUNE=291;
public final static short T_JULY=292;
public final static short T_AUGUST=293;
public final static short T_SEPTEMBER=294;
public final static short T_OCTOBER=295;
public final static short T_NOVEMBER=296;
public final static short T_DECEMBER=297;
public final static short T_ABS=298;
public final static short T_BANKERS_ROUNDED=299;
public final static short T_CEIL=300;
public final static short T_FLOOR=301;
public final static short T_FORMATDATE=302;
public final static short T_FORMATINTEGER=303;
public final static short T_FORMATNUMERIC=304;
public final static short T_FORMATSTRING=305;
public final static short T_ROUNDED=306;
public final static short T_SIGN=307;
public final static short T_TRUNC=308;
public final static short T_GET=309;
public final static short T_MAX=310;
public final static short T_MIN=311;
public final static short T_SMIN=312;
public final static short T_SMAX=313;
public final static short T_SUBSTRING=314;
public final static short T_MULTIPLY=315;
public final static short T_STRINGITEM=316;
public final static short T_SUM=317;
public final static short T_SUMV=318;
public final static short T_MINUTES=319;
public final static short T_HOURS=320;
public final static short T_DAYS=321;
public final static short T_MONTHS=322;
public final static short T_YEARS=323;
public final static short T_PUT_TEXT=324;
public final static short T_STRINGLENGTH=325;
public final static short V_INTEGER=326;
public final static short V_QUANTITY=327;
public final static short V_DURATION=328;
public final static short V_STRING=329;
public final static short V_IDENT=330;
public final static short T_EOF=331;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
   18,    0,    1,    1,    1,    1,    1,    2,    2,    3,
    3,    4,    4,    5,    5,    5,    5,    5,    5,    5,
    5,    5,    5,    5,    5,    5,    5,    5,    5,    5,
    5,    5,    5,    5,    5,    5,    5,    5,    6,    6,
    6,    6,    6,    6,    6,    6,    6,    7,    8,    8,
    9,    9,    9,    9,    9,    9,    9,    9,    9,    9,
    9,    9,    9,   10,   11,   11,   12,   12,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   14,   14,   15,
   15,   16,   16,   17,   17,   17,
};
final static short yylen[] = {                            2,
    0,    3,    1,    1,    1,    1,    1,    1,    3,    0,
    1,    1,    3,    1,    1,    1,    1,    1,    5,    3,
    2,    2,    2,    2,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    1,    1,
    1,    1,    1,    1,    4,    1,    1,    7,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    0,    3,    3,    3,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    3,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    2,    4,    1,
    1,    5,    6,    5,    4,    2,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    0,    0,    0,    0,    0,   43,    0,
    0,   42,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   39,   40,   44,   41,    0,    0,    0,    0,   14,   46,
   15,   16,   17,   18,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    6,    5,    3,    4,    7,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    2,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   20,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  101,  100,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   67,   68,   50,   49,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   90,    0,   45,   69,   70,   71,   72,   73,   74,
   75,   76,   77,   78,   81,    0,    0,   98,   87,   86,
   88,   89,   80,   83,   79,   84,   85,   91,   92,   93,
   94,   95,   96,   97,   52,   53,   54,   55,   56,   57,
   58,   59,   60,   61,   62,   63,   51,    0,    0,    0,
    0,  102,    0,    0,    0,    0,   19,    0,    0,  103,
   99,   66,    0,    0,   48,    0,  104,
};
final static short yydgoto[] = {                          1,
   79,    0,  116,  117,   48,   49,   50,  155,  218,   51,
  188,   52,   53,  133,  134,   54,  223,    2,
};
final static short yysindex[] = {                         0,
    0,  -39,  -39,  -39,  -39,   27,  -39,  -37,    0,  -39,
  -29,    0,  -21,  -18,   -4,   -3,   -2,   -1,    1,    7,
    9,   10,   18, -185,   19,   20,   22,   23,   24,   25,
   40,   41,   42,   45,   47,   48,   49,   54,   56,   57,
    0,    0,    0,    0,   -6, -309, -270,  958,    0,    0,
    0,    0,    0,    0, -178, -178, -178, -178,  885,  -39,
  835, -229,  -39,  -39,  -39,  -39,  -39,  -39,  -39,  -39,
  -39,  -39,  -39,    0,    0,    0,    0,    0, -314,  -39,
  -39,  -39,  -39,  -39, -230,  -39, -228, -227,  -39,  -39,
  -39,  -39,  -39,  -39,  -39, -265,  -24,  -39,    0,  -39,
  -39,  -39,  -39,  -39,  -39,  -39,  -39,  -39,  -39,  -39,
  -39,  -39,  -39,   61,    0,   63, -174,  -39,   65,   66,
   68,   69,   70,   71,   72,   73,   74,   75,   76,   77,
    0,    0,   78,   79,   80,   81,   82,   84,   85,   86,
   87,   88,   89,   90,   91,   93,   94,   95,   96,   97,
    0,    0,    0,    0, -218,  958,  979, 1019,  -35,  -35,
  -35,  -35,  -35,  -35,   44,   44, -178, -178, -178, -178,
  -39,    0,  923,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -314,  -39,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -24,   98,  -39,
  -39,    0, -153,   79,   99, -181,    0,  958,  873,    0,
    0,    0,  102,  -39,    0,  951,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  126,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  152,    0,    0,  -28,    0,    0,
    0,    0,    0,    0,  252,  400,  425,  456,    0,  109,
    0,    0,  109,  109,  109,  109,  109,  109,  109,  109,
  109,  109,  109,    0,    0,    0,    0,    0,    0,  109,
  109,  109,  109,  109,    0,  109,    0,    0,  109,  109,
  109,  109,  109,  109,  109,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  110,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  177,  101,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -27,  -23,  -26,  538,  650,
  679,  716,  772,  797,  594,  633,  487,  512,  543,  568,
  109,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  109,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  101,    0,    0,    0, -121,    0,    0,
    0,    0,    0,    0,    0, -120,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -38,  153, 1159,    0,    0,  -64,    0,    0,
  -68,    0,    0,    0,  -22,    0,  -79,    0,
};
final static int YYTABLESIZE=1393;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         46,
    7,  151,   60,    3,  131,    4,  110,  108,   98,  109,
   62,  111,   12,   13,   37,  132,   97,   38,   63,  152,
  153,   64,  154,    6,  120,  121,  122,  123,  124,  125,
  126,  127,  128,  129,  130,   65,   66,   67,   68,   96,
   69,  135,  136,  137,  138,  139,   70,  141,   71,   72,
  144,  145,  146,  147,  148,  149,  150,   73,   80,   81,
   99,   82,   83,   84,   85,   46,    7,  205,  206,  207,
  208,  209,  210,  211,  212,  213,  214,  215,  216,   86,
   87,   88,   74,   75,   89,  110,   90,   91,   92,    6,
  111,   76,   77,   93,   78,   94,   95,  114,  119,  140,
  171,  142,  143,  172,   98,  174,  175,  217,  176,  177,
  178,  179,  180,  181,  182,  183,  184,  185,  187,  230,
  189,  190,  191,  186,  192,  193,  194,  195,  196,  197,
  198,  199,  219,  200,  201,  202,  203,  204,  227,  232,
  235,   65,   65,   65,  233,   65,   65,   65,  225,   10,
   11,  106,  105,  226,   47,  231,  237,    0,    0,    0,
   65,   65,   65,  224,    0,    0,   47,   47,   47,    0,
   47,    0,   47,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   47,   47,   47,    0,    0,
    0,    0,   64,   64,   64,    0,   64,    0,   64,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   64,   64,   64,    0,    0,    0,   82,   82,   82,
    0,   82,    0,   82,    0,    5,  112,  113,    0,    8,
   37,   37,    0,   38,    9,   10,   82,   82,   82,    0,
  114,    0,   11,   12,   37,   37,   37,   38,   38,   38,
   12,   13,   37,    0,   37,   38,    0,   38,   13,   14,
   15,   16,   17,   18,   19,   20,   21,   22,   23,   24,
   25,   26,   27,   28,   29,   30,   31,   32,   33,   34,
   35,   36,   37,   38,   39,   40,   41,   42,   43,   44,
   45,    5,   22,   22,   22,    8,   22,    0,   22,    0,
    9,   10,   12,   13,   37,  112,  113,   38,   11,   12,
    0,   22,   22,   22,    0,    0,    0,    0,    0,  114,
    0,    0,    0,    0,   13,   14,   15,   16,   17,   18,
   19,   20,   21,   22,   23,   24,   25,   26,   27,   28,
   29,   30,   31,   32,   33,   34,   35,   36,   37,   38,
   39,   40,   41,   42,   43,   44,   45,   65,   65,   65,
   65,   65,   65,   65,    0,    0,    0,    0,    0,    0,
    0,   65,   65,   65,    0,    0,   65,    0,    0,   65,
    0,   65,   47,   47,   47,   47,   47,   47,   47,    0,
    0,    0,    0,    0,    0,    0,   47,   47,   47,    0,
    0,   47,    0,    0,   47,    0,   47,    0,   64,   64,
   64,   64,   64,   64,   64,    0,    0,    0,    0,    0,
    0,    0,   64,   64,   64,    0,    0,   64,    0,    0,
   64,   65,   64,   82,   82,   82,   82,   82,   82,   82,
   23,   23,   23,    0,   23,    0,   23,   82,   82,   82,
    0,    0,   82,    0,    0,   82,   47,   82,    0,   23,
   23,   23,    0,    0,    0,   21,   21,   21,    0,   21,
    0,   21,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   64,    0,   21,   21,   21,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   24,   24,   24,    0,
   24,    0,   24,    0,    0,    0,    0,   82,   22,   22,
   22,   22,   22,   22,   22,   24,   24,   24,    0,    0,
    0,    0,   22,   22,   22,    0,    0,   25,   25,   25,
   22,   25,   22,   25,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   25,   25,   25,    0,
    0,    0,   26,   26,   26,    0,   26,    0,   26,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   26,   26,   26,    0,    0,    0,    0,   31,    0,
    0,    0,   22,   27,   27,   27,    0,   27,    0,   27,
    0,    0,    0,    0,    0,    0,    0,   31,   31,   31,
    0,    0,   27,   27,   27,    0,    0,    0,   28,   28,
   28,    0,   28,    0,   28,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   28,   28,   28,
    0,    0,    0,    0,   29,    0,   29,    0,   29,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   29,   29,   29,   23,   23,   23,   23,
   23,   23,   23,    0,    0,    0,    0,    0,    0,    0,
   23,   23,   23,   30,    0,   30,    0,   30,   23,    0,
   23,   21,   21,   21,   21,   21,   21,   21,    0,    0,
   32,    0,   30,   30,   30,   21,   21,   21,    0,    0,
    0,    0,    0,   21,    0,   21,    0,    0,    0,   32,
   32,   32,   24,   24,   24,   24,   24,   24,   24,   33,
    0,    0,    0,    0,    0,    0,   24,   24,   24,    0,
   23,    0,    0,    0,   24,    0,   24,    0,   33,   33,
   33,    0,    0,   25,   25,   25,   25,   25,   25,   25,
    0,    0,    0,    0,    0,   21,   34,   25,   25,   25,
    0,    0,    0,    0,    0,   25,    0,   25,   26,   26,
   26,   26,   26,   26,   26,   34,   34,   34,    0,    0,
    0,    0,   26,   26,   26,    0,   24,    0,    0,    0,
   26,    0,   26,    0,   31,   31,   31,   31,   31,   27,
   27,   27,   27,   27,   27,   27,    0,    0,   31,   31,
   31,    0,   35,   27,   27,   27,   31,   25,   31,    0,
    0,   27,    0,   27,   28,   28,   28,   28,   28,   28,
   28,   35,   35,   35,    0,    0,    0,   36,   28,   28,
   28,    0,   26,    0,    0,    0,   28,    0,   28,    0,
   29,   29,   29,   29,   29,    0,   36,   36,   36,    0,
    0,    0,    0,    0,   29,   29,   29,    0,   31,    0,
    0,    0,   29,   27,   29,    0,  110,  108,    0,  109,
    0,  111,    0,    0,    0,    0,    0,    0,    0,   30,
   30,   30,   30,   30,  104,  102,  106,    0,   28,    0,
    0,    0,    0,   30,   30,   30,   32,   32,   32,   32,
   32,   30,    0,   30,  110,  108,    0,  109,    0,  111,
   32,   32,   32,    0,   29,  115,  110,  108,   32,  109,
   32,  111,  104,  102,  106,   33,   33,   33,   33,   33,
    0,    0,    0,    0,  104,  102,  106,    0,    0,   33,
   33,   33,    0,    0,    0,    0,    0,   33,    0,   33,
    0,    0,    0,   30,  110,  108,    0,  109,    0,  111,
    0,    0,   34,   34,   34,   34,   34,    0,    0,    0,
   32,    0,  104,  102,  106,    0,   34,   34,   34,    0,
    0,    0,  110,  108,   34,  109,   34,  111,    0,  110,
  108,    0,  109,    0,  111,    0,    0,    0,    0,   33,
  104,  102,  106,    0,    0,    0,    0,  104,  102,  106,
  110,  108,    0,  109,    0,  111,    0,    0,   35,   35,
   35,   35,   35,    0,    0,    0,    0,    0,  104,  102,
  106,    0,   35,   35,   35,    0,   34,    0,    0,    0,
   35,    0,   35,   36,   36,   36,   36,   36,    0,    0,
  110,  108,    0,  109,    0,  111,    0,   36,   36,   36,
    0,    0,    0,    0,    0,   36,    0,   36,  104,  102,
  106,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  100,  101,  103,  105,  107,  112,  113,    0,    0,
    0,    0,   35,    0,    0,    0,    0,    0,    0,    0,
  114,    0,    0,    0,    0,  118,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   36,    0,  100,
  101,  103,  105,  107,  112,  113,    0,    0,    0,    0,
    0,  100,  101,  103,  105,  107,  112,  113,  114,    0,
    0,    0,    0,  234,    0,    0,    0,    0,    0,    0,
  114,   55,   56,   57,   58,   59,    0,    0,   61,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  100,
  101,  103,  105,  107,  112,  113,    0,    0,    0,    0,
    0,    0,    0,  220,  221,  222,    0,    0,  114,    0,
    0,    0,    0,    0,    0,    0,    0,  100,  101,  103,
  105,  107,  112,  113,  100,  101,  103,  105,  107,  112,
  113,  220,  221,    0,    0,    0,  114,    0,    0,    0,
    0,    0,    0,  114,    0,    0,  101,  103,  105,  107,
  112,  113,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  114,    0,  156,    0,  157,  158,
  159,  160,  161,  162,  163,  164,  165,  166,  167,  168,
  169,  170,    0,    0,    0,    0,  173,  103,  105,  107,
  112,  113,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  114,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  228,  229,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  236,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         39,
   40,  267,   40,   43,  319,   45,   42,   43,  279,   45,
   40,   47,   41,   41,   41,  330,  326,   41,   40,  285,
   45,   40,   47,   63,   63,   64,   65,   66,   67,   68,
   69,   70,   71,   72,   73,   40,   40,   40,   40,   46,
   40,   80,   81,   82,   83,   84,   40,   86,   40,   40,
   89,   90,   91,   92,   93,   94,   95,   40,   40,   40,
  331,   40,   40,   40,   40,   39,   40,  286,  287,  288,
  289,  290,  291,  292,  293,  294,  295,  296,  297,   40,
   40,   40,  268,  269,   40,   42,   40,   40,   40,   63,
   47,  277,  278,   40,  280,   40,   40,  276,  328,  330,
   40,  330,  330,   41,  279,   41,   41,  326,   41,   41,
   41,   41,   41,   41,   41,   41,   41,   41,   40,  273,
   41,   41,   41,   46,   41,   41,   41,   41,   41,   41,
   41,   41,  171,   41,   41,   41,   41,   41,   41,   41,
   39,   41,   42,   43,  326,   45,   46,   47,  187,   41,
   41,  273,  273,  218,    2,  224,  236,   -1,   -1,   -1,
   60,   61,   62,  186,   -1,   -1,   41,   42,   43,   -1,
   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   60,   61,   62,   -1,   -1,
   -1,   -1,   41,   42,   43,   -1,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   60,   61,   62,   -1,   -1,   -1,   41,   42,   43,
   -1,   45,   -1,   47,   -1,  265,  262,  263,   -1,  269,
  257,  258,   -1,  257,  274,  275,   60,   61,   62,   -1,
  276,   -1,  282,  283,  271,  272,  273,  271,  272,  273,
  279,  279,  279,   -1,  281,  279,   -1,  281,  298,  299,
  300,  301,  302,  303,  304,  305,  306,  307,  308,  309,
  310,  311,  312,  313,  314,  315,  316,  317,  318,  319,
  320,  321,  322,  323,  324,  325,  326,  327,  328,  329,
  330,  265,   41,   42,   43,  269,   45,   -1,   47,   -1,
  274,  275,  331,  331,  331,  262,  263,  331,  282,  283,
   -1,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,  276,
   -1,   -1,   -1,   -1,  298,  299,  300,  301,  302,  303,
  304,  305,  306,  307,  308,  309,  310,  311,  312,  313,
  314,  315,  316,  317,  318,  319,  320,  321,  322,  323,
  324,  325,  326,  327,  328,  329,  330,  257,  258,  259,
  260,  261,  262,  263,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  271,  272,  273,   -1,   -1,  276,   -1,   -1,  279,
   -1,  281,  257,  258,  259,  260,  261,  262,  263,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  271,  272,  273,   -1,
   -1,  276,   -1,   -1,  279,   -1,  281,   -1,  257,  258,
  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  271,  272,  273,   -1,   -1,  276,   -1,   -1,
  279,  331,  281,  257,  258,  259,  260,  261,  262,  263,
   41,   42,   43,   -1,   45,   -1,   47,  271,  272,  273,
   -1,   -1,  276,   -1,   -1,  279,  331,  281,   -1,   60,
   61,   62,   -1,   -1,   -1,   41,   42,   43,   -1,   45,
   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  331,   -1,   60,   61,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   41,   42,   43,   -1,
   45,   -1,   47,   -1,   -1,   -1,   -1,  331,  257,  258,
  259,  260,  261,  262,  263,   60,   61,   62,   -1,   -1,
   -1,   -1,  271,  272,  273,   -1,   -1,   41,   42,   43,
  279,   45,  281,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   60,   61,   62,   -1,
   -1,   -1,   41,   42,   43,   -1,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   60,   61,   62,   -1,   -1,   -1,   -1,   41,   -1,
   -1,   -1,  331,   41,   42,   43,   -1,   45,   -1,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,   61,   62,
   -1,   -1,   60,   61,   62,   -1,   -1,   -1,   41,   42,
   43,   -1,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,   61,   62,
   -1,   -1,   -1,   -1,   41,   -1,   43,   -1,   45,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   60,   61,   62,  257,  258,  259,  260,
  261,  262,  263,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  271,  272,  273,   41,   -1,   43,   -1,   45,  279,   -1,
  281,  257,  258,  259,  260,  261,  262,  263,   -1,   -1,
   41,   -1,   60,   61,   62,  271,  272,  273,   -1,   -1,
   -1,   -1,   -1,  279,   -1,  281,   -1,   -1,   -1,   60,
   61,   62,  257,  258,  259,  260,  261,  262,  263,   41,
   -1,   -1,   -1,   -1,   -1,   -1,  271,  272,  273,   -1,
  331,   -1,   -1,   -1,  279,   -1,  281,   -1,   60,   61,
   62,   -1,   -1,  257,  258,  259,  260,  261,  262,  263,
   -1,   -1,   -1,   -1,   -1,  331,   41,  271,  272,  273,
   -1,   -1,   -1,   -1,   -1,  279,   -1,  281,  257,  258,
  259,  260,  261,  262,  263,   60,   61,   62,   -1,   -1,
   -1,   -1,  271,  272,  273,   -1,  331,   -1,   -1,   -1,
  279,   -1,  281,   -1,  257,  258,  259,  260,  261,  257,
  258,  259,  260,  261,  262,  263,   -1,   -1,  271,  272,
  273,   -1,   41,  271,  272,  273,  279,  331,  281,   -1,
   -1,  279,   -1,  281,  257,  258,  259,  260,  261,  262,
  263,   60,   61,   62,   -1,   -1,   -1,   41,  271,  272,
  273,   -1,  331,   -1,   -1,   -1,  279,   -1,  281,   -1,
  257,  258,  259,  260,  261,   -1,   60,   61,   62,   -1,
   -1,   -1,   -1,   -1,  271,  272,  273,   -1,  331,   -1,
   -1,   -1,  279,  331,  281,   -1,   42,   43,   -1,   45,
   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,   60,   61,   62,   -1,  331,   -1,
   -1,   -1,   -1,  271,  272,  273,  257,  258,  259,  260,
  261,  279,   -1,  281,   42,   43,   -1,   45,   -1,   47,
  271,  272,  273,   -1,  331,   41,   42,   43,  279,   45,
  281,   47,   60,   61,   62,  257,  258,  259,  260,  261,
   -1,   -1,   -1,   -1,   60,   61,   62,   -1,   -1,  271,
  272,  273,   -1,   -1,   -1,   -1,   -1,  279,   -1,  281,
   -1,   -1,   -1,  331,   42,   43,   -1,   45,   -1,   47,
   -1,   -1,  257,  258,  259,  260,  261,   -1,   -1,   -1,
  331,   -1,   60,   61,   62,   -1,  271,  272,  273,   -1,
   -1,   -1,   42,   43,  279,   45,  281,   47,   -1,   42,
   43,   -1,   45,   -1,   47,   -1,   -1,   -1,   -1,  331,
   60,   61,   62,   -1,   -1,   -1,   -1,   60,   61,   62,
   42,   43,   -1,   45,   -1,   47,   -1,   -1,  257,  258,
  259,  260,  261,   -1,   -1,   -1,   -1,   -1,   60,   61,
   62,   -1,  271,  272,  273,   -1,  331,   -1,   -1,   -1,
  279,   -1,  281,  257,  258,  259,  260,  261,   -1,   -1,
   42,   43,   -1,   45,   -1,   47,   -1,  271,  272,  273,
   -1,   -1,   -1,   -1,   -1,  279,   -1,  281,   60,   61,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,  263,   -1,   -1,
   -1,   -1,  331,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  276,   -1,   -1,   -1,   -1,  281,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  331,   -1,  257,
  258,  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,  263,  276,   -1,
   -1,   -1,   -1,  281,   -1,   -1,   -1,   -1,   -1,   -1,
  276,    3,    4,    5,    6,    7,   -1,   -1,   10,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,  263,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  271,  272,  273,   -1,   -1,  276,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,  257,  258,  259,  260,  261,  262,
  263,  271,  272,   -1,   -1,   -1,  276,   -1,   -1,   -1,
   -1,   -1,   -1,  276,   -1,   -1,  258,  259,  260,  261,
  262,  263,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  276,   -1,   98,   -1,  100,  101,
  102,  103,  104,  105,  106,  107,  108,  109,  110,  111,
  112,  113,   -1,   -1,   -1,   -1,  118,  259,  260,  261,
  262,  263,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  276,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  220,  221,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  234,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=331;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"'\\''","'('","')'","'*'","'+'",
null,"'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
null,"'<'","'='","'>'","'?'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"T_OR","T_AND","T_DIFFERENT","T_SMALLER_EQ",
"T_GREATER_EQ","T_INTEGER_DIV","T_MODULO","UNARY_OP","T_NOT","ATOMIC_CALL",
"T_APPLIC","T_BOOLEAN","T_DATE","T_DURATION","T_ELSE","T_ELSEIF","T_END",
"T_FALSE","T_IF","T_IN","T_INTEGER","T_NUMERIC","T_SEP","T_STRING","T_THEN",
"T_TIMESPAN","T_TRUE","T_UNKNOWN","T_FORMULA","T_JANUARY","T_FEBRUARY",
"T_MARCH","T_APRIL","T_MAY","T_JUNE","T_JULY","T_AUGUST","T_SEPTEMBER",
"T_OCTOBER","T_NOVEMBER","T_DECEMBER","T_ABS","T_BANKERS_ROUNDED","T_CEIL",
"T_FLOOR","T_FORMATDATE","T_FORMATINTEGER","T_FORMATNUMERIC","T_FORMATSTRING",
"T_ROUNDED","T_SIGN","T_TRUNC","T_GET","T_MAX","T_MIN","T_SMIN","T_SMAX",
"T_SUBSTRING","T_MULTIPLY","T_STRINGITEM","T_SUM","T_SUMV","T_MINUTES",
"T_HOURS","T_DAYS","T_MONTHS","T_YEARS","T_PUT_TEXT","T_STRINGLENGTH",
"V_INTEGER","V_QUANTITY","V_DURATION","V_STRING","V_IDENT","T_EOF",
};
final static String yyrule[] = {
"$accept : initialize",
"$$1 :",
"initialize : $$1 l_expr T_EOF",
"type : T_INTEGER",
"type : T_NUMERIC",
"type : T_DATE",
"type : T_BOOLEAN",
"type : T_STRING",
"identifierList : V_IDENT",
"identifierList : identifierList T_SEP V_IDENT",
"expressionList :",
"expressionList : l_expr",
"l_expr : expr",
"l_expr : l_expr T_SEP expr",
"expr : constant",
"expr : entity",
"expr : objectCall",
"expr : standardFunction",
"expr : instruction",
"expr : expr T_IN '(' expressionList ')'",
"expr : '(' expr ')'",
"expr : T_NOT expr",
"expr : '+' expr",
"expr : '-' expr",
"expr : '?' expr",
"expr : expr '*' expr",
"expr : expr '/' expr",
"expr : expr T_INTEGER_DIV expr",
"expr : expr T_MODULO expr",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : expr '=' expr",
"expr : expr T_DIFFERENT expr",
"expr : expr '<' expr",
"expr : expr T_SMALLER_EQ expr",
"expr : expr '>' expr",
"expr : expr T_GREATER_EQ expr",
"expr : expr T_AND expr",
"expr : expr T_OR expr",
"constant : V_INTEGER",
"constant : V_QUANTITY",
"constant : V_STRING",
"constant : T_TRUE",
"constant : T_FALSE",
"constant : V_DURATION",
"constant : T_TIMESPAN '(' V_DURATION ')'",
"constant : date",
"constant : '?'",
"date : '\\'' V_INTEGER separator month separator V_INTEGER '\\''",
"separator : '/'",
"separator : '-'",
"month : V_INTEGER",
"month : T_JANUARY",
"month : T_FEBRUARY",
"month : T_MARCH",
"month : T_APRIL",
"month : T_MAY",
"month : T_JUNE",
"month : T_JULY",
"month : T_AUGUST",
"month : T_SEPTEMBER",
"month : T_OCTOBER",
"month : T_NOVEMBER",
"month : T_DECEMBER",
"entity : V_IDENT",
"functionalCall :",
"functionalCall : '(' expressionList ')'",
"objectCall : V_IDENT '.' T_APPLIC",
"objectCall : V_IDENT '.' T_FORMULA",
"standardFunction : T_ABS '(' expressionList ')'",
"standardFunction : T_BANKERS_ROUNDED '(' expressionList ')'",
"standardFunction : T_CEIL '(' expressionList ')'",
"standardFunction : T_FLOOR '(' expressionList ')'",
"standardFunction : T_FORMATDATE '(' expressionList ')'",
"standardFunction : T_FORMATINTEGER '(' expressionList ')'",
"standardFunction : T_FORMATNUMERIC '(' expressionList ')'",
"standardFunction : T_FORMATSTRING '(' expressionList ')'",
"standardFunction : T_ROUNDED '(' expressionList ')'",
"standardFunction : T_SIGN '(' expressionList ')'",
"standardFunction : T_STRINGITEM '(' expressionList ')'",
"standardFunction : T_SUBSTRING '(' expressionList ')'",
"standardFunction : T_TRUNC '(' expressionList ')'",
"standardFunction : T_GET type getCall",
"standardFunction : T_MULTIPLY '(' V_IDENT ')'",
"standardFunction : T_SUM '(' V_IDENT ')'",
"standardFunction : T_SUMV '(' V_IDENT ')'",
"standardFunction : T_MIN '(' expressionList ')'",
"standardFunction : T_MAX '(' expressionList ')'",
"standardFunction : T_SMIN '(' expressionList ')'",
"standardFunction : T_SMAX '(' expressionList ')'",
"standardFunction : T_DATE '(' expressionList ')'",
"standardFunction : T_MINUTES '(' expressionList ')'",
"standardFunction : T_HOURS '(' expressionList ')'",
"standardFunction : T_DAYS '(' expressionList ')'",
"standardFunction : T_MONTHS '(' expressionList ')'",
"standardFunction : T_YEARS '(' expressionList ')'",
"standardFunction : T_PUT_TEXT '(' expressionList ')'",
"standardFunction : T_STRINGLENGTH '(' expressionList ')'",
"getCall : anObject functionalCall",
"getCall : getCall '.' anObject functionalCall",
"anObject : V_IDENT",
"anObject : T_MINUTES",
"instruction : T_IF expr T_THEN expr T_END",
"instruction : T_IF expr T_THEN expr else T_END",
"else : T_ELSEIF expr T_THEN expr else",
"else : T_ELSEIF expr T_THEN expr",
"else : T_ELSE expr",
};

//#line 620 "slang.y"
	/** Logger */
	protected static Logger logger = LoggerFactory.getLogger(SlangTab.class);

	/** Scanner */
	public SlangLex scanner;

	/** Semantical actions set for parsing */
	public SemanticalAction aSem;

	/** String represeneting the formula to analyze */
	private String formulaString;

	/** Error flag */
	private boolean error;

	/** Plan evaluator */
    private PlanEvaluator evaluator;

	/**
	 * Set anlyzer semantical actions set
	 * 
	 * @param aSem
	 */
	public void setSemanticalAction (SemanticalAction aSem) {
		this.aSem = aSem;
		assert (this.aSem == aSem);
	}
	
	/**
	 * @param formulaString the formulaString to set
	 */
	public void setFormulaString(String formulaString) {
		this.formulaString = formulaString;
	}

    /**
     * @return the evaluator
     */
    public PlanEvaluator getEvaluator() {
        return evaluator;
    }

    /**
     * @param evaluator the evaluator to set
     */
    public void setEvaluator(PlanEvaluator evaluator) {
        this.evaluator = evaluator;
    }

	/**
	 * Do grammatical analysis of the formulaString by calling inject(String) method 
	 */
	public void analyze() throws GLanguageException {
		if(scanner == null)	scanner = new SlangLex (System.in);
        try{
            error = false;
            this.aSem.beginAnalysis();
            inject(formulaString);
        } catch(GLanguageException e) {
            /* Handle GLanguageException thrown by this method. Just throw it as is. */
            error = true;
            throw e;
        } catch (Exception exp){
            error = true;
            logger.error("analyze()", exp);
            throw new GLanguageException(new ParserUnableToParseTextInnerError(formulaString, "analyse", null, exp));
        }
	}
	
	/**
	 * Does an error occurs during parsing
	 */
	public boolean isError(){
		return error;
	}

	/**
	 * Inject the String {@code formulaString} 
	 *
	 * @param formulaString
	 */
	private void inject(String formulaString) throws GLanguageException {
		int i;
        this.aSem.initialize();
        this.scanner.setFormulaString(formulaString);
        this.scanner.initializeLex();
        try {
            i = yyparse();
            if (i != 0) {
                if (!isError()) yyerror("unknown");
                throw new GLanguageException(new ParserInnerError());
            }
        } catch(GLanguageException e) {
            /* Handle GLanguageException thrown by this method. Just throw it as is. */
            throw e;
        } catch(Exception e) {
            /*
             * WORKAROUND
             * Given that SlangTab is generated, it is not possible to make the "yyparse()" method to handle
             * GLanguageException or any checked exception. Therefore, the methods called by "yyparse()" are forced
             * to throw unchecked exceptions.
             * This method is developed by ourselves and can therefore handle the exception.
             * To handle the exception, first check the type of the cause of the exception if it exists. If it is of
             * type GLanguageException, throw the cause. If not, just throw the exception as is.
             */
            if (e.getCause() != null && e.getCause() instanceof GLanguageException) {
                throw (GLanguageException) e.getCause();
            } else {
                throw e;
            }
        }
	}
	
	/** 
	 * Next terminal, given by scanner 
	 */
	private int yylex(){
		try{
			scanner.yylex();
		}catch(IOException ex){
			logger.error("yylex()", ex);
			return (-1);
		}
		yylval = new SemanticType(scanner.yylval);
		scanner.set_yylval(null);
		return(scanner.token);
	}

	/**
	 * An error occurs during parsing
	 *
	 * @param str A String indicating the type of the error
	 */
	private void yyerror(String str){
		logger.error("ER_GRAMMATICAL_ANALYSIS - " + scanner.yytext() + " - " + scanner.lineNumber + " \nError type : " + str);
	}
//#line 863 "SlangTab.java"
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
//#line 139 "slang.y"
{aSem.initialize(); if (yydebug) debug("initialize");}
break;
case 2:
//#line 141 "slang.y"
{if (yydebug) debug("teof yacc");aSem.endAnalysis(); if (yydebug) debug("endAnalysis"); /*return 0;*/}
break;
case 3:
//#line 147 "slang.y"
{
					yyval.formulaReturnType = FormulaReturnType.INTEGER; if (yydebug) debug("type t_integer");
				}
break;
case 4:
//#line 151 "slang.y"
{
					yyval.formulaReturnType = FormulaReturnType.NUMERIC; if (yydebug) debug("type t_numeric");
				}
break;
case 5:
//#line 155 "slang.y"
{
					yyval.formulaReturnType = FormulaReturnType.DATE; if (yydebug) debug("type t_date");
				}
break;
case 6:
//#line 159 "slang.y"
{
					yyval.formulaReturnType = FormulaReturnType.BOOLEAN; if (yydebug) debug("type t_boolean");
				}
break;
case 7:
//#line 163 "slang.y"
{
					yyval.formulaReturnType = FormulaReturnType.STRING; if (yydebug) debug("type t_string");
				}
break;
case 8:
//#line 172 "slang.y"
{
					yyval.stringList = new LinkedList<String> () ;
                                        yyval.stringList.add (val_peek(0).stringVal);
				}
break;
case 9:
//#line 177 "slang.y"
{
					yyval.stringList = val_peek(2).stringList; 
                                        yyval.stringList.add (val_peek(0).stringVal);
				}
break;
case 10:
//#line 185 "slang.y"
{
					yyval.formulaList = new LinkedList <AbstractFormula>(); if (yydebug) debug("expressionList empty");
				}
break;
case 11:
//#line 189 "slang.y"
{
					yyval.formulaList = val_peek(0).formulaList; if (yydebug) debug("expressionList avec une l_expr");
				}
break;
case 12:
//#line 195 "slang.y"
{
					yyval.formulaList =  new LinkedList <AbstractFormula>(); 
					yyval.formulaList.add (val_peek(0).abstractFormula); if (yydebug) debug("l_expr -> expr");
					aSem.setFormula(val_peek(0).abstractFormula);
				}
break;
case 13:
//#line 201 "slang.y"
{
					yyval.formulaList = val_peek(2).formulaList; 
                                        yyval.formulaList.add (val_peek(0).abstractFormula); if (yydebug) debug("l_expr -> l_expr t_sep expr");
				}
break;
case 14:
//#line 208 "slang.y"
{
					yyval.abstractFormula = val_peek(0).abstractFormula; if (yydebug) debug("constant in expr");
				}
break;
case 15:
//#line 212 "slang.y"
{
					yyval.abstractFormula = val_peek(0).abstractFormula; if (yydebug) debug("entity in expr");
				}
break;
case 16:
//#line 216 "slang.y"
{
					yyval.abstractFormula = val_peek(0).abstractFormula;if (yydebug) debug("objectCall in expr");
				}
break;
case 17:
//#line 220 "slang.y"
{
					yyval.abstractFormula = val_peek(0).abstractFormula; if (yydebug) debug("standardFunction %prec atomic_call in expr "+ATOMIC_CALL);
				}
break;
case 18:
//#line 224 "slang.y"
{
					yyval.abstractFormula = val_peek(0).abstractFormula; if (yydebug) debug("instruction in expr");
				}
break;
case 19:
//#line 228 "slang.y"
{
					yyval.abstractFormula = aSem.inOperation (val_peek(4).abstractFormula, val_peek(1).formulaList, getEvaluator());if (yydebug) debug("expr t_in in");
				}
break;
case 20:
//#line 232 "slang.y"
{
					yyval.abstractFormula = aSem.bracketFormula (val_peek(1).abstractFormula);if (yydebug) debug("parenthese in expr");
				}
break;
case 21:
//#line 236 "slang.y"
{
					yyval.abstractFormula = aSem.unaryOperation (FormulaType.OP_NOT, val_peek(0).abstractFormula, getEvaluator());
				}
break;
case 22:
//#line 240 "slang.y"
{
					yyval.abstractFormula = aSem.unaryOperation (FormulaType.OP_UNARY_PLUS, val_peek(0).abstractFormula, getEvaluator());if (yydebug) debug(" + in expr");
				}
break;
case 23:
//#line 244 "slang.y"
{
					yyval.abstractFormula = aSem.unaryOperation (FormulaType.OP_UNARY_MINUS, val_peek(0).abstractFormula, getEvaluator());if (yydebug) debug("- in expr");
				}
break;
case 24:
//#line 248 "slang.y"
{
					yyval.abstractFormula = aSem.unaryOperation (FormulaType.OP_EXIST, val_peek(0).abstractFormula, getEvaluator());if (yydebug) debug("? in expr");
				}
break;
case 25:
//#line 252 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_MULTIPLY, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());if (yydebug) debug("* in expr");
				}
break;
case 26:
//#line 256 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_DIVIDE, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());if (yydebug) debug("/ in expr");
				}
break;
case 27:
//#line 260 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_INTEGER_DIVISION, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());
				}
break;
case 28:
//#line 264 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_MODULO, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());
				}
break;
case 29:
//#line 268 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_PLUS, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());if (yydebug) debug("+expr in expr");
				}
break;
case 30:
//#line 272 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_MINUS, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());if (yydebug) debug("-expr in expr");
				}
break;
case 31:
//#line 276 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_EQUAL, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());if (yydebug) debug("=expr in expr");
				}
break;
case 32:
//#line 280 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_DIFFERENCE, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());
				}
break;
case 33:
//#line 284 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_SMALLER, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());
				}
break;
case 34:
//#line 288 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_SMALLER_OR_EQUAL, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());
				}
break;
case 35:
//#line 292 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_GREATER, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());
				}
break;
case 36:
//#line 296 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_GREATER_OR_EQUAL, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());
				}
break;
case 37:
//#line 300 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_AND, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());
				}
break;
case 38:
//#line 304 "slang.y"
{
					yyval.abstractFormula = aSem.binaryOperation (FormulaType.OP_OR, val_peek(2).abstractFormula, val_peek(0).abstractFormula, getEvaluator());
				}
break;
case 39:
//#line 311 "slang.y"
{
					yyval.abstractFormula = aSem.terminalIntegerFormula (val_peek(0).stringVal); if (yydebug) debug("v_integer");
				}
break;
case 40:
//#line 315 "slang.y"
{
					yyval.abstractFormula = aSem.terminalNumericFormula (val_peek(0).stringVal); if (yydebug) debug("v_quantity");
				}
break;
case 41:
//#line 319 "slang.y"
{
					yyval.abstractFormula = aSem.terminalStringFormula (val_peek(0).stringVal); if (yydebug) debug("v_string");
				}
break;
case 42:
//#line 323 "slang.y"
{
					yyval.abstractFormula = aSem.terminalBooleanFormula (true);
				}
break;
case 43:
//#line 327 "slang.y"
{
					yyval.abstractFormula = aSem.terminalBooleanFormula (false);

				}
break;
case 44:
//#line 332 "slang.y"
{
					yyval.abstractFormula = aSem.terminalDurationFormula (val_peek(0).stringVal);
				}
break;
case 45:
//#line 336 "slang.y"
{
					yyval.abstractFormula = aSem.terminalDurationFormula (val_peek(1).stringVal);
				}
break;
case 46:
//#line 340 "slang.y"
{
					yyval.abstractFormula = aSem.terminalDateFormula (val_peek(0).dateVal);
				}
break;
case 47:
//#line 344 "slang.y"
{
					yyval.abstractFormula = aSem.emptyFormula();
				}
break;
case 48:
//#line 351 "slang.y"
{
					yyval.dateVal = aSem.createDate (aSem.checkInteger (val_peek(5).stringVal, 1, 31), val_peek(3).integerVal, aSem.checkInteger (val_peek(1).stringVal, 1, -1));
				}
break;
case 51:
//#line 363 "slang.y"
{
					yyval.integerVal = aSem.checkInteger (val_peek(0).stringVal, 1, 12);
				}
break;
case 52:
//#line 367 "slang.y"
{
					yyval.integerVal = 1;
				}
break;
case 53:
//#line 371 "slang.y"
{
					yyval.integerVal = 2;
				}
break;
case 54:
//#line 375 "slang.y"
{
					yyval.integerVal = 3;
				}
break;
case 55:
//#line 379 "slang.y"
{
					yyval.integerVal = 4;
				}
break;
case 56:
//#line 383 "slang.y"
{
					yyval.integerVal = 5;
				}
break;
case 57:
//#line 387 "slang.y"
{
					yyval.integerVal = 6;
				}
break;
case 58:
//#line 391 "slang.y"
{
					yyval.integerVal = 7;
				}
break;
case 59:
//#line 395 "slang.y"
{
					yyval.integerVal = 8;
				}
break;
case 60:
//#line 399 "slang.y"
{
					yyval.integerVal = 9;
				}
break;
case 61:
//#line 403 "slang.y"
{
					yyval.integerVal = 10;
				}
break;
case 62:
//#line 407 "slang.y"
{
					yyval.integerVal = 11;
				}
break;
case 63:
//#line 411 "slang.y"
{
					yyval.integerVal = 12;
				}
break;
case 64:
//#line 418 "slang.y"
{
					yyval.abstractFormula = aSem.referenceFormula (val_peek(0).stringVal, getEvaluator()); if (yydebug) debug("v_ident entity");
				}
break;
case 65:
//#line 425 "slang.y"
{
					yyval.formulaList = new LinkedList<AbstractFormula>();
					if (yydebug) debug("functionalCall empty");
				}
break;
case 66:
//#line 430 "slang.y"
{
					yyval.formulaList = val_peek(1).formulaList; if (yydebug) debug("functionalCall expressionList");
				}
break;
case 67:
//#line 437 "slang.y"
{
					yyval.abstractFormula = aSem.applicabiltyCall (val_peek(2).stringVal, getEvaluator());
				}
break;
case 68:
//#line 441 "slang.y"
{
					yyval.abstractFormula = aSem.formulaCall (val_peek(2).stringVal, getEvaluator());
				}
break;
case 69:
//#line 448 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_ABS, val_peek(1).formulaList, getEvaluator());
				}
break;
case 70:
//#line 452 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_BANKERS_ROUNDED, val_peek(1).formulaList, getEvaluator());
				}
break;
case 71:
//#line 456 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_CEIL, val_peek(1).formulaList, getEvaluator());
				}
break;
case 72:
//#line 460 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_FLOOR, val_peek(1).formulaList, getEvaluator());
				}
break;
case 73:
//#line 464 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_FORMAT_DATE, val_peek(1).formulaList, getEvaluator());
				}
break;
case 74:
//#line 468 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_FORMAT_INTEGER, val_peek(1).formulaList, getEvaluator());
				}
break;
case 75:
//#line 472 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_FORMAT_NUMERIC, val_peek(1).formulaList, getEvaluator());
				}
break;
case 76:
//#line 476 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_FORMAT_STRING, val_peek(1).formulaList, getEvaluator());
				}
break;
case 77:
//#line 480 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_ROUNDED, val_peek(1).formulaList, getEvaluator());
				}
break;
case 78:
//#line 484 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_SIGN, val_peek(1).formulaList, getEvaluator());
				}
break;
case 79:
//#line 488 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_STRING_ITEM, val_peek(1).formulaList, getEvaluator());
				}
break;
case 80:
//#line 492 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_SUBSTRING, val_peek(1).formulaList, getEvaluator());
				}
break;
case 81:
//#line 496 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_TRUNC, val_peek(1).formulaList, getEvaluator());
				}
break;
case 82:
//#line 500 "slang.y"
{
					yyval.abstractFormula = aSem.getFunction (val_peek(1).formulaReturnType, val_peek(0).identifierParameterList, getEvaluator()); if (yydebug) debug("t_get "+val_peek(1).formulaReturnType+" "+val_peek(0).identifierParameterList);
				}
break;
case 83:
//#line 504 "slang.y"
{
					yyval.abstractFormula = aSem.groupFunction (FormulaType.G_MULT, val_peek(1).stringVal, getEvaluator());
				}
break;
case 84:
//#line 508 "slang.y"
{
					yyval.abstractFormula = aSem.groupFunction (FormulaType.G_SUM, val_peek(1).stringVal, getEvaluator());
				}
break;
case 85:
//#line 512 "slang.y"
{
					yyval.abstractFormula = aSem.groupFunction (FormulaType.G_SUMV, val_peek(1).stringVal, getEvaluator());
				}
break;
case 86:
//#line 516 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_MIN, val_peek(1).formulaList, getEvaluator());
				}
break;
case 87:
//#line 520 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_MAX, val_peek(1).formulaList, getEvaluator());
				}
break;
case 88:
//#line 524 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_SMIN, val_peek(1).formulaList, getEvaluator());
				}
break;
case 89:
//#line 528 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_SMAX, val_peek(1).formulaList, getEvaluator());
				}
break;
case 90:
//#line 532 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_DATE, val_peek(1).formulaList, getEvaluator());
				}
break;
case 91:
//#line 536 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_MINUTES, val_peek(1).formulaList, getEvaluator());
				}
break;
case 92:
//#line 540 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_HOURS, val_peek(1).formulaList, getEvaluator());
				}
break;
case 93:
//#line 544 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_DAYS, val_peek(1).formulaList, getEvaluator());
				}
break;
case 94:
//#line 548 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_MONTHS, val_peek(1).formulaList, getEvaluator());
				}
break;
case 95:
//#line 552 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_YEARS, val_peek(1).formulaList, getEvaluator());
				}
break;
case 96:
//#line 556 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_PUT_TEXT, val_peek(1).formulaList, getEvaluator());
				}
break;
case 97:
//#line 560 "slang.y"
{
					yyval.abstractFormula = aSem.standardFunction (FormulaType.F_STRING_LENGTH, val_peek(1).formulaList, getEvaluator());
				}
break;
case 98:
//#line 567 "slang.y"
{
					yyval.identifierParameterList = new IdentifierParameterList(val_peek(1).stringVal, val_peek(0).formulaList); if (yydebug) debug("getCall anObject functionalCall");
				}
break;
case 99:
//#line 572 "slang.y"
{
					yyval.identifierParameterList = val_peek(3).identifierParameterList; yyval.identifierParameterList.add (val_peek(1).stringVal, val_peek(0).formulaList); if (yydebug) debug("getCall. anObject functionalCall");
				}
break;
case 100:
//#line 579 "slang.y"
{
					yyval.stringVal = val_peek(0).stringVal; if (yydebug) debug("v_ident");
				}
break;
case 101:
//#line 583 "slang.y"
{
					yyval.stringVal = "minutes";
				}
break;
case 102:
//#line 590 "slang.y"
{
					if (yydebug) debug("t_if expr t_then expr t_end yacc");
					yyval.abstractFormula = aSem.ifInstruction(val_peek(3).abstractFormula,val_peek(1).abstractFormula,null, getEvaluator());
				}
break;
case 103:
//#line 595 "slang.y"
{
					if (yydebug) debug("t_if expr t_then expr else t_end yacc");
					yyval.abstractFormula = aSem.ifInstruction(val_peek(4).abstractFormula,val_peek(2).abstractFormula,val_peek(1).abstractFormula, getEvaluator());
				}
break;
case 104:
//#line 603 "slang.y"
{
					if (yydebug) debug("t_elseif expr t_then expr else yacc");
					yyval.abstractFormula = aSem.ifInstruction (val_peek(3).abstractFormula, val_peek(1).abstractFormula, val_peek(0).abstractFormula, getEvaluator());
				}
break;
case 105:
//#line 608 "slang.y"
{
					if (yydebug) debug("t_elseif expr t_then expr else yacc");
					yyval.abstractFormula = aSem.ifInstruction (val_peek(2).abstractFormula, val_peek(0).abstractFormula, null, getEvaluator());
				}
break;
case 106:
//#line 613 "slang.y"
{
					if (yydebug) debug("t_else expr yacc");
					yyval.abstractFormula = val_peek(0).abstractFormula;
				}
break;
//#line 1644 "SlangTab.java"
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
public SlangTab()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public SlangTab(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
