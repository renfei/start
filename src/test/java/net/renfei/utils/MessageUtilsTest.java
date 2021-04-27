package net.renfei.utils;

import lombok.extern.slf4j.Slf4j;
import net.renfei.util.MessageUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author renfei
 */
@Slf4j
@SpringBootTest
public class MessageUtilsTest {
    @Test
    public void getMsgTest() {
        log.info(MessageUtils.get("test.i18n"));
    }
}
