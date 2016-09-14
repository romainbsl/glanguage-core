package be.groups.glanguage.glanguage.api.business.analysis.byaccj;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

%%


%class SlangLex
%public
%integer
%eofclose

%{

/* Tokens copy/pasted from SlangTab */
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
public final static short T_ALIAS=267;
public final static short T_APPLIC=268;
public final static short T_BOOLEAN=269;
public final static short T_COMMENTS=270;
public final static short T_DATE=271;
public final static short T_DURATION=272;
public final static short T_ELSE=273;
public final static short T_ELSEIF=274;
public final static short T_END=275;
public final static short T_FALSE=277;
public final static short T_IF=280;
public final static short T_IN=281;
public final static short T_INTEGER=282;
public final static short T_NUMERIC=284;
public final static short T_SEP=287;
public final static short T_STRING=288;
public final static short T_THEN=289;
public final static short T_TIMESPAN=290;
public final static short T_TRUE=292;
public final static short T_UNKNOWN=293;
public final static short T_FORMULA=296;
public final static short T_JANUARY=299;
public final static short T_FEBRUARY=300;
public final static short T_MARCH=301;
public final static short T_APRIL=302;
public final static short T_MAY=303;
public final static short T_JUNE=304;
public final static short T_JULY=305;
public final static short T_AUGUST=306;
public final static short T_SEPTEMBER=307;
public final static short T_OCTOBER=308;
public final static short T_NOVEMBER=309;
public final static short T_DECEMBER=310;
public final static short T_ABS=311;
public final static short T_BANKERS_ROUNDED=312;
public final static short T_CEIL=313;
public final static short T_FLOOR=314;
public final static short T_FORMATDATE=315;
public final static short T_FORMATINTEGER=316;
public final static short T_FORMATNUMERIC=317;
public final static short T_FORMATSTRING=318;
public final static short T_ROUNDED=319;
public final static short T_SIGN=320;
public final static short T_TRUNC=321;
public final static short T_GET=322;
public final static short T_MAX=323;
public final static short T_MIN=324;
public final static short T_SMIN=325;
public final static short T_SMAX=326;
public final static short T_SUBSTRING=327;
public final static short T_MULTIPLY=328;
public final static short T_STRINGITEM=329;
public final static short T_SUM=330;
public final static short T_SUMV=331;
public final static short T_MINUTES=332;
public final static short T_HOURS=333;
public final static short T_DAYS=334;
public final static short T_MONTHS=335;
public final static short T_YEARS=336;
public final static short T_PUT_TEXT=337;
public final static short T_STRINGLENGTH=338;
public final static short V_INTEGER=339;
public final static short V_QUANTITY=340;
public final static short V_DURATION=341;
public final static short V_STRING=342;
public final static short V_IDENT=343;
public final static short T_EOF=344;
public final static short YYERRCODE=256;

	public String yylval;

	private int cptEnd = 0;
	
	public void set_yylval(String val){
    		yylval = val;
	}

	/** 
	 * Initialize the lexer 
	 */	
	public void initializeLex() {
		lineNumber = 1;
	}

	/** 
	  * Set token
	  *
	  * @param t The token to set
	  */
	public void setToken (int t){
		token = t;
	}

	/**
	  * Considers the special character '\' and return converted String
	  * A \" sequence is connverted to "
      *
	  * @param s The String to congvert
	 */
	public String unescape (String s){
	    assert (s != null);
		int i;
        i = 0;
		StringBuilder result = new StringBuilder(s.length());
		while (i < s.length()) {
			if (s.charAt(i) == '\\' && i < s.length() - 1) {
				result.append (s.charAt(i+1));
				i = i + 1;
			}
			else {
				result.append (s.charAt(i));
			}
			i = i + 1;
		}
		return result.toString();
	}

	public String unquotedString (String s){
	    assert (s != null && s.length() >= 2 && s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"');
		return s.substring(1, s.length() - 1);
	}

	/**
	  * Nombre de lignes scannees 
	  */
	public int lineNumber;

	/**
	  * Recognized terminal 
	  */
	public int token;

	/**
	  * Temp String for operations for operation on a String by a function of the scanner
	  */
	private String tmpStr;
	
	public void setFormulaString(String s) {
		try {
			this.zzReader = new StringReader(s);				
		} catch (Exception e) {
			Logger.getLogger(SlangLex.class.getName()).log(Level.SEVERE, null, e);
		}
	}

%}


	/* Keywords */
K_APPLIC 	=[Aa][Pp][Pp][Ll][Ii][Cc][Aa][Bb][Ll][Ee]
K_BOOLEAN 	=[Bb][Oo][Oo][Ll][Ee][Aa][Nn]
K_DATE   	=[Dd][Aa][Tt][Ee]
K_DURATION	=[Dd][Uu][Rr][Aa][Tt][Ii][Oo][Nn]
K_ELSE		=[Ee][Ll][Ss][Ee]
K_ELSEIF	=[Ee][Ll][Ss][Ee][Ii][Ff]
K_END		=[Ee][Nn][Dd]
K_FALSE		=[Ff][Aa][Ll][Ss][Ee]
K_IF		=[Ii][Ff]
K_IN		=[Ii][Nn]
K_INTEGER	=[Ii][Nn][Tt][Ee][Gg][Ee][Rr]
K_NUMERIC	=[Nn][Uu][Mm][Ee][Rr][Ii][Cc]
K_STRING	=[Ss][Tt][Rr][Ii][Nn][Gg]
K_THEN		=[Tt][Hh][Ee][Nn]
K_TIMESPAN	=[Tt][Ii][Mm][Ee][Ss][Pp][Aa][Nn]
K_TRUE		=[Tt][Rr][Uu][Ee]

	/* Object call */
	/* K_APPLICABLE =[Aa][Pp][Pp][Ll][Ii][Cc][Aa][Bb][Ll][Ee] */
	/* existe deja en tant que 'K_APPLIC' */
K_FORMULA	=[Ff][Oo][Rr][Mm][Uu][Ll][Aa]

	/* Meta language */

	/* Month of year */
M_JAN	=[Jj][Aa][Nn]([Uu][Aa][Rr][Yy])?
M_FEB	=[Ff][Ee][Bb]([Rr][Uu][Aa][Rr][Yy])?
M_MAR	=[Mm][Aa][Rr]([Cc][Hh])?
M_APR	=[Aa][Pp][Rr]([Ii][Ll])?
M_MAY	=[Mm][Aa][Yy]
M_JUN	=[Jj][Uu][Nn]([Ee])?
M_JUL	=[Jj][Uu][Ll]([Yy])?
M_AUG	=[Aa][Uu][Gg]([Uu][Ss][Tt])?
M_SEP	=[Ss][Ee][Pp]([Tt][Ee][Mm][Bb][Ee][Rr])?
M_OCT	=[Oo][Cc][Tt]([Oo][Bb][Ee][Rr])?
M_NOV	=[Nn][Oo][Vv]([Ee][Mm][Bb][Ee][Rr])?
M_DEC	=[Dd][Ee][Cc]([Ee][Mm][Bb][Ee][Rr])?

	/* Operators */
K_AND		=[Aa][Nn][Dd]
K_NOT		=[Nn][Oo][Tt]
K_OR		=[Oo][Rr]

	/* Standard functions */
K_ABS		=[Aa][Bb][Ss]
K_BANKERS_ROUNDED =[Bb][Aa][Nn][Kk][Ee][Rr][Ss][_][Rr][Oo][Uu][Nn][Dd][Ee][Dd]
K_CEIL		=[Cc][Ee][Ii][Ll]
K_FLOOR		=[Ff][Ll][Oo][Oo][Rr]
K_FORMATDATE	=[Ff][Oo][Rr][Mm][Aa][Tt][Dd][Aa][Tt][Ee]
K_FORMATINTEGER	=[Ff][Oo][Rr][Mm][Aa][Tt][Ii][Nn][Tt][Ee][Gg][Ee][Rr]
K_FORMATNUMERIC	=[Ff][Oo][Rr][Mm][Aa][Tt][Nn][Uu][Mm][Ee][Rr][Ii][Cc]
K_FORMATSTRING	=[Ff][Oo][Rr][Mm][Aa][Tt][Ss][Tt][Rr][Ii][Nn][Gg]
K_ROUNDED	=[Rr][Oo][Uu][Nn][Dd][Ee][Dd]
K_SIGN		=[Ss][Ii][Gg][Nn]
K_TRUNC		=[Tt][Rr][Uu][Nn][Cc]
K_GET		=[Gg][Ee][Tt]
K_MIN		=[Mm][Ii][Nn]
K_MAX		=[Mm][Aa][Xx]
K_SMIN		=[Ss][Mm][Ii][Nn]
K_SMAX		=[Ss][Mm][Aa][Xx]
K_MULTIPLY	=[Mm][Uu][Ll][Tt][Ii][Pp][Ll][Yy]
K_STRINGITEM	=[Ss][Tt][Rr][Ii][Nn][Gg][Ii][Tt][Ee][Mm]
K_SUBSTRING	=[Ss][Uu][Bb][Ss][Tt][Rr][Ii][Nn][Gg]
K_SUM		=[Ss][Uu][Mm]
K_SUMV		=[Ss][Uu][Mm][Vv]
K_MINUTES	=[Mm][Ii][Nn][Uu][Tt][Ee][Ss]
K_HOURS		=[Hh][Oo][Uu][Rr][Ss]
K_DAYS		=[Dd][Aa][Yy][Ss]
K_MONTHS	=[Mm][Oo][Nn][Tt][Hh][Ss]
K_YEARS		=[Yy][Ee][Aa][Rr][Ss]
K_PUT_TEXT  =[Pp][Uu][Tt][_][Tt][Ee][Xx][Tt]
K_STRINGLENGTH	=[Ss][Tt][Rr][Ii][Nn][Gg][Ll][Ee][Nn][Gg][Tt][Hh]

	/* Types */
X_QUANTITY	=([0-9]+[\.,]|[\.,][0-9]+)[0-9]*([eE][\+\-]?[0-9]+)?
X_INTEGER	=[0-9]+
X_DURATION	=\'([0-9]+y)?([0-9]+m)?[0-9]+d([0-9]+:[0-9]+)?\'
X_STRING	=\"([^\"\\]*|\\.)*\"
X_IDENT		=[a-zA-Z_][a-z0-9A-Z_]*
X_COMM		="--"[^\n]*
X_SPACES	=[ \t\r]+
X_CRLF		="\n"


%%

{X_COMM}		{break;}		/* Comments */
{X_SPACES}		{break;}		/* Blankspaces */
{X_CRLF}		{lineNumber = lineNumber + 1;}		/* Line counter */


	/* Keywords */
{K_AND}			{setToken (T_AND);return token;}
{K_APPLIC}		{setToken (T_APPLIC);return token;}
{K_BOOLEAN}		{setToken (T_BOOLEAN);return token;}
{K_DATE}		{setToken (T_DATE);return token;}
{K_DURATION}		{setToken (T_DURATION);return token;}
{K_ELSE}		{setToken (T_ELSE);return token;}
{K_ELSEIF}		{setToken (T_ELSEIF);return token;}
{K_END}			{setToken (T_END);return token;}
{K_FALSE}		{setToken (T_FALSE);return token;}
{K_IF}			{setToken (T_IF);return token;}
{K_IN}			{setToken (T_IN);return token;}
{K_INTEGER}		{setToken (T_INTEGER);return token;}
{K_NOT}			{setToken (T_NOT);return token;}
{K_NUMERIC}		{setToken (T_NUMERIC);return token;}
{K_OR}			{setToken (T_OR);return token;}
{K_STRING}		{setToken (T_STRING);return token;}
{K_THEN}		{setToken (T_THEN);return token;}
{K_TIMESPAN}		{setToken (T_TIMESPAN);return token;}
{K_TRUE}		{setToken (T_TRUE);return token;}

	/* Object call */
{K_FORMULA}		{setToken (T_FORMULA);return token;}

	/* Meta language */

	/* Month of year */
{M_JAN}		{setToken (T_JANUARY);return token;}
{M_FEB}		{setToken (T_FEBRUARY);return token;}
{M_MAR}		{setToken (T_MARCH);return token;}
{M_APR}		{setToken (T_APRIL);return token;}
{M_MAY}		{setToken (T_MAY);return token;}
{M_JUN}		{setToken (T_JUNE);return token;}
{M_JUL}		{setToken (T_JULY);return token;}
{M_AUG}		{setToken (T_AUGUST);return token;}
{M_SEP}		{setToken (T_SEPTEMBER);return token;}
{M_OCT}		{setToken (T_OCTOBER);return token;}
{M_NOV}		{setToken (T_NOVEMBER);return token;}
{M_DEC}		{setToken (T_DECEMBER);return token;}

	/* Standard functions */
{K_ABS}			{setToken (T_ABS);return token;}
{K_BANKERS_ROUNDED}	{setToken (T_BANKERS_ROUNDED);return token;}
{K_CEIL}		{setToken (T_CEIL);return token;}
{K_FLOOR}		{setToken (T_FLOOR);return token;}
{K_FORMATDATE}		{setToken (T_FORMATDATE);return token;}
{K_FORMATINTEGER}	{setToken (T_FORMATINTEGER);return token;}
{K_FORMATNUMERIC}	{setToken (T_FORMATNUMERIC);return token;}
{K_FORMATSTRING}	{setToken (T_FORMATSTRING);return token;}
{K_ROUNDED}		{setToken (T_ROUNDED);return token;}
{K_SIGN}		{setToken (T_SIGN);return token;}
{K_TRUNC}		{setToken (T_TRUNC);return token;}
{K_GET}			{setToken (T_GET);return token;}
{K_MIN}			{setToken (T_MIN);return token;}
{K_MAX}			{setToken (T_MAX);return token;}
{K_SMIN}		{setToken (T_SMIN);return token;}
{K_SMAX}		{setToken (T_SMAX);return token;}
{K_MULTIPLY}		{setToken (T_MULTIPLY);return token;}
{K_STRINGITEM}		{setToken (T_STRINGITEM);return token;}
{K_SUBSTRING}		{setToken (T_SUBSTRING);return token;}
{K_SUM}			{setToken (T_SUM);return token;}
{K_SUMV}		{setToken (T_SUMV);return token;}
{K_MINUTES}		{setToken (T_MINUTES);return token;}
{K_HOURS}		{setToken (T_HOURS);return token;}
{K_DAYS}		{setToken (T_DAYS);return token;}
{K_MONTHS}		{setToken (T_MONTHS);return token;}
{K_YEARS}		{setToken (T_YEARS);return token;}
{K_PUT_TEXT}	{setToken (T_PUT_TEXT);return token;}
{K_STRINGLENGTH}	{setToken (T_STRINGLENGTH);return token;}

	/* Operators */
"//"		{setToken (T_INTEGER_DIV);return token;}
"\\\\"		{setToken (T_MODULO);return token;}
"<>"		{setToken (T_DIFFERENT);return token;}
"/="		{setToken (T_DIFFERENT);return token;}
"<="		{setToken (T_SMALLER_EQ);return token;}
">="		{setToken (T_GREATER_EQ);return token;}
"+"		{setToken ('+');return token;}
"-"		{setToken ('-');return token;}
"*"		{setToken ('*');return token;}
"/"		{setToken('/');return token;}
"="		{setToken ('=');return token;}
"<"		{setToken ('<');return token;}
">"		{setToken ('>');return token;}

	/* Allowed characters */
"."		{setToken ('.');return token;}
"_"		{setToken ('_');return token;}
":"		{setToken(':');return token;}
"?"		{setToken('?');return token;}
","		{setToken(',');return token;}
";"		{setToken(T_SEP);return token;}
"'"		{setToken('\'');return token;}
"("		{setToken('(');return token;}
")"		{setToken(')');return token;}
"["		{setToken('[');return token;}
"]"		{setToken(']');return token;}
"{"		{setToken('{');return token;}
"}"		{setToken('}');return token;}

	/* Terminals */
{X_QUANTITY}	{
				tmpStr = yytext();
				tmpStr = tmpStr.replace(',', '.');
				set_yylval (tmpStr);
				setToken (V_QUANTITY);
				//System.out.println("x_quantity found");
				return token;
		}
{X_INTEGER}	{
			set_yylval (yytext()); setToken (V_INTEGER);
			//System.out.println("x_integer found");
			return token;
		}
{X_DURATION}	{
			set_yylval (yytext()); setToken (V_DURATION);
			//System.out.println("x_duration found");
			return token;
		}
{X_STRING}	{
			//System.out.println("x_string found ");
			//System.out.println(unescape (unquotedString (yytext())));
			set_yylval (unescape (unquotedString (yytext())));
			setToken (V_STRING);
			Matcher matcher = Pattern.compile(Pattern.quote("\n")).matcher(yytext());
	        int occur = 0;
	        while (matcher.find()) {
	            occur++;
	        }
	        lineNumber = lineNumber + occur;
			//System.out.println("----------------------------- NUMERO DE LIGNE LUE (String): "+lineNumber);
			return token;
		}
		
{X_IDENT}	{
			set_yylval (yytext()); setToken (V_IDENT);
			//System.out.println("v_ident found "+yytext());
			return token;
		}
		
.		{
			setToken (T_UNKNOWN);
			//System.out.println(". found");
			return token;
		}
		
<<EOF>> {
			if(cptEnd==0){
				//System.out.println("eof found");
				setToken(T_EOF);
				cptEnd++;
				return token;
			}else {
				setToken(0);
				return token;
			}
		}