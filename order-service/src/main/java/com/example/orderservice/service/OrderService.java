package com.example.orderservice.service;

import com.example.orderservice.config.MQConfig;
import com.example.orderservice.dto.ItemDTO;
import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.dto.PrepDTO;
import com.example.orderservice.entity.Item;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.ItemRepository;
import com.example.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final RabbitTemplate template;
    private static final String URL="localhost:8080";
    private final WebClient client = WebClient.create("https://" +URL);

    public void placeOrder(OrderDTO dto) {
        Order order = new Order();
        String orderEmail = getEmail().toString();
        if (orderRepository.findOrderByCustomerEmail(orderEmail).isPresent()) {
            throw new RuntimeException("you already have an order pending please wait");
        }
        List<Item> orderedItemList = dto.getOrderedItems()
                .stream()
                .map(this::DtoMapper)
                .toList();
        order.setOrderedItems(orderedItemList);
        order.setFullPrice(orderedItemList.stream().mapToDouble(Item::getTotalPrice).sum());
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setCustomerEmail(orderEmail);
        orderRepository.save(order);

        PrepDTO prepDTO = new PrepDTO();
        prepDTO.setOrderedItems(dto.getOrderedItems());
        prepDTO.setOrderNumber(order.getOrderNumber());
        prepDTO.setCustomerEmail(order.getCustomerEmail());
        prepDTO.setFullPrice(order.getFullPrice());

        template.convertAndSend(MQConfig.EXCHANGE,MQConfig.ROUTING_KEY,prepDTO);
    }

    private Item DtoMapper(ItemDTO dto) {
        Item item = Item.builder()
                .quantity(dto.getQuantity())
                .name(dto.getName())
                .itemPrice(dto.getPrice())
                .totalPrice(dto.getPrice()* dto.getQuantity())
                .build();
        itemRepository.save(item);
        return item;
    }

    private Flux<String> getEmail() {
        return client.get()
                .uri("/api/auth/test/current-email")
                .retrieve()
                .bodyToFlux(String.class);
    }


}
