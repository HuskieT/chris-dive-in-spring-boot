#
**redis4 可以用unlink 删除大key**
**redis 返回取值返回从0开始 ，范围为等值（例： 1 4  //从第二个到第五个 包括第五个值）**
# 1 springboot +mybatis+ redis 
@Cacheable 触发缓存入口
@CacheEvict 触发移除缓存
@CacahePut 更新缓存
@Caching 将多种缓存操作分组
@CacheConfig 类级别的缓存注解，允许共享缓存名称

# 2 redis通用命令

keys
dbsize  （可以再生产环境中使用  resdis内部计算）
exists key  （检查key是否存在）
del key [key ...]  （删除指定key-value）
expire key seconds    （key在seconds秒后过期）
ttl key               （查看key剩余的过期时间）
persist key           （去掉key的过期时间）
type key              （返回key类型（5种数据类型+none））  
## 2.1 keys  （一般不在生产环境中使用）
主要在 热备从节点时使用
> keys he*       
> keys he[a-p]*
> keys he? 

* 表示任意位
[a-p] 表示此位 位a-p段内的字符
? 匹配单个字符

# 3 redis 是单线程 
线上拒绝执行长（慢）命令：
keys、flushall、flushdb、slow lua script，nutil/exec 、operate big value(collection)

# 4 5种数据类型
## 4.1 字符串类型
适用场景： 缓存、计数器、 分布式锁 、其他 
> get key
> set key value
> del key 

> incr key  key自增1,如果key不存在，自增后get(key)=1
> decr key  key自减1,如果key不存在，自增后get(key)=-1
> incrby key k   key自增k,如果key不存在，自增后get(key)=k
> decrby key k   key自减k,如果key不存在，自增后get(key)=-k

实战场景：
1 记录网站每个用户个人主页的访问量？  =》使用 incr key
2 缓存视频的基本信息（数据源在mysql中）=》redis 缓存的使用
3 分布式id生成器  =》使用 incr key （因为redis是单线程的 所以生成的id不会重复）

> set kye value   不管可以是否存在，都设置
> setnx kye value  可以不存在 才设置 
> set kye value xx  key存在 ，才设置
> mget kye1 kye2  key3  一次返回多个key的value值    （时间复杂度 o(n) 因为是批处理）
> mset kye1 value1 kye2 value2 key3 value3  一次设置多个key value值 （时间复杂度 o(n)）
> getset key newvalue   set key newvalue  并返回旧的value
> append key value       将value追加到旧的value
> strlen key             strlen key   返回字符串的长度(注意中文)
> incrbyfloat key 3.6    增加可以对应的值3.6
> getrange key start end       获取字符串指定下标所有的值
>
例：
> set hello world
> append hello "java"   
> get hello   返回 worldjava

> set hello javabest
> getrange hello 0 2   //找到key是hello的值的下标从第一个字符开始到弟3个字符 （结果 jav）
> setrange hello 4 p   //找到key是hello的值 并将第5个字符替换为p

## 4.2 哈希类型

>   hset user:1:info age 23
>   hget user:1:info age      //结果  "23"
>   hset user:1:info name chris   
>   hget user:1:info        //结果  "age" "23" "name" "chris"
>   hdel user:1:info age 

>   hexists key field   // 判断hash key 是否有field
>   hlen key            // 获取hash key field的数量
>   hmget               // 批量获取hash key 的一批field对应的值
>   hmset               // 批量设置hash key 的一批field value

实战场景：
1 记录网站每个用户个人主页的访问量？  =》使用 hincrby user:1:info pageview count
2 缓存视频的基本信息（数据源在mysql中）=》使用hash类型

>   hgetall key         // 返回hash key 对应所有的field和value （所有 key value）
>   hvals key           // 返回 hash key 对应所有field的value  （所有 value）
>   hkeys key           // 返回 hash key 对应所有field         （所有 key） 
>   hsetnx key field value          //设置hash key对应field的value（如field 已经存在，则失败）
>   hincrby key field intCounter    //hash key 对应的field 的value自增intCounter
>   hincrbyfloat key field floatCounter     //hincrby 浮点数版

## 4.3 列表结构 （有序  允许重复 ）

>   rpush key value1 value2 ...valueN   //从列表右端插入
>   lpush key value1 value2 ...valueN   //从列表左端插入   
>   linsert key before|after value newValue     //在list指定的值前|后插入newValue
>   lpop key                            //从列表左侧弹出一个item
>   rpop key                            //从列表右侧弹出一个item
>   lrem key count value                //根据count值 ，从列表删除所有value相等的项
                                        count>0  从左到右  删除最多count个value相等的项
                                        count<0  从右到左  删除最多Math.abs(count)个value相等的项   
                                        count=0  删除所有value相等的项   

>   ltrim key start end（包含end）                 //按照索引范围修剪列表
>例：  ltrim  list  1 3    ； ltrim  list  2 -1   （-1 表示到最后一个值 ） 

>   lindex key index                    //获取列表指定索引的item
>   llen key                            //获取 列表长度
>   lset key index newvalue             // 设置列表指定索引值为newvalue
**补充**
>   blpop key timeout                   // lpop阻塞版本 timeout是阻塞超时时间，timeout=0 为永远不阻塞
>   brpop key timeout                   // rpop阻塞版本 timeout是阻塞超时时间，timeout=0 为永远不阻塞

实现栈的功能： lrush + lpop = stack
实现队列的功能：lpush + rpop = queue
实现有固定大小的列表的功能：lpush + ltrim 
实现消息队列的功能：lpush + brpop


实战场景：
1 微博关注人  最新动态/微博

## 4.4 集合结构

>  sadd key element （元素）            //向集合key添加element（如果element 已经存在 ，那么添加失败）
>  srem key element                     //将集合key中的element移除掉
>  scard  user:1:follow           //计算集合大小（结果：4  ）
>  sismember user:1:follow it    // 判断 it 是否存在集合中
>  srandmember user:1:follow  count   //从集合中随机挑选count个元素
>  spop  user:1:follow    //从集合中随机弹出一个元素
>  smembers user:1:follow     //获取集合所有元素
实战场景：
1 抽奖场景    =》spop  user:1:follow
2 赞 踩  =》
3 共同关注好友  =》  sinter user:1:follow   user:2:follow  

>  sdiff  user:1:follow   user:2:follow    //差集
>  sinter user:1:follow   user:2:follow    //交集
>  sunion user:1:follow   user:2:follow    //并集
>  sdiff|sinter|suion + store destkey    //将差集、交集、并集结果保存到destkey中
## 4.5 有序集合

>  zadd key score element (可以是多对)       //添加score 和 element（时间复杂度 o(logN)）
>  zrem key element                     // 删除元素
>  zscore key element                    //饭后元素的分值
>  zincrby key increScore element     //增加或者减少元素的分值
>  zcard key                                 //返回元素的总个数
>  zrank key  element                    //获取分值的排名
>   zrange key  start  end  [withscore]   //返回指定索引范围内的升序元素[分值](获取一段排名区间的所有分值)
    **withscore 为备选项  （有则显示 分值和元素，没有 只显示元素）**
> zrangebyscore key minScore maxScoe  [withscore]      //返回指定分数范围内的升序元素[分值]
>  zcount key minScore maxScore           //返回有序集合内在指定分数范围内的个数
> zremrangebyrank  key start end        //删除指定排名内的升序元素
> zremrangebyscore  key minScore maxScore        //删除指定分数内的升序元素

实战场景：
1 排行榜    =》存储   id    名称  （计算出rank）

>  zrevrank
>  zrevrange
>  zrevrangeby
>  zinterstore
>  zunionstore

# 5 redis其他功能

## 5.1 慢查询

slowlog-max-len         //慢查询队列长度 （通常设置 1000左右）

slowlog-log-slower-than      //  慢查询阈值（单位：微秒）默认10毫秒  （通常设置 1毫秒）
slowlog-log-slower-than =0   // 记录所有命令
slowlog-log-slower-than < 0   // 不记录任何命令

配置方式：
1 修改配置文件重启
2 命令行动态配置 
 config  set slowlog-max-len 100
config set slowlog-log-slower-than 1000      //(1 毫秒)

slowlog get [n]         获取慢查询队列
slowlog len               获取慢查询队列长度
slowlog  reset            清空慢查询队列

**定期持久化慢查询**
## 5.2 pipeline

    注意每次pipeline 携带数据量
    pipeline 每次只能作用在一个Redis节点上
    M操作与pipeline区别

##  5.3 发布订阅

>  publish channel  message            //发布命令 
>  subscribe [channel]                    //一个或多个  （订阅）  
>  unsubscribe [channel]                //一个或多个  （取消订阅）  
>  psubscribe [pattern...]            //订阅模式
>  punsubscribe [pattern...]          //退订指定的模式
>  punsub channels                  //  列出至少有一个订阅者的频道
>  pubsub bumsub [channel...]    // 列出给定频道的订阅者数量
>  pubsub numpat                  //列出被订阅模式的数量

## 5.4  bitmap 位图

>  setbit key offset value        // 给位图指定索引设置值
>  getbit key offset
>  bitcount key [start end]      //获取位图指定返回（start 到 end，单位为字节 ，如果不指定就是获取全部）位值为1的个数
使用场景:
1 独立用户统计  （统计每天登陆的用户  并存储 （假设有1亿用户 4000万用户登陆））

## 5.5 HyperLogLog
基于HyperLogLog 算法 ，使用极小空间完成独立数量统计
>  pfadd  key element [element...]          //向HyperLogLog添加元素
>  pfcount key [key...]                //计算HyperLogLog的独立总数
>  pfmerge destkey sourcekey [sourcekey...]        //合并多个HyperLogLog

使用经验
1 是否容忍错误 ？  （错误率  0.81%）
2 是否需要单条数据 ？

# 6 redis持久化
## 6.1 简介
RDB =》 快照
AOF =》写日志

## 6.2 RDB
触发机制（3种）
save 同步      （文件替换策略 新的替换旧的）
bgsave  异步   （RDB一般采用这种）
自动 

自动生成RDB：

>  save  seconds  changes        //seconds    时间 ；   changes        改变数
例如 save 60 10000      60秒内10000条数据改变时触发

## 6.3 AOF
AOF的三种策略： always  everysec   no
[1] always
redis =》写命令刷新的缓冲区=》每条命令fsnc到硬盘=》AOF文件
[2] everysec   
redis =》写命令刷新的缓冲区=》每秒把缓冲区fsnc到硬盘=》AOF文件
[2] no
redis =》写命令刷新的缓冲区=》由系统决定=》AOF文件
AOF 重写作用：
减少硬盘占用量
加速恢复速度
AOF 重写实现两种方式： bgrewriteaof   AOF重写配置
> bgrewriteaof      //启动一个线程 进行redisAOF重写（redis内）
 AOF重写配置：

auto-aof-rewrite-min-size          // AOF 文件重写需要的尺寸
auto-aof-rewrite-percentage      //AOF文件增长率
AOF 统计：
aof_current_size          // AOF 当前尺寸
aof_base_size            //AOF 上次启动和重写的尺寸

总结：
RDB 最佳策略 ：关闭   集中管理  主从 从开
AOF  最佳策略：
缓存和存储  开
AOF重写集中管理
everysec   

最佳策略 ： 小分片 、 缓存或者存储 、监控（硬盘、内存、负载、网络）、足够内存

# 7 运维开发常见问题

> info:latest_fork_usec        使用此命令可以查看持久化花费的时间

## 7.1 改善fork
（1）优先使用物理机或者高效的虚拟机支持fork操作
（2）控制redis实际最大可用内存：maxmemory
（3）合理配置linux内存分配策略：vm.overcommit_memory=1
（4）降低fork频率：例如放宽AOF重写自动触发时机，减少不必要的全量复制。


# 8 redis 复制原理及优化
## 8.1 主从复制
单机存在的问题？  机器故障 、 容量瓶颈 、 qps瓶颈
master（主）    slave（从）

一主多从模型：

    可以提高redis高可用
    可以用作读写分离的场景，主提供写，从提供读
    多个从节点可以用作数据备份，可以用于redis内存数据恢复和防止数据丢失
    数据流向是单向的，master到slave
## 8.2 主从复制配置

>  slaveof  127.0.0.1 6379             //(假设从节点是127.0.0.1 6380 ； 主节点是127.0.0.1 6379)   slaveof执行 复制命令 
>  slaveof no one         //** slave****执行取消复制命令**

slaveof no one 取消复制 （会保存原来从主节点复制的redis数据）
再吃绑定redis主节点（从节点会删除原来的redis数据，保存新主节点的redis数据）

可以在启动从节点redis服务器时配置
slaveof ip port           //邦定主节点
slaveof-read-only yes      //只读

命令方式 与 配置方式比较
命令： 优点 无需重启   缺点  不便于管理
配置： 优点 统一配置   缺点   需要重启

## 8.3 全量复制  部分复制

> redis-cli -p 6379 info replication        //查看redis信息

全量复制开销：

    bgsave时间
    RDB文件网络传输时间
    从节点清空数据时间
    从节点加载RDB的时间
    可能的AOF重写时间
    

## 8.4 总结
读写分离可能遇到的问题

    复制数据延迟
    读到过期数据
    从节点故障
    配置不一致
    节点运行ID不匹配=》主节点重启（运行id变化）
# 9  redis sentinel




