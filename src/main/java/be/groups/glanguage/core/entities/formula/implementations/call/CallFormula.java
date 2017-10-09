package be.groups.glanguage.core.entities.formula.implementations.call;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.utils.Agent;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.base.cannot.invoke.targets.FormulaCannotInvokeTargetMethodInnerError;

import javax.persistence.Entity;

/**
 * Abstract formula implementing a reference to a rule, a call to a formula or to a facade method
 *
 * @author michotte
 */
@Entity
public abstract class CallFormula extends AbstractNonTerminalFormula {

    public CallFormula() {
        super();
    }

    public CallFormula(FormulaDescription description, Evaluator evaluator) throws GLanguageException {
        super(description, null, evaluator);
    }

    /**
     * Helper method to return the result of calling a method on an object with parameters<br>
     * The method is found by reflection with the object type, the method name and the types of the parameters
     *
     * @param anObject the object on which to call the method
     * @param aMethodName the name of the method to call
     * @param someMethodParameters the parameters to be passed to the called method
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of calling the method with {@code aMethodName} name, on {@code anObject} object, with
     * {@code someMethodParameters} parameters
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the method doesn't exist
     */
    protected Object callFunctionAny(Object anObject,
                                     String aMethodName,
                                     AbstractFormula[] someMethodParameters,
                                     Evaluator evaluator) throws GLanguageException {
        Agent agent;
        Object result;
        if (someMethodParameters == null) {
            agent = new Agent(anObject, aMethodName);
            result = agent.call();
        } else {
            Class<?>[] someMethodParametersType = new Class[someMethodParameters.length];
            Object[] someArguments = new Object[someMethodParameters.length];
            for (int i = 0; i < someMethodParameters.length; i++) {
                someArguments[i] = someMethodParameters[i].getValue(evaluator);
                someMethodParametersType[i] = someArguments[i] == null ? Object.class : someArguments[i].getClass();
            }
            agent = new Agent(anObject, aMethodName, someMethodParametersType);
            try {
                result = agent.call(someArguments);
            } catch (Exception e) {
                throw new GLanguageException(new FormulaCannotInvokeTargetMethodInnerError(this,
                                                                                           evaluator,
                                                                                           anObject,
                                                                                           aMethodName,
                                                                                           someMethodParametersType));
            }
        }
        return result;
    }

}
