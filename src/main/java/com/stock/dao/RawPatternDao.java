package com.stock.dao;

import com.stock.model.RawPatternModel;

/**
 * Created by PC on 11/29/2014.
 */
public interface RawPatternDao {
    public boolean save(RawPatternModel rpm);

    long getNextSequenceId() throws Exception;
}
