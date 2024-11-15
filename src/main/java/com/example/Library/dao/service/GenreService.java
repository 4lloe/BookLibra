package com.example.Library.dao.service;

import com.example.Library.dao.GenreDao;
import com.example.Library.entity.Book;
import com.example.Library.entity.Genre;
import com.example.Library.spring.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenreService implements GenreDao {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {return genreRepository.findAll();}

    public List<Genre> getAll(Sort sort) {return genreRepository.findAll(sort);}

    @Override
    public Page<Genre> getAll(int pageNumber, int pageSize, String sortField,Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortField);
        return genreRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
    }

    @Override
    public List<Genre> search(String... searchString){
        return genreRepository.findByNameIgnoreCaseOrderByName(searchString[0]);
    }

    @Override
    public Page<Genre> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        if (searchString == null || searchString.length == 0) {
            return Page.empty();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField));

        return genreRepository.findByNameIgnoreCase(searchString[0], pageRequest);
    }

    @Override
    public Genre save(Genre genre){return genreRepository.save(genre);}

    @Override
    public void delete(Genre genre){genreRepository.delete(genre);}

    @Override
    public Genre get(long id) {
        Optional<Genre> genreOptional =genreRepository.findById(id);
        return genreOptional.orElse(null); // Вернет null, если книга не найдена
    }


}
