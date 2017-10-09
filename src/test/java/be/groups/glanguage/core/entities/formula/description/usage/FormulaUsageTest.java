package be.groups.glanguage.core.entities.formula.description.usage;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.combination.FormulaParameterCombination;
import be.groups.glanguage.core.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.core.entities.utils.MultilingualString;
import be.groups.glanguage.core.entities.utils.MultilingualStringItem;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.description.combination.FormulaParameterCombinationUnableToValidateInnerError;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by michotte on 3/08/2017.
 */
public class FormulaUsageTest {

    /**
     * Tests {@link FormulaUsage#validate(AbstractFormula, List, Evaluator)}
     * when {@link FormulaParameterCombination#validate(AbstractFormula, FormulaUsage, List, Evaluator)} does not
     * throw an exception
     */
    @Test
    public void validateValidCombination() {
        FormulaUsage usage = Mockito.spy(FormulaUsage.class);
        FormulaParameterCombination combination = mock(FormulaParameterCombination.class);
        try {
            doNothing().when(combination).validate(null, usage, null, null);
        } catch (GLanguageException e) {
            fail("Exception thrown" + e);
        }
        doReturn(combination).when(usage).getParameterCombination();
        try {
            usage.validate(null, null, null);
        } catch (GLanguageException e) {
            fail("Exception thrown" + e);
        }
    }

    /**
     * Tests {@link FormulaUsage#validate(AbstractFormula, List, Evaluator)}
     * when {@link FormulaParameterCombination#validate(AbstractFormula, FormulaUsage, List, Evaluator)} throws an
     * exception
     */
    @Test(expected = GLanguageException.class)
    public void validateNotValidCombination() throws GLanguageException {
        AbstractFormula formula = mock(AbstractFormula.class);
        FormulaUsage usage = Mockito.spy(FormulaUsage.class);
        FormulaParameterCombination combination = mock(FormulaParameterCombination.class);
        GLanguageException exception = new GLanguageException(new FormulaParameterCombinationUnableToValidateInnerError(
                formula,
                combination,
                null));
        try {
            doThrow(exception).when(combination).validate(null, usage, null, null);
        } catch (GLanguageException e) {
            fail("Exception thrown" + e);
        }
        doReturn(combination).when(usage).getParameterCombination();
        try {
            usage.validate(null, null, null);
        } catch (GLanguageException e) {
            InnerError ie = e.getError().getInnerError();
            assertNotNull("inner error is null", ie);
            assertEquals(exception, e);
            throw e;
        }
    }

    /**
     * Tests {@link FormulaUsage#isValid(List, Evaluator)}
     * when {@link FormulaParameterCombination#isValid(List, Evaluator)} is true
     */
    @Test
    public void isValidValidCombination() {
        FormulaParameterCombination combination = mock(FormulaParameterCombination.class);
        doReturn(true).when(combination).isValid(null, null);

        FormulaUsage usage = Mockito.spy(FormulaUsage.class);
        doReturn(combination).when(usage).getParameterCombination();
        assertTrue(usage.isValid(null, null));
    }

    /**
     * Tests {@link FormulaUsage#isValid(List, Evaluator)}
     * when {@link FormulaParameterCombination#isValid(List, Evaluator)} is false
     */
    @Test
    public void isValidNotValidCombination() {
        FormulaParameterCombination combination = mock(FormulaParameterCombination.class);
        doReturn(false).when(combination).isValid(null, null);

        FormulaUsage usage = Mockito.spy(FormulaUsage.class);
        doReturn(combination).when(usage).getParameterCombination();
        assertFalse(usage.isValid(null, null));
    }

    /**
     * Test {@link FormulaUsage#getParameterName(FormulaParameterCombinationItem)}
     * when there is no overridden parameter name
     */
    @Test
    public void getParameterNameDescriptionNotOverridden() {
        FormulaParameterCombination combination = mock(FormulaParameterCombination.class);
        FormulaParameterCombinationItem combinationItem = mock(FormulaParameterCombinationItem.class);
        MultilingualString multilingualName = new MultilingualString();
        MultilingualStringItem multilingualStringItemName = new MultilingualStringItem();
        multilingualStringItemName.setId(1L);
        Set<MultilingualStringItem> multilingualStringItemsName = new HashSet<>();
        multilingualStringItemsName.add(multilingualStringItemName);
        multilingualName.setItems(multilingualStringItemsName);
        doReturn(multilingualName).when(combinationItem).getName();
        MultilingualString multilingualDescription = new MultilingualString();
        MultilingualStringItem multilingualStringItemDescription = new MultilingualStringItem();
        multilingualStringItemDescription.setId(2L);
        Set<MultilingualStringItem> multilingualStringItemsDescription = new HashSet<>();
        multilingualStringItemsDescription.add(multilingualStringItemDescription);
        multilingualDescription.setItems(multilingualStringItemsDescription);
        doReturn(multilingualDescription).when(combinationItem).getDescription();
        SortedSet<FormulaParameterCombinationItem> combinationItems = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationItems.add(combinationItem);
        doReturn(combinationItems).when(combination).getCombinationParameters();

        FormulaUsage usage = Mockito.spy(FormulaUsage.class);
        doReturn(null).when(usage).getOverriddenParameters();
        assertEquals("name not returned", multilingualName, usage.getParameterName(combinationItem));
        assertEquals("description not returned",
                     multilingualDescription,
                     usage.getParameterDescription(combinationItem));
    }

    @Test
    public void getParameterNameDescriptionOverridden() {
        FormulaParameterCombination combination = mock(FormulaParameterCombination.class);
        FormulaParameterCombinationItem combinationItem = mock(FormulaParameterCombinationItem.class);
        MultilingualString multilingualName = new MultilingualString();
        MultilingualStringItem multilingualStringItemName = new MultilingualStringItem();
        multilingualStringItemName.setId(1L);
        Set<MultilingualStringItem> multilingualStringItemsName = new HashSet<>();
        multilingualStringItemsName.add(multilingualStringItemName);
        multilingualName.setItems(multilingualStringItemsName);
        doReturn(multilingualName).when(combinationItem).getName();
        MultilingualString multilingualDescription = new MultilingualString();
        MultilingualStringItem multilingualStringItemDescription = new MultilingualStringItem();
        multilingualStringItemDescription.setId(2L);
        Set<MultilingualStringItem> multilingualStringItemsDescription = new HashSet<>();
        multilingualStringItemsDescription.add(multilingualStringItemDescription);
        multilingualDescription.setItems(multilingualStringItemsDescription);
        doReturn(multilingualDescription).when(combinationItem).getDescription();
        SortedSet<FormulaParameterCombinationItem> combinationItems = new TreeSet<>(Comparator.comparing(
                FormulaParameterCombinationItem::getSequenceNumber));
        combinationItems.add(combinationItem);
        doReturn(combinationItems).when(combination).getCombinationParameters();

        FormulaUsage usage = Mockito.spy(FormulaUsage.class);
        List<FormulaUsageParameterCombinationItem> overriddenParameters = new ArrayList<>();
        FormulaUsageParameterCombinationItem overriddenParameter = mock(FormulaUsageParameterCombinationItem.class);
        doReturn(combinationItem).when(overriddenParameter).getCombinationParameter();
        MultilingualString overriddenMultilingualName = new MultilingualString();
        MultilingualStringItem overriddenMultilingualStringItemName = new MultilingualStringItem();
        overriddenMultilingualStringItemName.setId(3L);
        Set<MultilingualStringItem> overriddenMultilingualStringItemsName = new HashSet<>();
        overriddenMultilingualStringItemsName.add(overriddenMultilingualStringItemName);
        overriddenMultilingualName.setItems(overriddenMultilingualStringItemsName);
        doReturn(overriddenMultilingualName).when(overriddenParameter).getName();
        MultilingualString overriddenMultilingualDescription = new MultilingualString();
        MultilingualStringItem overriddenMultilingualStringItemDescription = new MultilingualStringItem();
        overriddenMultilingualStringItemDescription.setId(4L);
        Set<MultilingualStringItem> overriddenMultilingualStringItemsDescription = new HashSet<>();
        overriddenMultilingualStringItemsDescription.add(overriddenMultilingualStringItemDescription);
        overriddenMultilingualDescription.setItems(overriddenMultilingualStringItemsDescription);
        doReturn(overriddenMultilingualDescription).when(overriddenParameter).getDescription();
        overriddenParameters.add(overriddenParameter);
        doReturn(overriddenParameters).when(usage).getOverriddenParameters();
        assertEquals("overridden name not returned",
                     overriddenMultilingualName,
                     usage.getParameterName(combinationItem));
        assertEquals("overridden description not returned",
                     overriddenMultilingualDescription,
                     usage.getParameterDescription(combinationItem));
    }

}
