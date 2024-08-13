package com.bookstore.Jpa.repositories;

import com.bookstore.Jpa.models.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AuhtorRepository  extends JpaRepository<AuthorModel, UUID> {
}
