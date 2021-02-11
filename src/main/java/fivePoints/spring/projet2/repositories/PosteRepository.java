package fivePoints.spring.projet2.repositories;

import fivePoints.spring.projet2.models.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosteRepository  extends JpaRepository<Poste,Integer> {
    //   get Posts By Titre
    public List<Poste> getPostsByTitle(String title);

    //    get Posts by Titre and Description
    public List<Poste> getPostsByTitleAndDescription(String title, String description);

    // Custum SQL Query
}
