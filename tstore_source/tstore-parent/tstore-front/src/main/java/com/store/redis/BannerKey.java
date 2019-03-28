package com.store.redis;

public class BannerKey extends BasePrefix {
    
	public BannerKey( String prefix) {
		super(prefix);
	}

	public BannerKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

	public static BannerKey banner = new BannerKey(180,"banner");

}
