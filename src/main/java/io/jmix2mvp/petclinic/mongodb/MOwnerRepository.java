package io.jmix2mvp.petclinic.mongodb;

import io.jmix2mvp.petclinic.mongodb.entity.MOwner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MOwnerRepository extends MongoRepository<MOwner, String> {
}
