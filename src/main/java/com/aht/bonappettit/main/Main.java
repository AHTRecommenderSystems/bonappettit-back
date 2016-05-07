package com.aht.bonappettit.main;

import com.aht.api.recommender.ItemRecommenderCalculatedSimilitude;
import com.aht.bonappettit.configuration.AppConfig;
import com.aht.bonappettit.domain.node.Characteristic;
import com.aht.bonappettit.domain.node.Dish;
import com.aht.bonappettit.serviceimpl.node.CharacteristicServiceImpl;
import com.aht.bonappettit.serviceimpl.node.DishServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		DishServiceImpl dishService = context.getBean(DishServiceImpl.class);
		CharacteristicServiceImpl characteristicService = context.getBean(CharacteristicServiceImpl.class);

		Dish dish = dishService.retrieve(97);
		List<Characteristic> characteristics = new LinkedList<Characteristic>();
		for(Characteristic characteristic: dish.getCharacteristics()) {
			characteristics.add(characteristicService.retrieve(characteristic.getId()));
		}
		dish.setCharacteristics(characteristics); //Now it has characteristics where I can iterate! Yei!

		ItemRecommenderCalculatedSimilitude itemRecommenderCalculatedSimilitude = new ItemRecommenderCalculatedSimilitude();
		System.out.println(itemRecommenderCalculatedSimilitude.getTopNRecommendationByItem(dish,5));

		//DishServiceImpl dishService = context.getBean(DishServiceImpl.class);
		//Dish dish = dishService.retrieve(111);
		//ItemRecommenderCalculatedSimilitude itemRecommenderCalculatedSimilitude = new ItemRecommenderCalculatedSimilitude();
		//System.out.println(itemRecommenderCalculatedSimilitude.getTopNRecommendationByItem(dish,5));
		
		/**
		DishServiceImpl dishService = context.getBean(DishServiceImpl.class);
		CharacteristicServiceImpl characteristicService = context.getBean(CharacteristicServiceImpl.class);
		Dish dish = dishService.retrieve(97);
		for(long characteristicID : dish.getCharacteristicsID()) {
			System.out.println("characteristic --->" + characteristicService.retrieve(characteristicID).toString());
			Characteristic characteristic = characteristicService.retrieve(characteristicID);
			for(long dishID : characteristic.getDishesID()) {
				System.out.println("dish --->" + dishService.retrieve(dishID).toString());
			}
		}
		 * 
		 */

		((ConfigurableApplicationContext) context).close();
	}
}

/** crea una relación "HAS" entre una categoría y un platillo
	Characteristic characteristic = new Characteristic();
	Dish dish = new Dish();
	
	characteristic.setName("Categoria 1");
	dish.setName("Platillo 1");
	characteristic.addItem(dish);
	
	CharacteristicServiceImpl characteristicService = context.getBean(CharacteristicServiceImpl.class);
	characteristicService.create(characteristic);
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

/**
		Long id = Long.valueOf(314);
		DishServiceImpl dishServiceImpl = context.getBean(DishServiceImpl.class);
		Dish retrieved = dishServiceImpl.retrieve(id);
		System.out.println(retrieved);

		Dish anotherDish = dishServiceImpl.retrieve((long)315);

		ItemRecommenderNeo4j itemRecommenderNeo4j = new ItemRecommenderNeo4j();
		Set<Dish> recommendations = (Set<Dish>)(Object)itemRecommenderNeo4j.getTopNRecommendationByItem(retrieved,10);
		System.out.println(recommendations);

		ManhattanLength ml = new ManhattanLength();
		ml.getEvaluationForItems(retrieved, anotherDish);

		ItemRecommenderGeneral itemRecommenderGeneral = new ItemRecommenderGeneral();
		itemRecommenderGeneral.getTopNRecommendationByItem(retrieved,10);
		System.out.println(retrieved.getCharacteristics());


*/
