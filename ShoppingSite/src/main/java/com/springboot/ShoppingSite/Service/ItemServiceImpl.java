package com.springboot.ShoppingSite.Service;

import com.springboot.ShoppingSite.Entity.Item;
import com.springboot.ShoppingSite.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> findAllHeadwear() {
        return itemRepository.findAllHeadwear();
    }
}
