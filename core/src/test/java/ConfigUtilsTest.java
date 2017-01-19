import org.junit.Assert;
import org.junit.Test;
import org.simplemvc.util.ConfigUtils;

/**
 * Created by wuyuhuan on 2017/1/19.
 * Usage：Unit Test
 */
public class ConfigUtilsTest {
    @Test
    public void getProperty(){
        ConfigUtils.init();
        Assert.assertEquals("sm-core",ConfigUtils.getProperty("module.name"));
        Assert.assertEquals("it is a core module!",ConfigUtils.getProperty("module.info"));
    }
}
