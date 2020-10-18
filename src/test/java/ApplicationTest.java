import cn.las.utils.AESUtil;
import org.junit.Test;

public class ApplicationTest {

    @Test
    public void AESTest() {
        System.out.println(AESUtil.encrypt("11"));
    }
}
