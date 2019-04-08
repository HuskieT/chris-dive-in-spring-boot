package com.chris.springbootmongodb;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author renlf
 * @Date 2017-08-02 14:30
 * @Description DB 数据库导入导出
 **/
@Slf4j
public class DBImportAndExport {
    /*
    * MongoDB官方提供了两套数据导入导出工具，一般来说，进行整库导出导入时使用mongodump和mongorestore，这一对组合操作的数据是 BSON格式，
    * 进行大量dump和restore时效率较高。进行单个集合导出导入时使用mongoexport和mongoimport，这一对组合操作的数据是JSON格式，可读性较高
    */
    /*
    * mongodump是一个用于导出二进制数据库内容的实用工具，它导出的bson文档中只会包含着集合文档等信息，不包括索引信息（索引信息会单独导出），所以还原后，
    * 索引必须重建（这个不    *  用担心，使用mongorestore会自动重建mongodump生成的索引信息）。3.4版本中添加了对只读视图的支持
    */

    /**
    *@Author renlf
    *@Date 2017/8/2 14:35
    *@Description DB dump导出
    *@Param
    *@Return
    *@Throws
    */
    public static void DBdump(){
        //mongodump -d test -c table1 -o F:\bk  将test数据库中的集合table1 导出到F:\bk文件下
        //导出的table1.bson 就是table1集合（表）的数据信息；table1.metadata.json就是索引信息
    }
    public static void DBmongorestore(){
        // mongorestore --drop -d test -c table1 F:\\bk\\mytest\\user.bson
    }
    /**
    *@Author renlf
    *@Date 2017/8/2 14:41
    *@Description 单文件导出/导入
    *@Param
    *@Return
    *@Throws
    */
    public static void DBmongodump(){
        // mongoexport -d test -c table1 -o F:\bk\table1.json 导出为table1.json


        //  mongoimport --drop -d test -c table1 --file F:\bk\table1.json table1.json导入

    }


    public static void main(String[] args) {

    }

}
