package com.example.blogappapis.repositoreis;

import com.example.blogappapis.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
