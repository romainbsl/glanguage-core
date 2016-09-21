package be.groups.glanguage.glanguage.api.entities.formula.description;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

/**
 * Test class for {@link FormulaDescription}
 * 
 * @author DUPIREFR
 */
public class FormulaDescriptionTest {
	
	/*
	 * Static fields
	 */
	private static EntityManager em;
	
	/*
	 * Setups
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		Environment.setUp();
		TNSNames.setUp();
		
		JpaUtil.setEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));
		
		if (!TransactionHelper.isActive()) {
			TransactionHelper.begin();
		}
		
		em = JpaUtil.getEntityManager();
	}
	
	@AfterClass
	public static void close() {
		if (TransactionHelper.isActive()) {
			TransactionHelper.rollback();
		}
	}
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDescription} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		FormulaDescription formulaDescription = em.find(FormulaDescription.class, 214);
		
		/* Checking entity */
		assertNotNull(formulaDescription);
		
		assertEquals(Integer.valueOf(214), formulaDescription.getId());
		
		assertEquals(FormulaType.OP_OR, formulaDescription.getType());
		
		assertEquals("OR", formulaDescription.getName());
		
		assertEquals("Opération booléenne OU", formulaDescription.getDescriptionFr());
		assertEquals("OF boolean operatie", formulaDescription.getDescriptionNl());
		assertNull(formulaDescription.getDescriptionDe());
		assertEquals("OR boolean operator", formulaDescription.getDescriptionX());
		
		assertEquals(FormulaPriority.OR, formulaDescription.getPriority());
		
		/* Checking relationships */
		assertNotNull(formulaDescription.getParametersCombinations());
		assertEquals(1, formulaDescription.getParametersCombinations().size());
		assertEquals(Integer.valueOf(3), formulaDescription.getParametersCombinations().iterator().next().getId());
	}
	
	/**
	 * Tests {@link FormulaDescription#isValid(List)} when list is empty or null and there is an
	 * empty combination
	 */
	@Test
	public void testIsValidEmptyEmpty() {
		FormulaDescription formulaDescription = new FormulaDescription();
		
		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
		
		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersCombinations.add(parametersCombination);
		
		formulaDescription.setParametersCombinations(parametersCombinations);
		
		assertTrue(formulaDescription.isValid(null));
		assertTrue(formulaDescription.isValid(Arrays.asList()));
	}
	
	/**
	 * Tests {@link FormulaDescription#isValid(List)} when list is empty or null, but there is no
	 * empty combination
	 */
	@Test
	public void testIsValidEmptyNotEmpty() {
		FormulaDescription formulaDescription = new FormulaDescription();
		
		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
		
		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		SortedSet<FormulaParameterDescription> parametersDescriptions = new TreeSet<>();
		
		FormulaParameterDescription parameterDescription = mock(FormulaParameterDescription.class);
		when(parameterDescription.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersDescriptions.add(parameterDescription);
		
		when(parametersCombination.getParametersDescriptions()).thenReturn(parametersDescriptions);
		parametersCombinations.add(parametersCombination);
		
		formulaDescription.setParametersCombinations(parametersCombinations);
		
		assertFalse(formulaDescription.isValid(null));
		assertFalse(formulaDescription.isValid(Arrays.asList()));
	}
	
	/**
	 * Tests {@link FormulaDescription#isValid(List)} when list is not empty, but there is only
	 * empty combination
	 */
	@Test
	public void testIsValidNotEmptyEmpty() {
		FormulaDescription formulaDescription = new FormulaDescription();
		
		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
		
		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersCombinations.add(parametersCombination);
		
		formulaDescription.setParametersCombinations(parametersCombinations);
		
		assertFalse(formulaDescription.isValid(Arrays.asList(FormulaReturnType.BOOLEAN)));
	}
	
	/**
	 * Tests {@link FormulaDescription#isValid(List)} when list matches a combination
	 */
	@Test
	public void testIsValidMatching() {
		FormulaDescription formulaDescription = new FormulaDescription();
		
		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
		
		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		SortedSet<FormulaParameterDescription> parametersDescriptions = new TreeSet<>();
		
		FormulaParameterDescription parameterDescription1 = mock(FormulaParameterDescription.class);
		when(parameterDescription1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersDescriptions.add(parameterDescription1);
		
		FormulaParameterDescription parameterDescription2 = mock(FormulaParameterDescription.class);
		when(parameterDescription2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersDescriptions.add(parameterDescription2);
		
		when(parametersCombination.getParametersDescriptions()).thenReturn(parametersDescriptions);
		parametersCombinations.add(parametersCombination);
		
		formulaDescription.setParametersCombinations(parametersCombinations);
		
		assertTrue(formulaDescription.isValid(Arrays.asList(FormulaReturnType.BOOLEAN, FormulaReturnType.BOOLEAN)));
	}
	
	/**
	 * Tests {@link FormulaDescription#isValid(List)} when list doesn't match any combination
	 */
	@Test
	public void testIsValidNotMatching() {
		FormulaDescription formulaDescription = new FormulaDescription();
		
		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
		
		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		SortedSet<FormulaParameterDescription> parametersDescriptions = new TreeSet<>();
		
		FormulaParameterDescription parameterDescription1 = mock(FormulaParameterDescription.class);
		when(parameterDescription1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersDescriptions.add(parameterDescription1);
		
		FormulaParameterDescription parameterDescription2 = mock(FormulaParameterDescription.class);
		when(parameterDescription2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersDescriptions.add(parameterDescription2);
		
		when(parametersCombination.getParametersDescriptions()).thenReturn(parametersDescriptions);
		parametersCombinations.add(parametersCombination);
		
		formulaDescription.setParametersCombinations(parametersCombinations);
		
		assertFalse(formulaDescription.isValid(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.BOOLEAN)));
	}
	
	/**
	 * Tests {@link FormulaDescription#getReturnType(List)} when list is empty or null and there is
	 * an empty combination
	 */
	@Test
	public void testGetReturnTypeEmptyEmpty() {
		FormulaDescription formulaDescription = new FormulaDescription();
		
		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
		
		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersCombinations.add(parametersCombination);
		
		formulaDescription.setParametersCombinations(parametersCombinations);
		
		assertEquals(FormulaReturnType.BOOLEAN, formulaDescription.getReturnType(null));
		assertEquals(FormulaReturnType.BOOLEAN, formulaDescription.getReturnType(Arrays.asList()));
	}
	
	/**
	 * Tests {@link FormulaDescription#getReturnType(List)} when list is empty or null, but there is
	 * no empty combination
	 */
	@Test
	public void testGetReturnTypeEmptyNotEmpty() {
		FormulaDescription formulaDescription = new FormulaDescription();
		
		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
		
		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		SortedSet<FormulaParameterDescription> parametersDescriptions = new TreeSet<>();
		
		FormulaParameterDescription parameterDescription = mock(FormulaParameterDescription.class);
		when(parameterDescription.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersDescriptions.add(parameterDescription);
		
		when(parametersCombination.getParametersDescriptions()).thenReturn(parametersDescriptions);
		parametersCombinations.add(parametersCombination);
		
		formulaDescription.setParametersCombinations(parametersCombinations);
		
		assertNull(formulaDescription.getReturnType(null));
		assertNull(formulaDescription.getReturnType(Arrays.asList()));
	}
	
	/**
	 * Tests {@link FormulaDescription#getReturnType(List)} when list is not empty, but there is
	 * only empty combination
	 */
	@Test
	public void testGetReturnTypeNotEmptyEmpty() {
		FormulaDescription formulaDescription = new FormulaDescription();
		
		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
		
		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersCombinations.add(parametersCombination);
		
		formulaDescription.setParametersCombinations(parametersCombinations);
		
		assertNull(formulaDescription.getReturnType(Arrays.asList(FormulaReturnType.BOOLEAN)));
	}
	
	/**
	 * Tests {@link FormulaDescription#getReturnType(List)} when list matches a combination
	 */
	@Test
	public void testGetReturnTypeMatching() {
		FormulaDescription formulaDescription = new FormulaDescription();
		
		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
		
		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		SortedSet<FormulaParameterDescription> parametersDescriptions = new TreeSet<>();
		
		FormulaParameterDescription parameterDescription1 = mock(FormulaParameterDescription.class);
		when(parameterDescription1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersDescriptions.add(parameterDescription1);
		
		FormulaParameterDescription parameterDescription2 = mock(FormulaParameterDescription.class);
		when(parameterDescription2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersDescriptions.add(parameterDescription2);
		
		when(parametersCombination.getParametersDescriptions()).thenReturn(parametersDescriptions);
		parametersCombinations.add(parametersCombination);
		
		formulaDescription.setParametersCombinations(parametersCombinations);
		
		assertEquals(FormulaReturnType.BOOLEAN,
				formulaDescription.getReturnType(Arrays.asList(FormulaReturnType.BOOLEAN, FormulaReturnType.BOOLEAN)));
	}
	
	/**
	 * Tests {@link FormulaDescription#getReturnType(List)} when list doesn't match any combination
	 */
	@Test
	public void testGetReturnTypeNotMatching() {
		FormulaDescription formulaDescription = new FormulaDescription();
		
		Set<FormulaParametersCombination> parametersCombinations = new HashSet<>();
		
		FormulaParametersCombination parametersCombination = mock(FormulaParametersCombination.class);
		when(parametersCombination.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		SortedSet<FormulaParameterDescription> parametersDescriptions = new TreeSet<>();
		
		FormulaParameterDescription parameterDescription1 = mock(FormulaParameterDescription.class);
		when(parameterDescription1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersDescriptions.add(parameterDescription1);
		
		FormulaParameterDescription parameterDescription2 = mock(FormulaParameterDescription.class);
		when(parameterDescription2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		parametersDescriptions.add(parameterDescription2);
		
		when(parametersCombination.getParametersDescriptions()).thenReturn(parametersDescriptions);
		parametersCombinations.add(parametersCombination);
		
		formulaDescription.setParametersCombinations(parametersCombinations);
		
		assertNull(formulaDescription.getReturnType(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.BOOLEAN)));
	}
	
}
