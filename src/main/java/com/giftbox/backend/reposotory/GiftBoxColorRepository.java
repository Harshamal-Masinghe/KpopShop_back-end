package com.giftbox.backend.reposotory;

import com.giftbox.backend.model.GiftBoxColor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GiftBoxColorRepository extends MongoRepository<GiftBoxColor, String> {
}
