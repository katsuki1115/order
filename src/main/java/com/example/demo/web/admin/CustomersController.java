package com.example.demo.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.FlashData;
import com.example.demo.entity.Customer;
import com.example.demo.service.BaseService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/admin/customers")
public class CustomersController {
	@Autowired
	BaseService<Customer> customerService;
	
	/*
	 * 一覧表示
	 */
	@GetMapping(path = {"", "/"})
	public String list(Model model) {
		//全権取得	
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "admin/customers/list";
	}
	
	/*
	 * 新規作成画面表示
	 */
	@GetMapping(value = "/create")
	public String form(Customer customer, Model model) {
		model.addAttribute("customer", customer);
		return "admin/customers/create";
	}
	
	/*
	 * 新規登録
	 */
	@PostMapping(value = "/create")
	public String register(@Valid Customer customer, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			if(result.hasErrors()) {
				return "admin/customers/create";
			}
			//新規登録
			customerService.save(customer);
			flash = new FlashData().success("新規作成しました");
		} catch (Exception e) {
			flash  = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/customers";
	}
}
