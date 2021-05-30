package com.math;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mathdb")
public class MathDBConroller {

	@Autowired
	private OperationRepository operationRepository;

	@PostMapping("/operation/{operation}/number/{number}")
	public HttpEntity<Integer> create(@PathVariable String operation, @PathVariable int number) {
		MathOperation op = new MathOperation();
		op.setName(operation);
		op.setNum(number);
		operationRepository.save(op);
		return new ResponseEntity<>(op.getId(), HttpStatus.OK);
	}

}
