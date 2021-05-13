package net.renfei.service.start.impl;

import com.aliyun.oss.ServiceException;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.repository.dao.start.TStartSecretKeyMapper;
import net.renfei.repository.dao.start.model.TStartSecretKeyExample;
import net.renfei.repository.dao.start.model.TStartSecretKeyWithBLOBs;
import net.renfei.sdk.utils.*;
import net.renfei.service.BaseService;
import net.renfei.service.start.SecretKeyService;
import net.renfei.service.start.dto.SecretKeyDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 秘钥服务
 *
 * @author renfei
 */
@Slf4j
@Service
public class SecretKeyServiceImpl extends BaseService implements SecretKeyService {
    private final static int AES_KEY_SIZE = 16;
    private final static int SERVER_SECRET_KEY_SIZE = 2048;
    private final TStartSecretKeyMapper secretKeyMapper;

    protected SecretKeyServiceImpl(SystemConfig systemConfig,
                                   TStartSecretKeyMapper secretKeyMapper) {
        super(systemConfig);
        this.secretKeyMapper = secretKeyMapper;
    }


    @Override
    public Map<Integer, String> generateServerSecretKey() {
        Map<Integer, String> map = RSAUtils.genKeyPair(SERVER_SECRET_KEY_SIZE);
        if (BeanUtils.isEmpty(map)) {
            return null;
        }
        //保存
        String uuid = UUID.randomUUID().toString();
        TStartSecretKeyWithBLOBs secretKey = new TStartSecretKeyWithBLOBs();
        secretKey.setPrivateKey(map.get(1));
        secretKey.setPublicKey(map.get(0));
        secretKey.setCreateTime(new Date());
        secretKey.setUuid(uuid);
        secretKey.setIsDeleted(false);
        secretKeyMapper.insertSelective(secretKey);
        map.put(1, uuid);
        return map;
    }

    @Override
    public SecretKeyDTO getSecretKeyById(String secretKeyId) {
        if (BeanUtils.isEmpty(secretKeyId)) {
            throw new BusinessException("secretKeyId不能为空");
        }
        TStartSecretKeyExample example = new TStartSecretKeyExample();
        example.createCriteria()
                .andIsDeletedEqualTo(false)
                .andUuidEqualTo(secretKeyId);
        TStartSecretKeyWithBLOBs secretKeyDO = ListUtils.getOne(secretKeyMapper.selectByExampleWithBLOBs(example));
        if (secretKeyDO == null) {
            throw new BusinessException("secretKeyId不正确");
        }
        SecretKeyDTO secretKeyDTO = new SecretKeyDTO();
        org.springframework.beans.BeanUtils.copyProperties(secretKeyDO, secretKeyDTO);
        return secretKeyDTO;
    }

    @Override
    public String decryptByRsaKeyId(String str, String secretKeyId) {
        SecretKeyDTO secretKey = this.getSecretKeyById(secretKeyId);
        try {
            return RSAUtils.decrypt(str, secretKey.getPrivateKey());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new BusinessException("publicKey解密失败");
        }
    }

    @Override
    public String encryptByRsaKey(String str, String secretKey) {
        try {
            return RSAUtils.encrypt(str, secretKey.replaceAll("\n", ""));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ServiceException("服务器内部错误，使用RSA客户端公钥加密失败");
        }
    }

    @Override
    public Map<String, String> generateAesKey() {
        String aesKey = StringUtils.getRandomString(AES_KEY_SIZE);
        String uuid = UUID.randomUUID().toString();
        TStartSecretKeyWithBLOBs secretKey = new TStartSecretKeyWithBLOBs();
        secretKey.setPrivateKey(aesKey);
        secretKey.setCreateTime(new Date());
        secretKey.setUuid(uuid);
        secretKey.setIsDeleted(false);
        secretKeyMapper.insertSelective(secretKey);
        Map<String, String> map = new ConcurrentHashMap<>(2);
        map.put("keyId", uuid);
        map.put("aesKey", aesKey);
        return map;
    }

    @Override
    public String decryptByAesKeyId(String string, String secretKeyId) {
        SecretKeyDTO secretKey = this.getSecretKeyById(secretKeyId);
        try {
            return AESUtil.decrypt(string, secretKey.getPrivateKey());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new BusinessException("加密密文解密失败");
        }
    }
}
