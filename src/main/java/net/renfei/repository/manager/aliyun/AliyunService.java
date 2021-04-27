package net.renfei.repository.manager.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.IClientProfile;
import net.renfei.config.RenFeiConfig;
import net.renfei.service.BaseService;

/**
 * @author renfei
 */
public abstract class AliyunService extends BaseService {
    protected final RenFeiConfig renFeiConfig;
    protected IAcsClient client;

    protected AliyunService(RenFeiConfig renFeiConfig,
                            IClientProfile profile) {
        super(renFeiConfig);
        this.renFeiConfig = renFeiConfig;
        if (profile != null) {
            client = new DefaultAcsClient(profile);
        }
    }
}
