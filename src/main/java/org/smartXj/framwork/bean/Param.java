package org.smartXj.framwork.bean;

import org.smartXj.framwork.util.CastUtil;

import java.util.Map;

/**
 * 请求参数的对象
 */
public class Param {
    private Map<String,Object> paramMap;

    public Param(Map<String,Object> paramMap)
    {
        this.paramMap=paramMap;
    }

    public long getLong(String name)
    {
       return CastUtil.castLong(this.paramMap.get(name));
    }

    public Map<String,Object> getMap()
    {
        return this.paramMap;
    }
}
