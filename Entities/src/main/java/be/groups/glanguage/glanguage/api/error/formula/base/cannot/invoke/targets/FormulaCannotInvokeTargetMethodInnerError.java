package be.groups.glanguage.glanguage.api.error.formula.base.cannot.invoke.targets;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * Created by michotte on 20/12/2016.
 */
public class FormulaCannotInvokeTargetMethodInnerError extends FormulaInnerError {

    private Object object;
    private String methodSignatureName;
    private Class<?>[] methodSignatureParametersType;

    public FormulaCannotInvokeTargetMethodInnerError(AbstractFormula formula,
                                                     Evaluator evaluator,
                                                     Object object,
                                                     String methodSignatureName,
                                                     Class<?>[] methodSignatureParametersType) {
        super(GLanguageErrorRegistry.FORMULA_CANNOT_INVOKE_TARGET_OBJECT_METHOD, formula, evaluator,
              "getTargetedObject", getCause(object, methodSignatureName, methodSignatureParametersType));
        this.object = object;
        this.methodSignatureName = methodSignatureName;
        this.methodSignatureParametersType = methodSignatureParametersType;
    }

    private static String getCause(Object object, String methodWithParameters, Class<?>[]
            methodSignatureParametersType) {
        StringBuilder sb = new StringBuilder("The method ");
        sb.append("'" + methodWithParameters + "(" );
        if (methodSignatureParametersType != null && methodSignatureParametersType.length > 0) {
            for (int i=0 ; i < methodSignatureParametersType.length ; i++) {
                sb.append(methodSignatureParametersType[i].getSimpleName());
                if (i < methodSignatureParametersType.length - 1) {
                    sb.append("; ");
                }
            }
        }
        sb.append(")'");
        sb.append(" is undefined for object type " + object.getClass().getSimpleName());
        return sb.toString();
    }

}
