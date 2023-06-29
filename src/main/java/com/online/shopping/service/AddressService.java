package com.online.shopping.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.model.Address;
import com.online.shopping.repository.AddressRepository;

@Service
@Transactional
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

	public void postAddress(Address address) {
		try {
			addressRepository.save(address);
		} catch (Exception e) {			
			logger.warn("invalid definition exception, Please fill all details carefully");
		}
	}
	
	public Optional<Address> findAddressByUserId(int userId){
		return addressRepository.findAddressByUserId(userId);
	}
}
