package be.groups.glanguage.glanguage.api.business.analysis.byaccj;


import java.time.LocalDate;
import java.util.LinkedList;

import be.groups.glanguage.glanguage.api.business.analysis.IdentifierParameterList;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

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


