package com.skyler.mybibleAdmin_api.user.controller;

import com.skyler.mybibleAdmin_api.common.error.ApiErrorCodeExamples;
import com.skyler.mybibleAdmin_api.common.error.ErrorCode;
import com.skyler.mybibleAdmin_api.common.response.Response;
import com.skyler.mybibleAdmin_api.common.response.ResponseUtils;
import com.skyler.mybibleAdmin_api.user.dto.UserRequest;
import com.skyler.mybibleAdmin_api.user.entity.User;
import com.skyler.mybibleAdmin_api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;


    @ApiErrorCodeExamples({ErrorCode.METHOD_NOT_ALLOWED})
    @PostMapping("/auth/login")
    public Response<User> login(@RequestBody UserRequest.PostUserReq req) {
        User newUser = userService.save(new User(req.getLoginId()));
        return ResponseUtils.success(newUser);

    }

//    @ApiErrorCodeExamples({ErrorCode.METHOD_NOT_ALLOWED})
//    @PostMapping("/users")
//    public Response<User> postUser(@RequestBody UserRequest.PostUserReq req) {
//        User newUser = userService.save(new User(req.getLoginId()));
//        return ResponseUtils.success(newUser);
//
//    }

    @ApiErrorCodeExamples({ErrorCode.METHOD_NOT_ALLOWED, ErrorCode.INTERNAL_ERROR, ErrorCode.METHOD_})
    @GetMapping("/users/{userId}")
    public Response<User> getUser(@PathVariable Long userId){
        User user = userService.getUser(userId);

        return ResponseUtils.success(user);

    }

    @GetMapping("/users/list")
    public Response<List<User>> getUserList(){
        List<User> list = userService.getUserList();


        return ResponseUtils.success(list);

    }
}
