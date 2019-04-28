package com.ssm.controller;

import com.ssm.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author one
 * @date 2019/4/26
 */
@Controller
@RequestMapping(value = "/userRedPacket")
public class UserRedPacketController {
    private final UserRedPacketService userRedPacketService;

    @Autowired
    public UserRedPacketController(UserRedPacketService userRedPacketService) {
        this.userRedPacketService = userRedPacketService;
    }

//    抢红包
//    @GetMapping(value = "/grapRedPacket")
//    @ResponseBody
//    public Map<String, Object> grepRedPacket(@RequestParam(name = "redPacketId") Long redPacketId
//            , @RequestParam(name = "userId") Long userId) {
//
//        //抢红包
//        int result = userRedPacketService.grapRedPacket(redPacketId, userId);
//
//        Map<String, Object> resMap = new HashMap<String, Object>(1 << 4);
//
//        boolean flag = result > 0;
//        resMap.put("success", flag);
//        resMap.put("message", flag ? "抢红包成功！" : "抢红包失败");
//        return resMap;
//    }

//    悲观锁抢红包
//    @GetMapping(value = "/grapRedPacketForUpdate")
//    @ResponseBody
//    public Map<String, Object> grapRedPacketForUpdate(@RequestParam(name = "redPacketId") Long redPacketId
//            , @RequestParam(name = "userId") Long userId) {
//
//        //抢红包
//        int result = userRedPacketService.grapRedPacket(redPacketId, userId);
//
//        Map<String, Object> resMap = new HashMap<String, Object>(1 << 4);
//
//        boolean flag = result > 0;
//        resMap.put("success", flag);
//        resMap.put("message", flag ? "抢红包成功！" : "抢红包失败");
//        return resMap;
//    }

    //    乐观锁抢红包
    @GetMapping(value = "/grapRedPacketForVersion")
    @ResponseBody
    public Map<String, Object> grapRedPacketForVersion(@RequestParam(name = "redPacketId") Long redPacketId
            , @RequestParam(name = "userId") Long userId) {

        //抢红包
        long result = userRedPacketService.grapRedPacketByRedis(redPacketId, userId);

        Map<String, Object> resMap = new HashMap<String, Object>(1 << 4);

        boolean flag = result > 0;
        resMap.put("success", flag);
        resMap.put("message", flag ? "抢红包成功！" : "抢红包失败");
        return resMap;
    }
}
