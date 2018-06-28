package com.letv.cn.csg.controller;

import com.letv.cn.csg.RedisSourceManager;
import com.letv.cn.csg.dto.videoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页controller
 *
 * @author csg
 * @create 2017-06-11:15
 */
@Controller
@AllArgsConstructor
public class indexController {

    private final static String[] TAGS = {"LETV","PANDA"};

    private final RedisSourceManager redisSourceManager;

    @GetMapping("/index")
    public String home(Model model){
        List<videoDTO> carouselPics = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_CAROUSEL_KEY);
              model.addAttribute("carouselPics", carouselPics);
        return "home";
    }

}
