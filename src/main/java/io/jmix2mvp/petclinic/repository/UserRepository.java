package io.jmix2mvp.petclinic.repository;

import io.jmix2mvp.petclinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}