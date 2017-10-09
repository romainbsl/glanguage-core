package be.groups.glanguage.core.dao.formula.description.combination;

import be.groups.glanguage.core.entities.formula.description.combination.FormulaParameterCombination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link FormulaParameterCombination} <p>
 */
@Repository
public interface FormulaParameterCombinationDao extends
                                                JpaRepository<FormulaParameterCombination,
                                                    Long> {}
