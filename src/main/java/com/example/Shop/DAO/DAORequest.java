package com.example.Shop.DAO;

import com.example.Shop.tables.Request;

import java.util.List;

public interface DAORequest {
    void addRequest(Request request);
    void updateRequest(Request request);
    void deleteRequest(Request request);


    Request getRequestByID (Long request_ID);
    List<Request> getAllRequests ();
    List<Request> getRequestsByClientID (Long client_ID);

    Integer getCost(Request request);

}
