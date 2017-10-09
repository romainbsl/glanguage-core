package be.groups.glanguage.core.dao.rule;

import be.groups.glanguage.core.entities.rule.RuleGroupItem;
import be.groups.glanguage.core.entities.rule.RuleGroupItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link RuleGroupItem} <p>
 */
@Repository
public interface RuleGroupItemDao extends JpaRepository<RuleGroupItem, RuleGroupItemId> {}
