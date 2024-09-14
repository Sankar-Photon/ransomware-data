package com.interview.ransomware_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.interview.ransomware_data.service.RansomwareService;

@SpringBootApplication
public class RansomwareDataApplication implements CommandLineRunner {

	@Autowired
	private RansomwareService ransomwareService;
	public static void main(String[] args) {
		SpringApplication.run(RansomwareDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(":Welcome:");
		ransomwareService.insertRansomwareRecords();
	}

}
