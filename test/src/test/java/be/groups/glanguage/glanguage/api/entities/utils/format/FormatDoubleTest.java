package be.groups.glanguage.glanguage.api.entities.utils.format;

import org.junit.Test;

/**
 * Created by michotte on 17/05/2017.
 */
public class FormatDoubleTest {

    @Test
    public void formatted() throws Exception {
        FormatDouble format = new FormatDouble(10,4);
        format.setFill('x');
        format.setTrailingZerosShown(true);
        format.rightJustify();
        format.signIgnore();
        String formatted = format.formatted(-124.344);
        System.out.print(formatted);
    }

}
