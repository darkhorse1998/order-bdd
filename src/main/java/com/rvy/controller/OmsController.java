package com.rvy.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rvy.entity.OrderDetail;
import com.rvy.entity.OrderMaster;
import com.rvy.exception.OrderException;
import com.rvy.payload.request.OrderRequest;
import com.rvy.payload.response.MessageResponse;
import com.rvy.service.OrderDetailServiceImpl;
import com.rvy.service.OrderMasterServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api
@RestController
@RequestMapping("/oms/v1")
@ApiOperation(value = "Controller for Order Management System",
tags = "Order Management System Controller")
public class OmsController {

	@Autowired
	private OrderMasterServiceImpl orderMasterService;

	@Autowired
	private OrderDetailServiceImpl orderDetailService; 
	//http://localhost:8081/rvy/oms/v1/orders/1
//	@ApiOperation(value = "Find Order By Id",
//			consumes = "user id",
//			produces = "OrderMaster object",
//			response = OrderMaster.class,
//			tags = "FindOrderByID",
//			notes = "http://localhost:8081/rvy/api/oms/v1/orders/1")
//	@GetMapping("/orders/{id}")
//	public ResponseEntity<OrderMaster> findOrderById(@Valid @PathVariable("id") Integer id,
//			BindingResult bindingResult) throws OrderException{
//		try {
//			if(bindingResult.hasErrors()) {
//				throw new OrderException(bindingResult.getAllErrors().toString());
//			}
//			OrderMaster order = orderMasterService.getOrderDetailsById(id);  
//			return new ResponseEntity<>(order,HttpStatus.OK);
//		}catch(ResponseStatusException e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
//		}		
//	}

	// http://localhost:8081/cms/v1/orders
	//	@ApiOperation(value = "Add Order",
	//			consumes = "CustomerOrderMaster object",
	//			produces = "CustomerOrderMaster object",
	//			tags = "PostOrder",
	//			notes = "http://localhost:8081/rvy/api/oms/v1/orders")
	//	@PostMapping("/orders")
	//	public ResponseEntity<OrderMaster> addOrder(@Valid @RequestBody OrderMaster order,
	//			BindingResult bindingResult) throws OrderException{
	//		try {
	//			if(bindingResult.hasErrors()) {
	//				throw new OrderException(bindingResult.getAllErrors().toString());
	//			}
	//			
	//			orderMasterService.getTotalOrderAmount(order.getOrderId());
	//			orderMasterService.getBillingAmount(order.getOrderId());
	//			OrderMaster orderAdded = orderMasterService.addOrder(order);
	//			return new ResponseEntity<>(orderAdded,HttpStatus.OK);
	//		}catch(ResponseStatusException e) {
	//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
	//		}
	//	}

	@PostMapping("/orders/orderDetails")
	public ResponseEntity<OrderDetail> addOrderDetail(@Valid @RequestBody OrderDetail itemDetail,
			BindingResult bindingResult) throws OrderException{
		try {
			if(bindingResult.hasErrors()) {
				throw new OrderException(bindingResult.getAllErrors().toString());
			}
			OrderDetail orderDetailAdded = orderDetailService.addOrderDetail(itemDetail);
			return ResponseEntity.ok(orderDetailAdded);

		}catch(ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}


	// http://localhost:8081/cms/v1/orders
	@ApiOperation(value = "Get All Orders",
			produces = "List of CustomerOrderMaster objects",
			tags = "GetAllOrder",
			notes = "http://localhost:8083/rvy/oms/v1/orders")
	@GetMapping("/orders")
	public ResponseEntity<List<OrderMaster>> getAllOrders(){
		try {
			List<OrderMaster> orderList = orderMasterService.getAllOrders();

			return new ResponseEntity<>(orderList,HttpStatus.OK);
		}catch(OrderException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}

	}

	// http://localhost:8081/cms/v1/orders
	@ApiOperation(value = "Update Order",
			consumes = "CustomerOrderMaster object",
			produces = "CustomerOrderMaster object",
			response = OrderDetail.class,
			tags = "UpdateOrder",
			notes = "http://localhost:8083/rvy/api/oms/v1/orders")
	@PutMapping("/orders")
	public ResponseEntity<OrderMaster> updateOrder(@Valid @RequestBody OrderMaster order,
			BindingResult bindingResult) throws OrderException{
		try {
			if(bindingResult.hasErrors()) {
				throw new OrderException(bindingResult.getAllErrors().toString());
			}
			OrderMaster orderAdded = orderMasterService.updateOrder(order);
			return new ResponseEntity<>(orderAdded,HttpStatus.OK);
		}catch(ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}

	// http://localhost:8081/cms/v1/orders/1
//	@ApiOperation(value = "Delete Order By Id",
//			consumes = "user id",
//			produces = "user id of the deleted object",
//			response = Integer.class,
//			tags = "DeleteOrder",
//			notes = "http://localhost:8081/rvy/api/oms/v1/orders/1")
//	@DeleteMapping("/orders/{id}")
//	public ResponseEntity<Integer> deleteOrder(@PathVariable("id") Integer id){
//		try {
//			Integer deletedId= orderMasterService.deleteOrder(id); 
//			return ResponseEntity.ok(deletedId);
//		}catch(Exception e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
//		}
//	}

	//	@GetMapping("/orders/totalAmount/{id}")
	//	public ResponseEntity<Double> getTotalOrderAmout(@PathVariable("id") Integer id){
	//		try {
	//			Double totalOrderAmount = orderMasterService.getTotalOrderAmount(id);
	//			return  ResponseEntity.ok(totalOrderAmount);
	//			
	//		}catch(ResponseStatusException e) {
	//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
	//		}
	//	}
	@ApiOperation(value = "Find OrderDetails By Id",
			consumes = "Order id",
			produces = "OrderDetails object",
			tags = "FindOrderDetailsByOrderID",
			notes = "http://localhost:8083/rvy/api/oms/v1/orders/1")
	@GetMapping("/orders/{id}")
	public ResponseEntity<List<OrderDetail>> getAllOrderDetailsByOrderId(@PathVariable("id") Integer id){
		try {
			List<OrderDetail> orderDetailList = orderMasterService.getAllOrderDetailsByOrderId(id);
			return ResponseEntity.ok(orderDetailList);
		}catch(ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	@ApiOperation(value = "Add Orders",
			consumes = "order request",
			produces = "Message Response",
			response = MessageResponse.class,
			tags = "AddOrder",
			notes = "http://localhost:8083/rvy/api/oms/v1/orders")
	@PostMapping("/orders")
	public ResponseEntity<MessageResponse> addOrders(@RequestBody OrderRequest orderRequest) throws OrderException {

		try {
			OrderMaster orderMaster = new OrderMaster(  orderRequest.getBillNo(),
					orderRequest.getOrderDate(),
					orderRequest.getOrderAmount(),
					orderRequest.getTaxAmount(),
					orderRequest.getBillingAmount(),
					orderRequest.getPaymentMode(),
					orderRequest.getCurrencyType(),
					orderRequest.getCustomerId(),
					orderRequest.getStoreId()
					);
			orderMaster.setOrderDetailList(orderRequest.getOrderDetails());
			orderMasterService.addOrder(orderMaster);
			return ResponseEntity.ok(new MessageResponse("Order placed successfully"));
		}catch(ResponseStatusException e) {
			throw new OrderException(e.getMessage(),e);
		}
	}
	@ApiOperation(value = "Delete Order By Id",
			consumes = "Order id",
			produces = "Order id of the deleted object",
			response = Integer.class,
			tags = "DeleteOrder",
			notes = "http://localhost:8083/rvy/api/oms/v1/orders/1")
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<Integer> deleteOrderDetailsByOrderId(@PathVariable("orderId") Integer id) throws OrderException
	{
		try {
			Integer deletedOrderId = orderMasterService.deleteOrderDetailsByOrderId(id);
			return ResponseEntity.ok(deletedOrderId);
		}catch(ResponseStatusException e) {
			throw new OrderException(e.getMessage(),e);
		}
	}
}
