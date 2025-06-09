package com.twinswolves.web.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.twinswolves.web.admin.service.UsersService;
import com.twinswolves.web.admin.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户表，存储用户基本信息 前端控制器
 * </p>
 *
 * @author nacker
 * @since 2025-06-09
 */
@Controller
@RequestMapping("/users")
public class UsersController {


    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Users>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Users> aPage = usersService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Users> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(usersService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Users params) {
        usersService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        usersService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Users params) {
        usersService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
