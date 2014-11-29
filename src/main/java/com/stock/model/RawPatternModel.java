package com.stock.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by PC on 10/28/2014.
 */
@Document(collection = "raw_stock_model")
public class RawPatternModel {

    public RawPatternModel(){}
    @Id
    public long seq;
    public String stockName;
    public String stockCode;
    public LinkedHashMap<Date, DailyStockModel> dailyStocks;
    public Date buyingDate;
    public Date sellingDate;

    public Date getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(Date sellingDate) {
        this.sellingDate = sellingDate;
    }

    public Date getBuyingDate() {
        return buyingDate;
    }

    public void setBuyingDate(Date buyingDate) {
        this.buyingDate = buyingDate;
    }

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
