package com.jxau.li.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxau.li.model.User;
import com.jxau.li.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<User> {

    /**
     * 根据用户名和角色查询用户
     * @param user
     * @return
     */
    User selectByUsernameAndRole(UserDTO user);
}
