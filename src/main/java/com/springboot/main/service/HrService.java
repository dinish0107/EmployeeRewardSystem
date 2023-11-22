package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Hr;
import com.springboot.main.repository.HrRepository;

@Service
public class HrService {

	@Autowired
	private HrRepository hrRepository;

	public Hr insert(Hr hr) {
		// TODO Auto-generated method stub
		return hrRepository.save(hr);
	}

	public Hr getOne(int id) throws InvalidIdException {
		Optional<Hr> optional = hrRepository.findById(id);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Hr ID Invalid");
		}
		return optional.get();
	}

	public List<Hr> getAllHr(Pageable pageable) {
		// TODO Auto-generated method stub
		return hrRepository.findAll(pageable).getContent();
	}

	public void deleteHr(Hr hr) {

		hrRepository.delete(hr);
	}

	
}
