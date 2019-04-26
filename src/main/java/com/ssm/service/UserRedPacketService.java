package com.ssm.service;

/**
 * @author one
 * @date 2019/4/26
 */
public interface UserRedPacketService {

    /**
     * 保存抢红包信息
     *
     * @param redPacketId 红包id
     * @param userId      用户id
     * @return 抢红包信息
     */
    int grapRedPacket(Long redPacketId, Long userId);


//    Long grapRedPacketByRedis(Long redPacketId, Long userId);
}
