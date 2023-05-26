package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Repository
public class CustomerDao implements BaseDao<Customer> {
	@Autowired
	CustomerRepository repository;
	
	@Override
	public List<Customer> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}

	@Override
	public Customer findById(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return this.repository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public void save(Customer customer) {
		// TODO 自動生成されたメソッド・スタブ
		this.repository.save(customer);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			Customer customer = this.findById(id);
			this.repository.deleteById(customer.getId());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}

}
