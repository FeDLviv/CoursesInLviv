package lte.fed.test.repository;

import lte.fed.test.entity.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer> {

}
