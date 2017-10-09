package be.groups.glanguage.core.business.analysis;

import be.groups.glanguage.core.entities.formula.AbstractFormula;

import java.util.LinkedList;
import java.util.List;

// import com.google.java.contract.Ensures;
// import com.google.java.contract.Invariant;
// import com.google.java.contract.Requires;

/**
 * Identifier parameter list Needed by grammatical analyzer
 * 
 * @author micmax
 */
// @Invariant("identifiants!=null && parametres!=null && identifiants.size() == parametres.size()")
public class IdentifierParameterList {
	
	/**
	 * List of dentifiers
	 */
	private List<String> identifiers;
	
	/**
	 * List arameters associated to identifiers<br>
	 * n-th element of this list corresponds to n-th element of {@code identifiers}
	 */
	private List<List<AbstractFormula>> parameters;
	
	
	/**
	 * Create the list with {@code identifier} and {@code formulaList}<br>
	 * {@code formulaList} can contain null formula's, they are kept as such
	 * 
	 * @param identifier
	 * @param listeFormule
	 */
	// @Requires("stringVal!=null && !stringVal.isEmpty()")
	// @Ensures("identifiants!=null && parametres!=null && identifiants.size() == 1 &&
	// identifiants.getFirst() == stringVal && parametres.size() == 1 &&
	// parametres.getFirst()==listeFormule")
	public IdentifierParameterList(String identifier, List<AbstractFormula> formulaList) {
		if (identifier == null || identifier.isEmpty()) {
			throw new IllegalArgumentException("identifier must be a non-null non-empty string");
		}
		identifiers = new LinkedList<String>();
		parameters = new LinkedList<List<AbstractFormula>>();
		identifiers.add(identifier);
		parameters.add(formulaList);
	}
	
	/**
	 * Add {@code identifier} and {@code formulaList}
	 * 
	 * @param identifier
	 * @param formulaList
	 */
	// @Requires("stringVal!=null && !stringVal.isEmpty()")
	// @Ensures("identifiants.size() == (old(identifiants.size()) + 1) && identifiants.size() ==
	// parametres.size() && identifiants.getLast() == stringVal && parametres.getLast() ==
	// listeFormule")
	public void add(String identifier, List<AbstractFormula> formulaList) {
		if (identifier == null || identifier.isEmpty()) {
			throw new IllegalArgumentException("identifier must be a non-null non-empty string");
		}
		identifiers.add(identifier);
		parameters.add(formulaList);
	}
	
	/**
	 * @return the identifiers
	 */
	public List<String> getIdentifiers() {
		return identifiers;
	}
	
	/**
	 * @return the parameters
	 */
	public List<List<AbstractFormula>> getParameters() {
		return parameters;
	}
	
	/**
	 * @param identifiers the identifiers to set
	 */
	public void setIdentifiers(List<String> identifiers) {
		this.identifiers = identifiers;
	}
	
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(List<List<AbstractFormula>> parameters) {
		this.parameters = parameters;
	}
	
}
