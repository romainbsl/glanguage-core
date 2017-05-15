package be.groups.glanguage.glanguage.api.business.factory;

import be.groups.glanguage.glanguage.api.business.universe.Universe;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * Created by michotte on 8/12/2016.
 */
public class RuleVersionFactory {


    private static HashMap<PlanIdentification, List<RuleVersion>> ruleVersionsLists =
            new HashMap<>();

    public static List<RuleVersion> getRuleVersions(Integer ruleSetVersionId, LocalDate effectivityDate) {
        PlanIdentification key = new PlanIdentification(ruleSetVersionId, effectivityDate);
        if (!ruleVersionsLists.containsKey(key)) {
            ruleVersionsLists.put(key, Universe.getDefaultRuleVersions(Universe.getRuleSetVersion(ruleSetVersionId),
                    effectivityDate));
        }
        return ruleVersionsLists.get(key);
    }

}
