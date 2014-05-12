/*
 * Copyright (c) 2013-2014, Arjuna Technologies Limited, Newcastle-upon-Tyne, England. All rights reserved.
 */

package com.arjuna.databroker.metadata.store;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccessControlUtils
{
    private static final Logger logger = Logger.getLogger(AccessControlUtils.class.getName());

    public List<UUID> listAccessable(String requesterId, String userId)
    {
        logger.fine("AccessControlUtils.listAccessableIds: \"" + requesterId + "\", \"" + userId + "\"");

        try
        {
            TypedQuery<UUID> query = _entityManager.createQuery("SELECT ac._metadata.id FROM AccessControlEntity AS ac WHERE ((ac._requesterId = :requesterId) OR (ac._requesterId IS NULL)) AND ((ac._userId = :userId) OR (ac._userId IS NULL)) AND (ac._canList = TRUE)", UUID.class);
            query.setParameter("requesterId", requesterId);
            query.setParameter("userId", userId);

            return query.getResultList();
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
            return Collections.emptyList();
        }
    }

    public boolean canRead(UUID uuid, String requesterId, String userId)
    {
        logger.fine("AccessControlUtils.canRead: \"" + uuid + "\", \"" + requesterId + "\", \"" + userId + "\"");

        try
        {
            MetadataEntity metadata = _entityManager.find(MetadataEntity.class, uuid);

            if (metadata == null)
                return false;

            TypedQuery<AccessControlEntity> query = _entityManager.createQuery("SELECT ac FROM AccessControlEntity AS ac WHERE (ac._metadata = :metadata) AND ((ac._requesterId = :requesterId) OR (ac._requesterId IS NULL)) AND ((ac._userId = :userId) OR (ac._userId IS NULL)) AND (ac._canRead = TRUE)", AccessControlEntity.class);
            query.setParameter("metadata", metadata);
            query.setParameter("requesterId", requesterId);
            query.setParameter("userId", userId);

            return ! query.getResultList().isEmpty();
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
            return false;
        }
    }

    public boolean canUpdate(UUID uuid, String requesterId, String userId)
    {
        logger.fine("AccessControlUtils.canUpdate: \"" + uuid + "\", \"" + requesterId + "\", \"" + userId + "\"");
        
        try
        {
            MetadataEntity metadata = _entityManager.find(MetadataEntity.class, uuid);

            if (metadata == null)
                return false;

            TypedQuery<AccessControlEntity> query = _entityManager.createQuery("SELECT ac FROM AccessControlEntity AS ac WHERE (ac._metadata = :metadata) AND ((ac._requesterId = :requesterId) OR (ac._requesterId IS NULL)) AND ((ac._userId = :userId) OR (ac._userId IS NULL)) AND (ac._canUpdate = TRUE)", AccessControlEntity.class);
            query.setParameter("metadata", metadata);
            query.setParameter("requesterId", requesterId);
            query.setParameter("userId", userId);

            return ! query.getResultList().isEmpty();
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
            return false;
        }
    }

    public boolean canRemove(UUID uuid, String requesterId, String userId)
    {
        logger.fine("AccessControlUtils.canRemove: \"" + uuid + "\", \"" + requesterId + "\", \"" + userId + "\"");
        
        try
        {
            MetadataEntity metadata = _entityManager.find(MetadataEntity.class, uuid);

            if (metadata == null)
                return false;

            TypedQuery<AccessControlEntity> query = _entityManager.createQuery("SELECT ac FROM AccessControlEntity AS ac WHERE (ac._metadata = :metadata) AND ((ac._requesterId = :requesterId) OR (ac._requesterId IS NULL)) AND ((ac._userId = :userId) OR (ac._userId IS NULL)) AND (ac._canRemove = TRUE)", AccessControlEntity.class);
            query.setParameter("metadata", metadata);
            query.setParameter("requesterId", requesterId);
            query.setParameter("userId", userId);

            return ! query.getResultList().isEmpty();
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
            return false;
        }
    }

    public boolean canCreateChild(UUID uuid, String requesterId, String userId)
    {
        logger.fine("AccessControlUtils.canCreateChild: \"" + uuid + "\", \"" + requesterId + "\", \"" + userId + "\"");
    
        try
        {
            MetadataEntity metadata = _entityManager.find(MetadataEntity.class, uuid);

            if (metadata == null)
            {
                TypedQuery<AccessControlEntity> query = _entityManager.createQuery("SELECT ac FROM AccessControlEntity AS ac WHERE (ac._metadata = :metadata) AND ((ac._requesterId = :requesterId) OR (ac._requesterId IS NULL)) AND ((ac._userId = :userId) OR (ac._userId IS NULL)) AND (ac._canCreateChild = TRUE)", AccessControlEntity.class);
                query.setParameter("metadata", metadata);
                query.setParameter("requesterId", requesterId);
                query.setParameter("userId", userId);

                return ! query.getResultList().isEmpty();
            }
            else
            {
                TypedQuery<AccessControlEntity> query = _entityManager.createQuery("SELECT ac FROM AccessControlEntity AS ac WHERE (ac._metadata IS NULL) AND ((ac._requesterId = :requesterId) OR (ac._requesterId IS NULL)) AND ((ac._userId = :userId) OR (ac._userId IS NULL)) AND (ac._canCreateChild = TRUE)", AccessControlEntity.class);
                query.setParameter("requesterId", requesterId);
                query.setParameter("userId", userId);

                return ! query.getResultList().isEmpty();
            }
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
            return false;
        }
    }

    public boolean canChangeAccess(UUID uuid, String requesterId, String userId)
    {
        logger.fine("AccessControlUtils.canChangeAccess: \"" + uuid + "\", \"" + requesterId + "\", \"" + userId + "\"");
    
        try
        {
            MetadataEntity metadata = _entityManager.find(MetadataEntity.class, uuid);

            if (metadata == null)
                return false;

            TypedQuery<AccessControlEntity> query = _entityManager.createQuery("SELECT ac FROM AccessControlEntity AS ac WHERE (ac._metadata = :metadata) AND ((ac._requesterId = :requesterId) OR (ac._requesterId IS NULL)) AND ((ac._userId = :userId) OR (ac._userId IS NULL)) AND (ac._canChangeAccess = TRUE)", AccessControlEntity.class);
            query.setParameter("metadata", metadata);
            query.setParameter("requesterId", requesterId);
            query.setParameter("userId", userId);

            return ! query.getResultList().isEmpty();
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
            return false;
        }
    }

    @PersistenceContext(unitName="Metadata")
    private EntityManager _entityManager;
}