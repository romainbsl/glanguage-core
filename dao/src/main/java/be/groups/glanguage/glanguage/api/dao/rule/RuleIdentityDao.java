package be.groups.glanguage.glanguage.api.dao.rule;

import be.groups.glanguage.glanguage.api.entities.rule.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface RuleIdentityDao extends JpaRepository<RuleIdentity, Integer> {}
