package be.groups.glanguage.glanguage.api.dao;

import be.groups.glanguage.glanguage.api.entities.ruleset.*;
import org.springframework.data.jpa.repository.*;

/**
 * DAO for {@link RuleSet} <p>
 *
 * @author michotte
 */
public interface RuleSetDao extends JpaRepository<RuleSet, Integer> {

  /**
   * Find a {@link RuleSet} by id
   *
   * @param id the identifier of the {@link RuleSet} to be returned
   * @return The {@link RuleSet} identified by {@code id}, or null if it doesn't exists
   */
  RuleSet findById(Integer id);

  /**
   * Find a {@link RuleSet} by alias
   *
   * @param alias the alias of the {@link RuleSet} to be returned
   * @return The {@link RuleSet} identified by {@code alias}, or null if it doesn't exists
   */
  @Query("select rs from RuleSet rs"
      + " where exists ("
      + " select msi from MultilingualString ms, MultilingualStringItem msi"
      + " where ms.id = rs.alias.id and msi.multilingualString.id = ms.id"
      + " and dbms_lob.compare(msi.text, :alias) = 0"
      + ")")
  RuleSet findByAlias(String alias);
}
