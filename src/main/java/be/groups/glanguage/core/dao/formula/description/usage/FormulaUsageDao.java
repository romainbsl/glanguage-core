package be.groups.glanguage.core.dao.formula.description.usage;

import be.groups.glanguage.core.entities.formula.description.usage.FormulaUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link FormulaUsage} <p>
 */
@Repository
public interface FormulaUsageDao extends JpaRepository<FormulaUsage, Long> {}
