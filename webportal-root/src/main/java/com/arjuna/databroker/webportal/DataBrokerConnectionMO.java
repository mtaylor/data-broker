/*
 * Copyright (c) 2013-2014, Arjuna Technologies Limited, Newcastle-upon-Tyne, England. All rights reserved.
 */

package com.arjuna.databroker.webportal;

import java.io.Serializable;
import java.util.UUID;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.arjuna.databroker.webportal.store.DataBrokerEntity;
import com.arjuna.databroker.webportal.store.DataBrokerUtils;

@SessionScoped
@ManagedBean(name="databrokerconnection")
public class DataBrokerConnectionMO implements Serializable
{
    private static final long serialVersionUID = 5608639674146189356L;

    public DataBrokerConnectionMO()
    {
        _id             = null;
        _name           = "";
        _summary        = "";
        _serviceRootURL = "";
        _requesterId    = "";
    }

    public String getId()
    {
        return _id;
    }

    public void setId(String id)
    {
        _id = id;
    }

    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public String getSummary()
    {
        return _summary;
    }

    public void setSummary(String summary)
    {
        _summary = summary;
    }

    public String getServiceRootURL()
    {
        return _serviceRootURL;
    }

    public void setServiceRootURL(String serviceRootURL)
    {
        _serviceRootURL = serviceRootURL;
    }

    public String getRequesterId()
    {
        return _requesterId;
    }

    public void setRequesterId(String requesterId)
    {
        _requesterId = requesterId;
    }

    public String doAdd()
    {
        clear();

        return "databrokerconnection_add?faces-redirect=true";
    }

    public String doAddSubmit()
    {
        _dataBrokerUtils.createDataBroker(_name, _summary, _serviceRootURL, _requesterId);

        return "databrokerconnection?faces-redirect=true";
    }

    public String doChange(String id)
    {
        load(id);

        return "databrokerconnection_change?faces-redirect=true";
    }

    public String doChangeSubmit()
    {
        _dataBrokerUtils.replaceDataBroker(UUID.fromString(_id), _name, _summary, _serviceRootURL, _requesterId);

        return "databrokerconnection?faces-redirect=true";
    }

    private void clear()
    {
        _id             = "";
        _name           = "";
        _summary        = "";
        _serviceRootURL = "";
        _requesterId    = "";
    }

    private void load(String id)
    {
        try
        {
            DataBrokerEntity dataBroker = _dataBrokerUtils.retrieveDataBroker(UUID.fromString(id));

            clear();
            if (dataBroker != null)
            {
                _id             = dataBroker.getId().toString();
                _name           = dataBroker.getName();
                _summary        = dataBroker.getSummary();
                _serviceRootURL = dataBroker.getServiceRootURL();
                _requesterId    = dataBroker.getRequesterId();
            }
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
            clear();
        }
    }

    private String _id;
    private String _name;
    private String _summary;
    private String _serviceRootURL;
    private String _requesterId;

    @EJB
    private DataBrokerUtils _dataBrokerUtils;
}
