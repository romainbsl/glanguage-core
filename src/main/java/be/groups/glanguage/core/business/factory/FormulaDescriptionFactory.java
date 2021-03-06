package be.groups.glanguage.core.business.factory;

import be.groups.glanguage.core.dao.formula.FormulaDescriptionDao;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This factory allows to get {@link FormulaDescription formula descriptions}<br>
 * It has a cache in order to read {@link FormulaDescription formula descriptions} only once
 *
 * @author michotte
 */
@Component
public class FormulaDescriptionFactory {

    @Autowired
    private FormulaDescriptionDao formulaDescriptionDao;

    // TODO see for Cache or FactoryBean
    private Map<FormulaType, FormulaDescription> formulaDescriptionByType;

    /**
     * Get the {@link FormulaDescription} corresponding to a {@link FormulaType}
     *
     * @param formulaType the {@link FormulaType} corresponding to the {@link FormulaDescription} to be returned
     * @return the {@link FormulaDescription} corresponding to {@code formulaType}, or null if it doesn't exists
     */
    public FormulaDescription getDescription(FormulaType formulaType) {
        return getFormulaDescriptionByType().get(formulaType);
    }

    /**
     * Get the map of all known {@link FormulaDescription} (value) associated to its {@link FormulaType} (key)<br>
     * Get it from static field if it's not null or from database and assign it to the static field
     *
     * @return the map of all known {@link FormulaDescription} (value) associated to its {@link FormulaType} (key)
     */
    private Map<FormulaType, FormulaDescription> getFormulaDescriptionByType() {
        if (formulaDescriptionByType == null) {
            formulaDescriptionByType = formulaDescriptionDao.findAll().stream().collect(Collectors.toMap(
                    FormulaDescription::getType,
                    Function.identity()));
        }

        return formulaDescriptionByType;
    }
}
