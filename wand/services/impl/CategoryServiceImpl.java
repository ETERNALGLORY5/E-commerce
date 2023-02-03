package huffle.puff.wand.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import huffle.puff.wand.dtos.CategoryDto;
import huffle.puff.wand.dtos.PageableResponse;
import huffle.puff.wand.entities.Category;
import huffle.puff.wand.exception.ResourceNotFoundException;
import huffle.puff.wand.repositories.CategoryRepository;
import huffle.puff.wand.services.CategoryService;

public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	
	
	
	@Override
	public CategoryDto create(CategoryDto categoryDto) {
		Category category = mapper.map(categoryDto, Category.class);
		Category savedCategory = categoryRepository.save(category);
		return mapper.map(savedCategory, CategoryDto.class);
	}

	
	@Override
	public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found by this Id"));
		
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepository.save(category);
        
        
        return mapper.map(updatedCategory, CategoryDto.class);
	}

	
	
	@Override
	public void delete(String categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(" Category not found with given Id"));
		 
		categoryRepository.delete(category);
		
		
	}
	
	
	@Override
	public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending()) ; 
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Category> page = categoryRepository.findAll(pageable);
		
		return null;
	}


	@Override
	public CategoryDto get(String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDto> searchCategory(String title) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
