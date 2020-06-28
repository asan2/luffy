package com.jcokee.controller;

import com.jcokee.pojo.vo.ResultVO;
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

    @ApiOperation("获取用户信息")
    @GetMapping("/user")
    public Mono<ResultVO> getUser(@ApiParam("用户主键") @RequestHeader("uid") String uid) {
        UserVO userVO = new UserVO();
        userVO.setUid(uid);
        userVO.setNickname("阿三");
        userVO.setAvatarUrl("https://wx.qlogo.cn/mmopen/vi/132");
        userVO.setMobile("13888888888");
        return Mono.just(new ResultVO<>(ResultVO.Status.OK, userVO));
    }
}
