package com.twinswolves.web.admin.service.impl;

import com.twinswolves.web.admin.entity.Users;
import com.twinswolves.web.admin.mapper.UsersMapper;
import com.twinswolves.web.admin.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表，存储用户基本信息 服务实现类
 * </p>
 *
 * @author nacker
 * @since 2025-06-09
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
