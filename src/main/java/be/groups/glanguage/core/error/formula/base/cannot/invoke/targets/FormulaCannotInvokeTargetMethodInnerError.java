package be.groups.glanguage.core.error.formula.base.cannot.invoke.targets;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;

/**
 * @author michotte
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

    public Object getObject() {
        return object;
    }

    public String getMethodSignatureName() {
        return methodSignatureName;
    }

    public Class<?>[] getMethodSignatureParametersType() {
        return methodSignatureParametersType;
    }
}
