package com.example.Shop;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.DAO.DAOGood;
import com.example.Shop.DAO.DAOGoodBought;
import com.example.Shop.DAO.DAORequest;
import com.example.Shop.DAO.Implementations.DAOClientImpl;
import com.example.Shop.DAO.Implementations.DAOGoodBoughtImpl;
import com.example.Shop.DAO.Implementations.DAOGoodImpl;
import com.example.Shop.DAO.Implementations.DAORequestImpl;
import com.example.Shop.tables.Client;
import com.example.Shop.tables.Good;
import com.example.Shop.tables.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.List;

@Controller
public class TheController {
    @RequestMapping(value = { "/", "/main"})
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/allGoods" )
    public String allGoods() {
        return "allGoods";
    }

    @RequestMapping(value = "/allClients" )
    public String allClients() {
        return "allClients";
    }

    @RequestMapping(value = "/allRequests" )
    public String allRequests() {
        return "allRequests";
    }

    @Autowired
    private final DAOClient CDAO = new DAOClientImpl();

    @Autowired
    private final DAOGood GDAO = new DAOGoodImpl();

    @Autowired
    private final DAOGoodBought GBDAO = new DAOGoodBoughtImpl();

    @Autowired
    private final DAORequest  RDAO = new DAORequestImpl();

    @GetMapping("/allClients")
    public String AllClientsPage(Model model) {
        List<Client> clients = CDAO.getAllClients();
        model.addAttribute("clients", clients);
        model.addAttribute("clientService", CDAO);
        return "allClients";
    }

    @GetMapping("/client")
    public String clientPage(@RequestParam(name = "clientId") Long clientId, Model model) {
        Client client = CDAO.getClientByID(clientId);

        if (client == null) {
            return "error";
        }

        model.addAttribute("client", client);
        model.addAttribute("clientService", CDAO);
        model.addAttribute("requestService", RDAO);
        return "client";
    }

    @GetMapping("/editClient")
    public String editClientPage(@RequestParam(name = "clientId", required = false) Long clientId, Model model) {
        if (clientId == null) {
            model.addAttribute("client", new Client());
            return "editClient";
        }

        Client client = CDAO.getClientByID(clientId);

        if (client == null) {
            model.addAttribute("error_msg", "В базе нет места с ID = " + clientId);
            return "error";
        }

        model.addAttribute("client", client);
        return "editClient";
    }

    @GetMapping("/saveClient")
    public String saveClientPage(@RequestParam(name = "clientId") Long clientId,
                                 @RequestParam(name = "login") String login,
                                 @RequestParam(name = "password") String password,
                                 @RequestParam(name = "real_name") String real_name,
                                 @RequestParam(name = "is_admin") String is_admin,
                                 @RequestParam(name = "mail") String mail,
                                 @RequestParam(name = "address") String address,
                                 @RequestParam(name = "phone") String phone,
                                 Model model) {


        Client client = CDAO.getClientByID(clientId);
        boolean adm;
        if (is_admin=="true")adm=true;
        else adm=false;
        if (client != null) {
            client.setLogin(login);
            client.setPass(password);
            client.setReal_name(real_name);
            client.setIs_admin(adm);
            client.setMail(mail);
            client.setAddress(address);
            client.setPhone(phone);
            CDAO.updateClient(client);
        } else {
            client = new Client(clientId, login, password, real_name, adm, mail, address, phone);
            CDAO.addClient(client);
        }

        return "redirect:/allClients";

    }

    @PostMapping("/removeClient")
    public String removeClientPage(@RequestParam(name = "clientId") Long clientId) {
        CDAO.deleteClient(CDAO.getClientByID(clientId));
        return "redirect:/clients";
    }

    @GetMapping("/allGoods")
    public String AllGoodsPage(@RequestParam(name = "kind", required = false)String kind, Model model) {
        List<Good> goods;
        if (kind==null) goods = GDAO.getAllGoods();
        else goods = GDAO.getGoodsByKind(kind);
        model.addAttribute("kind", kind);
        model.addAttribute("goods", goods);
        model.addAttribute("clientService", CDAO);
        model.addAttribute("requestService", RDAO);
        return "allGoods";
    }

    @GetMapping("/good")
    public String GoodPage(@RequestParam(name = "goodId") Long goodId, Model model) {
        Good good = GDAO.getGoodByID(goodId);
        String chars = good.getFormChars();
        if (good == null) {
            model.addAttribute("error_msg", "Нет товара с с ID = " + goodId);
            return "error";
        }

        model.addAttribute("good", good);
        model.addAttribute("chars", chars);
        model.addAttribute("goodService", GDAO);
        model.addAttribute("clientService", CDAO);
        model.addAttribute("requestService", RDAO);
        model.addAttribute("relationService", GBDAO);
        return "good";
    }

    @GetMapping("/editGood")
    public String editGoodPage(@RequestParam(name = "goodId", required = false) Long goodId, Model model) {
        if (goodId == null) {
            model.addAttribute("good", new Good());
            model.addAttribute("goodService", GDAO);
            //model.addAttribute("clientService", CDAO);
            return "editGood";
        }
        Good good = GDAO.getGoodByID(goodId);
        if (good == null) {
            model.addAttribute("error_msg", "Нет товара с с ID = " + goodId);
            return "error";
        }
        model.addAttribute("good", good);
        model.addAttribute("goodService", GDAO);
        //model.addAttribute("clientService", CDAO);
        return "editGood";
    }


    @GetMapping("/saveGood")
    public String saveGoodPage(@RequestParam(name = "goodId", required = false) Long goodId,
                               @RequestParam(name = "manufacturer") String manufacturer,
                               @RequestParam(name = "model") String model_name,
                               @RequestParam(name = "kind") String kind,
                               @RequestParam(name = "chars", required = false) String chars,
                               @RequestParam(name = "price") Integer price,
                               @RequestParam(name = "in_stock") Integer in_stock,
                               @RequestParam(name = "country", required = false) String country,
                               Model model) {
        Good good = GDAO.getGoodByID(goodId);

        if (good != null) {
            good.setManufacturer(manufacturer);
            good.setModel(model_name);
            good.setKind(kind);
            good.setChars(chars);
            good.setIn_stock(price);
            good.setIn_stock(in_stock);
            good.setCountry(country);
            GDAO.updateGood(good);
        }
        else {
            good = new Good(goodId, manufacturer, model_name, kind, chars, price, in_stock, country);
            GDAO.addGood(good);
        }

        return "redirect:/allGoods";
    }

    @PostMapping("/removeGood")
    public String removeGoodPage(@RequestParam(name = "goodId") Long goodId) {
        GDAO.deleteGood(GDAO.getGoodByID(goodId));
        return "redirect:/allGoods";
    }

    @GetMapping("/allRequests")
    public String AllRequestsPage(Model model) {
        List<Request> requests = RDAO.getAllRequests();
        model.addAttribute("requests", requests);
        model.addAttribute("clientService", CDAO);
        model.addAttribute("requestService", RDAO);
        model.addAttribute("goodService", GDAO);
        return "allRequests";
    }

    @GetMapping("/request")
    public String requestPage(@RequestParam(name = "requestId") Long requestId, Model model) {
        Request request = RDAO.getRequestByID(requestId);

        if (request == null) {
            return "error";
        }

        model.addAttribute("request", request);
        model.addAttribute("clientService", CDAO);
        model.addAttribute("goodService", GDAO);
        model.addAttribute("requestService", RDAO);
        model.addAttribute("goodBoughtService", GBDAO);
        return "request";
    }

    @GetMapping("/editRequest")
    public String editRequestPage(@RequestParam(name = "requestId", required = false) Long requestId, Model model) {
        //return "allGoods";
        Request request = new Request();
        if (requestId != null) request = RDAO.getRequestByID(requestId);
        if (request == null) {
            model.addAttribute("error_msg", "Нет товара с с ID = " + requestId);
            return "error";
        }
        model.addAttribute("request", request);
        model.addAttribute("goods", GBDAO.getGoodsBoughtByRequest(request));
        model.addAttribute("requestService", RDAO);
        model.addAttribute("goodService", GDAO);
        model.addAttribute("goodBoughtService", GBDAO);
        return "editRequest";
    }

    @GetMapping("/saveRequest")
    public String saveRequestPage(@RequestParam(name = "requestId", required = false) Long requestId,
                                  @RequestParam(name = "creator") String creator,
                                  @RequestParam(name = "state") String state,
                                  @RequestParam(name = "reg_time", required = false) String reg_time,
                                  @RequestParam(name = "del_time", required = false) String del_time,
                                  @RequestParam(name = "cost", required = false) Integer cost,
                                  @RequestParam(name = "address", required = false) String adr,
                               Model model) {
        Request request = RDAO.getRequestByID(requestId);
        Client client = CDAO.getClientByLogin(creator);
        if (client == null) {
            model.addAttribute("error_msg", "В базе нет клиента с логином " + creator);
            return "error";
        }
        Date reg=null, del=null;
        try {
            if (reg_time!=null) reg = Date.valueOf(reg_time);
            if (del_time!=null) del =Date.valueOf(del_time);
        }
        catch (Exception e){
            model.addAttribute("error_msg", "Некорректно задана дата.");
            return "allGoods";
        }

        if (request != null) {
            request.setClient(client);
            request.setCur_state(state);
            request.setRegistration_time(reg);
            request.setDelivery_time(del);
            request.setDelivery_cost(cost);
            request.setDelivery_address(adr);
            RDAO.updateRequest(request);
        }
        else {
            request = new Request(requestId, client, state, reg, del, cost, adr);
            RDAO.addRequest(request);
        }

        return "redirect:/allRequests";
    }

    @PostMapping("/removeRequest")
    public String removeRequestPage(@RequestParam(name = "requestId") Long requestId) {
        RDAO.deleteRequest(RDAO.getRequestByID(requestId));
        return "redirect:/allRequests";
    }

    @GetMapping("/searchResults")
    public String ResultsPage(@RequestParam(name = "request", required = true)String request, Model model) {
        List<Good> goods =GDAO.searchGoods(request);
        model.addAttribute("request", request);
        model.addAttribute("goods", goods);
        return "searchResults";
    }

    @PostMapping("/fragments")
    public String forFragments(Model model) {
        List<String> kinds = List.of("Кофеварка", "Мультиварка", "Посудомоечная машина",
                "Холодильник", "Фен", "Утюг", "Соковыжималка", "Электрический чайник", "Мясорубка",
                "Микроволновая печь", "Миксер", "Электрическая плита", "Телевизор", "Пылесос",
                "Вентилятор", "Стиральная машина", "Видеокарта", "Приставка");
        model.addAttribute("kinds", kinds);
        return "fragments";
    }

    @PostMapping("/catalogue")
    public String cataloguePage(@RequestParam(name = "goodId") Long goodId) {
        GDAO.deleteGood(GDAO.getGoodByID(goodId));
        return "redirect:/allGoods";
    }

}

