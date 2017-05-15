package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

/**
 * Test class for {@link FormulaDescription}
 * 
 * @author DUPIREFR
 * @author MMI
 */
public class FormulaDescriptionTest  extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDescription} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		FormulaDescription formulaDescription = getEntityManager().find(FormulaDescription.class, 214);
		
		/* Checking entity */
		assertNotNull(formulaDescription);
		
		assertEquals(Integer.valueOf(214), formulaDescription.getId());
		
		assertEquals(FormulaType.OP_OR, formulaDescription.getType());
		
		assertEquals("OR", formulaDescription.getName());
				
		assertEquals("'OU' logique", formulaDescription.getDescription(Language.FR));
		assertEquals("Logische 'OF'", formulaDescription.getDescription(Language.NL));
		assertNull(formulaDescription.getDescription(Language.X));
		assertEquals("Logical 'OR'", formulaDescription.getDescription(Language.EN));
		
		assertEquals(FormulaPriority.OR, formulaDescription.getPriority());
		
		/* Checking relationships */
		assertNotNull(formulaDescription.getUsages());
		assertEquals(1, formulaDescription.getUsages().size());
		assertEquals(Integer.valueOf(96), formulaDescription.getUsages().iterator().next().getId());
	}

//
//	/**
//	 * Tests {@link FormulaDescription#getReturnType(List)} when list is empty or null and there is
//	 * an empty combination
//	 */
//	@Test
//	public void testGetReturnTypeEmptyEmpty() {
//		FormulaDescription formulaDescription = new FormulaDescription();
//
//		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
//
//		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
//		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
//		parametersCombinations.add(parametersCombination);
//
//		formulaDescription.setParametersCombinations(parametersCombinations);
//
//		assertEquals(FormulaReturnType.BOOLEAN, formulaDescription.getReturnType(null));
//		assertEquals(FormulaReturnType.BOOLEAN, formulaDescription.getReturnType(Arrays.asList()));
//	}
//
//	/**
//	 * Tests {@link FormulaDescription#getReturnType(List)} when list is empty or null, but there is
//	 * no empty combination
//	 */
//	@Test
//	public void testGetReturnTypeEmptyNotEmpty() {
//		FormulaDescription formulaDescription = new FormulaDescription();
//
//		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
//
//		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
//		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
//
//		SortedSet<FormulaParameterDescription> parametersDescriptions = new TreeSet<>();
//
//		FormulaParameterDescription parameterDescription = mock(FormulaParameterDescription.class);
//		when(parameterDescription.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
//		parametersDescriptions.add(parameterDescription);
//
//		when(parametersCombination.getParametersDescriptions()).thenReturn(parametersDescriptions);
//		parametersCombinations.add(parametersCombination);
//
//		formulaDescription.setParametersCombinations(parametersCombinations);
//
//		assertNull(formulaDescription.getReturnType(null));
//		assertNull(formulaDescription.getReturnType(Arrays.asList()));
//	}
//
//	/**
//	 * Tests {@link FormulaDescription#getReturnType(List)} when list is not empty, but there is
//	 * only empty combination
//	 */
//	@Test
//	public void testGetReturnTypeNotEmptyEmpty() {
//		FormulaDescription formulaDescription = new FormulaDescription();
//
//		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
//
//		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
//		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
//		parametersCombinations.add(parametersCombination);
//
//		formulaDescription.setParametersCombinations(parametersCombinations);
//
//		assertNull(formulaDescription.getReturnType(Arrays.asList(FormulaReturnType.BOOLEAN)));
//	}
//
//	/**
//	 * Tests {@link FormulaDescription#getReturnType(List)} when list matches a combination
//	 */
//	@Test
//	public void testGetReturnTypeMatching() {
//		FormulaDescription formulaDescription = new FormulaDescription();
//
//		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
//
//		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
//		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
//
//		SortedSet<FormulaParameterDescription> parametersDescriptions = new TreeSet<>();
//
//		FormulaParameterDescription parameterDescription1 = mock(FormulaParameterDescription.class);
//		when(parameterDescription1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
//		parametersDescriptions.add(parameterDescription1);
//
//		FormulaParameterDescription parameterDescription2 = mock(FormulaParameterDescription.class);
//		when(parameterDescription2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
//		parametersDescriptions.add(parameterDescription2);
//
//		when(parametersCombination.getParametersDescriptions()).thenReturn(parametersDescriptions);
//		parametersCombinations.add(parametersCombination);
//
//		formulaDescription.setParametersCombinations(parametersCombinations);
//
//		assertEquals(FormulaReturnType.BOOLEAN,
//				formulaDescription.getReturnType(Arrays.asList(FormulaReturnType.BOOLEAN, FormulaReturnType.BOOLEAN)));
//	}
//
//	/**
//	 * Tests {@link FormulaDescription#getReturnType(List)} when list doesn't match any combination
//	 */
//	@Test
//	public void testGetReturnTypeNotMatching() {
//		FormulaDescription formulaDescription = new FormulaDescription();
//
//		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
//
//		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
//		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
//
//		SortedSet<FormulaParameterDescription> parametersDescriptions = new TreeSet<>();
//
//		FormulaParameterDescription parameterDescription1 = mock(FormulaParameterDescription.class);
//		when(parameterDescription1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
//		parametersDescriptions.add(parameterDescription1);
//
//		FormulaParameterDescription parameterDescription2 = mock(FormulaParameterDescription.class);
//		when(parameterDescription2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
//		parametersDescriptions.add(parameterDescription2);
//
//		when(parametersCombination.getParametersDescriptions()).thenReturn(parametersDescriptions);
//		parametersCombinations.add(parametersCombination);
//
//		formulaDescription.setParametersCombinations(parametersCombinations);
//
//		assertNull(formulaDescription.getReturnType(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.BOOLEAN)));
//	}
//
}
