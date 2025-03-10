package com.jxau.li.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxau.li.model.OperationLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends BaseMapper<OperationLog> {
}
