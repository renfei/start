package net.renfei.repository.manager.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.IClientProfile;
import net.renfei.config.SystemConfig;
import net.renfei.service.BaseService;

/**
 * @author renfei
 */
public abstract class AliyunService extends BaseService {
    protected final SystemConfig systemConfig;
    protected IAcsClient client;

    protected AliyunService(SystemConfig systemConfig,
                            IClientProfile profile) {
        super(systemConfig);
        this.systemConfig = systemConfig;
        if (profile != null) {
            client = new DefaultAcsClient(profile);
        }
    }
}
