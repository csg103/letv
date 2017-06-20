package com.letv.cn.csg;

import com.alibaba.fastjson.JSONObject;
import com.letv.cn.csg.dto.videoDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis存取内容
 *
 * @author csg
 * @create 2017-06-9:36
 */
@Component
@AllArgsConstructor
public class RedisSourceManager {
    public final String VIDEO_PREFIX_HOME_CAROUSEL_KEY = "HOME_VIDEO_CAROUSEL";
    private final StringRedisTemplate stringRedisTemplate;
    private final String tag = "LETV";
    /**
     * 保存视频信息到 Redis
     */
    public void saveVideos(String key, List<videoDTO> videos) {
        key=key+"_"+tag;
        String value = JSONObject.toJSONString(videos);
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 得到视频信息
     */
    public List<videoDTO> getVideosByKeyAndTag(String key) {
        key = key + "_" + tag;
        String cacheValue = stringRedisTemplate.opsForValue().get(key);
        return JSONObject.parseArray(cacheValue, videoDTO.class);
    }
}