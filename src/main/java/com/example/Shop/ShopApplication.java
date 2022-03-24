package com.example.Shop;

import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.tables.TableClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
		//Thread.currentThread().setContextClassLoader(ClassLoader);
		List<TableClient> clients = DAOFactory.getInstance().getCDAO().getAllClients();
		if (clients != null) {
			for (TableClient person : clients) {
				System.out.println("u");
			}
		}
	}
}
