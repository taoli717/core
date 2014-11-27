package com.stock.controller;

import com.stock.config.AppConfig;
import com.stock.constant.TestStockName;
import com.stock.dao.StockDao;
import com.stock.dao.StockDaoImp;
import com.stock.model.StockModel;
import com.stock.retriever.MarkItOnDemondPriceRetriever;
import com.stock.retriever.PriceRetriever;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by PC on 11/15/2014.
 */
public class StockController {

    private static final Logger logger = Logger.getLogger(StockController.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        StockDao stockDao = (StockDao) ctx.getBean("stockDaoImp");
      /*  //TODO delete testing
        for(int i=0; i<10; i++){
            Object model = stockDao.loadNext();
            logger.info(model);
        }
        */
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        //StockDao stockDao = (StockDao) ctx.getBean("stockDaoImp");
        // String stockName = "NFJ";
        String days = "30000";
        PriceRetriever http = new MarkItOnDemondPriceRetriever();
        //String[] stocksName = ArrayUtils.addAll(NASDAQStockName.STOCK_NAMES, NYSCStockNames.STOCK_NAMES);
        String[] stocksName = TestStockName.ALL_STOCK_NAME;
       // String[] stocksName = {"TSLA"};
        //I'm Ken
        ArrayList<String> workingStock = new ArrayList<String>();
        int i = 0;
        long startTime = System.currentTimeMillis();
        for(String stockName : stocksName){
            Boolean autoIncre = true;
            try{
                StockModel sm = http.sendGet(stockName, days);
                if(i==0){
                    autoIncre = false;
                    sm.setSeq(0);
                }
                stockDao.save(sm, autoIncre);
                workingStock.add(sm.getStockName());
                i++;
                logger.info("Stock #: " + i);
            }catch(Exception e){
                if(e instanceof SocketException){
                    try{
                        Thread.sleep(2000);
                        StockModel sm = http.sendGet(stockName, days);
                        stockDao.save(sm, autoIncre);
                        workingStock.add(sm.getStockName());
                        i++;
                        logger.info("Stock #: " + i);
                    }catch(Exception ex){
                        logger.error("StockName: " + stockName);
                        logger.error(ex);
                    }
                }
                logger.error("StockName: " + stockName);
                logger.error(e);
            }
            Thread.sleep(1000);
        }
        String listString = "";

        long endTime = System.currentTimeMillis();

        logger.info("Total time spent: " + (endTime - startTime) / 1000);//total time in seconds
        for (String s : workingStock)
        {
            listString = listString + "\"" + s + "\", ";
        }

        logger.info(listString);
    }
}
