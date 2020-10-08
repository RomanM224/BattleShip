package com.maistruk.battleship.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.maistruk.battleship.exception.BattleShipException;
import com.maistruk.battleship.model.Field;
import com.maistruk.battleship.model.Fleet;
import com.maistruk.battleship.service.ComputerAI;
import com.maistruk.battleship.service.FieldChecker;
import com.maistruk.battleship.service.GamaManager;
import com.maistruk.battleship.service.ShipsGenerator;
import com.maistruk.battleship.service.TableCreater;
import com.maistruk.battleship.service.UserInputChecker;

@Controller
public class BattleshipController {
    
    @Autowired
    private GamaManager gameManager;
    
    @Autowired
    private FieldChecker fieldChecker;
    
    @Autowired
    private TableCreater tableCreater;
    
    @Autowired
    private ShipsGenerator shipsGenerator;
    
    @Autowired
    private UserInputChecker userInputChecker;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView helloWorld() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/startGame")
    public ModelAndView startGame(HttpSession session) {
        session.removeAttribute("exception");
        session.setAttribute("fieldChecker", fieldChecker);
        session.setAttribute("tableCreater", tableCreater);
        ModelAndView mav = new ModelAndView("startGame");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/startGame")
    public ModelAndView startGame(HttpSession session, HttpServletRequest request) {
        session.removeAttribute("exception");
        List<Field> myFields = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                String parameter = "my_" + shipsGenerator.getLatter(i) + j;
                Boolean value;
                if(request.getParameter(parameter) != null) {
                    value = true;
                } else {
                    value = false;
                }
                Field field = new Field(parameter, value);
                myFields.add(field);
                System.out.println(field);
            }
        }
        List<Field> enemyShots = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                String parameter = "enemyShot_" + shipsGenerator.getLatter(i) + j;
                Boolean value;
                if(request.getParameter(parameter) != null) {
                    value = true;
                } else {
                    value = false;
                }
                Field field = new Field(parameter, value);
                enemyShots.add(field);
            }
        }
        try {
            userInputChecker.checkInput(myFields);
        } catch (BattleShipException exception) {
            session.setAttribute("exception", exception.getMessage());
            return new ModelAndView("startGame");
        }
        Fleet myFleet = gameManager.generateFleet(myFields);
        session.setAttribute("enemyShots", enemyShots);
        session.setAttribute("myShipsFields", gameManager.generateFleet(myFields).getAllFields());
        
        session.setAttribute("myFleet", myFleet);
        
        
        
        List<Field> myShots = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                String parameter = "myShot_" + shipsGenerator.getLatter(i) + j;
                Boolean value;
                if(request.getParameter(parameter) != null) {
                    value = true;
                } else {
                    value = false;
                }
                Field field = new Field(parameter, value);
                myShots.add(field);
            }
        }
        List<Field> enemyShipsFields = shipsGenerator.generateShips();
        Fleet enemyFleet = gameManager.generateFleet(enemyShipsFields);
        session.setAttribute("myShots", myShots);
        session.setAttribute("enemyShipsFields", enemyShipsFields);
        session.setAttribute("enemyFleet", enemyFleet);
        
        ComputerAI computerAI = new ComputerAI();
        session.setAttribute("computerAI", computerAI);

        ModelAndView mav = new ModelAndView("game");
        return mav;
    }
    
    @PostMapping(value = "/game")
    public ModelAndView game(HttpSession session, HttpServletRequest request) {
        List<Field> myShots = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                String parameter = "myShot_" + shipsGenerator.getLatter(i) + j;
                Boolean value;
                if(request.getParameter(parameter) != null) {
                    value = true;
                } else {
                    value = false;
                }
                Field field = new Field(parameter, value);
                myShots.add(field);
            }
        }
        Fleet myFleet =(Fleet) session.getAttribute("myFleet");
        ComputerAI computerAI = (ComputerAI) session.getAttribute("computerAI");
        myFleet.destroyMyShip(computerAI.getEnemyShots());
        session.setAttribute("enemyShots", computerAI.getEnemyShots());
        computerAI.enemyHit();
        
        Fleet enemyFleet =(Fleet) session.getAttribute("enemyFleet");
        enemyFleet.destroyEnemyShip(myShots);
        session.setAttribute("myShots", myShots);
        
        session.setAttribute("myFleet", myFleet);
        session.setAttribute("enemyFleet", enemyFleet);
        session.setAttribute("computerAI", computerAI);
        if(enemyFleet.getShips().size() == 0) {
            session.setAttribute("info", "You Win!");
            return new ModelAndView("finish");
        }
        if(myFleet.getShips().size() == 0) {
            session.setAttribute("info", "You Lose!");
            return new ModelAndView("finish");
        }

        return new ModelAndView("game");
    }
}
