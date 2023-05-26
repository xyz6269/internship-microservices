package com.example.kitchenservice.service;

import com.example.kitchenservice.DTO.ItemDto;
import com.example.kitchenservice.DTO.OrderDto;
import com.example.kitchenservice.entity.Item;
import com.example.kitchenservice.entity.Order;
import com.example.kitchenservice.repository.ItemRepository;
import com.example.kitchenservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public void createOrder(OrderDto dto) {
        log.info(dto.getPrice().toString());
        log.info(dto.getItemDtoList().toString());
        Order order = new Order();
        List<Item> itemList = dto.getItemDtoList()
                        .stream()
                        .map(this::DtoMapper)
                        .toList();
        order.setItemList(itemList);
        order.setOrderAmount(itemList.stream().mapToInt(Item::getQuantity).sum());
        order.setPrice(dto.getPrice());
        orderRepository.save(order);

    }

    private Item DtoMapper(ItemDto itemDto) {
        Item item = Item.builder()
                .name(itemDto.getName())
                .quantity(itemDto.getQuantity())
                .build();
        itemRepository.save(item);
        return item;
    }

}
