package com.ssm.service;

import com.ssm.domain.RedPacket;

/**
 * @author one
 * @date 2019/4/26
 */
public interface RedPacketService {

    /**
     * 获取红包
     *
     * @param id 红包id
     * @return 红包信息
     */
    RedPacket getRedPacket(Long id);


    /**
     * 扣减红包
     *
     * @param id 红包id
     * @return 影响条数
     */
    int decreaseRedPacket(Long id);
}
