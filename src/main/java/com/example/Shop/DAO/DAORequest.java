package com.example.Shop.DAO;

import com.example.Shop.tables.Client;
import com.example.Shop.tables.Request;
import com.example.Shop.types.request_state;

import java.sql.Timestamp;
import java.util.List;

public interface DAORequest {
    void addRequest(Request request);
    void updateRequest(Request request);
    void deleteRequest(Request request);


    Request getRequestByID (Long request_ID);
    List<Request> getAllRequests ();
    List<Request> getRequestsByClient (List<Request> from, Client client);
    List<Request> getRequestsInState (List<Request> from, request_state state);
    List<Request> getRequestsInInterval (List<Request> from, Timestamp min, Timestamp max);
    Integer getCost(Request request);

}
