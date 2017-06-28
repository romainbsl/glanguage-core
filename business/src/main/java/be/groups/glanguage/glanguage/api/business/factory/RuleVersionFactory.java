package be.groups.glanguage.glanguage.api.business.factory;

import be.groups.glanguage.glanguage.api.business.universe.Universe;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * This factory allows to get {@link RuleVersion rule versions}<br>
 * It has a cache to keep all already asked {@link RuleVersion rule versions} available
 * <p>
 * Created by michotte on 8/12/2016.
 */
public class RuleVersionFactory {

    private static HashMap<PlanIdentification, List<RuleVersion>> ruleVersionsLists = new HashMap<>();

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
    public static List<RuleVersion> getRuleVersions(Integer ruleSetVersionId, LocalDate effectiveDate) {
        PlanIdentification key = new PlanIdentification(ruleSetVersionId, effectiveDate);
        if (!ruleVersionsLists.containsKey(key)) {
            ruleVersionsLists.put(key,
                                  Universe.getDefaultRuleVersions(Universe.getRuleSetVersion(ruleSetVersionId),
                                                                  effectiveDate));
        }
        return ruleVersionsLists.get(key);
    }

}
