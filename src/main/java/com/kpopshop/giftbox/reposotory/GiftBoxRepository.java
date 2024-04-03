package com.kpopshop.giftbox.reposotory;

import com.kpopshop.giftbox.model.GiftBox;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GiftBoxRepository extends MongoRepository<GiftBox, String> {
}
