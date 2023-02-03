package huffle.puff.wand.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import huffle.puff.wand.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{

}
