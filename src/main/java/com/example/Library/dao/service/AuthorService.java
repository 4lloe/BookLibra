package com.example.Library.dao.service;

import com.example.Library.dao.AuthorDao;
import com.example.Library.entity.Author;
import com.example.Library.entity.Genre;
import com.example.Library.spring.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService implements AuthorDao {
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public List<Author> getAll() {return authorRepository.findAll();}

    public List<Author> getAll(Sort sort){return authorRepository.findAll(sort);}

    @Override
    public Page<Author> getAll(int pageNumber, int pageSize,String sortField, Sort.Direction sortDirection)
    {
        Sort sort = Sort.by(sortDirection, sortField);
        return authorRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
    }

    @Override
    public List<Author> search(String... searchString){
        return authorRepository.findByFioContainingIgnoreCaseOrderByFio(searchString[0]);
    }

    @Override
    public Page<Author> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        if (searchString == null || searchString.length == 0) {
            return Page.empty();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField));

        return authorRepository.findByFioContainingIgnoreCase(searchString[0], pageRequest);
    }

    @Override
    public Author save(Author author){return authorRepository.save(author);}

    @Override
    public void delete(Author author){authorRepository.delete(author);}

    @Override
    public Author get(long id) {return authorRepository.findById(id);}
}
