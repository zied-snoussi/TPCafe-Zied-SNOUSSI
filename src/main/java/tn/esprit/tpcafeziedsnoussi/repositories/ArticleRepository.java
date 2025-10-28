package tn.esprit.tpcafeziedsnoussi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpcafeziedsnoussi.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
