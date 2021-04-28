package net.renfei.web.api.start;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 认证测试
 *
 * @author renfei
 */
@Slf4j
@Transactional
class AuthControllerTests extends ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getMyInfo() throws Exception {
        if (TOKEN != null) {
            MockHttpServletRequestBuilder requestBuilder = get("/api/auth/myInfo").contentType(MediaType.APPLICATION_JSON);
            requestBuilder.header("Authorization", "Bearer " + TOKEN);
            ResultActions resultActions = mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andDo(print());
            String result = resultActions
                    .andReturn().getResponse().getContentAsString();
            JSONObject apiResult = JSON.parseObject(result);
            assert apiResult.getInteger("code") == 200;
        }
    }
}
