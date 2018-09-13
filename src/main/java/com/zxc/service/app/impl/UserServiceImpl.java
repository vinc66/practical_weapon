package com.zxc.service.app.impl;

import com.zxc.cache.VRedis;
import com.zxc.mapper.UserMapper;
import com.zxc.model.User;
import com.zxc.service.app.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * _User: vinc
 * Date: 2018-07-30
 * Time: 10:48
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VRedis vRedis;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional(value = "txManager")
    @Override
    public void addRegister(User user) {
        User userExist = userMapper.selectByPrimaryKey(1);
        vRedis.set("1", "1");
        restTemplate.getForEntity("url", String.class);
    }

}
