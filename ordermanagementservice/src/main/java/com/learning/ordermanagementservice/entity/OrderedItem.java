package com.learning.ordermanagementservice.entity;



import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ordered_items")
public class OrderedItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderedItemId;
    private String name;
    private int quantity;
    private double price;
    private Long itemId;
    
    @ManyToOne
    private Order order;

	public OrderedItem(String name, int quantity, double price, Long itemId, Order order) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.itemId = itemId;
		this.order = order;
	}

	public OrderedItem() {
		super();
	}

	public Long getOrderedItemId() {
		return orderedItemId;
	}

	public void setOrderedItemId(Long orderedItemId) {
		this.orderedItemId = orderedItemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
