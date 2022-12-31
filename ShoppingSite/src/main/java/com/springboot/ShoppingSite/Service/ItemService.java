package com.springboot.ShoppingSite.Service;

import com.springboot.ShoppingSite.Entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ItemService {

    public List<Item> getItems();

    public void saveItem(Item item);

    public Item findItemById(int id);

    public List<Item> findAll();

    public List<Item> findAllHeadwear();



}
