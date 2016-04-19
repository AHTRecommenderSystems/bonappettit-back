package com.aht.bonappettit;

import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;
import com.aht.bonappettit.serviceimpl.node.UserServiceImpl;
import com.aht.recommend.ItemRecommender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aht.bonappettit.domain.node.Category;
import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.serviceimpl.node.CategoryServiceImpl;

import java.util.Set;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

/** crea una relación "HAS" entre una categoría y un platillo
	Category category = new Category();
	Dish dish = new Dish();
	
	category.setName("Categoria 1");
	dish.setName("Platillo 1");
	category.addItem(dish);
	
	CategoryServiceImpl categoryService = context.getBean(CategoryServiceImpl.class);
	categoryService.create(category);
*/

/** crea una relación "NEIGHBOR" entre dos usuarios
	User first = new User();
	User second = new User();
	
	first.setName("Héctor");
	first.setUsername("hector9317");
	first.setPassword("escom");
	
	second.setName("Blanca");
	second.setUsername("blankazucenalg");
	second.setPassword("escom");
	
	Neighbor neighbor = new Neighbor(first, second, (float) 0.5);
	
	NeighborServiceImpl neighborServiceImpl = context.getBean(NeighborServiceImpl.class);
	neighborServiceImpl.create(neighbor);
*/

/** busca a un usuario por su id y obtiene el número de vecinos que posee
	UserServiceImpl userServiceImpl = context.getBean(UserServiceImpl.class);
	System.out.println(userServiceImpl.retrieve(0).getNeighbors().size());
*/
        Long id = Long.valueOf(314);
        DishServiceImpl dishServiceImpl = context.getBean(DishServiceImpl.class);
        Dish retrieved = dishServiceImpl.retrieve(id);
        System.out.println(retrieved);

        ItemRecommender itemRecommender = new ItemRecommender();
        Set<Dish> recommendations = (Set<Dish>)(Object)itemRecommender.getTopNRecommendationsByItem(retrieved,1);
        System.out.println(recommendations);
		((ConfigurableApplicationContext) context).close();

	}
}