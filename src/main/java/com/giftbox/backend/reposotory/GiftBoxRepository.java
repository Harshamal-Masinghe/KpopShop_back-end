package com.giftbox.backend.reposotory;

import com.giftbox.backend.model.GiftBox;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GiftBoxRepository extends MongoRepository<GiftBox, String> {
}
