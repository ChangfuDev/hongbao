package com.ssm.dao;

import com.ssm.domain.RedPacket;
import org.apache.ibatis.annotations.Mapper;

/**
 * 红包信息
 *
 * @author one
 * @date 2019/4/25
 */
@Mapper
public interface RedPacketDao {

    /**
     * 获取红包信息
     *
     * @param id 红包id
     * @return 红包具体信息
     */
    RedPacket getRedPacket(Long id);


    /**
     * 扣减红包数量
     *
     * @param id 红包id
     * @return 更新记录条数
     */
    int decreaseRedPacket(Long id);

}
