package com.ssm.dao;

import com.ssm.domain.UserRedPacket;

/**
 * 抢红包信息
 *
 * @author one
 * @date 2019/4/26
 */
public interface UserRedPacketDao {

    /**
     * 插入抢红包信息
     *
     * @param userRedPacket 抢红包信息
     * @return 影响记录数
     */
    int grapRedPacket(UserRedPacket userRedPacket);
}
