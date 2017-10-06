package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombination;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombinationItemType;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsageReturnType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionNoMatchingUsageInnerError;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionUnableToValidateInnerError;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

/**
 * Created by michotte on 3/08/2017.
 */
public class FormulaDescriptionTest {

    /**
     * Tests {@link FormulaDescription#validate(AbstractFormula, List, Evaluator)} when not valid and no best usage
     * found
     */
    @Test(expected = GLanguageException.class)
    public void testValidateNoBestUsage() throws GLanguageException {
        FormulaDescription description = spy(FormulaDescription.class);
        doReturn(false).when(description).isValid(null, null);
        doReturn(null).when(description).getBestMatchingUsage(null, null);

        AbstractFormula formula = mock(AbstractFormula.class);
        when(formula.getId()).thenReturn(1L);
        try {
            description.validate(formula, null, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertTrue("inner error not of type FormulaDescriptionUnableToValidateInnerError",
                       ie instanceof FormulaDescriptionUnableToValidateInnerError);
            e.getError().invertInnerError();
            ie = e.getError().getInnerError();
            assertNotNull("inner error is null after inverting", ie);
            assertTrue("inner error not of type FormulaDescriptionNoMatchingUsageInnerError",
                       ie instanceof FormulaDescriptionNoMatchingUsageInnerError);
            throw e;
        }
    }

    /**
     * Tests {@link FormulaDescription#validate(AbstractFormula, List, Evaluator)} when not valid and best usage
     * found is not valid too
     */
    @Test(expected = GLanguageException.class)
    public void testValidateBestUsageNotValid() throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        when(formula.getId()).thenReturn(1L);

        FormulaUsage usage = mock(FormulaUsage.class);
        doThrow(new GLanguageException(null)).when(usage).validate(formula, null, null);

        FormulaDescription description = spy(FormulaDescription.class);
        doReturn(false).when(description).isValid(null, null);
        doReturn(usage).when(description).getBestMatchingUsage(null, null);

        try {
            description.validate(formula, null, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertTrue("inner error not of type FormulaDescriptionUnableToValidateInnerError",
                       ie instanceof FormulaDescriptionUnableToValidateInnerError);
            throw e;
        }
    }

    /**
     * Tests {@link FormulaDescription#isValid(List, Evaluator)} when there is no usage
     */
    @Test
    public void testIsValidNoUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        Set<FormulaUsage> usages = new HashSet<>();
        when(description.getUsages()).thenReturn(usages);

        when(description.isValid(null, null)).thenCallRealMethod();
        assertFalse(description.isValid(null, null));
    }

    /**
     * Tests {@link FormulaDescription#isValid(List, Evaluator)} when there is no valid usage
     */
    @Test
    public void testIsValidNoValidUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        when(usage.isValid(null, null)).thenReturn(false);
        Set<FormulaUsage> usages = new HashSet<>();
        usages.add(usage);
        when(description.getUsages()).thenReturn(usages);

        when(description.isValid(null, null)).thenCallRealMethod();
        assertFalse(description.isValid(null, null));
    }

    /**
     * Tests {@link FormulaDescription#isValid(List, Evaluator)} when there is a valid usage
     */
    @Test
    public void testIsValidValidUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        when(usage.isValid(null, null)).thenReturn(true);
        Set<FormulaUsage> usages = new HashSet<>();
        usages.add(usage);
        when(description.getUsages()).thenReturn(usages);

        when(description.isValid(null, null)).thenCallRealMethod();
        assertTrue(description.isValid(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getReturnType(List, Evaluator)} when there is no valid usage
     */
    @Test
    public void testGetReturnTypeNoValidUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        when(description.getValidUsage(null, null)).thenReturn(null);

        when(description.getReturnType(null, null)).thenCallRealMethod();
        assertEquals(FormulaReturnType.UNDEFINED, description.getReturnType(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getReturnType(List, Evaluator)} when there is a valid usage with 1 return type
     */
    @Test
    public void testGetReturnTypeValidUsage1Type() {
        Set<FormulaUsageReturnType> usageReturnTypes = new HashSet<>();
        FormulaUsageReturnType returnType = new FormulaUsageReturnType();
        returnType.setId(1L);
        returnType.setReturnType(FormulaReturnType.BOOLEAN);
        usageReturnTypes.add(returnType);
        FormulaUsage usage = mock(FormulaUsage.class);
        when(usage.getTypes()).thenReturn(usageReturnTypes);
        when(usage.getReturnTypes()).thenCallRealMethod();
        FormulaDescription description = mock(FormulaDescription.class);
        when(description.getValidUsage(null, null)).thenReturn(usage);

        when(description.getReturnType(null, null)).thenCallRealMethod();
        assertEquals(FormulaReturnType.BOOLEAN, description.getReturnType(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getReturnType(List, Evaluator)} when there is a valid usage with more than 1
     * return type
     */
    @Test
    public void testGetReturnTypeValidUsageMoreThan1Type() {
        Set<FormulaUsageReturnType> usageReturnTypes = new HashSet<>();
        FormulaUsageReturnType returnType1 = new FormulaUsageReturnType();
        returnType1.setId(1L);
        returnType1.setReturnType(FormulaReturnType.BOOLEAN);
        usageReturnTypes.add(returnType1);
        FormulaUsageReturnType returnType2 = new FormulaUsageReturnType();
        returnType2.setId(2L);
        returnType2.setReturnType(FormulaReturnType.BOOLEAN);
        usageReturnTypes.add(returnType2);
        FormulaUsage usage = mock(FormulaUsage.class);
        when(usage.getTypes()).thenReturn(usageReturnTypes);
        when(usage.getReturnTypes()).thenCallRealMethod();
        FormulaDescription description = mock(FormulaDescription.class);
        when(description.getValidUsage(null, null)).thenReturn(usage);

        when(description.getReturnType(null, null)).thenCallRealMethod();
        assertEquals(FormulaReturnType.UNDEFINED, description.getReturnType(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getValidUsage(List, Evaluator)} when there is no usage
     */
    @Test
    public void testGetValidUsageNoUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        Set<FormulaUsage> usages = new HashSet<>();
        when(description.getUsages()).thenReturn(usages);

        when(description.getValidUsage(null, null)).thenCallRealMethod();
        assertNull(description.getValidUsage(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getValidUsage(List, Evaluator)} when there is no valid usage
     */
    @Test
    public void testGetValidUsageNoValidUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        when(usage.isValid(null, null)).thenReturn(false);
        Set<FormulaUsage> usages = new HashSet<>();
        usages.add(usage);
        when(description.getUsages()).thenReturn(usages);

        when(description.getValidUsage(null, null)).thenCallRealMethod();
        assertNull(description.getValidUsage(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getValidUsage(List, Evaluator)} when there is 1 valid usage
     */
    @Test
    public void testGetValidUsageValidUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        when(usage.isValid(null, null)).thenReturn(true);
        Set<FormulaUsage> usages = new HashSet<>();
        usages.add(usage);
        when(description.getUsages()).thenReturn(usages);

        when(description.getValidUsage(null, null)).thenCallRealMethod();
        assertEquals(usage, description.getValidUsage(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getValidUsage(List, Evaluator)} when there are more than 1 usages but no one
     * is valid
     */
    @Test
    public void testGetValidUsageNoOneValidUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        Set<FormulaUsage> usages = new HashSet<>();
        FormulaUsage usage1 = mock(FormulaUsage.class);
        when(usage1.isValid(null, null)).thenReturn(false);
        usages.add(usage1);
        FormulaUsage usage2 = mock(FormulaUsage.class);
        when(usage2.isValid(null, null)).thenReturn(false);
        usages.add(usage2);
        when(description.getUsages()).thenReturn(usages);

        when(description.getValidUsage(null, null)).thenCallRealMethod();
        assertNull(description.getValidUsage(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getValidUsage(List, Evaluator)} when there are more than 1 usages but only 1
     * is valid
     */
    @Test
    public void testGetValidUsageOnlyOneValidUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        Set<FormulaUsage> usages = new HashSet<>();
        FormulaUsage usage1 = mock(FormulaUsage.class);
        when(usage1.isValid(null, null)).thenReturn(true);
        usages.add(usage1);
        FormulaUsage usage2 = mock(FormulaUsage.class);
        when(usage2.isValid(null, null)).thenReturn(false);
        usages.add(usage2);
        when(description.getUsages()).thenReturn(usages);

        when(description.getValidUsage(null, null)).thenCallRealMethod();
        assertEquals(usage1, description.getValidUsage(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getValidUsage(List, Evaluator)} when there are more than 1 valid usages but
     * only 1 has minimum parameter number
     */
    @Test
    public void testGetValidUsageOnlyOneValidMinimalParameterUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        Set<FormulaUsage> usages = new HashSet<>();
        FormulaUsage usage1 = mock(FormulaUsage.class);
        when(usage1.isValid(null, null)).thenReturn(true);
        usages.add(usage1);
        FormulaUsage usage2 = mock(FormulaUsage.class);
        when(usage2.isValid(null, null)).thenReturn(true);
        usages.add(usage2);
        when(description.getUsages()).thenReturn(usages);
        List<FormulaUsage> minimalParameterNumberUsages = new ArrayList<>();
        minimalParameterNumberUsages.add(usage1);
        when(description.getUsagesWithMinimalParameterNumber(anyList())).thenReturn(minimalParameterNumberUsages);

        when(description.getValidUsage(null, null)).thenCallRealMethod();
        assertEquals(usage1, description.getValidUsage(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getValidUsage(List, Evaluator)} when there are more than 1 valid usages with
     * minimum parameter number but only 1 has a more restrictive parameters types
     */
    @Test
    public void testGetValidUsageOnlyOneValidParameterUsageWithMoreRestrictiveTypes() {
        FormulaDescription description = mock(FormulaDescription.class);
        Set<FormulaUsage> usages = new HashSet<>();
        FormulaUsage usage1 = mock(FormulaUsage.class);
        when(usage1.isValid(null, null)).thenReturn(true);
        FormulaParameterCombination parameterCombination1 = mock(FormulaParameterCombination.class);
        FormulaParameterCombinationItem parameterCombinationItem1 = mock(FormulaParameterCombinationItem.class);
        Set<FormulaParameterCombinationItemType> parameterCombinationItem1Types = new HashSet<>();
        parameterCombinationItem1Types.add(mock(FormulaParameterCombinationItemType.class));
        when(parameterCombinationItem1.getTypes()).thenReturn(parameterCombinationItem1Types);
        when(usage1.getParameterCombination()).thenReturn(parameterCombination1);
        usages.add(usage1);
        FormulaUsage usage2 = mock(FormulaUsage.class);
        when(usage2.isValid(null, null)).thenReturn(true);
        FormulaParameterCombination parameterCombination2 = mock(FormulaParameterCombination.class);
        FormulaParameterCombinationItem parameterCombinationItem2 = mock(FormulaParameterCombinationItem.class);
        Set<FormulaParameterCombinationItemType> parameterCombinationItem2Types = new HashSet<>();
        parameterCombinationItem2Types.add(mock(FormulaParameterCombinationItemType.class));
        parameterCombinationItem2Types.add(mock(FormulaParameterCombinationItemType.class));
        when(parameterCombinationItem2.getTypes()).thenReturn(parameterCombinationItem2Types);
        when(usage2.getParameterCombination()).thenReturn(parameterCombination2);
        usages.add(usage2);
        when(description.getUsages()).thenReturn(usages);
        List<FormulaUsage> minimalParameterNumberUsages = new ArrayList<>();
        minimalParameterNumberUsages.add(usage1);
        minimalParameterNumberUsages.add(usage2);
        when(description.getUsagesWithMinimalParameterNumber(anyList())).thenReturn(minimalParameterNumberUsages);

        when(description.getValidUsage(null, null)).thenCallRealMethod();
        assertEquals(usage1, description.getValidUsage(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getBestMatchingUsage(List, Evaluator)} when there is no usage
     */
    @Test
    public void testGetBestMatchingUsageNoUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        Set<FormulaUsage> usages = new HashSet<>();
        when(description.getUsages()).thenReturn(usages);

        when(description.getBestMatchingUsage(null, null)).thenCallRealMethod();
        assertNull(description.getBestMatchingUsage(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getBestMatchingUsage(List, Evaluator)} when there is 1 usage
     */
    @Test
    public void testGetBestMatchingUsageOneUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        Set<FormulaUsage> usages = new HashSet<>();
        usages.add(usage);
        when(description.getUsages()).thenReturn(usages);

        when(description.getBestMatchingUsage(null, null)).thenCallRealMethod();
        assertEquals(usage, description.getBestMatchingUsage(null, null));
    }

    /**
     * Tests {@link FormulaDescription#getBestMatchingUsage(List, Evaluator)} when there are more than 1 usages but
     * only 1 has a matching parameter number
     */
    @Test
    public void testGetBestMatchingUsageOnlyOneMatchingParameterNumber() {
        FormulaDescription description = mock(FormulaDescription.class);
        Set<FormulaUsage> usages = new HashSet<>();
        FormulaUsage usage1 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination1 = mock(FormulaParameterCombination.class);
        when(parameterCombination1.getParametersMinimumNumber()).thenReturn(1);
        when(parameterCombination1.getParametersMaximumNumber()).thenReturn(1);
        when(usage1.getParameterCombination()).thenReturn(parameterCombination1);
        usages.add(usage1);
        FormulaUsage usage2 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination2 = mock(FormulaParameterCombination.class);
        when(parameterCombination2.getParametersMinimumNumber()).thenReturn(2);
        when(parameterCombination2.getParametersMaximumNumber()).thenReturn(2);
        when(usage2.getParameterCombination()).thenReturn(parameterCombination2);
        usages.add(usage2);
        when(description.getUsages()).thenReturn(usages);

        ArrayList<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        when(description.getBestMatchingUsage(parameters, null)).thenCallRealMethod();
        assertEquals(usage1, description.getBestMatchingUsage(parameters, null));
    }

    /**
     * Tests {@link FormulaDescription#getBestMatchingUsage(List, Evaluator)} when there are more than 1 usages with
     * matching parameter number but only 1 has minimum parameter number
     */
    @Test
    public void testGetBestMatchingUsageOnlyOneMinimalParameterNumber() {
        FormulaDescription description = mock(FormulaDescription.class);
        Set<FormulaUsage> usages = new HashSet<>();
        FormulaUsage usage1 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination1 = mock(FormulaParameterCombination.class);
        when(parameterCombination1.getParametersMinimumNumber()).thenReturn(1);
        when(parameterCombination1.getParametersMaximumNumber()).thenReturn(1);
        when(usage1.getParameterCombination()).thenReturn(parameterCombination1);
        usages.add(usage1);
        FormulaUsage usage2 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination2 = mock(FormulaParameterCombination.class);
        when(parameterCombination2.getParametersMinimumNumber()).thenReturn(1);
        when(parameterCombination2.getParametersMaximumNumber()).thenReturn(2);
        when(usage2.getParameterCombination()).thenReturn(parameterCombination2);
        usages.add(usage2);
        when(description.getUsages()).thenReturn(usages);
        List<FormulaUsage> minimalParameterNumberUsages = new ArrayList<>();
        minimalParameterNumberUsages.add(usage1);
        when(description.getUsagesWithMinimalParameterNumber(anyList())).thenReturn(minimalParameterNumberUsages);

        ArrayList<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        when(description.getBestMatchingUsage(parameters, null)).thenCallRealMethod();
        assertEquals(usage1, description.getBestMatchingUsage(parameters, null));
    }

    /**
     * Tests {@link FormulaDescription#getBestMatchingUsage(List, Evaluator)} when there are more than 1 usages with
     * matching parameter number and minimum parameter number but only 1 has a matching minimum parameter
     */
    @Test
    public void testGetBestMatchingUsageOnlyOneMatchingMinimumParameterNumber() {
        FormulaDescription description = mock(FormulaDescription.class);
        Set<FormulaUsage> usages = new HashSet<>();
        FormulaUsage usage1 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination1 = mock(FormulaParameterCombination.class);
        when(parameterCombination1.getParametersMinimumNumber()).thenReturn(1);
        when(parameterCombination1.getParametersMaximumNumber()).thenReturn(1);
        when(usage1.getParameterCombination()).thenReturn(parameterCombination1);
        usages.add(usage1);
        FormulaUsage usage2 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination2 = mock(FormulaParameterCombination.class);
        when(parameterCombination2.getParametersMinimumNumber()).thenReturn(0);
        when(parameterCombination2.getParametersMaximumNumber()).thenReturn(2);
        when(usage2.getParameterCombination()).thenReturn(parameterCombination2);
        usages.add(usage2);
        when(description.getUsages()).thenReturn(usages);
        List<FormulaUsage> minimalParameterNumberUsages = new ArrayList<>();
        minimalParameterNumberUsages.add(usage1);
        minimalParameterNumberUsages.add(usage2);
        when(description.getUsagesWithMinimalParameterNumber(anyList())).thenReturn(minimalParameterNumberUsages);

        ArrayList<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        when(description.getBestMatchingUsage(parameters, null)).thenCallRealMethod();
        assertEquals(usage1, description.getBestMatchingUsage(parameters, null));
    }

    /**
     * Tests {@link FormulaDescription#getBestMatchingUsage(List, Evaluator)} when there are more than 1 usages with
     * matching parameter number and minimum parameter number but only 1 has a matching maximum parameter
     */
    @Test
    public void testGetBestMatchingUsageOnlyOneMatchingMaximumParameterNumber() {
        FormulaDescription description = mock(FormulaDescription.class);
        Set<FormulaUsage> usages = new HashSet<>();
        FormulaUsage usage1 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination1 = mock(FormulaParameterCombination.class);
        when(parameterCombination1.getParametersMinimumNumber()).thenReturn(1);
        when(parameterCombination1.getParametersMaximumNumber()).thenReturn(1);
        when(usage1.getParameterCombination()).thenReturn(parameterCombination1);
        usages.add(usage1);
        FormulaUsage usage2 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination2 = mock(FormulaParameterCombination.class);
        when(parameterCombination2.getParametersMinimumNumber()).thenReturn(1);
        when(parameterCombination2.getParametersMaximumNumber()).thenReturn(2);
        when(usage2.getParameterCombination()).thenReturn(parameterCombination2);
        usages.add(usage2);
        when(description.getUsages()).thenReturn(usages);
        List<FormulaUsage> minimalParameterNumberUsages = new ArrayList<>();
        minimalParameterNumberUsages.add(usage1);
        minimalParameterNumberUsages.add(usage2);
        when(description.getUsagesWithMinimalParameterNumber(anyList())).thenReturn(minimalParameterNumberUsages);

        ArrayList<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));
        when(description.getBestMatchingUsage(parameters, null)).thenCallRealMethod();
        assertEquals(usage1, description.getBestMatchingUsage(parameters, null));
    }

    /**
     * Tests {@link FormulaDescription#getBestMatchingUsage(List, Evaluator)} when there are more than 1 usages with
     * matching parameter number, minimum parameter number and maximum parameter number but only 1 has matching
     * parameters types maximal number
     */
    @Test
    public void testGetBestMatchingUsageOnlyOneMatchingParameterTypesMaximalNumber() {
        ArrayList<AbstractFormula> parameters = new ArrayList<>();
        parameters.add(mock(AbstractFormula.class));

        FormulaDescription description = mock(FormulaDescription.class);
        Set<FormulaUsage> usages = new HashSet<>();
        FormulaUsage usage1 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination1 = mock(FormulaParameterCombination.class);
        when(parameterCombination1.getParametersMinimumNumber()).thenReturn(1);
        when(parameterCombination1.getParametersMaximumNumber()).thenReturn(1);
        SortedSet<FormulaParameterCombinationItem> parameterCombinationItems1 = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getId));
        FormulaParameterCombinationItem parameterCombinationItem1 = mock(FormulaParameterCombinationItem.class);
        when(parameterCombinationItem1.getId()).thenReturn(1L);
        when(parameterCombinationItem1.isValidType(parameters.get(0), null)).thenReturn(true);
        parameterCombinationItems1.add(parameterCombinationItem1);
        when(parameterCombination1.getCombinationParameters()).thenReturn(parameterCombinationItems1);
        when(usage1.getParameterCombination()).thenReturn(parameterCombination1);
        usages.add(usage1);
        FormulaUsage usage2 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination2 = mock(FormulaParameterCombination.class);
        when(parameterCombination2.getParametersMinimumNumber()).thenReturn(1);
        when(parameterCombination2.getParametersMaximumNumber()).thenReturn(1);
        SortedSet<FormulaParameterCombinationItem> parameterCombinationItems2 = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getId));
        FormulaParameterCombinationItem parameterCombinationItem2 = mock(FormulaParameterCombinationItem.class);
        when(parameterCombinationItem2.getId()).thenReturn(1L);
        when(parameterCombinationItem2.isValidType(parameters.get(0), null)).thenReturn(false);
        parameterCombinationItems2.add(parameterCombinationItem2);
        when(parameterCombination2.getCombinationParameters()).thenReturn(parameterCombinationItems2);
        when(usage2.getParameterCombination()).thenReturn(parameterCombination2);
        usages.add(usage2);
        when(description.getUsages()).thenReturn(usages);
        List<FormulaUsage> minimalParameterNumberUsages = new ArrayList<>();
        minimalParameterNumberUsages.add(usage1);
        minimalParameterNumberUsages.add(usage2);
        when(description.getUsagesWithMinimalParameterNumber(anyList())).thenReturn(minimalParameterNumberUsages);

        when(description.getBestMatchingUsage(parameters, null)).thenCallRealMethod();
        assertEquals(usage1, description.getBestMatchingUsage(parameters, null));
    }


    /**
     * Tests {@link FormulaDescription#getUsagesWithMinimalParameterNumber(List)} when there list is null
     */
    @Test
    public void testGetUsagesWithMinimalParameterNumberNullList() {
        FormulaDescription description = mock(FormulaDescription.class);

        when(description.getUsagesWithMinimalParameterNumber(null)).thenCallRealMethod();
        assertNull(description.getUsagesWithMinimalParameterNumber(null));
    }

    /**
     * Tests {@link FormulaDescription#getUsagesWithMinimalParameterNumber(List)} when there list is empty
     */
    @Test
    public void testGetUsagesWithMinimalParameterNumberEmptyList() {
        FormulaDescription description = mock(FormulaDescription.class);
        List<FormulaUsage> usages = new ArrayList<>();

        when(description.getUsagesWithMinimalParameterNumber(usages)).thenCallRealMethod();
        assertNull(description.getUsagesWithMinimalParameterNumber(usages));
    }

    /**
     * Tests {@link FormulaDescription#getUsagesWithMinimalParameterNumber(List)} when there is 1 usage in the list
     */
    @Test
    public void testGetUsagesWithMinimalParameterNumberOneUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        FormulaUsage usage = mock(FormulaUsage.class);
        List<FormulaUsage> usages = new ArrayList<>();
        usages.add(usage);

        when(description.getUsagesWithMinimalParameterNumber(usages)).thenCallRealMethod();
        assertEquals(1, description.getUsagesWithMinimalParameterNumber(usages).size());
        assertTrue(description.getUsagesWithMinimalParameterNumber(usages).contains(usage));
    }

    /**
     * Tests {@link FormulaDescription#getUsagesWithMinimalParameterNumber(List)} when there are more than 1 usages in
     * the list but only 1 has smallest minimum parameter number
     */
    @Test
    public void testGetUsagesWithMinimalParameterNumberOneSmallestMinimumParameterUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        List<FormulaUsage> usages = new ArrayList<>();
        FormulaUsage usage1 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination1 = mock(FormulaParameterCombination.class);
        when(parameterCombination1.getParametersMinimumNumber()).thenReturn(1);
        when(usage1.getParameterCombination()).thenReturn(parameterCombination1);
        usages.add(usage1);
        FormulaUsage usage2 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination2 = mock(FormulaParameterCombination.class);
        when(parameterCombination2.getParametersMinimumNumber()).thenReturn(2);
        when(usage2.getParameterCombination()).thenReturn(parameterCombination2);
        usages.add(usage2);

        when(description.getUsagesWithMinimalParameterNumber(usages)).thenCallRealMethod();
        assertEquals(1, description.getUsagesWithMinimalParameterNumber(usages).size());
        assertTrue(description.getUsagesWithMinimalParameterNumber(usages).contains(usage1));
    }

    /**
     * Tests {@link FormulaDescription#getUsagesWithMinimalParameterNumber(List)} when there are more than 1 usages in
     * the list but only 1 has smallest minimum parameter number
     */
    @Test
    public void testGetUsagesWithMinimalParameterNumberOneSmallestMaximumParameterUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        List<FormulaUsage> usages = new ArrayList<>();
        FormulaUsage usage1 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination1 = mock(FormulaParameterCombination.class);
        when(parameterCombination1.getParametersMinimumNumber()).thenReturn(1);
        when(parameterCombination1.getParametersMaximumNumber()).thenReturn(1);
        when(usage1.getParameterCombination()).thenReturn(parameterCombination1);
        usages.add(usage1);
        FormulaUsage usage2 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination2 = mock(FormulaParameterCombination.class);
        when(parameterCombination2.getParametersMinimumNumber()).thenReturn(1);
        when(parameterCombination2.getParametersMaximumNumber()).thenReturn(2);
        when(usage2.getParameterCombination()).thenReturn(parameterCombination2);
        usages.add(usage2);

        when(description.getUsagesWithMinimalParameterNumber(usages)).thenCallRealMethod();
        assertEquals(1, description.getUsagesWithMinimalParameterNumber(usages).size());
        assertTrue(description.getUsagesWithMinimalParameterNumber(usages).contains(usage1));
    }

    /**
     * Tests {@link FormulaDescription#getUsagesWithMinimalParameterNumber(List)} when there are more than 1 usages in
     * the list with smallest minimum parameter number
     */
    @Test
    public void testGetUsagesWithMinimalParameterNumberMoreThanOneSmallestMaximumParameterUsage() {
        FormulaDescription description = mock(FormulaDescription.class);
        List<FormulaUsage> usages = new ArrayList<>();
        FormulaUsage usage1 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination1 = mock(FormulaParameterCombination.class);
        when(parameterCombination1.getParametersMinimumNumber()).thenReturn(1);
        when(parameterCombination1.getParametersMaximumNumber()).thenReturn(1);
        when(usage1.getParameterCombination()).thenReturn(parameterCombination1);
        usages.add(usage1);
        FormulaUsage usage2 = mock(FormulaUsage.class);
        FormulaParameterCombination parameterCombination2 = mock(FormulaParameterCombination.class);
        when(parameterCombination2.getParametersMinimumNumber()).thenReturn(1);
        when(parameterCombination2.getParametersMaximumNumber()).thenReturn(1);
        when(usage2.getParameterCombination()).thenReturn(parameterCombination2);
        usages.add(usage2);

        when(description.getUsagesWithMinimalParameterNumber(usages)).thenCallRealMethod();
        assertEquals(2, description.getUsagesWithMinimalParameterNumber(usages).size());
        assertTrue(description.getUsagesWithMinimalParameterNumber(usages).contains(usage1));
        assertTrue(description.getUsagesWithMinimalParameterNumber(usages).contains(usage2));
    }

}
