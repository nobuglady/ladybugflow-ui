package io.github.nobuglady.network.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.nobuglady.network.ui.flow.Demo1Flow;

@Service
public class Demo1Service {

	@Autowired
	private Demo1Flow demo1Flow;
	
	/**
	 * Æô¶¯Á÷³Ì
	 */
	public void bookingHotel(String username) {
		demo1Flow.startFlow(true, username);
	}
}
