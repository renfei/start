package net.renfei.service.start.impl;

import net.renfei.service.start.StorageService;

import javax.servlet.http.HttpServletRequest;

/**
 * 使用Session实现的KV存储
 *
 * @author renfei
 */
public class StorageServiceSessionImpl implements StorageService {
    HttpServletRequest request;

    public StorageServiceSessionImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void set(String key, String object, Long expiration) {
        request.getSession().setAttribute(key, object);
    }

    @Override
    public String get(String key, Boolean remove) {
        Object object = request.getSession().getAttribute(key);
        if (object != null && remove) {
            request.getSession().removeAttribute(key);
            return (String) object;
        }
        return null;
    }
}
