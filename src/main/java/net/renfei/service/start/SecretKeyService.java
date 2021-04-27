package net.renfei.service.start;

import net.renfei.service.start.dto.SecretKeyDTO;

import java.util.Map;

/**
 * 秘钥服务
 *
 * @author renfei
 */
public interface SecretKeyService {
    /**
     * 生成服务器公钥
     *
     * @return 服务器公钥
     */
    Map<Integer, String> generateServerSecretKey();

    /**
     * 根据秘钥ID获取秘钥
     *
     * @param secretKeyId 秘钥ID
     * @return {@link SecretKeyDTO}
     */
    SecretKeyDTO getSecretKeyById(String secretKeyId);

    /**
     * 根据RSA秘钥ID解密
     *
     * @param str         密文
     * @param secretKeyId RSA秘钥
     * @return 明文
     */
    String decryptByRsaKeyId(String str, String secretKeyId);

    /**
     * RSA加密
     *
     * @param str         明文
     * @param secretKeyId 秘钥ID
     * @return 密文
     */
    String encryptByRsaKey(String str, String secretKeyId);

    /**
     * 创建 AES 秘钥
     *
     * @return AES秘钥
     */
    Map<String, String> generateAesKey();

    /**
     * 根据 AES 秘钥解密
     *
     * @param string      密文
     * @param secretKeyId AES秘钥ID
     * @return 明文
     */
    String decryptByAesKeyId(String string, String secretKeyId);
}
