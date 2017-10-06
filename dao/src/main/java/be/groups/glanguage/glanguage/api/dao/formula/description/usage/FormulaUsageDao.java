package be.groups.glanguage.glanguage.api.dao.formula.description.usage;

import be.groups.glanguage.glanguage.api.entities.formula.description.usage.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

/**
 * DAO for {@link FormulaUsage} <p>
 */
@Repository
public interface FormulaUsageDao extends JpaRepository<FormulaUsage, Long> {}
