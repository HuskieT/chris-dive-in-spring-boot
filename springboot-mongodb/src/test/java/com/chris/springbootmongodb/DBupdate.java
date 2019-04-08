package com.chris.springbootmongodb;

import com.mongodb.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author renlf
 * @Date 2017-08-02 13:41
 * @Description
 **/
@Slf4j
public class DBupdate {
    public static  void update(){
        System.out.println("------------------------------");
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("test");
        DBCollection dbCol = db.getCollection("table1");
        //取回一个条数据
        //BasicDBObject docFind = new BasicDBObject("name", "chris");
        //有多个id为1001时 只修改一个
        BasicDBObject docFind = new BasicDBObject("id", 1001);
        DBObject findResult = dbCol.findOne(docFind);
        log.error("********1"+findResult);
        BasicDBObject doc = new BasicDBObject();
        doc.put("id",1001);
        doc.put("name","swift");
//        doc.put("name","chris");
//        doc.put("age",25);
//        doc.put("likes",1000);
        dbCol.update(findResult,doc);
        findResult = dbCol.findOne(docFind);
        log.error("********2"+findResult);

    }
    public  static void upate2(){
        System.out.println("------------------------------");
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("test");

        // get a single collection
        DBCollection dbCol = db.getCollection("table1");
        DBCursor ret = dbCol.find();
        BasicDBObject doc = new BasicDBObject();
        BasicDBObject res = new BasicDBObject();
        res.put("age", 40);
        System.out.println("将数据集中的所有文档的age修改成40！");
        doc.put("$set", res);
        dbCol.update(new BasicDBObject(),doc,false,true);
        System.out.println("更新数据完成！");
        System.out.println("------------------------------");
    }
    /**
    *@Author renlf
    *@Date 2017/8/2 14:09
    *@Description 数据库跟新操作不同方法的区别
    *@Param
    *@Return
    *@Throws
    */
    public static void veryImportant(){
//        只更新第一条记录：
//        db.col.update( { "count" : { $gt : 1 } } , { $set : { "test2" : "OK"} } );
//        全部更新：
//        db.col.update( { "count" : { $gt : 3 } } , { $set : { "test2" : "OK"} },false,true );
//        只添加第一条：
//        db.col.update( { "count" : { $gt : 4 } } , { $set : { "test5" : "OK"} },true,false );
//        全部添加加进去:
//        db.col.update( { "count" : { $gt : 5 } } , { $set : { "test5" : "OK"} },true,true );
//        全部更新：
//        db.col.update( { "count" : { $gt : 15 } } , { $inc : { "count" : 1} },false,true );
//        只更新第一条记录：
//        db.col.update( { "count" : { $gt : 10 } } , { $inc : { "count" : 1} },false,false );
    }
    public static void main(String[] args) {
        update();
    }
}
