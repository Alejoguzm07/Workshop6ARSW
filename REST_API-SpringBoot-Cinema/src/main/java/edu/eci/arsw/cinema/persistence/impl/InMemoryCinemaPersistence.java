/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @author cristian
 */
@Service
public class InMemoryCinemaPersistence implements CinemaPersitence{
    
    private final Map<String,Cinema> cinemas=new HashMap<>();

    public InMemoryCinemaPersistence() {
        //load stub data
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("cinemaX",functions);
        cinemas.put("cinemaX", c);

        String functionDate2 = "1999-01-09 15:30";
        List<CinemaFunction> functions2= new ArrayList<>();
        CinemaFunction funct12 = new CinemaFunction(new Movie("Toy Story","Kids"),functionDate2);
        CinemaFunction funct22 = new CinemaFunction(new Movie("Split","Action"),functionDate2);
        functions2.add(funct12);
        functions2.add(funct22);
        Cinema c2=new Cinema("cinemaY",functions2);
        cinemas.put("cinemaY", c2);

        String functionDate3 = "1997-09-12 15:30";
        List<CinemaFunction> functions3= new ArrayList<>();
        CinemaFunction funct13 = new CinemaFunction(new Movie("Origins","Fiction"),functionDate3);
        CinemaFunction funct23 = new CinemaFunction(new Movie("Gol","Sport"),functionDate3);
        functions3.add(funct13);
        functions3.add(funct23);
        Cinema c3=new Cinema("cinemaZ",functions3);
        cinemas.put("cinemaZ", c3);
    }    

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        Cinema c = cinemas.get(cinema);
        List<CinemaFunction> functions = c.getFunctions();
        CinemaFunction cf = null;
        for(int i = 0; i < functions.size(); i++){
            CinemaFunction fun = functions.get(i);
            //System.err.println(fun.getDate()+" Nombre:"+fun.getMovie().getName());
            if(fun.getMovie().equals(movieName) && fun.getDate().equals(date)){
                cf = fun;
                cf.buyTicket(row, col);
                break;
            }
        }
        System.out.println(cf);
        
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        Cinema c = cinemas.get(cinema);
        List<CinemaFunction> functions = c.getFunctions();
        List<CinemaFunction> rta = new ArrayList<>();
        for(int i = 0; i < functions.size(); i++){
            CinemaFunction fun = functions.get(i);
            if(fun.getDate().equals(date)){
                rta.add(fun);
                break;
            }
        }
        return rta;
    }

    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())){
            throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
        }
        else{
            cinemas.put(c.getName(),c);
        }   
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        return cinemas.get(name);
    }

    @Override
    public Set<Cinema> getAllCinemas() {
        Set<Cinema> cms = new HashSet<>();
        for(String s: cinemas.keySet()){
            cms.add(cinemas.get(s));
        }
        return cms;
    }

}
