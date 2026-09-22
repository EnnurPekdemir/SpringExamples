package com.HaydiKodlayalim.repository;

import com.HaydiKodlayalim.entity.Kullanici;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KullaniciRepository extends MongoRepository<Kullanici, String> {

}
