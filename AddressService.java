package com.example.prj;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	@Autowired
	public AddressRepository addressRepository;


	public List<Address> AddressDetails() {
		return addressRepository.findAll();

	}

	public Optional<Address> getId(int id) {
		return addressRepository.findById(id);
	}

	public Address addAddress(Address adr) {
		return addressRepository.save(adr);

	}

	public boolean deleteAddress(int id) {
		Optional<Address> address = addressRepository.findById(id);
		if (address.isPresent()) {
			addressRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Address updateAddress(Address atr) {
		Optional<Address> existingAddress = addressRepository.findById(atr.getAddressId());
		if (existingAddress.isPresent()) {
			existingAddress.get().setStreetName(atr.getStreetName());
			existingAddress.get().setCity(atr.getCity());
			existingAddress.get().setPincode(atr.getPincode());
			existingAddress.get().setStudentId(atr.getStudentId());
			return addressRepository.saveAndFlush(existingAddress.get());
		}
		return null;

	}
}
