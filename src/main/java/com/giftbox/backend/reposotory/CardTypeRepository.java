package com.giftbox.backend.reposotory;

import com.giftbox.backend.model.CardType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardTypeRepository extends MongoRepository<CardType, String> {
}
