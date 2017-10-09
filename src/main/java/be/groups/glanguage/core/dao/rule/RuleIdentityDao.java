package be.groups.glanguage.core.dao.rule;

import be.groups.glanguage.core.entities.rule.RuleIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link RuleIdentity} <p>
 */
@Repository
public interface RuleIdentityDao extends JpaRepository<RuleIdentity, Long> {}
