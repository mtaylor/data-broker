/*
 * Copyright (c) 2013-2014, Arjuna Technologies Limited, Newcastle-upon-Tyne, England. All rights reserved.
 */

package com.arjuna.databroker.data;

/**
 * DataService is an interface to data service.
 */
public interface DataService extends DataFlowNode
{
    /**
     * Returns the data consumer, of the specified data class, of the data service.
     * 
     * @param dataClass the required data class
     * @return the data consumer, of the specified data class, of the data service
     */
    public <T> DataConsumer<T> getDataConsumer(Class<T> dataClass);

    /**
     * Returns the data provider, of the specified data class, of the data service.
     * 
     * @param dataClass the required data class
     * @return the data provider, of the specified data class, of the data service
     */
    public <T> DataProvider<T> getDataProvider(Class<T> dataClass);
}
