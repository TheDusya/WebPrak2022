package com.example.Shop.DAO;

import com.example.Shop.tables.TableRequest;

import java.util.List;

public interface DAORequest {
    void addRequest(TableRequest request);
    void updateRequest(TableRequest request);
    void deleteRequest(TableRequest request);

    TableRequest getRequestByID (Long request_ID);
    List<TableRequest> getAllRequests ();
    List<TableRequest> getRequestsByClientID (Long client_ID);


}
