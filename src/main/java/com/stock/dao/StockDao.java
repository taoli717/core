package com.stock.dao;

import com.stock.model.StockModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

/**
 * Created by PC on 11/7/2014.
 */
public interface StockDao {
    public boolean save(StockModel sm, Boolean autoIncrement);
    public StockModel load(String stockName);
    public Object loadNext();
    public long getNextSequenceId(String key) throws Exception;
}
