package com.jcokee.controller;

import com.jcokee.config.CustomConfig;
import com.jcokee.pojo.vo.ResultVO;
import com.jcokee.pojo.vo.ResultVO.Status;
import com.jcokee.pojo.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/user/centre")
public class UserController {

    private final CustomConfig customConfig;

    public UserController(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/user")
    public Mono<ResultVO<UserVO>> getUser(@ApiParam("用户主键") @RequestHeader("uid") String uid) {
        UserVO userVO = new UserVO();
        userVO.setUid(uid);
        userVO.setNickname("阿三");
        userVO.setAvatarUrl("https://wx.qlogo.cn/mmopen/vi/132");
        userVO.setMobile("13888888888");
        Mono<ResultVO<UserVO>> just = Mono.just(new ResultVO(Status.OK, userVO));
        return just;
    }

    @ApiOperation("测试配置信息")
    @GetMapping("/config")
    public Mono<ResultVO<String>> testConfig() {
        return Mono.just(new ResultVO(ResultVO.Status.OK, customConfig.getFirstConfig()));
    }
}
