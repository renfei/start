package net.renfei.repository.manager.wechat;

import lombok.extern.slf4j.Slf4j;
import net.renfei.ApplicationTests;
import net.renfei.repository.manager.wechat.model.AccessTokenDO;
import net.renfei.repository.manager.wechat.model.Code2SessionDO;
import net.renfei.repository.manager.wechat.model.PaidUnionIdDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 微信服务接口测试
 *
 * @author renfei
 */
@Slf4j
public class WeChatServiceTests extends ApplicationTests {
    @Autowired
    private WeChatService weChatService;

    @Test
    public void getAccessTokenTest() {
        AccessTokenDO accessTokenDO = weChatService.getAccessToken("test", "test", "test");
        log.info(accessTokenDO.toString());
    }

    @Test
    public void code2SessionTest() {
        Code2SessionDO code2SessionDO = weChatService.code2Session("test", "test", "test", "test");
        log.info(code2SessionDO.toString());
    }

    @Test
    public void getPaidUnionIdTest() {
        PaidUnionIdDO paidUnionId = weChatService.getPaidUnionId("test", "test", null, null, null);
        log.info(paidUnionId.toString());
    }
}
