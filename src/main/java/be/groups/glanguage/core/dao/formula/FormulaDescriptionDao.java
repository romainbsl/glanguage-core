package be.groups.glanguage.core.dao.formula;

import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link FormulaDescription} <p>
 *
 * @author michotte
 */
@Repository
public interface FormulaDescriptionDao extends JpaRepository<FormulaDescription, Long> {}
