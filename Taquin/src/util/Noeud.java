/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Vector;

/**
 *
 * @author M.Andriamahery
 */
public class Noeud {
    
    Vector<Vector<Integer>> position;
    Vector<Vector<Integer>> predecesseur;
    private int traite = 0;
    
    public Noeud(Vector<Vector<Integer>> solution){
        this.setPosition(position);
    }
    
    public Noeud(Vector<Vector<Integer>> solution, Vector<Vector<Integer>> predecesseur){
        this.setPosition(position);
        this.setPredecesseur(predecesseur);
    }
    
    public Noeud(){}

    public Vector<Vector<Integer>> getPosition() {
        return position;
    }

    public void setPosition(Vector<Vector<Integer>> position) {
        this.position = position;
    }

    public Vector<Vector<Integer>> getPredecesseur() {
        return predecesseur;
    }

    public void setPredecesseur(Vector<Vector<Integer>> predecesseur) {
        this.predecesseur = predecesseur;
    }
    
    public int getTraite() {
        return traite;
    }

    public void setTraite(int traite) {
        this.traite = traite;
    }

    

    
    
    
    
    
    public static void main(String[] args){
       Vector<Vector<Integer>> solution = Util.genererSolution();
       solution = Util.mix(solution);
       for(int i=0 ; i<solution.size(); i++){
           for(int j=0; j<solution.size(); j++){
               System.out.print(solution.get(i).get(j)+"   ");
           }
           System.out.println();
           
      Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);

        int indexToReplace = 2;
        int newValue = 5;

        vector.set(indexToReplace, newValue);

        System.out.println(vector);
    }
       
    
       
       
   }

    
    
}
