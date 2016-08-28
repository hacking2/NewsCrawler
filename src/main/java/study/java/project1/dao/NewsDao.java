package study.java.project1.dao;

import org.springframework.data.repository.CrudRepository;

import study.java.project1.byproduct.RawNews;

public interface NewsDao extends CrudRepository<RawNews, Long> {

}
