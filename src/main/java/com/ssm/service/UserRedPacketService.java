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

    /**
     * 通过Redis实现抢红包
     *
     * @param redPacketId --红包编号
     * @param userId      -- 用户编号
     * @return 0-没有库存，失败
     * 1--成功，且不是最后一个红包
     * 2--成功，且是最后一个红包
     */
    Long grapRedPacketByRedis(Long redPacketId, Long userId);
}
