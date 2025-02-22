package com.jxau.li.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxau.li.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoManagementMapper extends BaseMapper<User> {
    /**
     * 更新用户状态
     * @param id
     * @param status
     * @return
     */
    int updateById(Long id, String status);
}
