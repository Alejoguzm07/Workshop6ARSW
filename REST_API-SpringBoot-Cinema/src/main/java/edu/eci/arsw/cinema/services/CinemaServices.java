/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.filter.MovieFilter;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */
@Service
public class CinemaServices {
    @Autowired
    CinemaPersitence cps;
    
    @Autowired
    MovieFilter filtro;
    
    
    public void addNewCinema(Cinema c){
        
    }
    
    public Set<Cinema> getAllCinemas(){
        return cps.getAllCinemas();
    }
    
    /**
     * 
     * @param name cinema's name
     * @return the cinema of the given name created by the given author
     * @throws CinemaException
     */
    public Cinema getCinemaByName(String name) throws CinemaException, CinemaPersistenceException {
        Cinema c = cps.getCinema(name);
        if(c == null){
            throw new CinemaException("Cinema no encontrado");
        }
        return c;
    }
    
    
    public void buyTicket(int row, int col, String cinema, String date, String movieName){
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaPersistenceException {
        List<CinemaFunction> f = cps.getFunctionsbyCinemaAndDate(cinema,date);
        if(f.size() == 0){
            throw new CinemaPersistenceException("No se encontraron funciones");
        }
        return f;
    }
    
    public List<Movie> filterby(Cinema cinema,String date, String filter){
        return filtro.filter(cinema, date, filter);
    
    }


}
