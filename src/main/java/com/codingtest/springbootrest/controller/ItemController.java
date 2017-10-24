package com.codingtest.springbootrest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingtest.springbootrest.domain.Item;
import com.codingtest.springbootrest.repository.ItemJpaRepository;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemJpaRepository itemJpaRepository;

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Item> findAll() {
		return itemJpaRepository.findAll();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> findById(@PathVariable("id") Long id) {
		Item findItem = itemJpaRepository.findById(id);
		if (findItem == null) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Item>(findItem, HttpStatus.OK);
	}

	@PostMapping(value = "/load", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> load(@RequestBody final Item item) {
		Item newItem = itemJpaRepository.save(item);
		if (newItem == null) {
			return new ResponseEntity<Item>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Item>(itemJpaRepository.findById(newItem.getId()), HttpStatus.CREATED);
	}

}
