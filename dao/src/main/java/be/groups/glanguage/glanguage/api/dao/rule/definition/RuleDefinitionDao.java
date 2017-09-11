package be.groups.glanguage.glanguage.api.dao.rule.definition;

import be.groups.glanguage.glanguage.api.entities.rule.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

/**
 * DAO for {@link RuleDefinition} <p>
 */
@Repository
public interface RuleDefinitionDao extends JpaRepository<RuleDefinition, Integer> {
  /**
   * Find a {@link RuleDefinition} by id by {@code ruleDefinitionId} with all his children
   *
   * @param ruleDefinitionId the identifier of the {@link RuleDefinition} to be returned
   * @return The {@link RuleDefinition} identified by {@code id} with all his children, {@Link
   * RuleDefinition#definitionParameters} + {@Link RuleDefinition#versions}, or null if it doesn't
   * exists
   */
  @Deprecated // TODO must use super.findById() in a @Transactional state
  @Query("select rd from RuleDefinition rd "
      + " join fetch rd.versions rv "
      + " join fetch rd.definitionParameters dp "
      + " where rd.id = :ruleDefinitionId "
      + " and rv.ruleDefinition = rd "
      + " and dp.ruleDefinition = rd ")
  // TODO Check with micmax for Set items
  RuleDefinition findEagerById(@Param("ruleDefinitionId") Integer ruleDefinitionId);
}
