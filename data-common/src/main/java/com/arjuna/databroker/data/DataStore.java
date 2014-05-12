/*
 * Copyright (c) 2013-2014, Arjuna Technologies Limited, Newcastle-upon-Tyne, England. All rights reserved.
 */

package com.arjuna.databroker.data;

/**
 * DataStore is an interface to data store.
 */
public interface DataStore extends DataFlowNode
{
    /**
     * Returns the data provider, of the specified data class, of the data store.
     * 
     * @param dataClass the required data class
     * @return the data provider, of the specified data class, of the data store
     */
    public <T> DataProvider<T> getDataProvider(Class<T> dataClass);

    /**
     * Returns the data consumer, of the specified data class, of the data store.
     * 
     * @param dataClass the required data class
     * @return the data consumer, of the specified data class, of the data store
     */
    public <T> DataConsumer<T> getDataConsumer(Class<T> dataClass);
}
