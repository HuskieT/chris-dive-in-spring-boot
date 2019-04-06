package com.chris.springbootmongodb.entity;

import java.io.Serializable;
import java.util.Date;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 通知消息对象
 * @author oKong
 *
 */
@Document(collection="notify_msg")//集合名
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//加入@ApiModel
@ApiModel
public class NotifyMsg implements Serializable {

    /**
     * @Document(collection="notify_msg") 表示：操作的集合为:notify_msg。 这个可手动使用工具创建下
     * @CreatedDate注解，也和之前的jpa用法一直，创建时会自动赋值，需要在启动类中添加@EnableMongoAuditing注解使其生效！
     * @Field注解，可指定存储的键值名称，默认就是类字段名。如设置@Field("notify_Msg")后
     */
    private static final long serialVersionUID = -8985545025018238754L;

    @Id
    @ApiModelProperty(value="ID",dataType="String",name="ID",example="1020332806740959233")
    String id;

    /**
     * 消息类型
     */
    @Indexed
    @ApiModelProperty(value="消息类型",dataType="String",name="notifyType",example="001")
    String notifyType;

    /**
     * 消息单号
     */
    @Indexed
    @ApiModelProperty(value="消息单号",dataType="String",name="notifyNo",example="13212311")
    String notifyNo;

    /**
     * 消息通知日期
     */
    String notifyDate;

    /**
     * 消息体
     */
    @Field("notifyMsg")//可指定存储时的字段名
    String notifyMsg;

    /**
     * 创建时间
     */
    @CreatedDate
    Date gmtCreate;
}