package net.renfei.web.api.start;

import com.wf.captcha.ArithmeticCaptcha;
import net.renfei.config.SystemConfig;
import net.renfei.sdk.entity.APIResult;
import net.renfei.service.start.StorageService;
import net.renfei.service.start.StorageServiceFactory;
import net.renfei.web.BaseController;
import net.renfei.web.api.start.vo.CaptchaVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 公共Controller
 *
 * @author renfei
 */
@Controller
@RequestMapping("/common")
public class CommonApiController extends BaseController {
    private final StorageServiceFactory storageServiceFactory;

    public CommonApiController(SystemConfig systemConfig,
                               StorageServiceFactory storageServiceFactory) {
        super(systemConfig);
        this.storageServiceFactory = storageServiceFactory;
    }

    /**
     * 获取验证码
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("captcha.png")
    public void captcha(HttpServletResponse response) throws IOException {
        // 设置请求头为输出图片类型
        response.setContentType("image/png");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 三个参数分别为宽、高、位数
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48, 2);
        // 获取运算的公式：3+2=?
        captcha.getArithmeticString();
        // 获取运算的结果：5
        captcha.text();
        // 将正确答案保存起来
        StorageService storageService = storageServiceFactory.getStorageService(request);
        storageService.set("captcha", captcha.text().toLowerCase(), null);
        // 输出图片流
        captcha.out(response.getOutputStream());
    }

    /**
     * 前后端分离模式获取验证码
     *
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("captcha")
    public APIResult<CaptchaVO> captcha() {
        // 三个参数分别为宽、高、位数
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48, 2);
        // 获取运算的公式：3+2=?
        captcha.getArithmeticString();
        // 获取运算的结果：5
        captcha.text();
        String key = "CAPTCHA_" + UUID.randomUUID();
        // 将正确答案保存起来
        StorageService storageService = storageServiceFactory.getStorageService(request);
        storageService.set(key, captcha.text().toLowerCase(), 60000L);
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setKey(key);
        // 输出图片
        captchaVO.setImage(captcha.toBase64());
        return new APIResult<>(captchaVO);
    }
}
