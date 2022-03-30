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
			client.setAddress("{дом=25, город=Москва, улица=3-я Строителей, страна=Россия}");
			DAOClient dao = DAOFactory.getInstance().getCDAO();
			if (dao.getClientByLogin(client.getLogin())==null) dao.addClient(client);
			else System.out.println("aaa");
		}
		catch (Exception e){
			alright = false;
		}
	}
}
