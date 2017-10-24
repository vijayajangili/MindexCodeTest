package com.codingtest.springbootrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.codingtest.springbootrest.domain.Item;

@Component
public interface ItemJpaRepository extends JpaRepository<Item, Long> {

	Item findById(Long id);

}
