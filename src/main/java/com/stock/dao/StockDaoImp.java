package com.stock.dao;

import com.allanbank.mongodb.bson.element.UuidElement;
import com.mongodb.DBCursor;
import com.stock.model.StockModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by PC on 11/7/2014.
 */
@Service
public class StockDaoImp implements StockDao{

    private static DBCursor cursor;

    @Autowired
    private MongoOperations mongoOperation;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean save(StockModel sm) {
        Query query = new Query();
        query.addCriteria(Criteria.where("stockName").is(sm.getStockName()));

        StockModel dbSm = mongoOperation.findOne(query, StockModel.class);

        if(dbSm == null){
            mongoOperation.save(sm);
        }else{
            Update update = new Update();
            update.set("dailyStocks", sm.getDailyStocks());
            mongoOperation.updateFirst(query, update, StockModel.class);
        }
        return true;
    }

    @Override
    public StockModel load(String stockName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("stockName").is(stockName));
        StockModel dbSm = mongoOperation.findOne(query, StockModel.class);
        return dbSm;
    }
// TODO need to find a ORM mapping
    @Override
    public Object loadNext() {
        if(getCurrentStockModelDBCursor()!=null){
            mongoOperation.getConverter();
            return cursor.next();
        }else{
            return null;
        }
    }

    private DBCursor getCurrentStockModelDBCursor(){
        if(cursor == null){
            cursor = mongoTemplate.getCollection("stock_model").find();

        }else{
            while(cursor.hasNext()){

                return cursor;
            }
        }
        return cursor;
    }
}
