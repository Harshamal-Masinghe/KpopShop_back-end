package com.kpopshop.giftbox.reposotory;

import com.kpopshop.giftbox.model.GiftBoxColor;
import com.kpopshop.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GiftBoxColorRepository extends MongoRepository<GiftBoxColor, String> {

}
