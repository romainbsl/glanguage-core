package be.groups.glanguage.core.business.analysis.byaccj;


import be.groups.glanguage.core.business.analysis.IdentifierParameterList;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * BYACC/J Semantic type
 */
public class SemanticType {
	
	public int ival;
	
	public double dval;
	
	public LocalDate dateVal;
	
	public String stringVal;
	
	public AbstractFormula abstractFormula;
	
	public IdentifierParameterList identifierParameterList;
	
	public LinkedList<AbstractFormula> formulaList;
	
	public LinkedList<String> stringList;
	
	public int integerVal;
	
	public FormulaReturnType formulaReturnType;
	
	public boolean booleanVal;
	
	public SemanticType(int val) {
		ival = val;
	}
	
	/**
	 * Initialize me as a double
	 */
	public SemanticType(double val) {
		dval = val;
	}
	
	/**
	 * Initialize me as a string
	 */
	public SemanticType(String val) {
		stringVal = val;
	}
	
	SemanticType() {}
	
}


