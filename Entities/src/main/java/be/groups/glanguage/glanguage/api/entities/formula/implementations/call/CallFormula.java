package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.utils.Agents;

@Entity
public abstract class CallFormula extends AbstractNonTerminalFormula {
	
	public CallFormula() {
		super();
	}

	protected Object callFunctionAny(Object anObject, String aMethodName, AbstractFormula[] someMethodParameters) {
    	Agents agent;
    	Object result;
    	if(someMethodParameters == null) {
    		agent = new Agents(anObject, aMethodName);
    		result = agent.call();
    	} else {
	    	Class<?>[] someMethodParametersType = new Class [someMethodParameters.length];
	        Object[] someArguments = new Object [someMethodParameters.length];
	        for (int i = 0; i < someMethodParameters.length; i++) {
	        	someArguments[i] = someMethodParameters[i].getValue();
	            someMethodParametersType[i] = someArguments[i].getClass();
	        }
	        agent = new Agents(anObject, aMethodName, someMethodParametersType);   
	    	try {
	    		result = agent.call(someArguments);
	    	} catch (Exception e) {
	    		// TODO report evaluation error
	    		throw e;
	    	}
    	}
    	return result;
    }
	
}
