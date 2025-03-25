package com.nishanth.OnlineBookStore.repo;

import com.nishanth.OnlineBookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// An interface named BookRepository being created to extend JpaRepository
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
