package com.lixiaozhuo.house.service.search.impl;

import java.util.List;

import com.lixiaozhuo.house.repository.HouseDetailRepository;
import com.lixiaozhuo.house.repository.HouseRepository;
import com.lixiaozhuo.house.repository.HouseTagRepository;
import com.lixiaozhuo.house.repository.SupportAddressRepository;
import com.lixiaozhuo.house.service.ServiceMultiResult;
import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.service.house.IAddressService;
import com.lixiaozhuo.house.service.search.ISearchService;
import com.lixiaozhuo.house.web.dto.HouseBucketDTO;

import com.lixiaozhuo.house.web.form.MapSearch;
import com.lixiaozhuo.house.web.form.RentSearch;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 搜索服务
 */
@Service
public class SearchServiceImpl implements ISearchService {

    //索引名称
    private static final String INDEX_NAME = "lixiaozhuo";
    //索引类型
    private static final String INDEX_TYPE = "house";
    //索引
    private static final String INDEX_TOPIC = "house_build";
    //
    private static final Logger logger = LoggerFactory.getLogger(ISearchService.class);
    //java对象自动映射工具
    @Autowired
    private ModelMapper modelMapper;
    //房屋主体信息DAO
    @Autowired
    private HouseRepository houseRepository;
    //房屋详细信息DAO
    @Autowired
    private HouseDetailRepository houseDetailRepository;
    //房屋标签信息DAO
    @Autowired
    private HouseTagRepository tagRepository;
    //支持地址信息DAO
    @Autowired
    private SupportAddressRepository supportAddressRepository;
    //地址服务
    @Autowired
    private IAddressService addressService;

    /*@Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TransportClient esClient;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;*/

    /**
     * 索引目标房源
     */
    @Override
    public void index(Long houseId) {
        //this.index(houseId, 0);
    }

    /**
     *移除目标索引
     */
    @Override
    public void remove(Long houseId) {
        //this.remove(houseId, 0);
    }

    /**
     *查询房源
     */
    @Override
    public ServiceMultiResult<Long> query(RentSearch rentSearch) {
       /* BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(
                QueryBuilders.termQuery(HouseIndexKey.CITY_EN_NAME, rentSearch.getCityEnName())
        );
        if (rentSearch.getRegionEnName() != null && !"*".equals(rentSearch.getRegionEnName())) {
            boolQuery.filter(
                    QueryBuilders.termQuery(HouseIndexKey.REGION_EN_NAME, rentSearch.getRegionEnName())
            );
        }
        HouseValueBlock area = HouseValueBlock.matchArea(rentSearch.getAreaBlock());
        if (!HouseValueBlock.ALL.equals(area)) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(HouseIndexKey.AREA);
            if (area.getMax() > 0) {
                rangeQueryBuilder.lte(area.getMax());
            }
            if (area.getMin() > 0) {
                rangeQueryBuilder.gte(area.getMin());
            }
            boolQuery.filter(rangeQueryBuilder);
        }
        HouseValueBlock price = HouseValueBlock.matchPrice(rentSearch.getPriceBlock());
        if (!HouseValueBlock.ALL.equals(price)) {
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery(HouseIndexKey.PRICE);
            if (price.getMax() > 0) {
                rangeQuery.lte(price.getMax());
            }
            if (price.getMin() > 0) {
                rangeQuery.gte(price.getMin());
            }
            boolQuery.filter(rangeQuery);
        }
        if (rentSearch.getDirection() > 0) {
            boolQuery.filter(
                    QueryBuilders.termQuery(HouseIndexKey.DIRECTION, rentSearch.getDirection())
            );
        }
        if (rentSearch.getRentWay() > -1) {
            boolQuery.filter(
                QueryBuilders.termQuery(HouseIndexKey.RENT_WAY, rentSearch.getRentWay())
            );
        }
//        boolQuery.must(
//                QueryBuilders.matchQuery(HouseIndexKey.TITLE, rentSearch.getKeywords())
//                        .boost(2.0f)
//        );
        boolQuery.must(
                QueryBuilders.multiMatchQuery(rentSearch.getKeywords(),
                        HouseIndexKey.TITLE,
                        HouseIndexKey.TRAFFIC,
                        HouseIndexKey.DISTRICT,
                        HouseIndexKey.ROUND_SERVICE,
                        HouseIndexKey.SUBWAY_LINE_NAME,
                        HouseIndexKey.SUBWAY_STATION_NAME
                ));
        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .setQuery(boolQuery)
                .addSort(
                        HouseSort.getSortKey(rentSearch.getOrderBy()),
                        SortOrder.fromString(rentSearch.getOrderDirection())
                )
                .setFrom(rentSearch.getStart())
                .setSize(rentSearch.getSize())
                .setFetchSource(HouseIndexKey.HOUSE_ID, null);
        logger.debug(requestBuilder.toString());
        List<Long> houseIds = new ArrayList<>();
        SearchResponse response = requestBuilder.get();
        if (response.status() != RestStatus.OK) {
            logger.warn("Search status is no ok for " + requestBuilder);
            return new ServiceMultiResult<>(0, houseIds);
        }
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSource());
            houseIds.add(Longs.tryParse(String.valueOf(hit.getSource().get(HouseIndexKey.HOUSE_ID))));
        }
        return new ServiceMultiResult<>(response.getHits().totalHits, houseIds);*/
        return null;
    }

    /**
     *获取关键词补全建议
     */
    @Override
    public ServiceResult<List<String>> suggest(String prefix) {
        /*CompletionSuggestionBuilder suggestion = SuggestBuilders.completionSuggestion("suggest").prefix(prefix).size(5);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("autocomplete", suggestion);
        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .suggest(suggestBuilder);
        logger.debug(requestBuilder.toString());
        SearchResponse response = requestBuilder.get();
        Suggest suggest = response.getSuggest();
        if (suggest == null) {
            return ServiceResult.ofSuccess(new ArrayList<>());
        }
        Suggest.Suggestion result = suggest.getSuggestion("autocomplete");
        int maxSuggest = 0;
        Set<String> suggestSet = new HashSet<>();
        for (Object term : result.getEntries()) {
            if (term instanceof CompletionSuggestion.Entry) {
                CompletionSuggestion.Entry item = (CompletionSuggestion.Entry) term;
                if (item.getOptions().isEmpty()) {
                    continue;
                }
                for (CompletionSuggestion.Entry.Option option : item.getOptions()) {
                    String tip = option.getText().string();
                    if (suggestSet.contains(tip)) {
                        continue;
                    }
                    suggestSet.add(tip);
                    maxSuggest++;
                }
            }
            if (maxSuggest > 5) {
                break;
            }
        }
        List<String> suggests = Lists.newArrayList(suggestSet.toArray(new String[]{}));
        return ServiceResult.ofSuccess(suggests);*/
        return null;
    }

    /**
     *聚合指定小区的房间数
     */
    @Override
    public ServiceResult<Long> aggregateDistrictHouse(String cityEnName, String regionEnName, String district) {
        /*BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery(HouseIndexKey.CITY_EN_NAME, cityEnName))
                .filter(QueryBuilders.termQuery(HouseIndexKey.REGION_EN_NAME, regionEnName))
                .filter(QueryBuilders.termQuery(HouseIndexKey.DISTRICT, district));
        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .setQuery(boolQuery)
                .addAggregation(
                        AggregationBuilders.terms(HouseIndexKey.AGG_DISTRICT)
                        .field(HouseIndexKey.DISTRICT)
                ).setSize(0);
        logger.debug(requestBuilder.toString());
        SearchResponse response = requestBuilder.get();
        if (response.status() == RestStatus.OK) {
            Terms terms = response.getAggregations().get(HouseIndexKey.AGG_DISTRICT);
            if (terms.getBuckets() != null && !terms.getBuckets().isEmpty()) {
                return ServiceResult.ofSuccess(terms.getBucketByKey(district).getDocCount());
            }
        } else {
            logger.warn("Failed to Aggregate for " + HouseIndexKey.AGG_DISTRICT);
        }
        return ServiceResult.ofSuccess(0L);*/
        return null;
    }

    /**
     *聚合城市数据
     */
    @Override
    public ServiceMultiResult<HouseBucketDTO> mapAggregate(String cityEnName) {
       /* BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(QueryBuilders.termQuery(HouseIndexKey.CITY_EN_NAME, cityEnName));
        AggregationBuilder aggBuilder = AggregationBuilders.terms(HouseIndexKey.AGG_REGION)
                .field(HouseIndexKey.REGION_EN_NAME);
        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .setQuery(boolQuery)
                .addAggregation(aggBuilder);
        logger.debug(requestBuilder.toString());
        SearchResponse response = requestBuilder.get();
        List<HouseBucketDTO> buckets = new ArrayList<>();
        if (response.status() != RestStatus.OK) {
            logger.warn("Aggregate status is not ok for " + requestBuilder);
            return new ServiceMultiResult<>(0, buckets);
        }
        Terms terms = response.getAggregations().get(HouseIndexKey.AGG_REGION);
        for (Terms.Bucket bucket : terms.getBuckets()) {
            buckets.add(new HouseBucketDTO(bucket.getKeyAsString(), bucket.getDocCount()));
        }
        return new ServiceMultiResult<>(response.getHits().getTotalHits(), buckets);*/
        return null;
    }

    /**
     *城市级别地图数据查询
     */
    @Override
    public ServiceMultiResult<Long> mapQuery(String cityEnName, String orderBy,
                                             String orderDirection,
                                             int start,
                                             int size) {
        /*BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(QueryBuilders.termQuery(HouseIndexKey.CITY_EN_NAME, cityEnName));

        SearchRequestBuilder searchRequestBuilder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .setQuery(boolQuery)
                .addSort(HouseSort.getSortKey(orderBy), SortOrder.fromString(orderDirection))
                .setFrom(start)
                .setSize(size);

        List<Long> houseIds = new ArrayList<>();
        SearchResponse response = searchRequestBuilder.get();
        if (response.status() != RestStatus.OK) {
            logger.warn("Search status is not ok for " + searchRequestBuilder);
            return new ServiceMultiResult<>(0, houseIds);
        }

        for (SearchHit hit : response.getHits()) {
            houseIds.add(Longs.tryParse(String.valueOf(hit.getSource().get(HouseIndexKey.HOUSE_ID))));
        }
        return new ServiceMultiResult<>(response.getHits().getTotalHits(), houseIds);*/
        return null;
    }

    /**
     *精确范围地图数据查询
     */
    @Override
    public ServiceMultiResult<Long> mapQuery(MapSearch mapSearch) {
        /*BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(QueryBuilders.termQuery(HouseIndexKey.CITY_EN_NAME, mapSearch.getCityEnName()));

        boolQuery.filter(
            QueryBuilders.geoBoundingBoxQuery("location")
                .setCorners(
                        new GeoPoint(mapSearch.getLeftLatitude(), mapSearch.getLeftLongitude()),
                        new GeoPoint(mapSearch.getRightLatitude(), mapSearch.getRightLongitude())
                ));

        SearchRequestBuilder builder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .setQuery(boolQuery)
                .addSort(HouseSort.getSortKey(mapSearch.getOrderBy()),
                        SortOrder.fromString(mapSearch.getOrderDirection()))
                .setFrom(mapSearch.getStart())
                .setSize(mapSearch.getSize());

        List<Long> houseIds = new ArrayList<>();
        SearchResponse response = builder.get();
        if (RestStatus.OK != response.status()) {
            logger.warn("Search status is not ok for " + builder);
            return new ServiceMultiResult<>(0, houseIds);
        }

        for (SearchHit hit : response.getHits()) {
            houseIds.add(Longs.tryParse(String.valueOf(hit.getSource().get(HouseIndexKey.HOUSE_ID))));
        }
        return new ServiceMultiResult<>(response.getHits().getTotalHits(), houseIds);*/
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*private void index(Long houseId, int retry) {
        if (retry > HouseIndexMessage.MAX_RETRY) {
            logger.error("Retry index times over 3 for house: " + houseId + " Please check it!");
            return;
        }
        HouseIndexMessage message = new HouseIndexMessage(houseId, HouseIndexMessage.INDEX, retry);
        try {
            kafkaTemplate.send(INDEX_TOPIC, objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            logger.error("Json encode error for " + message);
        }
    }*/

    /*private boolean create(HouseIndexTemplate indexTemplate) {
        if (!updateSuggest(indexTemplate)) {
            return false;
        }
        try {
            IndexResponse response = this.esClient.prepareIndex(INDEX_NAME, INDEX_TYPE)
                    .setSource(objectMapper.writeValueAsBytes(indexTemplate), XContentType.JSON).get();

            logger.debug("Create index with house: " + indexTemplate.getHouseId());
            if (response.status() == RestStatus.CREATED) {
                return true;
            } else {
                return false;
            }
        } catch (JsonProcessingException e) {
            logger.error("Error to index house " + indexTemplate.getHouseId(), e);
            return false;
        }
    }*/

    /*private boolean update(String esId, HouseIndexTemplate indexTemplate) {
        if (!updateSuggest(indexTemplate)) {
            return false;
        }
        try {
            UpdateResponse response = this.esClient.prepareUpdate(INDEX_NAME, INDEX_TYPE, esId).setDoc(objectMapper.writeValueAsBytes(indexTemplate), XContentType.JSON).get();

            logger.debug("Update index with house: " + indexTemplate.getHouseId());
            if (response.status() == RestStatus.OK) {
                return true;
            } else {
                return false;
            }
        } catch (JsonProcessingException e) {
            logger.error("Error to index house " + indexTemplate.getHouseId(), e);
            return false;
        }
    }*/

    /*private void remove(Long houseId, int retry) {
        if (retry > HouseIndexMessage.MAX_RETRY) {
            logger.error("Retry remove times over 3 for house: " + houseId + " Please check it!");
            return;
        }
        HouseIndexMessage message = new HouseIndexMessage(houseId, HouseIndexMessage.REMOVE, retry);
        try {
            this.kafkaTemplate.send(INDEX_TOPIC, objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            logger.error("Cannot encode json for " + message, e);
        }
    }*/

      /*private boolean deleteAndCreate(long totalHit, HouseIndexTemplate indexTemplate) {
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(esClient)
                .filter(QueryBuilders.termQuery(HouseIndexKey.HOUSE_ID, indexTemplate.getHouseId()))
                .source(INDEX_NAME);
        logger.debug("Delete by query for house: " + builder);
        BulkByScrollResponse response = builder.get();
        long deleted = response.getDeleted();
        if (deleted != totalHit) {
            logger.warn("Need delete {}, but {} was deleted!", totalHit, deleted);
            return false;
        } else {
            return create(indexTemplate);
        }
    }*/

      /*private void createOrUpdateIndex(HouseIndexMessage message) {
        Long houseId = message.getHouseId();
        House house = houseRepository.findOne(houseId);
        if (house == null) {
            logger.error("Index house {} dose not exist!", houseId);
            this.index(houseId, message.getRetry() + 1);
            return;
        }
        HouseIndexTemplate indexTemplate = new HouseIndexTemplate();
        modelMapper.map(house, indexTemplate);

        HouseDetail detail = houseDetailRepository.findByHouseId(houseId);
        if (detail == null) {
        }
        modelMapper.map(detail, indexTemplate);
        SupportAddress city = supportAddressRepository.findByEnNameAndLevel(house.getCityEnName(), SupportAddress.Level.CITY.getValue());
        SupportAddress region = supportAddressRepository.findByEnNameAndLevel(house.getRegionEnName(), SupportAddress.Level.REGION.getValue());
        String address = city.getCnName() + region.getCnName() + house.getStreet() + house.getDistrict() + detail.getDetailAddress();
        ServiceResult<BaiduMapLocation> location = addressService.getBaiduMapLocation(city.getCnName(), address);
        if (!location.isSuccess()) {
            this.index(message.getHouseId(), message.getRetry() + 1);
            return;
        }
        indexTemplate.setLocation(location.getResult());
        List<HouseTag> tags = tagRepository.findAllByHouseId(houseId);
        if (tags != null && !tags.isEmpty()) {
            List<String> tagStrings = new ArrayList<>();
            tags.forEach(houseTag -> tagStrings.add(houseTag.getName()));
            indexTemplate.setTags(tagStrings);
        }
        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME).setTypes(INDEX_TYPE)
                .setQuery(QueryBuilders.termQuery(HouseIndexKey.HOUSE_ID, houseId));
        logger.debug(requestBuilder.toString());
        SearchResponse searchResponse = requestBuilder.get();
        boolean ofSuccess;
        long totalHit = searchResponse.getHits().getTotalHits();
        if (totalHit == 0) {
            ofSuccess = create(indexTemplate);
        } else if (totalHit == 1) {
            String esId = searchResponse.getHits().getAt(0).getId();
            ofSuccess = update(esId, indexTemplate);
        } else {
            ofSuccess = deleteAndCreate(totalHit, indexTemplate);
        }
        ServiceResult serviceResult = addressService.lbsUpload(location.getResult(), house.getStreet() + house.getDistrict(),
                city.getCnName() + region.getCnName() + house.getStreet() + house.getDistrict(),
                message.getHouseId(), house.getPrice(), house.getArea());
        if (!ofSuccess || !serviceResult.isSuccess()) {
            this.index(message.getHouseId(), message.getRetry() + 1);
        } else {
            logger.debug("Index ofSuccess with house " + houseId);
        }
    }*/

     /*private void removeIndex(HouseIndexMessage message) {
        Long houseId = message.getHouseId();
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(esClient)
                .filter(QueryBuilders.termQuery(HouseIndexKey.HOUSE_ID, houseId))
                .source(INDEX_NAME);
        logger.debug("Delete by query for house: " + builder);
        BulkByScrollResponse response = builder.get();
        long deleted = response.getDeleted();
        logger.debug("Delete total " + deleted);
        ServiceResult serviceResult = addressService.removeLbs(houseId);
        if (!serviceResult.isSuccess() || deleted <= 0) {
            logger.warn("Did not remove data from es for response: " + response);
            // 重新加入消息队列
            this.remove(houseId, message.getRetry() + 1);
        }
    }*/


    /*@KafkaListener(topics = INDEX_TOPIC)
    private void handleMessage(String content) {
        try {
            HouseIndexMessage message = objectMapper.readValue(content, HouseIndexMessage.class);
            switch (message.getOperation()) {
                case HouseIndexMessage.INDEX:
                    this.createOrUpdateIndex(message);
                    break;
                case HouseIndexMessage.REMOVE:
                    this.removeIndex(message);
                    break;
                default:
                    logger.warn("Not support message content " + content);
                    break;
            }
        } catch (IOException e) {
            logger.error("Cannot parse json for " + content, e);
        }
    }*/


    /*private boolean updateSuggest(HouseIndexTemplate indexTemplate) {
        AnalyzeRequestBuilder requestBuilder = new AnalyzeRequestBuilder(
                this.esClient, AnalyzeAction.INSTANCE, INDEX_NAME, indexTemplate.getTitle(),
                indexTemplate.getLayoutDesc(), indexTemplate.getRoundService(),
                indexTemplate.getDescription(), indexTemplate.getSubwayLineName(),
                indexTemplate.getSubwayStationName());
        requestBuilder.setAnalyzer("ik_smart");
        AnalyzeResponse response = requestBuilder.get();
        List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
        if (tokens == null) {
            logger.warn("Can not analyze token for house: " + indexTemplate.getHouseId());
            return false;
        }
        List<HouseSuggest> suggests = new ArrayList<>();
        for (AnalyzeResponse.AnalyzeToken token : tokens) {
            // 排序数字类型 & 小于2个字符的分词结果
            if ("<NUM>".equals(token.getType()) || token.getTerm().length() < 2) {
                continue;
            }
            HouseSuggest suggest = new HouseSuggest();
            suggest.setInput(token.getTerm());
            suggests.add(suggest);
        }
        // 定制化小区自动补全
        HouseSuggest suggest = new HouseSuggest();
        suggest.setInput(indexTemplate.getDistrict());
        suggests.add(suggest);
        indexTemplate.setSuggest(suggests);
        return true;
    }*/




}
