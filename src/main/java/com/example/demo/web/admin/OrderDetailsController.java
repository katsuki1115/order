package com.example.demo.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.FlashData;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.BaseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/orderdetails")
public class OrderDetailsController {
	@Autowired
	BaseService<OrderDetail> orderdetailService;
	
	@Autowired
	BaseService<Order> orderService;
	
	/*
	 * 受注詳細画面表示
	 */
	@GetMapping(value = "/create/{orderId}")
	public String form(@PathVariable Integer orderId, OrderDetail orderdetail, Model model) {
		model.addAttribute("orderdetail", orderdetail);
		model.addAttribute("orderId", orderId);
		return "admin/orderdetails/create";
	}
	/*
	 * 受注詳細新規登録
	 */
	@PostMapping(value = "/create/{orderId}")
	public String update(@PathVariable Integer orderId, @Valid OrderDetail orderdetail, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			if(result.hasErrors()) {
				return "admin/orderdetails/create/" + orderId;
			}
			Order order = orderService.findById(orderId);
			orderdetail.setOrder(order);
			//新規登録
			orderdetailService.save(orderdetail);
			flash = new FlashData().success("新規作成しました");
		} catch (Exception e) {
			flash  = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/orders/view/" + orderId;
	}
	
	
	
	@GetMapping(value = "/edit/{orderId}")
	public String edit(@PathVariable Integer orderId, Model model, OrderDetail orderdetail) {
		try {
			//存在確認
			OrderDetail orderdetailedit = orderdetailService.findById(orderId);
			model.addAttribute("orderdetailedit", orderdetailedit);
		} catch (Exception e) {
			return "redirect:/admin/orders";	
		}
		return "admin/orderdetails/edit";
	}
	
	@PostMapping(value = "/edit/{orderId}")
	public String update_edit(@PathVariable Integer orderId, @Valid OrderDetail orderdetail, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			if(result.hasErrors()) {
				return "admin/orderdetails/edit/" + orderId;
			}
			Order orderedit = orderService.findById(orderId);
			//新規登録
			orderdetail.setOrder(orderedit);
			orderdetailService.save(orderdetail);
			flash = new FlashData().success("更新しました");
		} catch (Exception e) {
			flash  = new FlashData().danger("該当データがありません");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/orders/view/" + orderId;
	}
	
	
	
	
	
	
	
}
