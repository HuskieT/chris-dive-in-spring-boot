
# springboot2.1 mongodb 使用示例
    mongodb
    swagger2

## 1 连接配置
单机模式：mongodb://name:pwd@ip:port/database
集群模式：mongodb://name:pwd@ip1:port1,ip2:port2/database

## 2  swagger2使用
   
swagger注释API: https://blog.csdn.net/chinassj/article/details/81875038
生产环境下springboot中配置禁用swagger: https://blog.csdn.net/weixin_37591536/article/details/82115325

    @ApiModelProperty()用于方法，字段； 表示对model属性的说明或者数据操作更改 
    value–字段说明 
    name–重写属性名字 
    dataType–重写属性类型 
    required–是否必填 
    example–举例说明 
    hidden–隐藏
    
示例：
    @ApiModel(value="user对象",description="用户对象user")
    public class User implements Serializable{
        private static final long serialVersionUID = 1L;
         @ApiModelProperty(value="用户名",name="username",example="xingguo")
         private String username;
         @ApiModelProperty(value="状态",name="state",required=true)
          private Integer state;
          private String password;
          private String nickName;
          private Integer isDeleted;
    
          @ApiModelProperty(value="id数组",hidden=true)
          private String[] ids;
          private List<String> idList;
         //省略get/set
    }  
    
## 3 save和insert

针对save和insert也需要注意下：

在无_id情况下，两者都能进行新增操作。
存在_id，同时记录库里不存在，两者都是进行插入操作。
存在_id，同时库里也存在记录，save相当于进行更新操作。而insert直接就异常了。    
  
## springboot 与mongodb整合
    
教程地址：https://www.jianshu.com/p/3dade40c5b8f   
springboot2 文档：https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/reference/html/  
springboot2 mongodb 文档：https://docs.spring.io/spring-data/mongodb/docs/2.1.4.RELEASE/reference/html/
mongodb官方 文档：https://docs.mongodb.com/manual/?jmp=footer&_ga=2.154057840.97341573.1554462095-542225666.1547827413   