package com.SenierProject.NoticeBoard.firebase;

public interface MessageService {

    void sendSaleCompletedMessage(String token);

    void sendPurchaseCompletedMessage(String token);
}
