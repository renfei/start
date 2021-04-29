package net.renfei.web.api.start;

import lombok.extern.slf4j.Slf4j;
import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author renfei
 */
@Slf4j
public class TestControllerTests extends ApplicationTests {
    @Test
    public void testOne() throws Exception {
        if (TOKEN != null) {
            MockHttpServletRequestBuilder requestBuilder = get("/api/test/testOne")
                    .contentType(MediaType.TEXT_PLAIN)
                    .session(SESSION);
            requestBuilder.header("Authorization", "Bearer " + TOKEN);
            ResultActions resultActions = mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andDo(print());
            String result = resultActions
                    .andReturn().getResponse().getContentAsString();
            assert "testOne".equals(result);
        }
    }

    @Test
    public void testTwo() throws Exception {
        if (TOKEN != null) {
            MockHttpServletRequestBuilder requestBuilder = get("/api/test/testTwo")
                    .contentType(MediaType.TEXT_PLAIN)
                    .session(SESSION);
            requestBuilder.header("Authorization", "Bearer " + TOKEN);
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isForbidden())
                    .andDo(print());
        }
    }

    @Test
    public void testThree() throws Exception {
        if (TOKEN != null) {
            MockHttpServletRequestBuilder requestBuilder = get("/api/test/testThree")
                    .contentType(MediaType.TEXT_PLAIN)
                    .session(SESSION);
            requestBuilder.header("Authorization", "Bearer " + TOKEN);
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isForbidden())
                    .andDo(print());
        }
    }
}
