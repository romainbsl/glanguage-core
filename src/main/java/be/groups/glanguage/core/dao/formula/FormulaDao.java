package be.groups.glanguage.core.dao.formula;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link AbstractFormula} <p>
 *
 * @author michotte
 */
@Repository
public interface FormulaDao extends JpaRepository<AbstractFormula, Long> {}
