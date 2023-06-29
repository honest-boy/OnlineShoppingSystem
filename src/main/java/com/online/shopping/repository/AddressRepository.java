package com.online.shopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.shopping.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	public Optional<Address> findAddressByUserId(int userId);

}
