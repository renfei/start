package net.renfei.repository.manager.aliyun;

import com.aliyuncs.dcdn.model.v20180115.SetDcdnDomainCertificateRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import net.renfei.config.RenFeiConfig;
import org.springframework.stereotype.Service;

/**
 * 阿里云全站CDN服务
 *
 * @author renfei
 */
@Service
public class AliyunDCDN extends AliyunService {
    protected AliyunDCDN(RenFeiConfig renFeiConfig) {
        super(renFeiConfig,
                DefaultProfile.getProfile("cn-hangzhou",
                        renFeiConfig.getAliyun().getAccessKeyId(),
                        renFeiConfig.getAliyun().getAccessKeySecret()));
    }

    public void setDcdnDomainCertificate(String domainName, String certName, String privateKey) throws ClientException {
        SetDcdnDomainCertificateRequest request = new SetDcdnDomainCertificateRequest();
        request.setDomainName(domainName);
        request.setCertName(certName);
        request.setCertType("cas");
        request.setSSLProtocol("on");
        request.setSSLPri(privateKey);
        client.getAcsResponse(request);
    }
}
