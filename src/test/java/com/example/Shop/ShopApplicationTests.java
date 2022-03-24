package com.example.Shop;

import com.example.Shop.DAO.Factory.DAOFactory;
import com.example.Shop.tables.TableClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShopApplicationTests {
	/*
	public static void main(String[] args) {
		SpringApplication.run(ShopApplicationTests.class, args);

		List<TableClient> clients = DAOFactory.getInstance().getCDAO().getAllClients();
		if (clients != null) {
			for (var person : clients) {
				System.out.println(person.toString());
			}
		}
	}
	*/
	/*@Test
	void contextLoads() {
	}
	*/
}
