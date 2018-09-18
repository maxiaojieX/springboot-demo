/**
 * Copyright 2015 ProficientCity Inc.
 */
package com.danger.bean;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.KetamaConnectionFactory;
import net.spy.memcached.MemcachedClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Memcached工具类
 *
 */
public class MemcachedUtil {
	private static Logger logger = LoggerFactory.getLogger(MemcachedUtil.class);

	private static MemcachedClient mclient;

	/**
	 * 链接超时，默认1秒
	 */
	private static long CONNECT_TIMEOUT = 1000L;
	/**
	 * 操作超时，默认5秒
	 */
	private static long OPERATE_TIMEOUT = 5000L;

	static {

		try {
			mclient = new MemcachedClient(new KetamaConnectionFactory(){
				@Override
				public long getOperationTimeout() { 
					//设置默认1秒连接超时
					return CONNECT_TIMEOUT; 
				}
			}, AddrUtil.getAddresses("119.27.161.212:11211"));
		} catch (IOException e) {
			logger.error("MemcachedClient initial fail", e);
		}
}

	/**
	 * 根据Key获取缓存对象
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return StringUtils.isEmpty(key)?null:mclient.get(key);
	}

	public static Object getAndTouch(String key, int expiry){
		Object value = StringUtils.isEmpty(key)?null:mclient.get(key);
		if(value != null){
			reset(key, expiry);
		}
		return value;
	}

	/**
	 * 根据key获取缓存对象, 并指定一个刷新源, 如果没有命中使用刷新源重新获取数据，并根据指定过期时间 重新保存到缓存中
	 * 
	 * @param key
	 *            -- 缓存key
	 * @param refreshSource
	 *            -- 刷新源
	 * @return -- 缓存对象
	 *         <p>
	 *         备注:
	 *         若调用refreshSource抛出异常，该方法会捕捉并重新抛出一个RuntimeException提示“执行刷新缓存对象失败”
	 *         等价代码: Object result = cacheServcie.get(key); if (null == result)
	 *         {
	 * 
	 *         result = refreshSource.call(); cacheServcie.set(key, result,
	 *         expiry); } return result;
	 */
	public static Object get(String key, Callable<?> refreshSource, int expiry) {
		Object value = mclient.get(key);
		if (null == value) {
			try {
				value = refreshSource.call();
				if (null != value) {
					mclient.set(key, expiry, value);
				}
			} catch (Exception ex) {
				throw new RuntimeException("保存数据到Memcached异常", ex);
			}
		}
		return value;
	}

	public static Object getAndTouch(String key, Callable<?> refreshSource, int expiry){
		Object value = mclient.get(key);
		if (null == value) {
			try {
				value = refreshSource.call();
				if (null != value) {
					mclient.set(key, expiry, value);
				}
			} catch (Exception ex) {
				throw new RuntimeException("保存数据到Memcached异常", ex);
			}
		}else{
			reset(key, expiry);
		}
		return value;
	}

	/**
	 * 设置缓存对象
	 * 
	 * @param key
	 * @param expiry
	 *            过期时间，单位是秒。这个数字在两个不同值域有不同意思，当值小于60*60*24*30，即30天，
	 *            会被Memcached理解为时间的Offset；当该值大于30天时，会被视为Unix时间锉，而不再是Offset
	 * @param value
	 * @return
	 */
	public static boolean set(String key, int expiry, Object value) {
		try {
			Future<Boolean> f = mclient.set(key, expiry, value);
			return f.get(OPERATE_TIMEOUT, TimeUnit.MILLISECONDS).booleanValue();
		} catch (InterruptedException e) {
			logger.error("Memcached set data fail, key:" + key, e);
		} catch (ExecutionException e) {
			logger.error("Memcached set data fail, key:" + key, e);
		} catch (TimeoutException e) {
			logger.error("Memcached set data timeout, key:" + key, e);
		}
		return false;
	}

	/**
	 * 类似于set方法，区别在于该方法只会在key不存在时添加
	 */
	public static boolean add(String key, int expiry, Object value) {
		try {
			Future<Boolean> f = mclient.add(key, expiry, value);
			return f.get(OPERATE_TIMEOUT, TimeUnit.MILLISECONDS).booleanValue();
		} catch (InterruptedException e) {
			logger.error("Memcached set data fail, key:" + key, e);
		} catch (ExecutionException e) {
			logger.error("Memcached set data fail, key:" + key, e);
		} catch (TimeoutException e) {
			logger.error("Memcached set data timeout, key:" + key, e);
		}
		return false;
	}
	
	/**
	 * 重置缓存对象过期时间
	 * 
	 * @param key
	 * @param expiry
	 *          过期时间，单位是秒。这个数字在两个不同值域有不同意思，当值小于60*60*24*30，即30天，
	 *          会被Memcached理解为时间的Offset；当该值大于30天时，会被视为Unix时间锉，而不再是Offset
	 * @return
	 */
	public static boolean reset(String key, int expiry) {
		try {
			Object obj = mclient.get(key);
			if(obj == null) {
				return false;
			}
			Future<Boolean> f = mclient.set(key, expiry, obj);
			return f.get(OPERATE_TIMEOUT, TimeUnit.MILLISECONDS).booleanValue();
		} catch (InterruptedException e) {
			logger.error("Memcached reset data timeout fail, key: {}", key, e); 
		} catch (ExecutionException e) {
			logger.error("Memcached set data fail, key: {}", key, e);
		} catch (TimeoutException e) {
			logger.error("Memcached reset data timeout, key:{}", key, e);
		}
		return false;
	}

	/**
	 * 删除缓存对象
	 * 
	 * @param key
	 * @return
	 */
	public static boolean delete(String key) {
		try {
			Future<Boolean> f = mclient.delete(key);
			return f.get(OPERATE_TIMEOUT, TimeUnit.MILLISECONDS).booleanValue();
		} catch (InterruptedException e) {
			logger.error("Memcached delete data fail, key:" + key, e);
		} catch (ExecutionException e) {
			logger.error("Memcached delete data fail, key:" + key, e);
		} catch (TimeoutException e) {
			logger.error("Memcached delete data timeout, key:" + key, e);
		}
		return false;

	}
}
