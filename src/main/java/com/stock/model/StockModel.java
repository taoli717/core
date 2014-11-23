package com.stock.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by PC on 10/28/2014.
 */
@Document(collection = "stock_model")
public class StockModel {

    public StockModel(){}
    public long seq;
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

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }
}
