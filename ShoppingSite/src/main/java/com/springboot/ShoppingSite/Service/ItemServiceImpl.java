package com.springboot.ShoppingSite.Service;

import com.springboot.ShoppingSite.Entity.Item;
import com.springboot.ShoppingSite.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Item findItemById(int id) {
        Optional<Item> res = itemRepository.findById(id);

        if(res == null) throw new NotFoundException("Item not found");

        Item item = res.get();

        return item;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findAllHeadwear() {
        return itemRepository.findAllHeadwear();
    }
}
