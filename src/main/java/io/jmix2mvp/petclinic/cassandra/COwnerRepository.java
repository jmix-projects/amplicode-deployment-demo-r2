package io.jmix2mvp.petclinic.cassandra;

import io.jmix2mvp.petclinic.cassandra.entity.COwner;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface COwnerRepository extends CassandraRepository<COwner, Long> {
}
