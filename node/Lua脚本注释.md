# Lua脚本使用
```java
 String script = "local listKey = 'red_packet_list_'..KEYS[1] \n"
                + "local redPacket = 'red_packet_'..KEYS[1] \n"
                + "local stock = tonumber(redis.call('hget', redPacket, 'stock')) \n"
                + "if stock <= 0 then return 0 end \n"
                + "stock = stock -1 \n"
                + "redis.call('hset', redPacket, 'stock', tostring(stock)) \n"
                + "redis.call('rpush', listKey, ARGV[1]) \n"
                + "if stock == 0 then return 2 end \n"
                + "return 1 \n";
```
                
  //缓存抢红包信息列表的key
  
  local listKey = 'red_packet_list_'..KEYS[1]
  
  //当前抢红包的key
  
  local redPacket = 'red_packet_'..KEYS[1]
  
  //缓存当前红包库存
  
  local stock = tonumber(redis.call('hget', redPacket, 'stock'))
  
  //没有库存，返回0 
  
  if stock <= 0 then return 0 end
  
  //库存 - 1
  
  stock = stock -1
  
  //保存当前库存
  
  redis.call('hset', redPacket, 'stock', tostring(stock))
  
  //往红包list中加入当前红包信息
  
  redis.call('rpush', listKey, tonumber(ARGV[1]))
  
  //如果库存 == 0，返回2
  
  if stock == 0 then return 2 end
  
  //返回1 ，表示抢红包成功
  
  return 1
  
