package com.tus.ecommerce.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PurchaseResponse {

    @NonNull // add constructor with this parameter
    private String orderTrackingNumber;

}
