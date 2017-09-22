package be.groups.glanguage.glanguage.api.dao.formula;

import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 * DAO for {@link FormulaDescription} <p>
 *
 * @author michotte
 */
@Repository
public interface FormulaDescriptionDao extends JpaRepository<FormulaDescription, Long> {}
