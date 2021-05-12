package net.renfei.web.api.start;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 行政区划代码接口单元测试
 *
 * @author renfei
 */
@Slf4j
@Transactional
public class RegionCodeControllerTests extends ApplicationTests {
    @Test
    public void getRegion() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/api/regionCode")
                .contentType(MediaType.APPLICATION_JSON)
                .session(SESSION);
        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());
        String result = resultActions
                .andReturn().getResponse().getContentAsString();
        JSONObject apiResult = JSON.parseObject(result);
        assert apiResult.getInteger("code") == 200;
    }

    @Test
    public void getRegionByCode() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/api/regionCode/130000")
                .contentType(MediaType.APPLICATION_JSON)
                .session(SESSION);
        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());
        String result = resultActions
                .andReturn().getResponse().getContentAsString();
        JSONObject apiResult = JSON.parseObject(result);
        assert apiResult.getInteger("code") == 200;
    }

    @Test
    public void getRegionByCode2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/api/regionCode/130100")
                .contentType(MediaType.APPLICATION_JSON)
                .session(SESSION);
        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());
        String result = resultActions
                .andReturn().getResponse().getContentAsString();
        JSONObject apiResult = JSON.parseObject(result);
        assert apiResult.getInteger("code") == 200;
    }
}
