package net.renfei.service.start;

import lombok.extern.slf4j.Slf4j;
import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Redis服务测试
 *
 * @author renfei
 */
@Slf4j
public class RedisServiceTests extends ApplicationTests {
    @Autowired
    private RedisService redisService;

    /**
     * 测试简单对象缓存
     */
    @Test
    public void simpleTest() {
        List<String> arrString = new ArrayList<String>() {{
            this.add("aaaa");
            this.add("bbbb");
            this.add("dddd");
            this.add("cccc");
        }};
        redisService.set("redis:simple:arrString", arrString);
        Object obj = redisService.get("redis:simple:arrString");
        assert obj != null;
        assert obj instanceof Collection;
        List<String> arrStringBack = (List<String>) obj;
        assert arrStringBack.size() == 4;
        redisService.del("redis:simple:arrString");
    }

    /**
     * 测试Hash结构的缓存
     */
    @Test
    public void hashTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("test", "testValue");
        map.put("123", 123);
        map.put("date", new Date());
        redisService.hSetAll("redis:hash:map", map);
        Map<Object, Object> cacheValue = redisService.hGetAll("redis:hash:map");
        assert map.equals(cacheValue);
    }

    /**
     * 测试Set结构的缓存
     */
    @Test
    public void setTest() {
        String key = "redis:set:all";
        Object[] arrObj = new Object[4];
        arrObj[0] = "string";
        arrObj[1] = 123;
        arrObj[2] = new Date();
        arrObj[3] = "string2";
        redisService.sAdd(key, arrObj);
        Set<Object> cachedBrandList = redisService.sMembers(key);
        assert cachedBrandList.size() == 4;
        redisService.sRemove(key, arrObj[3]);
        cachedBrandList = redisService.sMembers(key);
        assert cachedBrandList.size() == 3;
    }

    /**
     * 测试List结构的缓存
     */
    @Test
    public void listTest() {
        String key = "redis:list:all";
        Object[] arrObj = new Object[4];
        arrObj[0] = "string";
        arrObj[1] = 123;
        arrObj[2] = new Date();
        arrObj[3] = "string2";
        redisService.lPushAll(key, arrObj);
        redisService.lRemove(key, 1, arrObj[3]);
        List<Object> cachedBrandList = redisService.lRange(key, 0, 2);
        assert cachedBrandList.size() == 3;
    }
}
