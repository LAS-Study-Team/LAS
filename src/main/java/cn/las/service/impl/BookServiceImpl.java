package cn.las.service.impl;

import cn.las.mapper.BookMapper;
import cn.las.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;
}
