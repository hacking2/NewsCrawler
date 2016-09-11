package study.java.project1.dao;

import org.springframework.data.repository.CrudRepository;

import study.java.project1.model.News;

public interface NewsDao extends CrudRepository<News, Long> {

}
