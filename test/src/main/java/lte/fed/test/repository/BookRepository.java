package lte.fed.test.repository;

import lte.fed.test.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

    @Query("SELECT b FROM Book b LEFT JOIN b.author WHERE b.author.name = 'Fed'")
    Page<Book> test(Pageable pageable);

}
