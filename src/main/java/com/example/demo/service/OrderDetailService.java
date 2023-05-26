package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.OrderDetail;

@Service
public class OrderDetailService implements BaseService<OrderDetail> {
	@Autowired
	private BaseDao<OrderDetail> dao;
	
	@Override
	public List<OrderDetail> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findAll();
	}

	@Override
	public OrderDetail findById(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findById(id);
	}

	@Override
	public void save(OrderDetail orderdetail) {
		// TODO 自動生成されたメソッド・スタブ
		dao.save(orderdetail);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		dao.deleteById(id);
	}

}
