package com.ssm.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author one
 * @date 2019/4/25
 */
@Data
public class UserRedPacket implements Serializable {
    private Long id;
    private Long redPacketId;
    private Long userId;
    private Double amount;
    private Timestamp grabTime;
    private String note;
}
