package com.oched.booksprj.repositories;


import com.oched.booksprj.entities.BookDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookDescriptionEntity, Long> {
    Optional<BookDescriptionEntity> findByTitle(String title);
}
