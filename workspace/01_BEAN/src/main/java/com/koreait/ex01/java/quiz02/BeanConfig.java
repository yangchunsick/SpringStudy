package com.koreait.ex01.java.quiz02;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource("quiz04.xml")
@Configuration
public class BeanConfig {
	
	@Bean
	public Gun gun2() {
		Gun gun = new Gun();
		gun.setModel("M4");
		gun.setBullet(15);
		return gun;
	}
	@Bean
	public Soldier soldier2() {
		Soldier soldier = new Soldier();
		soldier.setName("김상사");
		soldier.setGun(gun2());
		
		Map<String, String> army = new HashMap<String, String>();
		army.put("name", "강철");
		army.put("address", "강원도 철원군");
		soldier.setArmy(army);

		return soldier;
	}
	 
}
