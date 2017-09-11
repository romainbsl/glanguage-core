package be.groups.glanguage.glanguage.api.business.factory;

import be.groups.glanguage.glanguage.api.business.universe.*;
import be.groups.glanguage.glanguage.api.entities.rule.*;
import be.groups.glanguage.glanguage.api.entities.ruleset.*;
import java.time.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/**
 * This factory allows to get {@link RuleVersion rule versions}<br>
 * It has a cache to keep all already asked {@link RuleVersion rule versions} available
 * <p>
 * @author michotte
 */
@Component
public class RuleVersionFactory {

    @Autowired
    private Universe universe;

    // TODO see for Cache or FactoryBean
    private HashMap<PlanIdentification, List<RuleVersion>> ruleVersionsLists = new HashMap<>();

    /**
     * Get the list of {@link RuleVersion rule versions} corresponding to a {@code ruleSetVersionId} and a {@code
     * effectiveDate}<br>
     * Get it from the cache if it exists or delegate to
     * {@link Universe#getDefaultRuleVersions(RuleSetVersion, LocalDate)} and add it to the cache
     *
     * @param ruleSetVersionId the identifier of the {@link RuleSetVersion} from which to extract the list of
     * {@link RuleVersion rule versions} to be returned
     * @param effectiveDate    the {@link LocalDate} to be used to filter the list of
     * {@link RuleVersion rule versions} to be returned
     * @return the list of {@link RuleVersion rule versions} corresponding to a {@code ruleSetVersionId} and a {@code
     * effectiveDate}
     * @see Universe#getDefaultRuleVersions(RuleSetVersion, LocalDate)
     * @see Universe#getRuleSetVersion(Integer)
     */
    public List<RuleVersion> getRuleVersions(Integer ruleSetVersionId, LocalDate effectiveDate) {
        PlanIdentification key = new PlanIdentification(ruleSetVersionId, effectiveDate);
        if (!ruleVersionsLists.containsKey(key)) {
            ruleVersionsLists.put(key,
                universe.getDefaultRuleVersions(universe.getRuleSetVersion
                    (ruleSetVersionId), effectiveDate));
        }
        return ruleVersionsLists.get(key);
    }

}
