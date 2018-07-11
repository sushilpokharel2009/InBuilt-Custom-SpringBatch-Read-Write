package com.uvk.readwrite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.uvk.model.Vendor;

@Component
public class DatabaseItemReader implements ItemReader<Vendor> {

	private int nextVendorIndex;

	private List<Vendor> vendors = new ArrayList<Vendor>();

	@Autowired
	private DataSource dataSource;

	DatabaseItemReader() {
System.out.println("INside DatabaseItemReader >>>> "+dataSource);
		initialize();

	}

	private void initialize() {

		try {

			Connection conn = dataSource.getConnection();

			Statement stmt = conn.createStatement();

			ResultSet rs2;

			rs2 =

					stmt.executeQuery("SELECT accountNumber, routingNumber from Test1");

			while (rs2.next()) {
				Vendor vendor = new Vendor();

				String accountNumber = rs2.getString("accountNumber");

				String routingNumber = rs2.getString("routingNumber");

				vendor.setAccountNumber(accountNumber);

				vendor.setRoutingNumber(routingNumber);

				System.out.println("vendor : " + vendor);

				vendors.add(vendor);
			}

			conn.close();

		} catch (Exception e) {

			System.err.println("Got an exception! ");

			System.err.println(e.getMessage());

		}

		nextVendorIndex = 0;

	}

	public Vendor read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		// TODO Auto-generated method stub

		Vendor vendor = null;

		if (nextVendorIndex < vendors.size()) {

			vendor = vendors.get(nextVendorIndex);

			nextVendorIndex++;

		}

		return vendor;

	}

}
