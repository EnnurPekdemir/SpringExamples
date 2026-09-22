package com.HaydiKodlayalim.repository;

import java.util.List;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.HaydiKodlayalim.entity.Kisi;

@Repository
public interface KisiRepository extends ElasticsearchRepository<Kisi, String> {

    @Query("{\"match\": {\"ad\": \"?0\"}}")
    List<Kisi> getByCustomQuery(String search);
}
