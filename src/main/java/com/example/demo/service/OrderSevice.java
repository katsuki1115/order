package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.Order;

@Service
public class OrderSevice implements BaseService<Order> {
	@Autowired
	private BaseDao<Order> dao;
	
	@Override
	public List<Order> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findAll();
	}

	@Override
	public Order findById(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findById(id);
	}

	@Override
	public void save(Order order) {
		// TODO 自動生成されたメソッド・スタブ
		dao.save(order);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		dao.deleteById(id);
	}

}
