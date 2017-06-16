package be.groups.glanguage.glanguage.api.business.analysis.byaccj;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import be.groups.glanguage.glanguage.api.business.analysis.byaccj.SlangTab;

%%


%class SlangLex
%public
%integer
%eofclose

%{
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
	  * @param s The String to convert
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
	  * Number of scanned lines 
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
X_DURATION	=\'P([0-9]+Y)?([0-9]+M)?([0-9]+D)?(T([0-9]+H)?([0-9]+M)?([0-9]+(.[0-9]+)?S)?)?\'
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
{K_AND}			{setToken (SlangTab.T_AND);return token;}
{K_APPLIC}		{setToken (SlangTab.T_APPLIC);return token;}
{K_BOOLEAN}		{setToken (SlangTab.T_BOOLEAN);return token;}
{K_DATE}		{setToken (SlangTab.T_DATE);return token;}
{K_DURATION}	{setToken (SlangTab.T_DURATION);return token;}
{K_ELSE}		{setToken (SlangTab.T_ELSE);return token;}
{K_ELSEIF}		{setToken (SlangTab.T_ELSEIF);return token;}
{K_END}			{setToken (SlangTab.T_END);return token;}
{K_FALSE}		{setToken (SlangTab.T_FALSE);return token;}
{K_IF}			{setToken (SlangTab.T_IF);return token;}
{K_IN}			{setToken (SlangTab.T_IN);return token;}
{K_INTEGER}		{setToken (SlangTab.T_INTEGER);return token;}
{K_NOT}			{setToken (SlangTab.T_NOT);return token;}
{K_NUMERIC}		{setToken (SlangTab.T_NUMERIC);return token;}
{K_OR}			{setToken (SlangTab.T_OR);return token;}
{K_STRING}		{setToken (SlangTab.T_STRING);return token;}
{K_THEN}		{setToken (SlangTab.T_THEN);return token;}
{K_TIMESPAN}	{setToken (SlangTab.T_TIMESPAN);return token;}
{K_TRUE}		{setToken (SlangTab.T_TRUE);return token;}

	/* Object call */
{K_FORMULA}		{setToken (SlangTab.T_FORMULA);return token;}

	/* Meta language */

	/* Month of year */
{M_JAN}		{setToken (SlangTab.T_JANUARY);return token;}
{M_FEB}		{setToken (SlangTab.T_FEBRUARY);return token;}
{M_MAR}		{setToken (SlangTab.T_MARCH);return token;}
{M_APR}		{setToken (SlangTab.T_APRIL);return token;}
{M_MAY}		{setToken (SlangTab.T_MAY);return token;}
{M_JUN}		{setToken (SlangTab.T_JUNE);return token;}
{M_JUL}		{setToken (SlangTab.T_JULY);return token;}
{M_AUG}		{setToken (SlangTab.T_AUGUST);return token;}
{M_SEP}		{setToken (SlangTab.T_SEPTEMBER);return token;}
{M_OCT}		{setToken (SlangTab.T_OCTOBER);return token;}
{M_NOV}		{setToken (SlangTab.T_NOVEMBER);return token;}
{M_DEC}		{setToken (SlangTab.T_DECEMBER);return token;}

	/* Standard functions */
{K_ABS}				{setToken (SlangTab.T_ABS);return token;}
{K_BANKERS_ROUNDED}	{setToken (SlangTab.T_BANKERS_ROUNDED);return token;}
{K_CEIL}			{setToken (SlangTab.T_CEIL);return token;}
{K_FLOOR}			{setToken (SlangTab.T_FLOOR);return token;}
{K_FORMATDATE}		{setToken (SlangTab.T_FORMATDATE);return token;}
{K_FORMATINTEGER}	{setToken (SlangTab.T_FORMATINTEGER);return token;}
{K_FORMATNUMERIC}	{setToken (SlangTab.T_FORMATNUMERIC);return token;}
{K_FORMATSTRING}	{setToken (SlangTab.T_FORMATSTRING);return token;}
{K_ROUNDED}			{setToken (SlangTab.T_ROUNDED);return token;}
{K_SIGN}			{setToken (SlangTab.T_SIGN);return token;}
{K_TRUNC}			{setToken (SlangTab.T_TRUNC);return token;}
{K_GET}				{setToken (SlangTab.T_GET);return token;}
{K_MIN}				{setToken (SlangTab.T_MIN);return token;}
{K_MAX}				{setToken (SlangTab.T_MAX);return token;}
{K_SMIN}			{setToken (SlangTab.T_SMIN);return token;}
{K_SMAX}			{setToken (SlangTab.T_SMAX);return token;}
{K_MULTIPLY}		{setToken (SlangTab.T_MULTIPLY);return token;}
{K_STRINGITEM}		{setToken (SlangTab.T_STRINGITEM);return token;}
{K_SUBSTRING}		{setToken (SlangTab.T_SUBSTRING);return token;}
{K_SUM}				{setToken (SlangTab.T_SUM);return token;}
{K_SUMV}			{setToken (SlangTab.T_SUMV);return token;}
{K_MINUTES}			{setToken (SlangTab.T_MINUTES);return token;}
{K_HOURS}			{setToken (SlangTab.T_HOURS);return token;}
{K_DAYS}			{setToken (SlangTab.T_DAYS);return token;}
{K_MONTHS}			{setToken (SlangTab.T_MONTHS);return token;}
{K_YEARS}			{setToken (SlangTab.T_YEARS);return token;}
{K_PUT_TEXT}		{setToken (SlangTab.T_PUT_TEXT);return token;}
{K_STRINGLENGTH}	{setToken (SlangTab.T_STRINGLENGTH);return token;}

	/* Operators */
"//"	{setToken (SlangTab.T_INTEGER_DIV);return token;}
"\\\\"	{setToken (SlangTab.T_MODULO);return token;}
"<>"	{setToken (SlangTab.T_DIFFERENT);return token;}
"/="	{setToken (SlangTab.T_DIFFERENT);return token;}
"<="	{setToken (SlangTab.T_SMALLER_EQ);return token;}
">="	{setToken (SlangTab.T_GREATER_EQ);return token;}
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
";"		{setToken(SlangTab.T_SEP);return token;}
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
				setToken (SlangTab.V_QUANTITY);
				//System.out.println("x_quantity found");
				return token;
		}
{X_INTEGER}	{
			set_yylval (yytext()); setToken (SlangTab.V_INTEGER);
			//System.out.println("x_integer found");
			return token;
		}
{X_DURATION}	{
			set_yylval (yytext()); setToken (SlangTab.V_DURATION);
			//System.out.println("x_duration found");
			return token;
		}
{X_STRING}	{
			//System.out.println("x_string found ");
			//System.out.println(unescape (unquotedString (yytext())));
			set_yylval (unescape (unquotedString (yytext())));
			setToken (SlangTab.V_STRING);
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
			set_yylval (yytext()); setToken (SlangTab.V_IDENT);
			//System.out.println("v_ident found "+yytext());
			return token;
		}
		
.		{
			setToken (SlangTab.T_UNKNOWN);
			//System.out.println(". found");
			return token;
		}
		
<<EOF>> {
			if(cptEnd==0){
				//System.out.println("eof found");
				setToken(SlangTab.T_EOF);
				cptEnd++;
				return token;
			}else {
				setToken(0);
				return token;
			}
		}