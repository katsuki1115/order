package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Repository
public class ProductDao implements BaseDao<Product> {
	@Autowired
	ProductRepository repository;
	
	@Override
	public List<Product> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}

	@Override
	public Product findById(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public void save(Product product) {
		// TODO 自動生成されたメソッド・スタブ
		this.repository.save(product);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			Product product = this.findById(id);
			this.repository.deleteById(product.getId());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}

}
