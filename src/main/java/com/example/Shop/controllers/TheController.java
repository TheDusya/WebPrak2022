package com.example.Shop.controllers;

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

    @Autowired
    private final DAOClient CDAO = new DAOClientImpl();

    @Autowired
    private final DAOGood GDAO = new DAOGoodImpl();

    @Autowired
    private final DAOGoodBought GBDAO = new DAOGoodBoughtImpl();

    @Autowired
    private final DAORequest  RDAO = new DAORequestImpl();

    @GetMapping("/allGoods")
    public String AllGoodsPage(Model model) {
        List<Good> goods = GDAO.getAllGoods();
        model.addAttribute("good", goods);
        model.addAttribute("clientService", CDAO);
        model.addAttribute("requestService", RDAO);
        return "allGoods";
    }

    @GetMapping("/good")
    public String GoodPage(@RequestParam(name = "goodId") Long GoodId, Model model) {
        Good good = GDAO.getGoodByID(GoodId);

        if (good == null) {
            model.addAttribute("error_msg", "Нет товара с с ID = " + GoodId);
            return "errorPage";
        }

        model.addAttribute("good", good);
        model.addAttribute("goodService", GDAO);
        model.addAttribute("clientService", CDAO);
        model.addAttribute("requestService", RDAO);
        model.addAttribute("relationService", GBDAO);
        return "good";
    }

    @GetMapping("/editGood")
    public String editGoodPage(@RequestParam(name = "GoodId", required = false) Long GoodId, Model model) {
        if (GoodId == null) {
            model.addAttribute("Good", new Good());
            model.addAttribute("GoodService", GDAO);
            //model.addAttribute("clientService", CDAO);
            return "editGood";
        }

        Good good = GDAO.getGoodByID(GoodId);

        if (good == null) {
            model.addAttribute("error_msg", "Нет товара с с ID = " + GoodId);
            return "errorPage";
        }

        model.addAttribute("good", good);
        model.addAttribute("goodDAO", GDAO);
        //model.addAttribute("clientService", CDAO);
        return "editGood";
    }

    @PostMapping("/saveGood")
    public String saveGoodPage(@RequestParam(name = "GoodId") Long GoodId,
                               @RequestParam(name = "manufacturer") String manufacturer,
                               @RequestParam(name = "model") String model_name,
                               @RequestParam(name = "kind") String kind,
                               @RequestParam(name = "chars", required = false) String chars,
                               @RequestParam(name = "in_stock") Integer price,
                               @RequestParam(name = "in_stock") Integer in_stock,
                               @RequestParam(name = "country") String country,
                                 Model model) {
        Good good = GDAO.getGoodByID(GoodId);
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
            good = new Good(GoodId, manufacturer, model_name, kind, chars, price, in_stock, country);
        }

        model.addAttribute("error_msg", "Данные не сохранены");
        return "errorPage";
    }

    @PostMapping("/removeGood")
    public String removeGoodPage(@RequestParam(name = "GoodId") Long GoodId) {
        GDAO.deleteGood(GDAO.getGoodByID(GoodId));
        return "redirect:/goods";
    }






    @GetMapping("/allClients")
    public String clientsListPage(Model model) {
        List<Client> clients = CDAO.getAllClients();
        model.addAttribute("clients", clients);
        return "allClients";
    }

    @GetMapping("/client")
    public String clientPage(@RequestParam(name = "clientId") Long clientId, Model model) {
        Client client = CDAO.getClientByID(clientId);

        if (client == null) {
            model.addAttribute("error_msg", "В базе нет населённого пункта с ID = " + clientId);
            return "errorPage";
        }

        model.addAttribute("client", client);
        model.addAttribute("clientDAO", CDAO);
        model.addAttribute("requestDAO", RDAO);
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
            return "errorPage";
        }

        model.addAttribute("client", client);
        return "editClient";
    }

    @PostMapping("/saveClient")
    public String saveClientPage(@RequestParam(name = "ClientID") Long ClientId,
                                 @RequestParam(name = "login") String login,
                                 @RequestParam(name = "password") String password,
                                 @RequestParam(name = "real_name") String real_name,
                                 @RequestParam(name = "is_admin") boolean is_admin,
                                 @RequestParam(name = "mail") String mail,
                                 @RequestParam(name = "address") String address,
                                 @RequestParam(name = "phone") String phone,
                                Model model) {


        Client client = CDAO.getClientByID(ClientId);
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
            client = new Client(ClientId, login, password, real_name, is_admin, mail, address, phone);
        }

        model.addAttribute("error_msg", "Данные не сохранены");
        return "errorPage";

        //return String.format("redirect:/client?clientId=%d", client.getId());

       /* model.addAttribute("error_msg", "Данные о месте не сохранены");
        return "errorPage";*/
    }

    @PostMapping("/removeClient")
    public String removeClientPage(@RequestParam(name = "clientId") Long ClientId) {
        CDAO.deleteClient(CDAO.getClientByID(ClientId));
        return "redirect:/clients";
    }


}
