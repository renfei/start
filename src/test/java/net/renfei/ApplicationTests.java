package net.renfei;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.SystemConfig;
import net.renfei.sdk.utils.AESUtil;
import net.renfei.sdk.utils.DateUtils;
import net.renfei.sdk.utils.RSAUtils;
import net.renfei.sdk.utils.StringUtils;
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
    protected static String AES_KEY;
    protected static String AES_KEY_ID;
    protected MockHttpSession SESSION = new MockHttpSession();
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected SystemConfig systemConfig;

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
            // ????????????
            SecretKeyVO aesKey = setSecretKey(secretKey);
            SignInAO signInAo = new SignInAO();
            String password = "tests";
            signInAo.setPassword(AESUtil.encrypt(password, aesKey.getPublicKey()));
            signInAo.setKeyId(aesKey.getKeyId());
            signInAo.setUseVerCode(false);
            signInAo.setUserName("tester");
            SignInVO signInVO = signIn(signInAo);
            // ?????? SESSION ??????????????????????????? Token
            log.info("Token:{}", signInVO.getToken());
            log.info("ucScript:{}", signInVO.getUcScript());
            if (signInVO.getToken() != null) {
                TOKEN = signInVO.getToken();
            }
        }
    }

    /**
     * ???????????????????????????????????????????????????
     *
     * @param secretKey
     * @return
     * @throws Exception
     */
    public SecretKeyVO setSecretKey(SecretKeyVO secretKey) throws Exception {
        // ?????????????????????
        Map<Integer, String> map = RSAUtils.genKeyPair(1024);
        // ???????????????
        String privateKey = map.get(1);
        // ???????????????
        String publicKey = map.get(0);
        // ??????????????????????????????????????????
        String publicKeys = RSAUtils.encrypt(publicKey, secretKey.getPublicKey());
        ReportPublicKeyAO reportPublicKey = new ReportPublicKeyAO();
        reportPublicKey.setPublicKey(publicKeys);
        reportPublicKey.setKeyId(secretKey.getKeyId());
        // ??????????????????
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
        // ?????? AES ??????
        String aes = RSAUtils.decrypt(secretKeys.getPublicKey(), privateKey);
        AES_KEY_ID = secretKeys.getKeyId();
        AES_KEY = aes;
        log.info("??????ID???{}", secretKeys.getKeyId());
        log.info("?????????{}", aes);
        secretKeys.setPublicKey(aes);
        secretKeys.setPrivateKey(aes);
        return secretKeys;
    }

    public SignInVO signIn(SignInAO signInAo) throws Exception {
        String result = mockMvc.perform(post("/api/auth/signIn" + getSignature())
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

    public String getSignature() {
        String timestamp = DateUtils.getUnixTimestamp() + "";
        String nonce = StringUtils.getRandomNumber(6);
        String aesKeyId = AES_KEY_ID;
        String signature = StringUtils.signature(timestamp, nonce, AES_KEY);
        return "?timestamp=" + timestamp + "&nonce=" + nonce + "&aesKeyId=" + aesKeyId + "&signature=" + signature;
    }
}
