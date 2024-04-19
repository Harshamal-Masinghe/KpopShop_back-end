package com.kpopshop.product.repository;

import com.kpopshop.product.model.Size;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SizeRepository extends MongoRepository<Size, String> {
}
