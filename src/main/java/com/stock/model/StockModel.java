package com.stock.model;

import com.allanbank.mongodb.bson.element.UuidElement;
import com.stock.constant.DailyStockModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * Created by PC on 10/28/2014.
 */
@Document(collection = "stock_model")
public class StockModel {

    public StockModel(){}
    @Id
    public String stockName;
    public String stockCode;
    public LinkedHashMap<Date, DailyStockModel> dailyStocks;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public LinkedHashMap<Date, DailyStockModel> getDailyStocks() {
        return dailyStocks;
    }

    public void setDailyStocks(LinkedHashMap<Date, DailyStockModel> dailyStocks) {
        this.dailyStocks = dailyStocks;
    }

}
