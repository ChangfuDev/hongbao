
/**
查询同一组测试下的时间间隔和库存
 */
select t.stock as stock,min(u.grab_time),max(u.grab_time),max(u.grab_time) - min(u.grab_time) as useTime  from t_user_red_packet as u , t_red_packet as t where u.red_packet_id = 11 and t.id = 11;
