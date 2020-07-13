package com.jcokee.controller;

import com.jcokee.config.CustomConfig;
import com.jcokee.entity.User;
import com.jcokee.mapper.UserMapper;
import com.jcokee.pojo.vo.ResultVO;
import com.jcokee.pojo.vo.ResultVO.Status;
import com.jcokee.pojo.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/v1/user/centre")
public class UserController {
    private  final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

    private final CustomConfig customConfig;

    public UserController(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/user")
    public Mono<ResultVO<List<User>>> getUser(@ApiParam("用户主键") @RequestHeader("uid") String uid) {
        List<User> users = null;
        try {
            users = userMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Mono<ResultVO<List<User>>> just = Mono.just(new ResultVO(Status.OK, users));
        return just;
    }

    @ApiOperation("测试配置信息")
    @GetMapping("/config")
    public Mono<ResultVO<String>> testConfig() {
        return Mono.just(new ResultVO(ResultVO.Status.OK, customConfig.getFirstConfig()));
    }
}
