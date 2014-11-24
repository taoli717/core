package com.stock.parser;

import com.stock.config.AppConfig;
import com.stock.dao.StockDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by PC on 2014/11/23.
 */
public class StockParser {
    public static void main(String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(com.stock.config.AppConfig.class);
        //StockDao stockDao = (StockDao) ctx.getBean("stockDaoImp");
        //System.out.println(stockDao.loadNext());
    }
}
