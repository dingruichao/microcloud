package com.ding.springcloud.service.calc;

import java.util.LinkedHashMap;

public class LruCache<K, V> extends LinkedHashMap<K, V> {


    private int maxCacheSize;

    public LruCache(int maxCacheSize) {

        // 第三个参数为 accessOrder，默认为false。表示按照按照访问顺序排列元素，最近访问的元素会排雷在队末尾

        super(maxCacheSize, 0.75f, true);

        this.maxCacheSize = maxCacheSize;

    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {

        // 当达到预设缓存上限时删除最老元素
        return this.size() >= maxCacheSize + 1;
    }

}
