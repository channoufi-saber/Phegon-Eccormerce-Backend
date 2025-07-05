package com.phegondev.Phegon_Ecommerce.service.impl;

import org.springframework.stereotype.Service;

import com.phegondev.Phegon_Ecommerce.dto.AddressDto;
import com.phegondev.Phegon_Ecommerce.dto.Response;
import com.phegondev.Phegon_Ecommerce.entity.Address;
import com.phegondev.Phegon_Ecommerce.entity.User;
import com.phegondev.Phegon_Ecommerce.repository.AddressRepo;
import com.phegondev.Phegon_Ecommerce.service.interf.AddressService;
import com.phegondev.Phegon_Ecommerce.service.interf.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

	private final AddressRepo addressRepo;
	private final UserService userService;

	@Override
	public Response saveAndUpdateAddress(AddressDto addressDto) {
		User user = userService.getLoginUser();
		Address address = user.getAddress();

		if (address == null) {
			address = new Address();
			address.setUser(user);
		}

		if (addressDto.getStreet() != null) {
			address.setStreet(addressDto.getStreet());
		}
		if (addressDto.getCity() != null) {
			address.setCity(addressDto.getCity());
		}
		if (addressDto.getState() != null) {
			address.setState(addressDto.getState());
		}
		if (addressDto.getZipCode() != null) {
			address.setZipCode(addressDto.getZipCode());
		}
		if (addressDto.getCountry() != null) {
			address.setCountry(addressDto.getCountry());
		}

		addressRepo.save(address);
		String message = (user.getAddress() == null) ? "Address successfully created" : "Address successfully updated";
		return Response.builder().status(200).message(message).build();
	}

}
