package net.renfei.service.start.impl;

import net.renfei.repository.dao.start.TStartKvStorageMapper;
import net.renfei.repository.dao.start.model.TStartKvStorage;
import net.renfei.repository.dao.start.model.TStartKvStorageExample;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.service.start.StorageService;

import java.util.Date;

/**
 * 使用数据库实现的的KV存储
 *
 * @author renfei
 */
public class StorageServiceDataBaseImpl implements StorageService {
    private final TStartKvStorageMapper kvStorageMapper;

    public StorageServiceDataBaseImpl(TStartKvStorageMapper kvStorageMapper) {
        this.kvStorageMapper = kvStorageMapper;
    }

    /**
     * 存入对象
     *
     * @param key        key
     * @param object     对象
     * @param expiration 过期时间（毫秒）
     */
    @Override
    public void set(String key, String object, Long expiration) {
        if (BeanUtils.isEmpty(key) || object == null || expiration == null) {
            return;
        }
        // 如果有存在的相同的 Key，先设置为过期，再新增
        String obj = this.get(key, true);
        TStartKvStorage kvStorage = new TStartKvStorage();
        kvStorage.setKeys(key);
        kvStorage.setValues(object);
        kvStorage.setExpiration(new Date(System.currentTimeMillis() + expiration));
        kvStorageMapper.insertSelective(kvStorage);
    }

    @Override
    public String get(String key, Boolean remove) {
        if (BeanUtils.isEmpty(key)) {
            return null;
        }
        TStartKvStorageExample example = new TStartKvStorageExample();
        example.createCriteria()
                .andKeysEqualTo(key)
                .andExpirationGreaterThanOrEqualTo(new Date());
        TStartKvStorage kvStorage = ListUtils.getOne(kvStorageMapper.selectByExampleWithBLOBs(example));
        if (kvStorage != null && remove) {
            kvStorage.setExpiration(new Date(System.currentTimeMillis() - 1000));
            kvStorageMapper.updateByPrimaryKeySelective(kvStorage);
            return kvStorage.getValues();
        }
        return null;
    }
}
