package com.synechron;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class MathController {
	
	
//	@GetMapping("/operations")
//	public List<String> getOperations() {
//		List<String> operations = Arrays.asList(
//				"http://localhost:8080/add/12/13", 
//				"http://localhost:8080/subtract/12/13");
//		return operations;
//	}
	
	
	//Here's the result of multiplication; But alongwith you can also try addition and subtraction and here're the links to those
	@GetMapping("/multiply/{a}/{b}")
	public HttpEntity<MathResult> multiply(@PathVariable int a, 
			@PathVariable int b) {
		MathResult mathResult = new MathResult();
		mathResult.setResult("Product of " + a + " and " + b + " is " + (a * b));
		mathResult.add(linkTo(methodOn(this.getClass()).add(a, b)).withRel("Addition"));
		mathResult.add(linkTo(methodOn(this.getClass()).subtract(a, b)).withRel("Subtraction"));
		return new ResponseEntity<MathResult>(mathResult, HttpStatus.OK);
	}
	
	
	@GetMapping("/operations/num1/{num1}/num2/{num2}")
	public RepresentationModel getOperations(@PathVariable int num1, @PathVariable int num2) {
		RepresentationModel model = new RepresentationModel();
		model.add(linkTo(methodOn(this.getClass()).add(num1, num2)).withRel("Addition"));
		model.add(linkTo(methodOn(this.getClass()).subtract(num1, num2)).withRel("Subtraction"));
		return model;
	}
	
	
	
	@ApiOperation(value = "Addition", notes = "Adds two numbers")
	@ApiResponses({
		@ApiResponse(code = 200, response = Integer.class, message = "Sum is"),
		@ApiResponse(code = 500, response = Integer.class, message = "Oops!!!")
	})
	@GetMapping("/add/{a}/{b}")
	public HttpEntity<Integer> add(@PathVariable @ApiParam(example = "12", defaultValue = "number1") int a, 
			@PathVariable @ApiParam(example = "20", defaultValue = "number2") int b) {
		return new ResponseEntity<Integer>(a + b, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Subtraction", notes = "Subtracts two numbers")
	@ApiResponse(code = 200, response = Integer.class, message = "Difference is")
	@GetMapping("/subtract/{a}/{b}")
	public HttpEntity<Integer> subtract(@PathVariable @ApiParam(example = "10") int a, 
			@PathVariable @ApiParam(example = "8") int b) {
		return new ResponseEntity<Integer>(a - b, HttpStatus.OK);
	}
}
