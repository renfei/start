package net.renfei.web.api.start;

import net.renfei.config.SystemConfig;
import net.renfei.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于给单元测试准备的Controller
 *
 * @author renfei
 */
@RestController
@RequestMapping("/api/test")
public class TestController extends BaseController {
    protected TestController(SystemConfig systemConfig) {
        super(systemConfig);
    }

    @GetMapping("testOne")
    private String testOne() {
        return "testOne";
    }

    @GetMapping("testTwo")
    private String testTwo() {
        return "testTwo";
    }

    @GetMapping("testThree")
    private String testThree() {
        return "testThree";
    }
}
