package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;

@Repository
public class OrderDetailDao implements BaseDao<OrderDetail> {
	@Autowired
	OrderDetailRepository repository;
	
	@Override
	public List<OrderDetail> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}

	@Override
	public OrderDetail findById(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return this.repository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public void save(OrderDetail orderdetail) {
		// TODO 自動生成されたメソッド・スタブ
		this.repository.save(orderdetail);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			OrderDetail orderdetail = this.findById(id);
			this.repository.deleteById(orderdetail.getId());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}
}
