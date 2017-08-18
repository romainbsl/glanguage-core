package be.groups.glanguage.glanguage.api.dao.rule;

import be.groups.glanguage.glanguage.api.entities.rule.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

/**
 * DAO for {@link RuleDescription} <p>
 */
@Repository
public interface RuleDescriptionDao extends JpaRepository<RuleDescription, Integer> {

  /**
   * Find a {@link RuleDescription} by id by {@code ruleDescriptionId} with all his children
   *
   * @param ruleDescriptionId the identifier of the {@link RuleDescription} to be returned
   * @return The {@link RuleDescription} identified by {@code id} with all his children, {@Link
   * RuleDescription#ruleVersions}, or null if it doesn't exists
   */
  @Query("select rd from RuleDescription rd "
      + " join fetch rd.ruleVersions rv "
      + " where rd.id = :ruleDescriptionId "
      + " and rv.ruleDescription = rd ")
  RuleDescription findEagerById(@Param("ruleDescriptionId") Integer ruleDescriptionId);
}
