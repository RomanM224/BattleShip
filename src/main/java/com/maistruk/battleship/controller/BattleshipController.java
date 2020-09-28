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
import com.maistruk.battleship.model.Field;
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
        Fleet myFleet = gameManager.generateFlot(myFields);
        session.setAttribute("myFleet", myFleet);
        List<Field> enemyFields = gameManager.generateShips();
        Fleet enemyFleet = gameManager.generateFlot(enemyFields);
        session.setAttribute("enemyFleet", enemyFleet);
        
        ComputerAI computerAI = new ComputerAI();
        computerAI.setMyFleet(myFleet);
       // computerAI.addFleet(myFleet);
        session.setAttribute("computerAI", computerAI);

        ModelAndView mav = new ModelAndView("game");
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
                System.out.println(field);
            }
        }
        Fleet myFleet =(Fleet) session.getAttribute("myFleet");
        Fleet enemyFleet =(Fleet) session.getAttribute("enemyFleet");
        ComputerAI computerAI = (ComputerAI) session.getAttribute("computerAI");
        computerAI.enemyHit();
        myFleet.destroyMyShip(computerAI.getEnemyHits());
        enemyFleet.destroyEnemyShip(myHits);
        session.setAttribute("myFleet", myFleet);
        session.setAttribute("enemyFleet", enemyFleet);
        session.setAttribute("myHits", myHits);
        session.setAttribute("computerAI", computerAI);

        ModelAndView mav = new ModelAndView("game");
        return mav;
    }
}
