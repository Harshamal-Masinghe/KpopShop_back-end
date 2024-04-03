package com.kpopshop.giftbox.reposotory;

import com.kpopshop.giftbox.model.CardType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardTypeRepository extends MongoRepository<CardType, String> {
}
