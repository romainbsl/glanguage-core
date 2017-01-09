package be.groups.glanguage.glanguage.api;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;

/**
 * Created by michotte on 23/12/2016.
 */
public class GLanguageEvaluationExceptionTest {

    public void handleException(GLanguageException e) throws GLanguageException {
        System.out.println("error code: " + e.getError().getCode());
        if (e.getError().getMessage() != null) {
            System.out.println("error message: " + e.getError().getMessage());
        }
        if (e.getError().getTarget() != null) {
            System.out.println("error target: " + e.getError().getTarget());
        }
        if (e.getError().getDetails() != null) {
            System.out.println("error details: " + e.getError().getDetails());
        }
        InnerError innerError = e.getError().getInnerError();
        int i = 1;
        while (innerError != null) {
            for (int j = i ; j > 1 ; j--) {
                System.out.print("\t");
            }
            System.out.println("InnerError[" + i + "] code: " + innerError.getCode());
            if (innerError.getMessage() != null) {
                for (int j = i ; j > 1 ; j--) {
                    System.out.print("\t");
                }
                System.out.println("InnerError[" + i + "] message: " + innerError.getMessage());
            }
            innerError = innerError.getInnerError();
            i++;
        }
        e.printStackTrace();
        throw e;
    }

}
