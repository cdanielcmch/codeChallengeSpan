/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package leagueranking.model;

/**
 *  This class will be used to store the data 
 *  related to the teams of the league.
 */
public class Team {
    private String name;
    private int points;
    private int position;
    
    public Team(String name, int points, int position){
        this.name = name;
        this.points = points;
        this.position = position;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getPoints(){
        return this.points;
    }
    
    public void setPoints(int points){
        this.points = points;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
}
