/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 *
 * @author cristian
 */

@RestController
@RequestMapping(value = "/cinemas")
public class CinemaAPIController {
    @Autowired
    CinemaServices cs;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllCinemas(){
        //obtener datos que se enviarán a través del API
        return new ResponseEntity<>(cs.getAllCinemas(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getCinemaName(@PathVariable("name") String name){
        try {
            return new ResponseEntity<>(cs.getCinemaByName(name), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{name}/{date}")
    public ResponseEntity<?> getFunctionsByCinemaNameAndDate(@PathVariable("name") String name, @PathVariable("date") String date){
        try {
            return new ResponseEntity<>(cs.getFunctionsbyCinemaAndDate(name,date), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}/{date}/{moviename}")
    public ResponseEntity<?> getMovieByName(@PathVariable("name") String name, @PathVariable("date") String date, @PathVariable("moviename") String moviename){
        CinemaFunction cinemaFunction = null;
        try {
            cinemaFunction = cs.getFunctionsbyCinemaAndDate(name,date).stream().filter(x -> x.getMovie().getName().equals(moviename)).findFirst().orElse(null);
            if(cinemaFunction==null)
                return new ResponseEntity<>("No se encontro pelicula "+moviename, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(cinemaFunction, HttpStatus.ACCEPTED);
        } catch (CinemaPersistenceException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
