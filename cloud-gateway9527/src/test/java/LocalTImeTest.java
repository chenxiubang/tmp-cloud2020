import lombok.experimental.var;
import lombok.val;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * CREATE BY OMEN ON 2021.06.02 21:23 星期三
 * DESC:
 */
public class LocalTImeTest {
    @Test
    public void test1() {
        var time = LocalDateTime.now();
        System.out.println(time);

        val zonedDateTime= ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
