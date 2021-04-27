package net.renfei.service.start;

/**
 * 存储服务，例如存入Session或者Redis
 *
 * @author renfei
 */
public interface StorageService {
    /**
     * 存入对象
     *
     * @param key        key
     * @param object     对象
     * @param expiration 过期时间
     */
    void set(String key, Object object, Long expiration);

    /**
     * 取出对象
     *
     * @param key    key
     * @param remove 是否移除
     * @return 对象
     */
    Object get(String key, Boolean remove);
}
