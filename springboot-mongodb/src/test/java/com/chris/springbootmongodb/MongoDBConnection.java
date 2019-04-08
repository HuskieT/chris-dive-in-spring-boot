package com.chris.springbootmongodb;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Set;

/**
 * @Author renlf
 * @Date 2017-08-01 9:58
 * @Description MongoDB 连接数据库
 **/
@Slf4j
public class MongoDBConnection {
    //下面的例子展示了通过5种方式来连接本地的数据库 mydb，如果数据库不存在，MongoDB会自动创建
    public void connect(){
        // (1)简单直接的连接数据库，默认为本机地址localhost，端口号27017
        MongoClient mongoClient = new MongoClient();

        // 或者像这样指定连接地址
        MongoClient mongoClient2 = new MongoClient( "localhost" );

    // 或者像这样指定连接地址和端口号
        MongoClient mongoClient3 = new MongoClient( "localhost" , 27017 );

    // 或者像这样连接到一个副本集，需要提供一个列表
        MongoClient mongoClient4 = new MongoClient(
            Arrays.asList(new ServerAddress("localhost", 27017),
                    new ServerAddress("localhost", 27018),
                    new ServerAddress("localhost", 27019)));

    // 或者使用连接字符串
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017,localhost:27018,localhost:27019");
        MongoClient mongoClient5 = new MongoClient(connectionString);
    // 获取到数据库对象mydb，如果不存在则自动创建
        MongoDatabase database = mongoClient.getDatabase("mydb");
    }
    /**
     *@Author renlf
     *@Date 2017/8/2 11:34
     *@Description  连接MongoDB 数据库
     *@Param String str 数据库名
     *@Return
     *@Throws
     */
    public static  DB connectDB(String str){
        // 连接到 mongodb 服务 实例化Mongo对象，连接27017端口
        Mongo  mongo = new MongoClient( "localhost" , 27017 );
        // 连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立
        DB  db = mongo.getDB(str);
        return  db;
    }
    /**
    *@Author renlf
    *@Date 2017/8/2 11:38
    *@Description  MongoDB 对表的操作
    *@Param String str 表名
    *@Return
    *@Throws
    */
    public static void operateTable(String str,DB  db){
        // 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立
        DBCollection dbCollection =  db.getCollection("table1");

//        //删除表名为cc的表(drop不仅删除集合的文档，也会删除集合本身，同时也会删除在集合上创建的索引)
//        db.getCollection("cc").drop();
//
//        // 获取当前数据所有表的名称
//        Set<String> colls = db.getCollectionNames();

         //当前数据库说有表的集合
          DBCursor dbCursor = dbCollection.find();
          for (DBObject dbObject : dbCursor) {
              //remove用于将集合中的文档删除，但不删除集合本身，也不删除集合的索引
              dbCollection.remove(dbObject);
          }

        // 使用BasicDBObject对象创建一个mongodb的document,并给予赋值。
        BasicDBObject document = new BasicDBObject();
        document.put("id", 1001);
        document.put("msg", "hello world mongoDB in Java");
        // 将新建立的document保存到collection中去
        // dbCollection.insert(document);

        // 创建要查询的document
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("id", 1001);
        // 使用collection的find方法查找document
        DBCursor cursor = dbCollection.find(searchQuery);
        // 循环输出结果
        while (cursor.hasNext()) {
            log.debug( String.valueOf( cursor.next() ) );
        }


    }
    /**
    *@Author renlf
    *@Date 2017/8/2 11:40
    *@Description 关闭数据库连接
    *@Param
    *@Return
    *@Throws
    */
    public static void close(DB db,Mongo  mongo){
        //关闭连接
        mongo.close();
    }

    public static void main(String[] args) {
        try{
            DB db =  connectDB("test");
            // 获取当前数据所有表的名称
            Set<String> colls = db.getCollectionNames();
            for (String s : colls) {
                log.debug(s);
            }


        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
