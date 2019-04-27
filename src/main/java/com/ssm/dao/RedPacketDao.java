package com.ssm.dao;

import com.ssm.domain.RedPacket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 红包信息
 *
 * @author one
 * @date 2019/4/25
 */
@Repository
public interface RedPacketDao {

    /**
     * 获取红包信息
     *
     * @param id 红包id
     * @return 红包具体信息
     */
    RedPacket getRedPacket(@Param("id") Long id);


    /**
     * 扣减红包数量
     *
     * @param id 红包id
     * @return 更新记录条数
     */
    int decreaseRedPacket(Long id);

}
