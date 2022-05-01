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

    @GetMapping("/allRequests")
    public String AllRequestsPage(Model model) {
        List<Request> requests = RDAO.getAllRequests();
        model.addAttribute("requests", requests);
        model.addAttribute("clientService", CDAO);
        model.addAttribute("requestService", RDAO);
        model.addAttribute("goodService", GDAO);
        return "allRequests";
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

    @PostMapping("/saveClient")
    public String saveClientPage(@RequestParam(name = "clientId") Long clientId,
                                 @RequestParam(name = "login") String login,
                                 @RequestParam(name = "password") String password,
                                 @RequestParam(name = "real_name") String real_name,
                                 @RequestParam(name = "is_admin") boolean is_admin,
                                 @RequestParam(name = "mail") String mail,
                                 @RequestParam(name = "address") String address,
                                 @RequestParam(name = "phone") String phone,
                                 Model model) {


        Client client = CDAO.getClientByID(clientId);
        boolean changeIsSuccessful = false;

        if (client != null) {
            client.setLogin(login);
            client.setPass(password);
            client.setReal_name(real_name);
            client.setIs_admin(is_admin);
            client.setMail(mail);
            client.setAddress(address);
            client.setPhone(phone);
        } else {
            client = new Client(clientId, login, password, real_name, is_admin, mail, address, phone);
        }

        model.addAttribute("error_msg", "Данные не сохранены");
        return "error";

        //return String.format("redirect:/client?clientId=%d", client.getId());

       /* model.addAttribute("error_msg", "Данные о месте не сохранены");
        return "errorPage";*/
    }

    @PostMapping("/removeClient")
    public String removeClientPage(@RequestParam(name = "clientId") Long clientId) {
        CDAO.deleteClient(CDAO.getClientByID(clientId));
        return "redirect:/clients";
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
            model.addAttribute("Good", new Good());
            model.addAttribute("GoodService", GDAO);
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

    @PostMapping("/saveGood")
    public String saveGoodPage(@RequestParam(name = "goodId") Long goodId,
                               @RequestParam(name = "manufacturer") String manufacturer,
                               @RequestParam(name = "model") String model_name,
                               @RequestParam(name = "kind") String kind,
                               @RequestParam(name = "chars", required = false) String chars,
                               @RequestParam(name = "price") Integer price,
                               @RequestParam(name = "in_stock") Integer in_stock,
                               @RequestParam(name = "country") String country,
                                 Model model) {
        Good good = GDAO.getGoodByID(goodId);
        boolean changeIsSuccessful = false;

        if (good != null) {
            good.setManufacturer(manufacturer);
            good.setModel(model_name);
            good.setKind(kind);
            good.setChars(chars);
            good.setIn_stock(price);
            good.setIn_stock(in_stock);
            good.setCountry(country);
        } else {
            good = new Good(goodId, manufacturer, model_name, kind, chars, price, in_stock, country);
        }

        model.addAttribute("error_msg", "Данные не сохранены");
        return "error";
    }

    @PostMapping("/removeGood")
    public String removeGoodPage(@RequestParam(name = "goodId") Long goodId) {
        GDAO.deleteGood(GDAO.getGoodByID(goodId));
        return "redirect:/allGoods";
    }


    @PostMapping("/fragments")
    public String forFragments(Model model) {
        List<String> kinds = List.of("Кофеварка", "Мультиварка", "Посудомоечная_машина",
                "Холодильник", "Фен", "Утюг", "Соковыжималка", "Электрический_чайник", "Мясорубка",
                "Микроволновая_печь", "Миксер", "Электрическая_плита", "Телевизор", "Пылесос",
                "Вентилятор", "Стиральная_машина", "Видеокарта", "Приставка");
        model.addAttribute("kinds", kinds);
        return "fragments";
    }

    @PostMapping("/catalogue")
    public String cataloguePage(@RequestParam(name = "goodId") Long goodId) {
        GDAO.deleteGood(GDAO.getGoodByID(goodId));
        return "redirect:/allGoods";
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
}

