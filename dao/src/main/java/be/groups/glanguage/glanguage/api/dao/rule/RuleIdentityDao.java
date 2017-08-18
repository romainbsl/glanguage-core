package be.groups.glanguage.glanguage.api.dao.rule;

import be.groups.glanguage.glanguage.api.entities.rule.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

/**
 * DAO for {@link RuleIdentity} <p>
 */
@Repository
public interface RuleIdentityDao extends JpaRepository<RuleIdentity, Integer> {

  /**
   * Find a {@link RuleIdentity} by id by {@code ruleSetVersionId} with all his children
   *
   * @param ruleIdentityId the identifier of the {@link RuleIdentity} to be returned
   * @return The {@link RuleIdentity} identified by {@code id} with all his children, {@Link
   * RuleIdentity#ruleDefinitions}, or null if it doesn't exists
   */
  @Query("Select ri from RuleIdentity ri "
      + " join fetch ri.ruleDefinitions rd "
      + " where ri.id = :ruleIdentityId "
      + " and rd.ruleIdentity = ri ")
  RuleIdentity findEagerById(@Param("ruleIdentityId") Integer ruleIdentityId);
}
