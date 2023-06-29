package com.online.shopping.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@RestController
public class PaymentRestController {

	// for payment
	@PostMapping("/cart/create_order")
	public String createOrder(@RequestBody Map<String, Object> data) {

		double amt = Double.parseDouble(data.get("amount").toString());
		try {
			var client = new RazorpayClient("rzp_test_uK7UVURCzPUR6d", "ekpTt4cPIKWznADo30JadRpx");

			JSONObject ob = new JSONObject();
			ob.put("amount", amt * 100);
			ob.put("currency", "INR");
			ob.put("receipt", "txn_123456");
			Order order = client.Orders.create(ob);

			return order.toString();
		} catch (Exception e) {
			return "transaction failure";
		}

	}
}
