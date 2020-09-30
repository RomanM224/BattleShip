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

import com.maistruk.battleship.model.ComputerAI;
import com.maistruk.battleship.model.TableCreater;
import com.maistruk.battleship.model.Field;
import com.maistruk.battleship.model.FieldChecker;
import com.maistruk.battleship.model.Fleet;
import com.maistruk.battleship.service.GamaManager;

@Controller
public class BattleshipController {
    
    @Autowired
    private GamaManager gameManager;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView helloWorld() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/startGame")
    public ModelAndView startGame() {
        ModelAndView mav = new ModelAndView("startGame");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/startGame")
    public ModelAndView startGame(HttpSession session, HttpServletRequest request) {
        List<Field> myFields = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                String parameter = "my_" + gameManager.getLatter(i) + j;
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
        List<Field> enemyHits = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                String parameter = "enemyHit_" + gameManager.getLatter(i) + j;
                Boolean value;
                if(request.getParameter(parameter) != null) {
                    value = true;
                } else {
                    value = false;
                }
                Field field = new Field(parameter, value);
                enemyHits.add(field);
           //     System.out.println(field);
            }
        }
        Fleet myFleet = gameManager.generateFleet(myFields);
        FieldChecker myFieldChecker = new FieldChecker();
        myFieldChecker.setFleet(myFleet);
        myFieldChecker.setFields(enemyHits);
        myFieldChecker.setShipsFields(gameManager.generateFleet(myFields).getAllFields());
        session.setAttribute("myFieldChecker", myFieldChecker);
        TableCreater myTableCreater = new TableCreater();
        myTableCreater.setFieldChecker(myFieldChecker);
        session.setAttribute("myTableCreater", myTableCreater);
        session.setAttribute("myFleet", myFleet);
        
        
        
        List<Field> myHits = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                String parameter = "myHit_" + gameManager.getLatter(i) + j;
                Boolean value;
                if(request.getParameter(parameter) != null) {
                    value = true;
                } else {
                    value = false;
                }
                Field field = new Field(parameter, value);
                myHits.add(field);
           //     System.out.println(field);
            }
        }
        List<Field> enemyFields = gameManager.generateShips();
        Fleet enemyFleet = gameManager.generateFleet(enemyFields);
        FieldChecker enemyFieldChecker = new FieldChecker();
        enemyFieldChecker.setFleet(enemyFleet);
        enemyFieldChecker.setFields(myHits);
        enemyFieldChecker.setShipsFields(enemyFields);
        TableCreater enemyTableCreater = new TableCreater();
        enemyTableCreater.setFieldChecker(enemyFieldChecker);
        session.setAttribute("enemyTableCreater", enemyTableCreater);
        session.setAttribute("enemyFleet", enemyFleet);
        session.setAttribute("enemyFieldChecker", enemyFieldChecker);
        
        ComputerAI computerAI = new ComputerAI();
        computerAI.setMyFleet(myFleet);
        session.setAttribute("computerAI", computerAI);

        ModelAndView mav = new ModelAndView("game2");
        return mav;
    }
    
    @PostMapping(value = "/game")
    public ModelAndView game(HttpSession session, HttpServletRequest request) {
        List<Field> myHits = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                String parameter = "myHit_" + gameManager.getLatter(i) + j;
                Boolean value;
                if(request.getParameter(parameter) != null) {
                    value = true;
                } else {
                    value = false;
                }
                Field field = new Field(parameter, value);
                myHits.add(field);
           //     System.out.println(field);
            }
        }
        Fleet myFleet =(Fleet) session.getAttribute("myFleet");
        ComputerAI computerAI = (ComputerAI) session.getAttribute("computerAI");
        FieldChecker myFieldChecker = (FieldChecker)session.getAttribute("myFieldChecker");
        myFieldChecker.setFields(computerAI.getEnemyHits());
        computerAI.enemyHit();
        myFleet.destroyMyShip(computerAI.getEnemyHits());
        
        
        Fleet enemyFleet =(Fleet) session.getAttribute("enemyFleet");
        enemyFleet.destroyEnemyShip(myHits);
        FieldChecker enemyFieldChecker = (FieldChecker)session.getAttribute("enemyFieldChecker");
        enemyFieldChecker.setFields(myHits);
        
        session.setAttribute("myFleet", myFleet);
        session.setAttribute("enemyFleet", enemyFleet);
        session.setAttribute("myHits", myHits);
        session.setAttribute("computerAI", computerAI);
        System.out.println(enemyFleet.getShips().size());
        if(enemyFleet.getShips().size() == 0) {
            session.setAttribute("info", "You Win!");
            return new ModelAndView("finish");
        }
        if(myFleet.getShips().size() == 0) {
            session.setAttribute("info", "You Lose!");
            return new ModelAndView("finish");
        }

        return new ModelAndView("game2");
    }
}
