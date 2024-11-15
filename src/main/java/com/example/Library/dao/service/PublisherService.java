package com.example.Library.dao.service;

import com.example.Library.dao.PublisherDao;
import com.example.Library.entity.Publisher;
import com.example.Library.spring.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PublisherService implements PublisherDao {
    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Page<Publisher> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortField);
        return publisherRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
    }

    @Override
    public List<Publisher> getAll(Sort sort) {
        return publisherRepository.findAll(sort);
    }

    @Override
    public List<Publisher> search(String... searchString) {
        return publisherRepository.findByNameIgnoreCaseOrderByName(searchString[0]);
    }

    @Override
    public Page<Publisher> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        if (searchString == null || searchString.length == 0) {
            return Page.empty();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField));

        return publisherRepository.findByNameIgnoreCase( searchString[0], pageRequest);
    }

    @Override
    public Publisher get(long id) {
        return  publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found"));
    }

    @Override
    public Publisher save(Publisher obj) {
        return publisherRepository.save(obj);
    }

    @Override
    public void delete(Publisher obj) {
        publisherRepository.delete(obj);
    }

}
