package com.tus.ecommerce.service;

import com.tus.ecommerce.dao.UserInfoRepository;
import com.tus.ecommerce.dao.UserRepository;
import com.tus.ecommerce.dto.Purchase;
import com.tus.ecommerce.dto.PurchaseResponse;
import com.tus.ecommerce.entity.User;
import com.tus.ecommerce.entity.UserInfo;
import com.tus.ecommerce.entity.Order;
import com.tus.ecommerce.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private UserInfoRepository userInfoRepository;
    private UserRepository userRepository;

    @Autowired
    public CheckoutServiceImpl(UserInfoRepository userInfoRepository, UserRepository userRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase, String username) {

        Order order = purchase.getOrder();

        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderDetail> orderDetails = purchase.getOrderDetails();
        orderDetails.forEach(item -> order.add(item));

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        User user = userRepository.findFirstByUsername(username);

        UserInfo userInfo = purchase.getUserInfo();
        userInfo.setOrder(order);

        userInfo.setUser(user);

        userInfoRepository.save(userInfo);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        return UUID.randomUUID().toString();
    }
}









