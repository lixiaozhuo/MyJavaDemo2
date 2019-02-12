package com.lixiaozhuo.house.base.constant;

import java.util.Map;
import com.google.common.collect.ImmutableMap;

/**
 * 房屋常用值区间
 */
public class HouseValueBlock {
    /**
     * 价格区间定义
     */
    public static final Map<String, HouseValueBlock> PRICE_BLOCK;

    /**
     * 面积区间定义
     */
    public static final Map<String, HouseValueBlock> AREA_BLOCK;

    /**
     * 无限制区间
     */
    public static final HouseValueBlock ALL = new HouseValueBlock("*", -1, -1);

    static {
        PRICE_BLOCK = ImmutableMap.<String, HouseValueBlock>builder()
                .put("*-1000", new HouseValueBlock("*-1000", -1, 1000))
                .put("1000-3000", new HouseValueBlock("1000-3000", 1000, 3000))
                .put("3000-*", new HouseValueBlock("3000-*", 3000, -1))
                .build();

        AREA_BLOCK = ImmutableMap.<String, HouseValueBlock>builder()
                .put("*-30", new HouseValueBlock("*-30", -1, 30))
                .put("30-50", new HouseValueBlock("30-50", 30, 50))
                .put("50-*", new HouseValueBlock("50-*", 50, -1))
                .build();
    }
    /**
     * 匹配价格区间
     */
    public static HouseValueBlock matchPrice(String key) {
        HouseValueBlock block = PRICE_BLOCK.get(key);
        if (block == null) {
            return ALL;
        }
        return block;
    }

    /**
     * 匹配面积区间
     */
    public static HouseValueBlock matchArea(String key) {
        HouseValueBlock block = AREA_BLOCK.get(key);
        if (block == null) {
            return ALL;
        }
        return block;
    }

    //区间key
    private String key;
    //最小值
    private int min;
    //最大值
    private int max;

    public HouseValueBlock(String key, int min, int max) {
        this.key = key;
        this.min = min;
        this.max = max;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
