package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbination;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItemType;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by michotte on 9/05/2017.
 */
public class FormulaParameterConbinationTest {

    /**
     * Tests {@link FormulaParameterConbination#isValid(List, Evaluator)}
     * when list is empty or null and there is no parameter in the combination
     */
    @Test
    public void testIsValidEmptyEmpty() {
        FormulaParameterConbination formulaParameterConbination = mock(FormulaParameterConbination.class);
        when(formulaParameterConbination.getParametersMinimumNumber()).thenReturn(0);
        when(formulaParameterConbination.getParametersMaximumNumber()).thenReturn(0);
        when(formulaParameterConbination.getParameters()).thenReturn(null);

        assertTrue(formulaParameterConbination.isValid(null, null));
        assertTrue(formulaParameterConbination.isValid(Collections.emptyList(), null));
    }

    /**
     * Tests {@link FormulaParameterConbination#isValid(List, Evaluator)}
     * when list is empty or null but there is are parameters in the combination
     */
    @Test
    public void testIsValidEmptyNotEmpty() {
        FormulaParameterConbinationItem formulaParameterConbinationItem = mock(FormulaParameterConbinationItem.class);
        when(formulaParameterConbinationItem.getOptional()).thenReturn(false);
        when(formulaParameterConbinationItem.getRepeatable()).thenReturn(false);
        SortedSet<FormulaParameterConbinationItem> formulaParameterConbinationItems = new TreeSet<>();
        formulaParameterConbinationItems.add(formulaParameterConbinationItem);

        FormulaParameterConbination formulaParameterConbination = mock(FormulaParameterConbination.class);
//		when(formulaParameterConbination.getParametersMinimumNumber()).thenReturn(0);
//		when(formulaParameterConbination.getParametersMaximumNumber()).thenReturn(0);
        when(formulaParameterConbination.getParameters()).thenReturn(formulaParameterConbinationItems);

        assertFalse(formulaParameterConbination.isValid(null, null));
        assertFalse(formulaParameterConbination.isValid(Collections.emptyList(), null));
    }

    /**
     * Tests {@link FormulaParameterConbination#isValid(List, Evaluator)}
     * when list is not empty, but there is no parameter in the combination
     */
    @Test
    public void testIsValidNotEmptyEmpty() {
        FormulaParameterConbination formulaParameterConbination = mock(FormulaParameterConbination.class);
        when(formulaParameterConbination.getParametersMinimumNumber()).thenReturn(0);
        when(formulaParameterConbination.getParametersMaximumNumber()).thenReturn(0);
        when(formulaParameterConbination.getParameters()).thenReturn(null);

        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);

        assertFalse(formulaParameterConbination.isValid(Collections.singletonList(parameter), null));
    }

    /**
     * Tests {@link FormulaParameterConbination#isValid(List, Evaluator)}
     * when list matches the list of parameters of the combination
     */
    @Test
    public void testIsValidMatching() {
        FormulaParameterConbinationItem formulaParameterConbinationItem1 = mock(FormulaParameterConbinationItem.class);
        when(formulaParameterConbinationItem1.getOptional()).thenReturn(false);
        when(formulaParameterConbinationItem1.getRepeatable()).thenReturn(false);
        Set<FormulaParameterConbinationItemType> formulaParameterConbinationItemTypes1 = new HashSet<>();
        FormulaParameterConbinationItemType formulaParameterConbinationItemType1 = mock
                (FormulaParameterConbinationItemType.class);
        when(formulaParameterConbinationItemType1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
        formulaParameterConbinationItemTypes1.add(formulaParameterConbinationItemType1);
        when(formulaParameterConbinationItem1.getTypes()).thenReturn(formulaParameterConbinationItemTypes1);

        FormulaParameterConbinationItem formulaParameterConbinationItem2 = mock(FormulaParameterConbinationItem.class);
        when(formulaParameterConbinationItem2.getOptional()).thenReturn(false);
        when(formulaParameterConbinationItem2.getRepeatable()).thenReturn(false);
        Set<FormulaParameterConbinationItemType> formulaParameterConbinationItemTypes2 = new HashSet<>();
        FormulaParameterConbinationItemType formulaParameterConbinationItemType2 = mock
                (FormulaParameterConbinationItemType.class);
        when(formulaParameterConbinationItemType2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
        formulaParameterConbinationItemTypes2.add(formulaParameterConbinationItemType2);
        when(formulaParameterConbinationItem2.getTypes()).thenReturn(formulaParameterConbinationItemTypes2);

        SortedSet<FormulaParameterConbinationItem> formulaParameterConbinationItems = new TreeSet<>();
        formulaParameterConbinationItems.add(formulaParameterConbinationItem1);
        formulaParameterConbinationItems.add(formulaParameterConbinationItem2);

        FormulaParameterConbination formulaParameterConbination = mock(FormulaParameterConbination.class);
//		when(formulaParameterConbination.getParametersMinimumNumber()).thenReturn(0);
//		when(formulaParameterConbination.getParametersMaximumNumber()).thenReturn(0);
        when(formulaParameterConbination.getParameters()).thenReturn(formulaParameterConbinationItems);

        AbstractFormula parameter1 = mock(AbstractFormula.class);
        when(parameter1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);

        AbstractFormula parameter2 = mock(AbstractFormula.class);
        when(parameter2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);

        assertTrue(formulaParameterConbination.isValid(Arrays.asList(parameter1, parameter2), null));
    }

    /**
     * Tests {@link FormulaParameterConbination#isValid(List, Evaluator)}
     * when list doesn't match the list of parameters of the combination
     */
    @Test
    public void testIsValidNotMatching() {
        FormulaParameterConbinationItem formulaParameterConbinationItem1 = mock(FormulaParameterConbinationItem.class);
        when(formulaParameterConbinationItem1.getOptional()).thenReturn(false);
        when(formulaParameterConbinationItem1.getRepeatable()).thenReturn(false);
        Set<FormulaParameterConbinationItemType> formulaParameterConbinationItemTypes1 = new HashSet<>();
        FormulaParameterConbinationItemType formulaParameterConbinationItemType1 = mock
                (FormulaParameterConbinationItemType.class);
        when(formulaParameterConbinationItemType1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
        formulaParameterConbinationItemTypes1.add(formulaParameterConbinationItemType1);
        when(formulaParameterConbinationItem1.getTypes()).thenReturn(formulaParameterConbinationItemTypes1);

        FormulaParameterConbinationItem formulaParameterConbinationItem2 = mock(FormulaParameterConbinationItem.class);
        when(formulaParameterConbinationItem2.getOptional()).thenReturn(false);
        when(formulaParameterConbinationItem2.getRepeatable()).thenReturn(false);
        Set<FormulaParameterConbinationItemType> formulaParameterConbinationItemTypes2 = new HashSet<>();
        FormulaParameterConbinationItemType formulaParameterConbinationItemType2 = mock
                (FormulaParameterConbinationItemType.class);
        when(formulaParameterConbinationItemType2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
        formulaParameterConbinationItemTypes2.add(formulaParameterConbinationItemType2);
        when(formulaParameterConbinationItem2.getTypes()).thenReturn(formulaParameterConbinationItemTypes2);

        SortedSet<FormulaParameterConbinationItem> formulaParameterConbinationItems = new TreeSet<>();
        formulaParameterConbinationItems.add(formulaParameterConbinationItem1);
        formulaParameterConbinationItems.add(formulaParameterConbinationItem2);

        FormulaParameterConbination formulaParameterConbination = mock(FormulaParameterConbination.class);
//		when(formulaParameterConbination.getParametersMinimumNumber()).thenReturn(0);
//		when(formulaParameterConbination.getParametersMaximumNumber()).thenReturn(0);
        when(formulaParameterConbination.getParameters()).thenReturn(formulaParameterConbinationItems);

        AbstractFormula parameter1 = mock(AbstractFormula.class);
        when(parameter1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);

        AbstractFormula parameter2 = mock(AbstractFormula.class);
        when(parameter2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);

        assertFalse(formulaParameterConbination.isValid(Arrays.asList(parameter1, parameter2), null));
    }

    /**
     * Tests {@link FormulaParameterConbination#isValid(List, Evaluator)}
     * when list matches the list of parameters of the combination
     */
    @Test
    public void testIsValidOptionalParameterMissing() {
        FormulaParameterConbinationItem formulaParameterConbinationItem1 = mock(FormulaParameterConbinationItem.class);
        when(formulaParameterConbinationItem1.getOptional()).thenReturn(true);
        when(formulaParameterConbinationItem1.getRepeatable()).thenReturn(false);
        Set<FormulaParameterConbinationItemType> formulaParameterConbinationItemTypes1 = new HashSet<>();
        FormulaParameterConbinationItemType formulaParameterConbinationItemType1 = mock
                (FormulaParameterConbinationItemType.class);
        when(formulaParameterConbinationItemType1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
        formulaParameterConbinationItemTypes1.add(formulaParameterConbinationItemType1);
        when(formulaParameterConbinationItem1.getTypes()).thenReturn(formulaParameterConbinationItemTypes1);

        FormulaParameterConbinationItem formulaParameterConbinationItem2 = mock(FormulaParameterConbinationItem.class);
        when(formulaParameterConbinationItem2.getOptional()).thenReturn(false);
        when(formulaParameterConbinationItem2.getRepeatable()).thenReturn(false);
        Set<FormulaParameterConbinationItemType> formulaParameterConbinationItemTypes2 = new HashSet<>();
        FormulaParameterConbinationItemType formulaParameterConbinationItemType2 = mock
                (FormulaParameterConbinationItemType.class);
        when(formulaParameterConbinationItemType2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
        formulaParameterConbinationItemTypes2.add(formulaParameterConbinationItemType2);
        when(formulaParameterConbinationItem2.getTypes()).thenReturn(formulaParameterConbinationItemTypes2);

        SortedSet<FormulaParameterConbinationItem> formulaParameterConbinationItems = new TreeSet<>();
        formulaParameterConbinationItems.add(formulaParameterConbinationItem1);
        formulaParameterConbinationItems.add(formulaParameterConbinationItem2);

        FormulaParameterConbination formulaParameterConbination = mock(FormulaParameterConbination.class);
//		when(formulaParameterConbination.getParametersMinimumNumber()).thenReturn(0);
//		when(formulaParameterConbination.getParametersMaximumNumber()).thenReturn(0);
        when(formulaParameterConbination.getParameters()).thenReturn(formulaParameterConbinationItems);

        AbstractFormula parameter2 = mock(AbstractFormula.class);
        when(parameter2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);

        assertTrue(formulaParameterConbination.isValid(Arrays.asList(parameter2), null));
    }


}
