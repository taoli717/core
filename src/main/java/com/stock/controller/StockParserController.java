package com.stock.controller;

import com.stock.config.AppConfig;
import com.stock.dao.StockDao;
import com.stock.model.DailyStockModel;
import com.stock.model.StockModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;

/**
 * Created by PC on 2014/11/23.
 */
public class StockParserController {
    public static double targetPect = .1;
    public static void main(String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        StockDao stockDao = (StockDao) ctx.getBean("stockDaoImp");
        StockModel sm = (StockModel) stockDao.loadNext();
        long total = 0;
        if(sm!=null){
            System.out.println("processing: " + total);
            total++;
            LinkedHashMap<Date,DailyStockModel> dmMap = sm.getDailyStocks();
            // ignore newly released stock
            if(dmMap.size()>150){
                int buyingDayIndex = 60;
                Set<Date> dmKeySet = dmMap.keySet();
                LinkedList<Date> dateList = new LinkedList<Date>(dmKeySet);
                Collections.sort(dateList);
                System.out.println(String.valueOf(dateList));
                DailyStockModel dsm = dmMap.get(dateList.get(buyingDayIndex));
                long buyingPrice = takeDailyAverage(dsm);
                for(int i=0; i<20; i++){
                    DailyStockModel tempDsm = dmMap.get(dateList.get(buyingDayIndex + i));
                    long currentPrice = takeDailyAverage(tempDsm);
                    if(currentPrice> buyingPrice*(1+targetPect)){

                    }else{

                    }
                }
            }
            sm = (StockModel) stockDao.loadNext();
        }
        System.out.println("done  " + total);
    }

    public static long takeDailyAverage(DailyStockModel dsm){
        Long open = Long.parseLong(dsm.getOpen());
        Long close = Long.parseLong(dsm.getClose());
        Long high = Long.parseLong(dsm.getHigh());
        Long low = Long.parseLong(dsm.getLow());
        return (open + close + high + low)/4;
    }

}
