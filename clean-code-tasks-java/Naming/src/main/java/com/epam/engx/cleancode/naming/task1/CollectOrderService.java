package com.epam.engx.cleancode.naming.task1;


import com.epam.engx.cleancode.naming.task1.thirdpartyjar.CollectionService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Message;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.NotificationManager;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Order;

public class CollectOrderService implements OrderService {

    private static final int INFO_NOTIFICATION = 4;
    private static final int CRITICAL_NOTIFICATION = 1;

    private CollectionService collectionService;
    private NotificationManager notificationManager;

    public void submitOrder(Order order) {
        if (collectionService.isEligibleForCollection(order))
            notificationManager.notifyCustomer(Message.READY_FOR_COLLECT, INFO_NOTIFICATION);
        else
            notificationManager.notifyCustomer(Message.IMPOSSIBLE_TO_COLLECT, CRITICAL_NOTIFICATION);
    }

    public void setCollectionService(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    public void setNotificationManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }
}
