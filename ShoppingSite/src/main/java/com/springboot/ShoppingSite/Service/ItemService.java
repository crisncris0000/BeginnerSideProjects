package com.springboot.ShoppingSite.Service;

import com.springboot.ShoppingSite.Entity.Item;

import java.util.List;

public interface ItemService {

    public List<Item> getItems();

    public void saveItem(Item item);



}
