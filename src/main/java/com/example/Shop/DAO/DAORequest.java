package com.example.Shop.DAO;

import com.example.Shop.tables.Client;
import com.example.Shop.tables.Request;
import com.example.Shop.types.request_state;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface DAORequest {
    void addRequest(Request request);
    void updateRequest(Request request);
    void deleteRequest(Request request);


    Request getRequestByID (Long request_ID);
    Request getRequestByID (int request_ID);
    List<Request> getAllRequests ();
    List<Request> getRequestsByClient (List<Request> from, Client client);
    List<Request> getRequestsByClient (Client client);
    List<Request> getRequestsInState (List<Request> from, String state);
    List<Request> getRequestsInState (String state);
    List<Request> getRequestsInInterval (List<Request> from, Date min, Date max);
    List<Request> getRequestsInInterval (Date min, Date max);
    Integer getCost(Request request);

}
