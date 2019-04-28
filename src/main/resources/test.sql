
/**
查询同一组测试下的时间间隔
 */
select min(grab_time),max(grab_time) from t_user_red_packet where red_packet_id = 10;
