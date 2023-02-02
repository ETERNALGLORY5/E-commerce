package huffle.puff.wand.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import huffle.puff.wand.dtos.PageableResponse;

public class Helper {
    

    public static<U,V> PageableResponse<V> getPageableresponse(Page<U> page,Class<V> type)
    {
        List<U> entity = page.getContent();

        // 	Page<User> page = userRepository.findAll(pageable); 
        //  List<User> users  =  page.getContent();                     
        List<V>  dtoList =  entity
                             .stream()
                             .map(object -> new ModelMapper()
                             .map(object,type))
                             .collect(Collectors.toList());
         
    
         PageableResponse<V> response = new PageableResponse<>();
         response.setContent(dtoList);
         response.setPageNumber(page.getNumber());
         response.setPageSize(page.getSize());
         response.setTotalElements(page.getTotalElements());
         response.setTotalPages(page.getTotalPages());			
         response.setLastPage(page.isLast());
        
             return response;
    }
}
