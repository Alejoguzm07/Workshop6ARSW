/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.ui;

import edu.eci.arsw.cinema.*;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author hcadavid
 */
public class Main {

    /**public static void main(String[] args) throws CinemaPersistenceException {
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	CinemaServices gc=ac.getBean(CinemaServices.class);

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("Movies Bogotá",functions);

        ipct.saveCinema(c);
        List<Movie> films= gc.filterby(c, functionDate, "1");
        for(int i=0; i<films.size();i++){
            Movie m= films.get(i);
            System.out.println(m.getName()+" "+ m.getGenre());
        }



}
     */
}
