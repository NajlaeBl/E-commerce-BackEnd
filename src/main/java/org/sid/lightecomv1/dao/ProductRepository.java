package org.sid.lightecomv1.dao;

import org.sid.lightecomv1.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {


    //une méthode qui permet de retourner les produits sélectionnés
    @RestResource(path="/selectedProducts")
    public List<Product> findBySelectedIsTrue(); //findby que dan intellij


    @RestResource(path="/productsByKeyword")
    public List<Product> findByNameContains(@Param("mc") String mc); //findby que dan intellij
}
