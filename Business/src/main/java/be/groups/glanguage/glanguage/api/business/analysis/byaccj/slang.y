%{
package be.groups.glanguage.glanguage.api.business.analysis.byaccj;

import be.groups.glanguage.glanguage.api.business.action.SemanticalAction;
import be.groups.glanguage.glanguage.api.business.analysis.byaccj.SlangLex;
import be.groups.glanguage.glanguage.api.business.analysis.IdentifierParameterList;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.FormulaBracket;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.parser.ParserInnerError;
import be.groups.glanguage.glanguage.api.error.parser.ParserUnableToParseTextInnerError;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
%}

	/* Declaration of the terms manipulated in this grammar */

	/* The operators, with their type of precedence and associativity, from lowest to highest priority */
%left	T_OR
%left	T_AND
%left	'=' T_DIFFERENT '<' T_SMALLER_EQ '>' T_GREATER_EQ
%left	'+' '-'
%left	'*' '/' T_INTEGER_DIV T_MODULO
%right	UNARY_OP T_NOT '?'
%right	'('
%right	ATOMIC_CALL


	/* Terminals */
	
	/* Keywords */
%token	T_APPLIC
%token	T_BOOLEAN
%token	T_DATE
%token	T_DURATION
%token	T_ELSE
%token	T_ELSEIF
%token	T_END
%token	T_FALSE
%token	T_IF
%token	T_IN
%token	T_INTEGER
%token	T_NUMERIC
%token	T_SEP
%token	T_STRING
%token	T_THEN
%token	T_TIMESPAN
%token	T_TRUE
%token	T_UNKNOWN

	/* Object calls */
%token	T_FORMULA

	/* Meta language */

	/* Months of year */
%token	T_JANUARY
%token	T_FEBRUARY
%token	T_MARCH
%token	T_APRIL
%token	T_MAY
%token	T_JUNE
%token	T_JULY
%token	T_AUGUST
%token	T_SEPTEMBER
%token	T_OCTOBER
%token	T_NOVEMBER
%token	T_DECEMBER

	/* Standard functions */
%token	T_ABS
%token 	T_BANKERS_ROUNDED
%token	T_CEIL
%token	T_FLOOR
%token	T_FORMATDATE
%token	T_FORMATINTEGER
%token	T_FORMATNUMERIC
%token	T_FORMATSTRING
%token	T_ROUNDED
%token	T_SIGN
%token	T_TRUNC
%token	T_GET
%token	T_MAX
%token	T_MIN
%token	T_SMIN
%token	T_SMAX
%token	T_SUBSTRING
%token	T_MULTIPLY
%token	T_STRINGITEM
%token	T_SUM
%token	T_SUMV
%token	T_MINUTES
%token	T_HOURS
%token	T_DAYS
%token	T_MONTHS
%token	T_YEARS
%token	T_PUT_TEXT
%token	T_STRINGLENGTH

	/* Valeurs */
%token	<stringVal> V_INTEGER V_QUANTITY V_DURATION V_STRING
%token	<stringVal> V_IDENT

	/* End of file */
%token  T_EOF

	/* Non-Terminals, with their type */
%type	<formulaReturnType>	type
%type	<stringList>	identifierList
%type	<formulaList>	expressionList
%type	<formulaList>	l_expr
%type	<abstractFormula>	expr
%type	<abstractFormula>	constant
%type	<dateVal>	date
%type	<objectVal>		separator
%type	<integerVal>	month
%type	<abstractFormula>	entity
%type	<formulaList>	functionalCall
%type	<abstractFormula>	objectCall
%type	<abstractFormula>	standardFunction
%type	<identifierParameterList> getCall
%type	<stringVal>	anObject
%type	<abstractFormula>	instruction
%type	<abstractFormula>	else

	/* Options */
%start initialize		/* Start */

%%

	/* Language grammar */

	/* Start */
initialize:
	{aSem.initialize(); if (yydebug) debug("initialize");} 
	l_expr
	T_EOF {if (yydebug) debug("teof yacc");aSem.endAnalysis(); if (yydebug) debug("endAnalysis"); /*return 0;*/} 
	;

	/* Types */
type:
	T_INTEGER		
				{
					$$ = FormulaReturnType.INTEGER; if (yydebug) debug("type t_integer");
				}
	| T_NUMERIC		
				{
					$$ = FormulaReturnType.NUMERIC; if (yydebug) debug("type t_numeric");
				}
	| T_DATE		
				{
					$$ = FormulaReturnType.DATE; if (yydebug) debug("type t_date");
				}
	| T_BOOLEAN		
				{
					$$ = FormulaReturnType.BOOLEAN; if (yydebug) debug("type t_boolean");
				}
	| T_STRING		
				{
					$$ = FormulaReturnType.STRING; if (yydebug) debug("type t_string");
				}
	;

	/* Expressions */

identifierList:
	V_IDENT									
				{
					yyval.stringList = new LinkedList<String> () ;
                                        yyval.stringList.add (val_peek(0).stringVal);
				}
	| identifierList T_SEP V_IDENT	
				{
					$$ = $1; 
                                        $$.add ($3);
				}
	;

expressionList:
	// Empty --	
				{
					$$ = new LinkedList <AbstractFormula>(); if (yydebug) debug("expressionList empty");
				}
	| l_expr	
				{
					$$ = $1; if (yydebug) debug("expressionList avec une l_expr");
				}
	;
l_expr:
	expr				
				{
					$$ =  new LinkedList <AbstractFormula>(); 
					$$.add ($1); if (yydebug) debug("l_expr -> expr");
					aSem.setFormula($1);
				}
	| l_expr T_SEP expr	
				{
					$$ = $1; 
                                        $$.add ($3); if (yydebug) debug("l_expr -> l_expr t_sep expr");
				}
	;
expr:
	constant %prec ATOMIC_CALL				
				{
					$$ = $1; if (yydebug) debug("constant in expr");
				}
	| entity %prec ATOMIC_CALL				
				{
					$$ = $1; if (yydebug) debug("entity in expr");
				}
	| objectCall %prec ATOMIC_CALL			
				{
					$$ = $1;if (yydebug) debug("objectCall in expr");
				}
	| standardFunction %prec ATOMIC_CALL	
				{
					$$ = $1; if (yydebug) debug("standardFunction %prec atomic_call in expr "+ATOMIC_CALL);
				}
	| instruction %prec ATOMIC_CALL			
				{
					$$ = $1; if (yydebug) debug("instruction in expr");
				}
	| expr T_IN '(' expressionList ')' %prec ATOMIC_CALL	
				{
					$$ = aSem.inOperation ($1, $4);if (yydebug) debug("expr t_in in");
				}
	| '(' expr ')'				
				{
					$$ = aSem.bracketFormula ($2);if (yydebug) debug("parenthese in expr");
				}
	| T_NOT expr				
				{
					$$ = aSem.unaryOperation (FormulaType.OP_NOT, $2);
				}
	| '+' expr %prec UNARY_OP	
				{
					$$ = aSem.unaryOperation (FormulaType.OP_UNARY_PLUS, $2);if (yydebug) debug(" + in expr");
				}
	| '-' expr %prec UNARY_OP	
				{
					$$ = aSem.unaryOperation (FormulaType.OP_UNARY_MINUS, $2);if (yydebug) debug("- in expr");
				}
	| '?' expr					
				{
					$$ = aSem.unaryOperation (FormulaType.OP_EXIST, $2);if (yydebug) debug("? in expr");
				}
	| expr '*' expr				
				{
					$$ = aSem.binaryOperation (FormulaType.OP_MULTIPLY, $1, $3);if (yydebug) debug("* in expr");
				}
	| expr '/' expr				
				{
					$$ = aSem.binaryOperation (FormulaType.OP_DIVIDE, $1, $3);if (yydebug) debug("/ in expr");
				}
	| expr T_INTEGER_DIV expr	
				{
					$$ = aSem.binaryOperation (FormulaType.OP_INTEGER_DIVISION, $1, $3);
				}
	| expr T_MODULO expr		
				{
					$$ = aSem.binaryOperation (FormulaType.OP_MODULO, $1, $3);
				}
	| expr '+' expr				
				{
					$$ = aSem.binaryOperation (FormulaType.OP_PLUS, $1, $3);if (yydebug) debug("+expr in expr");
				}
	| expr '-' expr				
				{
					$$ = aSem.binaryOperation (FormulaType.OP_MINUS, $1, $3);if (yydebug) debug("-expr in expr");
				}
	| expr '=' expr				
				{
					$$ = aSem.binaryOperation (FormulaType.OP_EQUAL, $1, $3);if (yydebug) debug("=expr in expr");
				}
	| expr T_DIFFERENT expr		
				{
					$$ = aSem.binaryOperation (FormulaType.OP_DIFFERENCE, $1, $3);
				}
	| expr '<' expr				
				{
					$$ = aSem.binaryOperation (FormulaType.OP_SMALLER, $1, $3);
				}
	| expr T_SMALLER_EQ expr	
				{
					$$ = aSem.binaryOperation (FormulaType.OP_SMALLER_OR_EQUAL, $1, $3);
				}
	| expr '>' expr				
				{
					$$ = aSem.binaryOperation (FormulaType.OP_GREATER, $1, $3);
				}
	| expr T_GREATER_EQ expr	
				{
					$$ = aSem.binaryOperation (FormulaType.OP_GREATER_OR_EQUAL, $1, $3);
				}
	| expr T_AND expr			
				{
					$$ = aSem.binaryOperation (FormulaType.OP_AND, $1, $3);
				}
	| expr T_OR expr			
				{
					$$ = aSem.binaryOperation (FormulaType.OP_OR, $1, $3);
				}
	;

constant:
	V_INTEGER							
				{
					$$ = aSem.terminalIntegerFormula ($1); if (yydebug) debug("v_integer");
				}
	| V_QUANTITY						
				{
					$$ = aSem.terminalNumericFormula ($1); if (yydebug) debug("v_quantity");
				}
	| V_STRING							
				{
					$$ = aSem.terminalStringFormula ($1); if (yydebug) debug("v_string");
				}
	| T_TRUE							
				{
					$$ = aSem.terminalBooleanFormula (true);
				}
	| T_FALSE							
				{
					$$ = aSem.terminalBooleanFormula (false);

				}
 	| V_DURATION						
				{
					$$ = aSem.terminalDurationFormula ($1);
				}
 	| T_TIMESPAN '(' V_DURATION ')'		
				{
					$$ = aSem.terminalDurationFormula ($3);
				}
	| date								
				{
					$$ = aSem.terminalDateFormula ($1);
				}
	| '?'								
				{
					$$ = aSem.emptyFormula();
				}
	;
	
date:
	'\'' V_INTEGER separator month separator V_INTEGER '\''	  
				{
					$$ = aSem.createDate (aSem.checkInteger ($2, 1, 31), $4, aSem.checkInteger ($6, 1, -1));
				}
	;
	
separator:
	'/'
	| '-'
	;
	
month:
	V_INTEGER		
				{
					$$ = aSem.checkInteger ($1, 1, 12);
				}
	| T_JANUARY		
				{
					$$ = 1;
				}
	| T_FEBRUARY	
				{
					$$ = 2;
				}
	| T_MARCH		
				{
					$$ = 3;
				}
	| T_APRIL		
				{
					$$ = 4;
				}
	| T_MAY			
				{
					$$ = 5;
				}
	| T_JUNE		
				{
					$$ = 6;
				}
	| T_JULY		
				{
					$$ = 7;
				}
	| T_AUGUST		
				{
					$$ = 8;
				}
	| T_SEPTEMBER	
				{
					$$ = 9;
				}
	| T_OCTOBER		
				{
					$$ = 10;
				}
	| T_NOVEMBER	
				{
					$$ = 11;
				}
	| T_DECEMBER	
				{
					$$ = 12;
				}
	;

entity:
	V_IDENT					
				{
					$$ = aSem.referenceFormula ($1); if (yydebug) debug("v_ident entity");
				}
	;
	
functionalCall:
	// Empty --				
				{
					$$ = new LinkedList<AbstractFormula>();
					if (yydebug) debug("functionalCall empty");
				}
	| '(' expressionList ')'	
				{
					$$ = $2; if (yydebug) debug("functionalCall expressionList");
				}
	;
	
objectCall:
	V_IDENT '.' T_APPLIC		
				{
					$$ = aSem.applicabiltyCall ($1);
				}
	| V_IDENT '.' T_FORMULA		
				{
					$$ = aSem.formulaCall ($1);
				}
	;
	
standardFunction:
	T_ABS '(' expressionList ')'			
				{
					$$ = aSem.standardFunction (FormulaType.F_ABS, $3);
				}
	| T_BANKERS_ROUNDED '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_BANKERS_ROUNDED, $3);
				}
	| T_CEIL '(' expressionList ')'			
				{
					$$ = aSem.standardFunction (FormulaType.F_CEIL, $3);
				}
	| T_FLOOR '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_FLOOR, $3);
				}
	| T_FORMATDATE '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_FORMAT_DATE, $3);
				}
	| T_FORMATINTEGER '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_FORMAT_INTEGER, $3);
				}
	| T_FORMATNUMERIC '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_FORMAT_NUMERIC, $3);
				}
	| T_FORMATSTRING '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_FORMAT_STRING, $3);
				}
	| T_ROUNDED '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_ROUNDED, $3);
				}
	| T_SIGN '(' expressionList ')'			
				{
					$$ = aSem.standardFunction (FormulaType.F_SIGN, $3);
				}
	| T_STRINGITEM '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_STRING_ITEM, $3);
				}				
	| T_SUBSTRING '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_SUBSTRING, $3);
				}				
	| T_TRUNC '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_TRUNC, $3);
				}
	| T_GET type getCall				
				{
					$$ = aSem.getFunction ($2, $3); if (yydebug) debug("t_get "+$2+" "+$3);
				}
	| T_MULTIPLY '(' V_IDENT ')'		
				{
					$$ = aSem.groupFunction (FormulaType.G_MULT, $3);
				}
	| T_SUM '(' V_IDENT ')'				
				{
					$$ = aSem.groupFunction (FormulaType.G_SUM, $3);
				}
	| T_SUMV '(' V_IDENT ')'			
				{
					$$ = aSem.groupFunction (FormulaType.G_SUMV, $3);
				}
	| T_MIN '(' expressionList ')'			
				{
					$$ = aSem.standardFunction (FormulaType.F_MIN, $3);
				}
	| T_MAX '(' expressionList ')'			
				{
					$$ = aSem.standardFunction (FormulaType.F_MAX, $3);
				}
	| T_SMIN '(' expressionList ')'			
				{
					$$ = aSem.standardFunction (FormulaType.F_SMIN, $3);
				}
	| T_SMAX '(' expressionList ')'			
				{
					$$ = aSem.standardFunction (FormulaType.F_SMAX, $3);
				}
	| T_DATE '(' expressionList ')'			
				{
					$$ = aSem.standardFunction (FormulaType.F_DATE, $3);
				}
 	| T_MINUTES '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_MINUTES, $3);
				}
 	| T_HOURS '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_HOURS, $3);
				}
	| T_DAYS '(' expressionList ')'			
				{
					$$ = aSem.standardFunction (FormulaType.F_DAYS, $3);
				}
	| T_MONTHS '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_MONTHS, $3);
				}
	| T_YEARS '(' expressionList ')'		
				{
					$$ = aSem.standardFunction (FormulaType.F_YEARS, $3);
				}
	| T_PUT_TEXT '(' expressionList ')'
				{
					$$ = aSem.standardFunction (FormulaType.F_PUT_TEXT, $3);
				}
	| T_STRINGLENGTH '(' expressionList ')'
				{
					$$ = aSem.standardFunction (FormulaType.F_STRING_LENGTH, $3);
				}
	;

getCall:
	anObject functionalCall				
				{
					$$ = new IdentifierParameterList ($1, $2); if (yydebug) debug("getCall anObject functionalCall");
				}
	| getCall '.' anObject functionalCall
		
				{
					$$ = $1; $$.add ($3, $4); if (yydebug) debug("getCall. anObject functionalCall");
				}
	;

anObject:
	V_IDENT			
				{
					$$ = $1; if (yydebug) debug("v_ident");
				}
	| T_MINUTES		
				{
					$$ = "minutes";
				}
	;
	
instruction:
	T_IF expr T_THEN expr T_END
				{
					if (yydebug) debug("t_if expr t_then expr t_end yacc");
					$$ = aSem.ifInstruction($2,$4,null);
				}
	| T_IF expr T_THEN expr else T_END
				{
					if (yydebug) debug("t_if expr t_then expr else t_end yacc");
					$$ = aSem.ifInstruction($2,$4,$5);
				}
	;
	
else:
	T_ELSEIF expr T_THEN expr else	
				{
					if (yydebug) debug("t_elseif expr t_then expr else yacc");
					$$ = aSem.ifInstruction ($2, $4, $5);
				}
	| T_ELSEIF expr T_THEN expr
				{
					if (yydebug) debug("t_elseif expr t_then expr else yacc");
					$$ = aSem.ifInstruction ($2, $4, null);
				}
	| T_ELSE expr						
				{
					if (yydebug) debug("t_else expr yacc");
					$$ = $2;
				}
	;

%%
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
            throw new GLanguageException(new ParserUnableToParseTextInnerError(formulaString, "analyse", exp));
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

