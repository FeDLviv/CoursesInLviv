package lte.fed.test.controller;

import lte.fed.test.entity.Author;
import lte.fed.test.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;


    @GetMapping
    private List<Author> getAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable).getContent();
    }

}
