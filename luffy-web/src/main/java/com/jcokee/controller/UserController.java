package com.jcokee.controller;

import com.alibaba.fastjson.JSON;
import com.jcokee.config.CustomConfig;
import com.jcokee.entity.User;
import com.jcokee.pojo.vo.ResultVO;
import com.jcokee.pojo.vo.ResultVO.Status;
import com.jcokee.service.impl.UserServiceImpl;
import com.jcokee.util.RedisClient;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user/centre")
@Slf4j
@RefreshScope
public class UserController {

    @Autowired
    private RedisClient redisClient;
    @Autowired
    private UserServiceImpl userService;

    private final CustomConfig customConfig;

    public UserController(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/user")
    public ResultVO<String> getUser(@ApiParam("用户主键") @RequestHeader("uid") String uid) {
        User user = userService.selectById(uid);
        return new ResultVO(Status.OK, user);
    }

    @ApiOperation("测试配置信息")
    @GetMapping("/config")
    public ResultVO<String> testConfig() {
        return new ResultVO(ResultVO.Status.OK, customConfig.getFirstConfig());
    }

    @ApiOperation("测试缓存信息")
    @GetMapping("/cache")
    public ResultVO<User> testCache(@RequestParam String uid) {
        User user = userService.selectById(uid);
        if (user == null) {
            return new ResultVO(ResultVO.Status.OK, null);
        }

        String cache = JSON.toJSONString(user);
        redisClient.setForTimeMIN("SOA:" + uid, cache, 10);

        return new ResultVO(ResultVO.Status.OK, user);
    }
}
