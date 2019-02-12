package com.lixiaozhuo.house.service.search;

import com.lixiaozhuo.house.service.ServiceMultiResult;
import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.web.dto.HouseBucketDTO;
import com.lixiaozhuo.house.web.form.MapSearch;
import com.lixiaozhuo.house.web.form.RentSearch;

import java.util.List;
/**
 * 搜索服务接口
 */
public interface ISearchService {
    /**
     * 索引目标房源
     * @param houseId 房屋id
     */
    void index(Long houseId);

    /**
     * 移除目标索引
     * @param houseId  房源id
     */
    void remove(Long houseId);

    /**
     * 查询房源
     * @param rentSearch 搜索请求
     */
    ServiceMultiResult<Long> query(RentSearch rentSearch);

    /**
     *  获取关键词补全建议
     * @param prefix 关键词前缀
     */
    ServiceResult<List<String>> suggest(String prefix);

    /**
     * 聚合指定小区的房间数
     * @param cityEnName 城市英文缩写
     * @param regionEnName 地区英文缩写
     * @param district 所在小区
     */
    ServiceResult<Long> aggregateDistrictHouse(String cityEnName, String regionEnName, String district);

    /**
     * 聚合城市数据
     * @param cityEnName 城市英文缩写
     */
    ServiceMultiResult<HouseBucketDTO> mapAggregate(String cityEnName);

    /**
     * 城市级别地图数据查询
     * @param cityEnName 城市英文缩写
     * @param orderBy 排序
     * @param orderDirection 排序方式
     * @param start 开始页
     * @param size 页面大小
     */
    ServiceMultiResult<Long> mapQuery(String cityEnName, String orderBy,
                                      String orderDirection, int start, int size);
    /**
     * 精确范围地图数据查询
     * @param mapSearch 地铁搜索请求
     */
    ServiceMultiResult<Long> mapQuery(MapSearch mapSearch);


}
