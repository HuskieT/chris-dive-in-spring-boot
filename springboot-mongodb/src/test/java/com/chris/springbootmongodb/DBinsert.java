package com.chris.springbootmongodb;

import com.mongodb.*;
import com.mongodb.util.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author renlf
 * @Date 2017-08-02 13:24
 * @Description  mongoDB添加数据的4中方式
 **/
public class DBinsert {
    public static void main(String[] args) {

        try {

            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("test");

            // get a single collection
            DBCollection collection = db.getCollection("table1");

            // BasicDBObject example
            /***************方法一***********************/
            System.out.println("BasicDBObject example...");
            BasicDBObject document = new BasicDBObject();
            document.put("database", "mkyongDB");
            document.put("table", "hosting");

            BasicDBObject documentDetail = new BasicDBObject();
            documentDetail.put("records", "99");
            documentDetail.put("index", "vps_index1");
            documentDetail.put("active", "true");
            document.put("detail", documentDetail);

            collection.insert(document);

            DBCursor cursorDoc = collection.find();
            while (cursorDoc.hasNext()) {
                System.out.println(cursorDoc.next());
            }

            collection.remove(new BasicDBObject());

            // BasicDBObjectBuilder example
            /***************方法二***********************/
            System.out.println("BasicDBObjectBuilder example...");
            BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()
                    .add("database", "mkyongDB")
                    .add("table", "hosting");

            BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder.start()
                    .add("records", "99")
                    .add("index", "vps_index1")
                    .add("active", "true");

            documentBuilder.add("detail", documentBuilderDetail.get());

            collection.insert(documentBuilder.get());

            DBCursor cursorDocBuilder = collection.find();
            while (cursorDocBuilder.hasNext()) {
                System.out.println(cursorDocBuilder.next());
            }

            collection.remove(new BasicDBObject());

            // Map example
            /***************方法三***********************/
            System.out.println("Map example...");
            Map<String, Object> documentMap = new HashMap<String, Object>();
            documentMap.put("database", "mkyongDB");
            documentMap.put("table", "hosting");

            Map<String, Object> documentMapDetail = new HashMap<String, Object>();
            documentMapDetail.put("records", "99");
            documentMapDetail.put("index", "vps_index1");
            documentMapDetail.put("active", "true");

            documentMap.put("detail", documentMapDetail);

            collection.insert(new BasicDBObject(documentMap));

            DBCursor cursorDocMap = collection.find();
            while (cursorDocMap.hasNext()) {
                System.out.println(cursorDocMap.next());
            }

            collection.remove(new BasicDBObject());

            // JSON parse example
            /***************方法四***********************/
            System.out.println("JSON parse example...");

            String json = "{'database' : 'mkyongDB','table' : 'hosting'," +
                    "'detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";

            DBObject dbObject = (DBObject) JSON.parse(json);

            collection.insert(dbObject);

            DBCursor cursorDocJSON = collection.find();
            while (cursorDocJSON.hasNext()) {
                System.out.println(cursorDocJSON.next());
            }

            collection.remove(new BasicDBObject());

        } catch (MongoException e) {
            e.printStackTrace();
        }

    }

}
