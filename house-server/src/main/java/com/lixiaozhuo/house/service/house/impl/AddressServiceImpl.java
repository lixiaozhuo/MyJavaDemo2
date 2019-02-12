package com.lixiaozhuo.house.service.house.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lixiaozhuo.house.entity.Subway;
import com.lixiaozhuo.house.entity.SubwayStation;
import com.lixiaozhuo.house.entity.SupportAddress;
import com.lixiaozhuo.house.repository.SubwayRepository;
import com.lixiaozhuo.house.repository.SubwayStationRepository;
import com.lixiaozhuo.house.repository.SupportAddressRepository;
import com.lixiaozhuo.house.service.ServiceMultiResult;
import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.service.house.IAddressService;
import com.lixiaozhuo.house.web.dto.SubwayDTO;
import com.lixiaozhuo.house.web.dto.SubwayStationDTO;
import com.lixiaozhuo.house.web.dto.SupportAddressDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 地址服务
 */
@Service
public class AddressServiceImpl implements IAddressService {
    //java对象自动映射工具
    @Autowired
    private ModelMapper modelMapper;
    //地铁线路信息DAO
    @Autowired
    private SubwayRepository subwayRepository;
    //地铁站信息DAO
    @Autowired
    private SubwayStationRepository subwayStationRepository;
    //支持地址信息DAO
    @Autowired
    private SupportAddressRepository supportAddressRepository;

    ////////////////////////////////百度地图///////////////////////////////////////////////////////////////////////////
    @Autowired
    private ObjectMapper objectMapper;
    private static final String BAIDU_MAP_KEY = "6QtSF673D1pYl3eQkEXfwp8ZgsQpB77U";
    private static final String BAIDU_MAP_GEOCONV_API = "http://api.map.baidu.com/geocoder/v2/?";
    // POI数据管理接口
    private static final String LBS_CREATE_API = "http://api.map.baidu.com/geodata/v3/poi/create";
    private static final String LBS_QUERY_API = "http://api.map.baidu.com/geodata/v3/poi/list?";
    private static final String LBS_UPDATE_API = "http://api.map.baidu.com/geodata/v3/poi/update";
    private static final String LBS_DELETE_API = "http://api.map.baidu.com/geodata/v3/poi/delete";
    //private static final Logger logger = LoggerFactory.getLogger(IAddressService.class);

    //////////////////////////////////指定信息//////////////////////////////////////////////////////////////
    /**
     * 获取指定地区详细信息
     */
    @Override
    public Map<SupportAddress.Level, SupportAddressDTO> findCityAndRegion(String cityEnName, String regionEnName) {
        Map<SupportAddress.Level, SupportAddressDTO> result = new HashMap<>();
        //参数检查
        if (cityEnName == null || regionEnName == null) {
            return result;
        }
        //根据城市缩写获取城市信息
        SupportAddress city = supportAddressRepository.findByEnNameAndLevel(cityEnName, SupportAddress.Level.CITY
                .getValue());
        if (city == null) {
            return result;
        }
        //根据地区缩写和所在城市缩写获取详细地区信息
        SupportAddress region = supportAddressRepository.findByEnNameAndBelongTo(regionEnName, city.getEnName());
        if(region == null){
            return result;
        }
        //将数据转换为DTO数据存放到集合中
        result.put(SupportAddress.Level.CITY, modelMapper.map(city, SupportAddressDTO.class));
        result.put(SupportAddress.Level.REGION, modelMapper.map(region, SupportAddressDTO.class));
        return result;
    }


    /**
     * 获取指定城市详细信息
     */
    @Override
    public ServiceResult<SupportAddressDTO> findCity(String cityEnName) {
        //参数检查
        if (cityEnName == null) {
            return ServiceResult.ofMessage(false,"参数为空");
        }
        //根据城市简写获取城市信息
        SupportAddress supportAddress = supportAddressRepository.findByEnNameAndLevel(cityEnName,
                SupportAddress.Level.CITY.getValue());
        if (supportAddress == null) {
            return ServiceResult.notFound();
        }
        //将SupportAddress转换为SupportAddressDTO
        return ServiceResult.ofSuccess(modelMapper.map(supportAddress, SupportAddressDTO.class));
    }
    /**
     * 获取指定地铁线信息
     */
    @Override
    public ServiceResult<SubwayDTO> findSubway(Long subwayId) {
        //参数检查
        if (subwayId == null) {
            return ServiceResult.ofMessage(false,"参数为空");
        }
        //根据id获取地铁线路信息
        Optional<Subway> optional = subwayRepository.findById(subwayId);
        if (!optional.isPresent()) {
            return ServiceResult.notFound();
        }
        //将Subway转换为SubwayDTO
        return ServiceResult.ofSuccess(modelMapper.map(optional.get(), SubwayDTO.class));
    }
    /**
     * 获取指定地铁站信息
     */
    @Override
    public ServiceResult<SubwayStationDTO> findSubwayStation(Long stationId) {
        //参数检查
        if (stationId == null) {
            return ServiceResult.ofMessage(false,"参数为空");
        }
        //根据id获取地铁站信息
        Optional<SubwayStation> optional = subwayStationRepository.findById(stationId);
        if (!optional.isPresent()) {
            return ServiceResult.notFound();
        }
        ////将SubwayStation转换为SubwayStationDTO
        return ServiceResult.ofSuccess(modelMapper.map(optional.get(), SubwayStationDTO.class));
    }
    ////////////////////////////////////信息集合////////////////////////////////////////////////////////
    /**
     * 获取所有支持的城市列表
     */
    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllCities() {
        List<SupportAddressDTO> addressDTOS = new ArrayList<>();
        //获取所有支持的城市数据
        List<SupportAddress> addresses = supportAddressRepository.findAllByLevel(SupportAddress.Level.CITY.getValue());
        //将SupportAddress数据转换为SupportAddressDTO数据
        for (SupportAddress supportAddress : addresses) {
            SupportAddressDTO target = modelMapper.map(supportAddress, SupportAddressDTO.class);
            addressDTOS.add(target);
        }
        //返回结果集
        return new ServiceMultiResult<>(addressDTOS.size(), addressDTOS);
    }
    /**
     * 获取指定城市所有支持地区
     */
    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllRegionsByCityName(String cityName) {
        //参数检查
        if (cityName == null) {
            return new ServiceMultiResult<>(0, null);
        }
        List<SupportAddressDTO> result = new ArrayList<>();
        //根据城市缩写获取地区集合
        List<SupportAddress> regions = supportAddressRepository.findAllByLevelAndBelongTo(SupportAddress.Level.REGION
                .getValue(), cityName);
        //将SupportAddress转换为SupportAddressDTO
        for (SupportAddress region : regions) {
            result.add(modelMapper.map(region, SupportAddressDTO.class));
        }
        //返回数据集合
        return new ServiceMultiResult<>(regions.size(), result);
    }
    /**
     * 获取指定城市所有的地铁线路
     */
    @Override
    public ServiceMultiResult<SubwayDTO> findAllSubwayByCity(String cityEnName) {
        //参数检查
        if (cityEnName == null) {
            return new ServiceMultiResult<>(0, null);
        }
        List<SubwayDTO> result = new ArrayList<>();
        //获取指定城市地铁线路集合
        List<Subway> subways = subwayRepository.findAllByCityEnName(cityEnName);
        //将Support转换为SupportDTO
        for (Subway subway : subways) {
            result.add(modelMapper.map(subway, SubwayDTO.class));
        }
        //返回数据集合
        return new ServiceMultiResult<>(subways.size(), result);
    }
    /**
     *获取地铁线路所有的站点
     */
    @Override
    public ServiceMultiResult<SubwayStationDTO> findAllStationBySubway(Long subwayId) {
        //参数检查
        if (subwayId == null) {
            return new ServiceMultiResult<>(0, null);
        }
        List<SubwayStationDTO> result = new ArrayList<>();
        //获取指定地铁线路的站点集合
        List<SubwayStation> stations = subwayStationRepository.findAllBySubwayId(subwayId);
        //将SSubwayStation转换为SubwayStationDTO
        for (SubwayStation station : stations) {
            result.add(modelMapper.map(station, SubwayStationDTO.class));
        }
        //返回数据集合
        return new ServiceMultiResult<>(stations.size(), result);
    }
    ////////////////////////////////////////地图服务////////////////////////////////////////////////////////////////////
    /**
     *根据城市以及具体地位获取地图的经纬度
     */
    /*@Override
    public ServiceResult<BaiduMapLocation> getBaiduMapLocation(String city, String address) {
        String encodeAddress;
        String encodeCity;

        try {
            encodeAddress = URLEncoder.encode(address, "UTF-8");
            encodeCity = URLEncoder.encode(city, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Error to encode house address", e);
            return new ServiceResult<BaiduMapLocation>(false, "Error to encode hosue address");
        }

        HttpClient httpClient = HttpClients.createDefault();
        StringBuilder sb = new StringBuilder(BAIDU_MAP_GEOCONV_API);
        sb.append("address=").append(encodeAddress).append("&")
                .append("city=").append(encodeCity).append("&")
                .append("output=json&")
                .append("ak=").append(BAIDU_MAP_KEY);

        HttpGet get = new HttpGet(sb.toString());
        try {
            HttpResponse response = httpClient.execute(get);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return new ServiceResult<BaiduMapLocation>(false, "Can not get baidu map location");
            }

            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            JsonNode jsonNode = objectMapper.readTree(result);
            int status = jsonNode.get("status").asInt();
            if (status != 0) {
                return new ServiceResult<BaiduMapLocation>(false, "Error to get map location for status: " + status);
            } {
                BaiduMapLocation location = new BaiduMapLocation();
                JsonNode jsonLocation = jsonNode.get("result").get("location");
                location.setLongitude(jsonLocation.get("lng").asDouble());
                location.setLatitude(jsonLocation.get("lat").asDouble());
                return ServiceResult.ofSuccess(location);
            }

        } catch (IOException e) {
            logger.error("Error to fetch baidumap api", e);
            return new ServiceResult<BaiduMapLocation>(false, "Error to fetch baidumap api");
        }
    }*/

    /**
     *上传LBS数据
     */
    /*@Override
    public ServiceResult lbsUpload(BaiduMapLocation location, String title,
                                   String address,
                                   long houseId, int price,
                                   int area) {
        HttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("latitude", String.valueOf(location.getLatitude())));
        nvps.add(new BasicNameValuePair("longitude", String.valueOf(location.getLongitude())));
        nvps.add(new BasicNameValuePair("coord_type", "3")); // 百度坐标系
        nvps.add(new BasicNameValuePair("geotable_id", "175730"));
        nvps.add(new BasicNameValuePair("ak", BAIDU_MAP_KEY));
        nvps.add(new BasicNameValuePair("houseId", String.valueOf(houseId)));
        nvps.add(new BasicNameValuePair("price", String.valueOf(price)));
        nvps.add(new BasicNameValuePair("area", String.valueOf(area)));
        nvps.add(new BasicNameValuePair("title", title));
        nvps.add(new BasicNameValuePair("address", address));

        HttpPost post;
        if (isLbsDataExists(houseId)) {
            post = new HttpPost(LBS_UPDATE_API);
        } else {
            post = new HttpPost(LBS_CREATE_API);
        }

        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = httpClient.execute(post);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                logger.error("Can not upload lbs data for response: " + result);
                return new ServiceResult(false, "Can not upload baidu lbs data");
            } else {
                JsonNode jsonNode = objectMapper.readTree(result);
                int  status = jsonNode.get("status").asInt();
                if (status != 0) {
                    String message = jsonNode.get("message").asText();
                    logger.error("Error to upload lbs data for status: {}, and message: {}", status, message);
                    return new ServiceResult(false, "Error to upload lbs data");
                } else {
                    return ServiceResult.ofSuccess();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ServiceResult(false);
    }*/



    /**
     *移除LBS数据
     */
    /*@Override
    public ServiceResult removeLbs(Long houseId) {
        HttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("geotable_id", "175730"));
        nvps.add(new BasicNameValuePair("ak", BAIDU_MAP_KEY));
        nvps.add(new BasicNameValuePair("houseId", String.valueOf(houseId)));

        HttpPost delete = new HttpPost(LBS_DELETE_API);
        try {
            delete.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = httpClient.execute(delete);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                logger.error("Error to delete lbs data for response: " + result);
                return new ServiceResult(false);
            }

            JsonNode jsonNode = objectMapper.readTree(result);
            int status = jsonNode.get("status").asInt();
            if (status != 0) {
                String message = jsonNode.get("message").asText();
                logger.error("Error to delete lbs data for message: " + message);
                return new ServiceResult(false, "Error to delete lbs data for: " + message);
            }
            return ServiceResult.ofSuccess();
        } catch (IOException e) {
            logger.error("Error to delete lbs data.", e);
            return new ServiceResult(false);
        }
    }*/

    /**
     * 判断Lbs数据是否存在
     */
    private boolean isLbsDataExists(Long houseId) {
        /*HttpClient httpClient = HttpClients.createDefault();
        StringBuilder sb = new StringBuilder(LBS_QUERY_API);
        sb.append("geotable_id=").append("175730").append("&")
                .append("ak=").append(BAIDU_MAP_KEY).append("&")
                .append("houseId=").append(houseId).append(",").append(houseId);
        HttpGet get = new HttpGet(sb.toString());
        try {
            HttpResponse response = httpClient.execute(get);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                logger.error("Can not get lbs data for response: " + result);
                return false;
            }

            JsonNode jsonNode = objectMapper.readTree(result);
            int status = jsonNode.get("status").asInt();
            if (status != 0) {
                logger.error("Error to get lbs data for status: " + status);
                return false;
            } else {
                long size = jsonNode.get("size").asLong();
                if (size > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }*/
        return false;
    }
}
