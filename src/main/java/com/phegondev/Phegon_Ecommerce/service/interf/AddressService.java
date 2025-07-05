package com.phegondev.Phegon_Ecommerce.service.interf;

import com.phegondev.Phegon_Ecommerce.dto.AddressDto;
import com.phegondev.Phegon_Ecommerce.dto.Response;

public interface AddressService {

	Response saveAndUpdateAddress(AddressDto addressDto);
}
