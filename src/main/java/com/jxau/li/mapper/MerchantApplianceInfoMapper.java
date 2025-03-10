package com.jxau.li.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxau.li.model.ApplianceInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MerchantApplianceInfoMapper extends BaseMapper<ApplianceInfo> {


    int updateStatus(long id, String status);

    boolean updateIsOrdered(long id, String isOrdered);
}
