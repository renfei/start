package net.renfei;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.RenFeiConfig;
import net.renfei.sdk.utils.AESUtil;
import net.renfei.sdk.utils.RSAUtils;
import net.renfei.web.api.start.ao.ReportPublicKeyAO;
import net.renfei.web.api.start.ao.SignInAO;
import net.renfei.web.api.start.vo.SecretKeyVO;
import net.renfei.web.api.start.vo.SignInVO;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {
    protected static String TOKEN;
    protected MockHttpSession SESSION = new MockHttpSession();
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected RenFeiConfig renFeiConfig;

    @BeforeEach
    public void getToken() throws Exception {
        if (TOKEN == null) {
            String result = mockMvc.perform(get("/api/auth/secretKey")
                    .contentType(MediaType.APPLICATION_JSON)
                    .session(SESSION))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            JSONObject apiResult = JSON.parseObject(result);
            assert apiResult.getInteger("code") == 200;
            SecretKeyVO secretKey = apiResult.getObject("data", SecretKeyVO.class);
            // 交换秘钥
            SecretKeyVO aesKey = setSecretKey(secretKey);
            SignInAO signInAo = new SignInAO();
            String password = "tests";
            signInAo.setPassword(AESUtil.encrypt(password, aesKey.getPublicKey()));
            signInAo.setKeyId(aesKey.getKeyId());
            signInAo.setUseVerCode(false);
            signInAo.setUserName("tester");
            SignInVO signInVO = signIn(signInAo);
            // 如是 SESSION 模式，这里不会返回 Token
            log.info("Token:{}", signInVO.getToken());
            log.info("ucScript:{}", signInVO.getUcScript());
            if (signInVO.getToken() != null) {
                TOKEN = signInVO.getToken();
            }
        }
    }

    /**
     * 上报客户端的公钥给服务器，交换秘钥
     *
     * @param secretKey
     * @return
     * @throws Exception
     */
    public SecretKeyVO setSecretKey(SecretKeyVO secretKey) throws Exception {
        // 生成客户端秘钥
        Map<Integer, String> map = RSAUtils.genKeyPair(1024);
        // 客户端私钥
        String privateKey = map.get(1);
        // 客户端公钥
        String publicKey = map.get(0);
        // 使用服务器公钥加密客户端公钥
        String publicKeys = RSAUtils.encrypt(publicKey, secretKey.getPublicKey());
        ReportPublicKeyAO reportPublicKey = new ReportPublicKeyAO();
        reportPublicKey.setPublicKey(publicKeys);
        reportPublicKey.setKeyId(secretKey.getKeyId());
        // 上报给服务器
        String result = mockMvc.perform(post("/api/auth/secretKey")
                .content(JSON.toJSONString(reportPublicKey))
                .contentType(MediaType.APPLICATION_JSON)
                .session(SESSION))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        JSONObject apiResult = JSON.parseObject(result);
        assert apiResult.getInteger("code") == 200;
        SecretKeyVO secretKeys = apiResult.getObject("data", SecretKeyVO.class);
        assert secretKeys != null;
        // 解密 AES 秘钥
        String aes = RSAUtils.decrypt(secretKeys.getPublicKey(), privateKey);
        log.info("秘钥ID：{}", secretKeys.getKeyId());
        log.info("秘钥：{}", aes);
        secretKeys.setPublicKey(aes);
        secretKeys.setPrivateKey(aes);
        return secretKeys;
    }

    public SignInVO signIn(SignInAO signInAo) throws Exception {
        String result = mockMvc.perform(post("/api/auth/signIn")
                .content(JSON.toJSONString(signInAo))
                .contentType(MediaType.APPLICATION_JSON)
                .session(SESSION))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        JSONObject apiResult = JSON.parseObject(result);
        assert apiResult.getInteger("code") == 200;
        SignInVO signInVO = apiResult.getObject("data", SignInVO.class);
        assert signInVO != null;
        return signInVO;
    }
}
