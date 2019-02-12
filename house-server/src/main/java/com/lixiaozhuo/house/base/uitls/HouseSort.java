package com.lixiaozhuo.house.base.uitls;

import java.util.Set;

import com.google.common.collect.Sets;
import org.springframework.data.domain.Sort;


/**
 * 排序生成器
 */
public class HouseSort {
    //默认排序key
    public static final String DEFAULT_SORT_KEY = "lastUpdateTime";
    //地铁距离排序key
    public static final String DISTANCE_TO_SUBWAY_KEY = "distanceToSubway";

    /**
     * 排序key集合
     */
    private static final Set<String> SORT_KEYS = Sets.newHashSet(
        DEFAULT_SORT_KEY,
            "createTime",
            "price",
            "area",
            DISTANCE_TO_SUBWAY_KEY
    );

    /**
     * 获取排序Key
     * @param key 排序key
     * @return 如果此key属于排序key中,则返回此key,否则返回默认排序key
     */
    public static String getSortKey(String key) {
        if (!SORT_KEYS.contains(key)) {
            key = DEFAULT_SORT_KEY;
        }
        return key;
    }


    /**
     * 生成排序对象
     * @param key 排序key
     * @param directionKey 排序类型
     */
    public static Sort generateSort(String key, String directionKey) {
        //获取当前排序key
        key = getSortKey(key);
        //获取排序类型
        Sort.Direction direction = Sort.Direction.fromString(directionKey);
        //默认降序
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }
        //排序对象
        return new Sort(direction, key);
    }
}
