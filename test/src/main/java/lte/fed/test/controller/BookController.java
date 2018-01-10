package lte.fed.test.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lte.fed.test.entity.Book;
import lte.fed.test.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {


    @Autowired
    private BookRepository bookRepository;

    //http://localhost:8080/books?sort=author.name&sort=id,desc
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                    value = "Номер сторінки, яку відобразити (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
                    value = "Кількість записів на сторінці."),
            @ApiImplicitParam(name = "sort", dataType = "string", allowMultiple = true, paramType = "query",
                    value = "Критерій сортування у форматі: property(,asc|desc). За замовчуванням, йде сортування по зростанню. Є підтримка для декількох параметрів сортування.")
    })
    public List<Book> getBooks(Pageable pageable) {
        //return bookRepository.findAll(pageable);
        return bookRepository.test(pageable).getContent();
        //return bookRepository.findAll(pageable).getContent();
    }

}
