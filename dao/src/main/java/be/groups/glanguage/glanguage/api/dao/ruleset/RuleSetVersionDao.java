package be.groups.glanguage.glanguage.api.dao.ruleset;

import be.groups.glanguage.glanguage.api.entities.ruleset.*;
import java.time.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

/**
 * DAO for {@link RuleSetVersion} <p>
 *
 * @author michotte
 */
@Repository
public interface RuleSetVersionDao extends JpaRepository<RuleSetVersion, Integer> {

  /**
   * Find a {@link RuleSetVersion} by {@code ruleSetVersionId} with all his children
   *
   * @param ruleSetVersionId the identifier of the {@link RuleSetVersion} to be returned
   * @return The {@link RuleSetVersion} identified by {@code ruleSetVersionId} with all his children,
   * {@link RuleSetVersion#children} + {@link RuleSetVersion#includedIn} +
   * {@link RuleSetVersion#includes},
   * or null if it doesn't exists
   */
  @Query(" select rsv from RuleSetVersion rsv "
      + " join fetch rsv.children ch "
      + " left join fetch rsv.includes "
      + " left join fetch rsv.includedIn "
      + " where rsv.id = :ruleSetVersionId "
      + " and ch.parent = rsv ")
  RuleSetVersion findEagerById(@Param("ruleSetVersionId") Integer ruleSetVersionId);

  /**
   * Find a {@link RuleSetVersion} by {@code ruleSetId} and {@code version}
   *
   * @param ruleSetId the identifier of the {@link RuleSet} of the {@link RuleSetVersion} to be
   * returned
   * @param version the version of the {@link RuleSetVersion} to be returned
   * @return The {@link RuleSetVersion} of the {@link RuleSet} identified by {@code ruleSetId}
   * corresponding to the {@code version}, or null if it doesn't exists
   */
  @Query("select rsv from RuleSetVersion rsv "
      + " where rsv.ruleSet.id = :ruleSetId "
      + " and rsv.version = :version")
  RuleSetVersion findByRuleSetIdAndVersion(@Param("ruleSetId") Integer ruleSetId,
                                           @Param("version") String version);

  /**
   * Find a {@link RuleSetVersion} by {@code ruleSetAlias} and {@code version}
   *
   * @param ruleSetAlias the alias of the {@link RuleSet} of the {@link RuleSetVersion} to be
   * returned
   * @param version the version of the {@link RuleSetVersion} to be returned
   * @return The {@link RuleSetVersion} of the {@link RuleSet} identified by {@code ruleSetAlias}
   * orresponding to the {@code version}, or null if it doesn't exists
   */
  @Query(("select rsv from RuleSetVersion rsv join rsv.ruleSet rs "
      + " where exists ("
      + " select msi from MultilingualString ms, MultilingualStringItem msi "
      + " where ms.id = rs.alias.id and msi.multilingualString.id = ms.id "
      + " and dbms_lob.compare(msi.text, :ruleSetAlias) = 0"
      + ")"
      + " and rsv.version = :version"))
  RuleSetVersion findByRuleSetAliasAndVersion(@Param("ruleSetAlias") String ruleSetAlias,
                                              @Param("version") String version);

  /**
   * Find a {@link RuleSetVersion} by {@code rulSetId} and {@code productionDate}
   *
   * @param ruleSetId the identifier of the {@link RuleSet} of the {@link RuleSetVersion} to be
   * returned
   * @param productionDate the maximum {@link RuleSetVersion#productionStartDate} of the {@link
   * RuleSetVersion} to be returned
   * @return The {@link RuleSetVersion} of the {@link RuleSet} identified by {@code rulSetId}
   * corresponding to the {@code productionDate}
   */
  @Query("select rsv from RuleSetVersion rsv"
      + " where rsv.ruleSet.id = :ruleSetId "
      + " and rsv.productionStartDate = ("
      + " select max(rsv2.productionStartDate) from RuleSetVersion rsv2"
      + " where rsv2.ruleSet.id = rsv.ruleSet.id"
      + " and rsv2.productionStartDate < :productionDate "
      + ")")
  RuleSetVersion findByRuleSetIdAndProductionDate(@Param("ruleSetId") Integer ruleSetId,
                                                  @Param("productionDate")
                                                      LocalDateTime productionDate);

  /**
   * Find a {@link RuleSetVersion} by {@code ruleSetAlias} and {@code productionDate}
   *
   * @param ruleSetAlias the alias of the {@link RuleSet} of the {@link RuleSetVersion} to be
   * returned
   * @param productionDate the maximum {@link RuleSetVersion#productionStartDate} of the {@link
   * RuleSetVersion} to be returned
   * @return The {@link RuleSetVersion} of the {@link RuleSet} identified by {@code ruleSetAlias}
   * corresponding to the {@code productionDate}
   */
  @Query("select rsv from RuleSetVersion rsv join rsv.ruleSet rs "
      + " where exists ("
      + " select msi from MultilingualString ms, MultilingualStringItem msi"
      + " where ms.id = rs.alias.id and msi.multilingualString.id = ms.id "
      + " and dbms_lob.compare(msi.text, :alias) = 0"
      + ")"
      + " and rsv.productionStartDate = ("
      + " select max(rsv2.productionStartDate) from RuleSetVersion rsv2"
      + " where rsv2.ruleSet.id = rsv.ruleSet.id"
      + " and rsv2.productionStartDate < :productionDate "
      + ")")
  RuleSetVersion findByRuleSetAliasAndProductionDate(@Param("alias") String ruleSetAlias,
                                                     @Param("productionDate")
                                                         LocalDateTime productionDate);
}
