package com.ssm.service.impl;

import com.ssm.dao.RedPacketDao;
import com.ssm.dao.UserRedPacketDao;
import com.ssm.domain.RedPacket;
import com.ssm.domain.UserRedPacket;
import com.ssm.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author one
 * @date 2019/4/26
 */
@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {

    private final RedPacketDao redPacketDao;

    private final UserRedPacketDao userRedPacketDao;


    private int FAILED = 0;

    @Autowired
    public UserRedPacketServiceImpl(RedPacketDao redPacketDao, UserRedPacketDao userRedPacketDao) {
        this.redPacketDao = redPacketDao;
        this.userRedPacketDao = userRedPacketDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int grapRedPacket(Long redPacketId, Long userId) {


//        //获取红包信息 by 加锁
//        RedPacket redPacket = redPacketDao.getRedPacketForUpdate(redPacketId);
        for (int i = 0; i < 3; i++) {
            //       获取红包信息
            RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
            //当前小红包库存大于0
            if (redPacket != null && redPacket.getStock() > 0) {
//            redPacketDao.decreaseRedPacket(redPacketId);
//          开始时间戳
//            long start = System.currentTimeMillis();


                //获取循环中当前时间
//                long end = System.currentTimeMillis();

//                //限制在100ms内可以重入
//                if (end - start > 1000){
//                    return FAILED;
//                }

                //            再次传入线程保存的 version 旧值给 SQL 判断，是否有其他线程修改过数据
                int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());

                //如果没有数据更新，重新抢夺锁
                if (update == 0) {
                    continue;
                }

                //生成抢红包信息
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(redPacket.getAmount());
                userRedPacket.setNote("抢红包 : " + redPacketId);


                //插入抢红包信息
                return userRedPacketDao.grapRedPacket(userRedPacket);
            } else {
                return FAILED;
            }

        }

        return FAILED;
    }
}
