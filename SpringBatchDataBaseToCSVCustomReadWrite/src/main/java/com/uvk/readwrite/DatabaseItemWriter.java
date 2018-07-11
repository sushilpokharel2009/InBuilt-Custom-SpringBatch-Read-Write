package com.uvk.readwrite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.uvk.model.Vendor;

public class DatabaseItemWriter implements ItemWriter<Vendor>{
	
	@Autowired
	private DataSource dataSource;

	public void write(List<? extends Vendor> vendors) throws Exception {
		
		
		Vendor vendor = vendors.get(0);
		System.out.println("Vendor in writer : "+vendor);
		
		try {
			

			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();

			conn.close();
			
		}catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}


	}

}