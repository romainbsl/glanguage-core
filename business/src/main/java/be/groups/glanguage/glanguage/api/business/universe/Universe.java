package be.groups.glanguage.glanguage.api.business.universe;

import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.dao.FormulaDao;
import be.groups.glanguage.glanguage.api.dao.RuleSetDao;
import be.groups.glanguage.glanguage.api.dao.RuleSetVersionDao;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSet;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class Universe {

    /**
     * Get all {@link RuleSet RuleSets} defined in the system
     *
     * @return the {@link List} of all {@link RuleSet RuleSets} defined in the system
     */
    public static List<RuleSet> getAllRuleSets() {
        return new RuleSetDao().findAll();
    }

    /**
     * Get a {@link RuleSet} corresponding to a {@code ruleSetId} by selecting the one whose {@link RuleSet#id} is
     * equal to {@code ruleSetId}
     *
     * @param ruleSetId the {@link RuleSet#id} of the {@link RuleSet} to be returned
     * @return the {@link RuleSet} whose {@link RuleSet#id} is equal to {@code ruleSetId}, or null if it doesn't exists
     */
    public static RuleSet getRuleSet(Integer ruleSetId) {
        return new RuleSetDao().findById(ruleSetId);
    }

    /**
     * Get a {@link RuleSet} corresponding to a {@code ruleSetAlias} by selecting the one whose
     * {@link RuleSet#aliasFr} or {@link RuleSet#aliasNl} or {@link RuleSet#aliasDe} or {@link RuleSet#aliasX} is
     * equal to {@code ruleSetAlias}
     *
     * @param ruleSetAlias the {@link RuleSet#aliasFr} or the {@link RuleSet#aliasNl} or the {@link RuleSet#aliasDe}
     *                     or the {@link RuleSet#aliasX} of the {@link RuleSet} to be returned
     * @return the {@link RuleSet} whose {@link RuleSet#aliasFr} or {@link RuleSet#aliasNl} or
     * {@link RuleSet#aliasDe} or {@link RuleSet#aliasX} is equal to {@code ruleSetAlias}, or null if it doesn't exists
     */
    public static RuleSet getRuleSet(String ruleSetAlias) {
        return new RuleSetDao().findByAlias(ruleSetAlias);
    }

    /**
     * Get a {@link RuleSetVersion} corresponding to a {@code ruleSetVersionId} by selecting the one whose
     * {@link RuleSetVersion#id} is equal to {@code ruleSetVersionId}
     *
     * @param ruleSetVersionId the {@link RuleSetVersion#id} of the {@link RuleSetVersion} to be returned
     * @return the {@link RuleSetVersion} whose {@link RuleSetVersion#id} is equal to {@code ruleSetVersionId}, or
     * null if it doesn't exists
     */
    public static RuleSetVersion getRuleSetVersion(Integer ruleSetVersionId) {
        return new RuleSetVersionDao().findById(ruleSetVersionId);
    }

    /**
     * Get a {@link RuleSetVersion} corresponding to a {@code ruleSetId} and a {@code version} by selecting the one
     * whose
     * {@link RuleSet#id} is equal to {@code ruleSetId} and {@link RuleSetVersion#version} is equal to {@code version}
     *
     * @param ruleSetId the {@link RuleSet#id} of the {@link RuleSetVersion} to be returned
     * @param version   the {@link RuleSetVersion#version} of the {@link RuleSetVersion} to be returned
     * @return the {@link RuleSetVersion} whose {@link RuleSet#id} is equal to {@code ruleSetId} and
     * {@link RuleSetVersion#version} is equal to {@code version}, or null if it doesn't exists
     */
    public static RuleSetVersion getRuleSetVersion(Integer ruleSetId, String version) {
        return new RuleSetVersionDao().findByRuleSetIdAndVersion(ruleSetId, version);
    }

    /**
     * Get a {@link RuleSet} corresponding to a {@code ruleSetAlias} and a {@code
     * version} by selecting the one whose {@link RuleSet#aliasFr} or {@link RuleSet#aliasNl} or
     * {@link RuleSet#aliasDe} or {@link RuleSet#aliasX} is equal to {@code ruleSetAlias} and
     * {@link RuleSetVersion#version} is equal to {@code version}
     *
     * @param ruleSetAlias the {@link RuleSet#aliasFr} or the {@link RuleSet#aliasNl} or the {@link RuleSet#aliasDe}
     *                     or the {@link RuleSet#aliasX} of the {@link RuleSet} to be returned
     * @param version      the {@link RuleSetVersion#version} of the {@link RuleSetVersion} to be returned
     * @return the {@link RuleSet} whose {@link RuleSet#aliasFr} or {@link RuleSet#aliasNl} or
     * {@link RuleSet#aliasDe} or {@link RuleSet#aliasX} is equal to {@code ruleSetAlias} and
     * {@link RuleSetVersion#version} is equal to {@code version}, or null if it doesn't exists
     */
    public static RuleSetVersion getRuleSetVersion(String ruleSetAlias, String version) {
        return new RuleSetVersionDao().findByRuleSetAliasAndVersion(ruleSetAlias, version);
    }

    /**
     * Get a {@link RuleSetVersion} corresponding to a {@code ruleSetId} and a {@code productionDate} by selecting
     * the one whose {@link RuleSet#id} is equal to {@code ruleSetId} and {@link RuleSetVersion#productionStartDate}
     * is the greatest known not after {@code productionDate}
     *
     * @param ruleSetId      the {@link RuleSet#id} of the {@link RuleSetVersion} to be returned
     * @param productionDate the maximum {@link RuleSetVersion#productionStartDate} of the {@link RuleSetVersion} to be
     *                       returned
     * @return the {@link RuleSetVersion} whose {@link RuleSet#id} is equal to {@code ruleSetId} and
     * {@link RuleSetVersion#productionStartDate} is the greatest known not after {@code productionDate}, or null if
     * it doesn't exists
     */
    public static RuleSetVersion getRuleSetVersion(Integer ruleSetId, LocalDateTime productionDate) {
        return new RuleSetVersionDao().findByRuleSetIdAndProductionDate(ruleSetId, productionDate);
    }

    /**
     * Get a {@link RuleSetVersion} corresponding to a {@code ruleSetAlias} and a {@code productionDate} by selecting
     * the one whose {@link RuleSet#aliasFr} or {@link RuleSet#aliasNl} or {@link RuleSet#aliasDe} or
     * {@link RuleSet#aliasX} is equal to {@code ruleSetAlias} and {@link RuleSetVersion#productionStartDate} is the
     * greatest known not after {@code productionDate}
     *
     * @param ruleSetAlias   the {@link RuleSet#id} of the {@link RuleSetVersion} to be returned
     * @param productionDate the maximum {@link RuleSetVersion#productionStartDate} of the {@link RuleSetVersion} to be
     *                       returned
     * @return the {@link RuleSetVersion} whose {@link RuleSet#aliasFr} or {@link RuleSet#aliasNl} or
     * {@link RuleSet#aliasDe} or {@link RuleSet#aliasX} is equal to {@code ruleSetAlias} and
     * {@link RuleSetVersion#productionStartDate} is the greatest known not after {@code productionDate}, or null if
     * it doesn't exists
     */
    public static RuleSetVersion getRuleSetVersion(String ruleSetAlias, LocalDateTime productionDate) {
        return new RuleSetVersionDao().findByRuleSetAliasAndProductionDate(ruleSetAlias, productionDate);
    }

    /**
     * Get a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetVersionId}, that are
     * effective at {@code effectiveDate}
     *
     * @param ruleSetVersionId the identifier of the {@link RuleSetVersion} to be used to create the {@link Plan} to
     *                         be returned
     * @param effectiveDate    the date at which the {@link RuleVersion RuleVersions}, to be added to the {@link Plan},
     *                         have to be effective
     * @return a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetVersionId}, that are
     * effective at {@code effectiveDate}
     */
    public static Plan getPlan(Integer ruleSetVersionId, LocalDate effectiveDate) {
        return createPlan(getRuleSetVersion(ruleSetVersionId), null, effectiveDate);
    }

    /**
     * Get a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetVersionId}, that are
     * effective at {@code effectiveDate} and whose {@link RuleVersion#ruleDefinition} best matches the collection of
     * {@link RuleDefinitionParameter definitionParameters}
     *
     * @param ruleSetVersionId     the identifier of the {@link RuleSetVersion} to be used to create the {@link Plan} to
     *                             be returned
     * @param effectiveDate        the date at which the {@link RuleVersion RuleVersions}, to be added to the
     *                             {@link Plan} to be returned, have to be effective
     * @param definitionParameters the definition parameters for which the {@link RuleVersion#ruleDefinition} of the
     *                             {@link RuleVersion}, to be added to the {@link Plan} to be returned, best matches
     * @return a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetVersionId}, that are
     * effective at {@code effectiveDate} and whose {@link RuleVersion#ruleDefinition} best matches the collection of
     * {@link RuleDefinitionParameter definitionParameters}
     */
    public static Plan getPlan(Integer ruleSetVersionId,
                               LocalDate effectiveDate,
                               Collection<RuleDefinitionParameter> definitionParameters) {
        return createPlan(getRuleSetVersion(ruleSetVersionId), definitionParameters, effectiveDate);
    }

    /**
     * Get a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetId} and {@code
     * version}, that are effective at {@code effectiveDate}
     *
     * @param ruleSetId     the identifier of the {@link RuleSet} of the {@link RuleSetVersion} to be used to create the
     *                      {@link Plan} to be returned
     * @param version       the version identifying the {@RuleSetVersion}, of the {@link RuleSet} identified by {@code
     *                      ruleSetId}, to be used to create the {@link Plan} to be returned
     * @param effectiveDate the date at which the {@link RuleVersion RuleVersions}, to be added to the {@link Plan},
     *                      have to be effective
     * @return a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetId} and {@code
     * version}, that are effective at {@code effectiveDate}
     */
    public static Plan getPlan(Integer ruleSetId, String version, LocalDate effectiveDate) {
        return createPlan(getRuleSetVersion(ruleSetId, version), null, effectiveDate);
    }

    /**
     * Get a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetId} and {@code
     * version}, that are effective at {@code effectiveDate} and whose {@link RuleVersion#ruleDefinition} best
     * matches the collection of {@link RuleDefinitionParameter definitionParameters}
     *
     * @param ruleSetId            the identifier of the {@link RuleSet} of the {@link RuleSetVersion} to be used to
     *                             create the {@link Plan} to be returned
     * @param version              the version identifying the {@RuleSetVersion}, of the {@link RuleSet} identified
     *                             by {@code ruleSetId}, to be used to create the {@link Plan} to be returned
     * @param effectiveDate        the date at which the {@link RuleVersion RuleVersions}, to be added to the
     *                             {@link Plan}, have to be effective
     * @param definitionParameters the definition parameters for which the {@link RuleVersion#ruleDefinition} of the
     *                             {@link RuleVersion}, to be added to the {@link Plan} to be returned, best matches
     * @return a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetId} and {@code
     * version}, that are effective at {@code effectiveDate} and whose {@link RuleVersion#ruleDefinition} best
     * matches the collection of {@link RuleDefinitionParameter definitionParameters}
     */
    public static Plan getPlan(Integer ruleSetId,
                               String version,
                               LocalDate effectiveDate,
                               Collection<RuleDefinitionParameter> definitionParameters) {
        return createPlan(getRuleSetVersion(ruleSetId, version), definitionParameters, effectiveDate);
    }

    /**
     * Get a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetAlias} and {@code
     * version}, that are effective at {@code effectiveDate}
     *
     * @param ruleSetAlias  the alias of the {@link RuleSet} of the {@link RuleSetVersion} to be used to create the
     *                      {@link Plan} to be returned
     * @param version       the version identifying the {@RuleSetVersion}, of the {@link RuleSet} identified by {@code
     *                      ruleSetAlias}, to be used to create the {@link Plan} to be returned
     * @param effectiveDate the date at which the {@link RuleVersion RuleVersions}, to be added to the {@link Plan},
     *                      have to be effective
     * @return a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetAlias} and {@code
     * version}, that are effective at {@code effectiveDate}
     */
    public static Plan getPlan(String ruleSetAlias, String version, LocalDate effectiveDate) {
        return createPlan(getRuleSetVersion(ruleSetAlias, version), null, effectiveDate);
    }

    /**
     * Get a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetAlias} and {@code
     * version}, that are effective at {@code effectiveDate} and whose {@link RuleVersion#ruleDefinition} best
     * matches the collection of {@link RuleDefinitionParameter definitionParameters}
     *
     * @param ruleSetAlias         the alias of the {@link RuleSet} of the {@link RuleSetVersion} to be used to
     *                             create the {@link Plan} to be returned
     * @param version              the version identifying the {@RuleSetVersion}, of the {@link RuleSet} identified
     *                             by {@code ruleSetAlias}, to be used to create the {@link Plan} to be returned
     * @param effectiveDate        the date at which the {@link RuleVersion RuleVersions}, to be added to the
     *                             {@link Plan}, have to be effective
     * @param definitionParameters the definition parameters for which the {@link RuleVersion#ruleDefinition} of the
     *                             {@link RuleVersion}, to be added to the {@link Plan} to be returned, best matches
     * @return a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetAlias} and {@code
     * version}, that are effective at {@code effectiveDate} and whose {@link RuleVersion#ruleDefinition} best
     * matches the collection of {@link RuleDefinitionParameter definitionParameters}
     */
    public static Plan getPlan(String ruleSetAlias,
                               String version,
                               LocalDate effectiveDate,
                               Collection<RuleDefinitionParameter> definitionParameters) {
        return createPlan(getRuleSetVersion(ruleSetAlias, version), definitionParameters, effectiveDate);
    }

    /**
     * Get a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetId} and {@code
     * productionDate}, that are effective at {@code effectiveDate}
     *
     * @param ruleSetId      the identifier of the {@link RuleSet} of the {@link RuleSetVersion} to be used to create
     *                       the
     *                       {@link Plan} to be returned
     * @param productionDate the maximum {@link RuleSetVersion#productionStartDate} of the {@link RuleSetVersion} to be
     *                       returned
     * @param effectiveDate  the date at which the {@link RuleVersion RuleVersions}, to be added to the {@link Plan},
     *                       have to be effective
     * @return a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetId} and {@code
     * productionDate}, that are effective at {@code effectiveDate}
     */
    public static Plan getPlan(Integer ruleSetId, LocalDateTime productionDate, LocalDate effectiveDate) {
        return createPlan(getRuleSetVersion(ruleSetId, productionDate), null, effectiveDate);
    }

    /**
     * Get a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetId} and {@code
     * productionDate}, that are effective at {@code effectiveDate} and whose {@link RuleVersion#ruleDefinition} best
     * matches the collection of {@link RuleDefinitionParameter definitionParameters}
     *
     * @param ruleSetId            the identifier of the {@link RuleSet} of the {@link RuleSetVersion} to be used to
     *                             create the {@link Plan} to be returned
     * @param productionDate       the maximum {@link RuleSetVersion#productionStartDate} of the
     *                             {@link RuleSetVersion} to be returned
     * @param effectiveDate        the date at which the {@link RuleVersion RuleVersions}, to be added to the
     *                             {@link Plan}, have to be effective
     * @param definitionParameters the definition parameters for which the {@link RuleVersion#ruleDefinition} of the
     *                             {@link RuleVersion}, to be added to the {@link Plan} to be returned, best matches
     * @return a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetId} and {@code
     * productionDate}, that are effective at {@code effectiveDate} and whose {@link RuleVersion#ruleDefinition} best
     * matches the collection of {@link RuleDefinitionParameter definitionParameters}
     */
    public static Plan getPlan(Integer ruleSetId,
                               LocalDateTime productionDate,
                               LocalDate effectiveDate,
                               Collection<RuleDefinitionParameter> definitionParameters) {
        return createPlan(getRuleSetVersion(ruleSetId, productionDate), definitionParameters, effectiveDate);
    }

    /**
     * Get a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetAlias} and {@code
     * productionDate}, that are effective at {@code effectiveDate}
     *
     * @param ruleSetAlias   the alias of the {@link RuleSet} of the {@link RuleSetVersion} to be used to create
     *                       the {@link Plan} to be returned
     * @param productionDate the maximum {@link RuleSetVersion#productionStartDate} of the {@link RuleSetVersion} to be
     *                       returned
     * @param effectiveDate  the date at which the {@link RuleVersion RuleVersions}, to be added to the {@link Plan},
     *                       have to be effective
     * @return a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetAlias} and {@code
     * productionDate}, that are effective at {@code effectiveDate}
     */
    public static Plan getPlan(String ruleSetAlias, LocalDateTime productionDate, LocalDate effectiveDate) {
        return createPlan(getRuleSetVersion(ruleSetAlias, productionDate), null, effectiveDate);
    }

    /**
     * Get a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetAlias} and {@code
     * productionDate}, that are effective at {@code effectiveDate} and whose {@link RuleVersion#ruleDefinition} best
     * matches the collection of {@link RuleDefinitionParameter definitionParameters}
     *
     * @param ruleSetAlias         the alias of the {@link RuleSet} of the {@link RuleSetVersion} to be used to create
     *                             the {@link Plan} to be returned
     * @param productionDate       the maximum {@link RuleSetVersion#productionStartDate} of the
     *                             {@link RuleSetVersion} to be returned
     * @param effectiveDate        the date at which the {@link RuleVersion RuleVersions}, to be added to the
     *                             {@link Plan}, have to be effective
     * @param definitionParameters the definition parameters for which the {@link RuleVersion#ruleDefinition} of the
     *                             {@link RuleVersion}, to be added to the {@link Plan} to be returned, best matches
     * @return a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@link RuleSetVersion} identified by {@code ruleSetAlias} and {@code
     * productionDate}, that are effective at {@code effectiveDate} and whose {@link RuleVersion#ruleDefinition} best
     * matches the collection of {@link RuleDefinitionParameter definitionParameters}
     */
    public static Plan getPlan(String ruleSetAlias,
                               LocalDateTime productionDate,
                               LocalDate effectiveDate,
                               Collection<RuleDefinitionParameter> definitionParameters) {
        return createPlan(getRuleSetVersion(ruleSetAlias, productionDate), definitionParameters, effectiveDate);
    }

    /**
     * Creates a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of a {@code ruleSetVersion} that are effective at {@code effectiveDate}
     * and whose {@link RuleVersion#ruleDefinition} best matches the collection of
     * {@link RuleDefinitionParameter definitionParameters}
     *
     * @param ruleSetVersion       the {@link RuleSetVersion} to be used to create the {@link Plan} to be returned
     * @param definitionParameters the definition parameters for which the {@link RuleVersion#ruleDefinition} of the
     *                             {@link RuleVersion}, to be added to the {@link Plan} to be returned, best matches
     * @param effectiveDate        the date at which the {@link RuleVersion RuleVersions}, to be added to the
     *                             {@link Plan}, have to be effective
     * @return a {@link Plan} with a list of {@link RuleVersion} resulting of the filtering of all
     * {@link RuleVersion RuleVersions}, of the {@code ruleSetVersion} that are effective at {@code effectiveDate}
     * and whose {@link RuleVersion#ruleDefinition} best matches the collection of
     * {@link RuleDefinitionParameter definitionParameters}, or "default" ones if {@code definitionParameters} is
     * null or empty
     * @see RuleSetVersion#getBestDefinedRuleVersions(Collection)
     * @see RuleSetVersion#getDefaultRuleVersions(LocalDate)
     * @see RuleVersion#isEffective(LocalDate)
     */
    private static Plan createPlan(RuleSetVersion ruleSetVersion,
                                   Collection<RuleDefinitionParameter> definitionParameters,
                                   LocalDate effectiveDate) {
        Plan plan = new Plan();
        if (definitionParameters == null || definitionParameters.isEmpty()) {
            plan.setRuleVersions(getDefaultRuleVersions(ruleSetVersion, effectiveDate));
        } else {
            plan.setRuleVersions(getBestDefinedRuleVersions(ruleSetVersion, definitionParameters, effectiveDate));
        }
        return plan;
    }

    /**
     * Get the list of all "default" {@link RuleVersion}, from a {@code ruleSetVersion}, that are effective at {@code
     * effectiveDate}
     *
     * @param ruleSetVersion the {@link RuleSetVersion} from which the list of "default" {@link RuleVersion} to be
     *                       returned, has to be extracted
     * @param effectiveDate  the date at which the {@link RuleVersion RuleVersions}, to be added to the
     *                       list, have to be effective
     * @return a list of all "default" {@link RuleVersion}, from the {@code ruleSetVersion}, that are effective at
     * {@code effectiveDate}
     * @see RuleSetVersion#getDefaultRuleVersions(LocalDate)
     * @see RuleVersion#isEffective(LocalDate)
     */
    public static List<RuleVersion> getDefaultRuleVersions(RuleSetVersion ruleSetVersion, LocalDate effectiveDate) {
        final List<RuleVersion> ruleVersions = ruleSetVersion.getDefaultRuleVersions(effectiveDate);
        if (ruleSetVersion.getIncludes() != null && !ruleSetVersion.getIncludes().isEmpty()) {
            ruleSetVersion.getIncludes().stream().forEach(i -> ruleVersions
                    .addAll(getDefaultRuleVersions(i, effectiveDate)));
        }
        return ruleVersions;
    }

    /**
     * Get the list of all "best defined" {@link RuleVersion}, from a {@code ruleSetVersion}, that are effective at
     * {@code effectiveDate} and whose {@link RuleVersion#ruleDefinition} best matches the collection of
     * {@link RuleDefinitionParameter definitionParameters}
     *
     * @param ruleSetVersion       the {@link RuleSetVersion} from which the list of "default" {@link RuleVersion} to be
     *                             returned, has to be extracted
     * @param definitionParameters the definition parameters for which the {@link RuleVersion#ruleDefinition} of the
     *                             {@link RuleVersion}, to be added to the {@link Plan} to be returned, best matches
     * @param effectiveDate        the date at which the {@link RuleVersion RuleVersions}, to be added to the
     *                             list, have to be effective
     * @return a list of all "default" {@link RuleVersion}, from the {@code ruleSetVersion}, that are effective at
     * {@code effectiveDate} and whose {@link RuleVersion#ruleDefinition} best matches the collection of
     * {@link RuleDefinitionParameter definitionParameters}
     * @see RuleSetVersion#getBestDefinedRuleVersions(Collection)
     * @see RuleVersion#isEffective(LocalDate)
     */
    public static List<RuleVersion> getBestDefinedRuleVersions(RuleSetVersion ruleSetVersion,
                                                               Collection<RuleDefinitionParameter> definitionParameters,
                                                               LocalDate effectiveDate) {
        final List<RuleVersion> ruleVersions = ruleSetVersion.getBestDefinedRuleVersions(definitionParameters,
                                                                                         effectiveDate);
        if (ruleSetVersion.getIncludes() != null && !ruleSetVersion.getIncludes().isEmpty()) {
            ruleSetVersion.getIncludes().stream().forEach(i -> ruleVersions
                    .addAll(getBestDefinedRuleVersions(i, definitionParameters, effectiveDate)));
        }
        return ruleVersions;
    }

    /**
     * Get the {@link AbstractFormula} identified by {@code id}
     *
     * @param id the id of the {@link AbstractFormula} to be returned
     * @return the {@link AbstractFormula} identified by {@code id}, or null if it doesn't exists
     */
    public static AbstractFormula getFormula(Integer id) {
        return new FormulaDao().findById(id);
    }
}
