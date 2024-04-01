package com.kpopshop.giftbox.reposotory;

import com.kpopshop.giftbox.model.GiftBoxColor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GiftBoxColorRepository extends MongoRepository<GiftBoxColor, String> {
}
