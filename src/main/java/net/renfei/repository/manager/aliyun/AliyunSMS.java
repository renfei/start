package net.renfei.repository.manager.aliyun;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.RenFeiConfig;
import net.renfei.service.start.SmsService;
import net.renfei.service.start.dto.SmsDTO;
import net.renfei.util.GeneralConvertor;
import org.springframework.stereotype.Service;

/**
 * 阿里云短信服务
 *
 * @author renfei
 */
@Slf4j
@Service
public class AliyunSMS extends AliyunService implements SmsService {
    protected AliyunSMS(GeneralConvertor convertor, RenFeiConfig renFeiConfig) {
        super(convertor, renFeiConfig, null);
    }

    @Override
    public void send(SmsDTO sms) {
        // TODO 检查发送频率，1条/分钟，5条/小时，10条/24小时
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", renFeiConfig.getAliyun().getSms().getRegionId());
        request.putQueryParameter("PhoneNumbers", sms.getPhoneNumbers());
        request.putQueryParameter("SignName", sms.getSignName());
        request.putQueryParameter("TemplateCode", sms.getTemplateCode());
        request.putQueryParameter("TemplateParam", sms.getTemplateParam());
        request.putQueryParameter("OutId", sms.getOutId());
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            log.error("inputParams:{} and errorMessage:{}", sms, e.getMessage(), e);
        }
    }
}
