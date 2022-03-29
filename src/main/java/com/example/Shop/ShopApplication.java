package com.example.Shop;

import com.example.Shop.DAO.DAOClient;
import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.tables.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		boolean alright = true;
		try {
			Client client = new Client("Human", "123456", false);
			client.setReal_name("Фыва Олдж");
			DAOClient dao = DAOFactory.getInstance().getCDAO();
			if (dao.getClientByLogin(client.getLogin())==null) dao.addClient(client);
			else System.out.println("aaa");
		}
		catch (Exception e){
			alright = false;
		}
	}
}
