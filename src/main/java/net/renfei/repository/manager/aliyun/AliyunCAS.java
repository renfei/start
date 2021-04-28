package net.renfei.repository.manager.aliyun;

import com.aliyuncs.cas.model.v20180713.CreateUserCertificateRequest;
import com.aliyuncs.cas.model.v20180713.CreateUserCertificateResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import net.renfei.config.RenFeiConfig;
import org.springframework.stereotype.Service;

/**
 * 阿里云证书服务
 *
 * @author renfei
 */
@Service
public class AliyunCAS extends AliyunService {
    protected AliyunCAS(RenFeiConfig renFeiConfig) {
        super(renFeiConfig, DefaultProfile.getProfile("cn-hangzhou",
                renFeiConfig.getAliyun().getAccessKeyId(),
                renFeiConfig.getAliyun().getAccessKeySecret()));
    }

    /**
     * 添加证书
     *
     * @param certName 自定义的证书名称:同一个用户下的证书名称不能重复。
     * @param cert     指定PEM格式的证书内容:指定PEM格式的证书内容
     * @param certKey  指定PEM格式证书的私钥内容:指定PEM格式证书的私钥内容
     */
    public CreateUserCertificateResponse createUserCertificate(String certName, String cert, String certKey) throws ClientException {
        CreateUserCertificateRequest request = new CreateUserCertificateRequest();
        request.setName(certName);
        request.setCert(cert);
        request.setKey(certKey);
        return client.getAcsResponse(request);
    }
}
