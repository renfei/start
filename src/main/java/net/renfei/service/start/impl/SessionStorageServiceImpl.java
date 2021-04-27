package net.renfei.service.start.impl;

import net.renfei.service.start.StorageService;

import javax.servlet.http.HttpServletRequest;

/**
 * 使用Session实现的存储
 *
 * @author renfei
 */
public class SessionStorageServiceImpl implements StorageService {
    HttpServletRequest request;

    public SessionStorageServiceImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void set(String key, Object object, Long expiration) {
        request.getSession().setAttribute(key, object);
    }

    @Override
    public Object get(String key, Boolean remove) {
        Object object = request.getSession().getAttribute(key);
        if (remove != null && remove) {
            request.getSession().removeAttribute(key);
        }
        return object;
    }
}
