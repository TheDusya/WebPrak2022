package com.example.Shop;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.DAO.DAORequest;
import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.tables.Client;
import com.example.Shop.tables.Request;
import com.example.Shop.types.request_state;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RequestTests {

    @Test
    public void testGetRequestsByClient() {
        DAORequest dao = DAOFactory.getInstance().getRDAO();
        DAOClient dao2 = DAOFactory.getInstance().getCDAO();
        Client client = dao2.getClientByID(3);
        List<Request> list = dao.getRequestsByClient(client);
        List<Request> realList = Arrays.asList(dao.getRequestByID(Long.valueOf(1)));
        assertEquals(list, realList);
    }

    @Test
    public void testGetRequestsInStateInInterval() {
        DAORequest dao = DAOFactory.getInstance().getRDAO();
        Date i1 = Date.valueOf("2022-03-01");
        Date i2 = Date.valueOf("2022-04-01");
        boolean k = i2.after(i1);

        List<Request> intList = dao.getRequestsInInterval(i1, i2);
        List<Request> list = dao.getRequestsInState(intList, "assembled");
        List<Request> realList = Arrays.asList(dao.getRequestByID(Long.valueOf(6)), dao.getRequestByID(Long.valueOf(7)));
        assertEquals(list, realList);
    }

    @Test
    public void testAddRequest(){
        boolean alright = true;
        DAOClient dao = DAOFactory.getInstance().getCDAO();
        Request request = new Request(dao.getClientByID(10), "cancelled");
        DAORequest dao2 = DAOFactory.getInstance().getRDAO();
        List<Request> list1 = dao2.getRequestsByClient(dao2.getRequestsInState("cancelled"),
                (dao.getClientByID(10)));
        dao2.addRequest(request);
        List<Request> list2 = dao2.getRequestsByClient(dao2.getRequestsInState("cancelled"),
                (dao.getClientByID(10)));
        assertEquals(list1.size()+1, list2.size());

    }

    @Test
    public void testDeleteRequest(){
        int someId = 14;
        DAORequest dao = DAOFactory.getInstance().getRDAO();
        Request request = dao.getRequestByID(someId);
        dao.deleteRequest(request);
        assertEquals(dao.getRequestByID(someId), null);
    }

    @Test
    public void testUpdateRequest(){
        int someId = 7;
        DAORequest dao = DAOFactory.getInstance().getRDAO();
        Request request = dao.getRequestByID(someId);
        request.setCur_state("assembled");
        dao.updateRequest(request);
        assertEquals(dao.getRequestByID(someId).getCur_state().equals("assembled"), true);
    }

}
