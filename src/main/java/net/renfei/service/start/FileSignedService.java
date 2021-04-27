package net.renfei.service.start;

import java.util.Date;

/**
 * 文件授权签名服务
 *
 * @author renfei
 */
public interface FileSignedService {
    /**
     * 获取签名URL进行临时授权，默认有效期24小时
     *
     * @param objectName 对象地址
     * @return 签名URL
     */
    String getSignedUrl(String objectName);

    /**
     * 获取签名URL进行临时授权
     *
     * @param objectName 对象地址
     * @param expiration 授权过期时间
     * @return 签名URL
     */
    String getSignedUrl(String objectName, Date expiration);

    /**
     * 获取签名URL进行临时授权
     *
     * @param bucketName 储存桶
     * @param objectName 对象地址
     * @param expiration 授权过期时间
     * @return 签名URL
     */
    String getSignedUrl(String bucketName, String objectName, Date expiration);
}
