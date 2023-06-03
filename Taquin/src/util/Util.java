/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author M.Andriamahery
 */
public class Util {
    
    //former les indices possible au voisinage de 0
    public static Vector<Vector<Integer>> formIndicePossible(Vector<Integer> indice0) {
        Vector<Vector<Integer>> reponse = new Vector<>();
        int[] offsets = {0, 1, 0, -1, 1, 0, -1, 0};
        int x = indice0.get(0);
        int y = indice0.get(1);
        for (int i = 0; i < offsets.length; i += 2) {
            int newX = x + offsets[i];
            int newY = y + offsets[i + 1];
            reponse.add(new Vector<>(Arrays.asList(newX, newY)));
        }
        return reponse;
    }
    
    //former tous les indices pour verification 
    public static Vector<Vector<Integer>> formIndiceVerification(){
        Vector<Vector<Integer>>  reponse = new Vector<>();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                Vector<Integer> toadd = new Vector<>();
                toadd.add(i);
                toadd.add(j);
                reponse.add(toadd);
            }
        }
        return reponse;
    }
    
    //memoriser les indices qui se trouvent au voisinage de 0
    public static Vector<Vector<Integer>> generateIndicePeripherie(Vector<Integer> indice0, Vector<Vector<Integer>> verification){
        Vector<Vector<Integer>> reponse = new Vector<>();
        Vector<Vector<Integer>> indicePossibles = Util.formIndicePossible(indice0);
        for(int i=0; i<indicePossibles.size(); i++){
            if(verification.contains(indicePossibles.get(i))){
                reponse.add(indicePossibles.get(i));
            }
        }
        return reponse;
    }
    
    //get les peripheries aux voisinage de 0 avec leur indice respective
    public static Vector<Vector<Integer>> getPeripherieWithIndice(Vector<Vector<Integer>> verification, Vector<Integer> indice0, Vector<Vector<Integer>> solution){
        Vector<Vector<Integer>> peripheries = new Vector<>();
        Vector<Vector<Integer>> indPeripheries = Util.generateIndicePeripherie(indice0, verification);
        peripheries = Util.finalise(indPeripheries,solution);
        return peripheries;
    }

    //finaliser la generation au voisinage de 0
    public static Vector<Vector<Integer>> finalise(Vector<Vector<Integer>> indPeripheries, Vector<Vector<Integer>> solution) {
        Vector<Vector<Integer>> reponse = new Vector<>();
        for(int i=0; i<indPeripheries.size(); i++){
            Vector<Integer> toadd = new Vector<>();
            int[] indiceSolution = new int[2];
            for(int j=0; j<2 ; j++){
                indiceSolution[j] = indPeripheries.get(i).get(j);
            }
            toadd.add(solution.get(indiceSolution[0]).get(indiceSolution[1]));
            toadd.add(indiceSolution[0]);
            toadd.add(indiceSolution[1]);
            reponse.add(toadd);
        }
        return reponse;
    }
    
    //generer solution
    public static Vector<Vector<Integer>> genererSolution(){
        int nbSolution = 1;
        Vector<Vector<Integer>> solution = new Vector<>();
        for(int i = 0; i < 3 ;i++){
            Vector<Integer> toadd = new Vector<>();
            for(int j = 0; j < 3; j++ ){
                if(i==2 && j==2){
                    toadd.add(0);
                }
                else{
                    toadd.add(nbSolution);
                    nbSolution++;
                }
            }
            solution.add(toadd);
        }
        return solution;
    }
     
    //echanger 0 avec le nombre recu par random
    public static Vector<Vector<Integer>> exchangeWithZero(Vector<Vector<Integer>> solution, Vector<Vector<Integer>> peripherie,Vector<Integer> indice0){
        Random rand = new Random();
        int randomNumber = rand.nextInt(peripherie.size()); // Ajoutez 1 pour inclure le nombre maximum
        Vector<Integer> toExchange = peripherie.get(randomNumber);
        solution = Util.exchange(toExchange, indice0, solution);
        return solution;
    }

    //fonction echanger
    public static Vector<Vector<Integer>> exchange(Vector<Integer> toExchange, Vector<Integer> indice0, Vector<Vector<Integer>> solution){
        Vector<Vector<Integer>> reponse = new Vector<>();
        for(int i=0; i<solution.size(); i++){
            Vector<Integer> toadd = new Vector<>();
            for(int j=0; j<solution.get(i).size(); j++){
                if(i==indice0.get(0) && j==indice0.get(1)){
                    toadd.add(solution.get(toExchange.get(1)).get(toExchange.get(2)));
                }
                else if(i==toExchange.get(1) && j==toExchange.get(2)){
                    toadd.add(0);
                }
                else{
                    toadd.add(solution.get(i).get(j));
                }
            }
            reponse.add(toadd);
        }
        Util.clearIndice0(indice0, toExchange); //effacer les indices de 0 et les remplacer par ce de l'element a echanger    
        return reponse;
    }
    
    //melanger akorontana
    public static Vector<Vector<Integer>>  mix(Vector<Vector<Integer>> solution){
        Vector<Vector<Integer>> verification = Util.formIndiceVerification();
        Vector<Integer> indice0 = new Vector<>();
        indice0.add(2);
        indice0.add(2);
        for(int i=0; i<15; i++){
            Vector<Vector<Integer>> peripherie1 = Util.getPeripherieWithIndice(verification, indice0, solution);
            solution = Util.exchangeWithZero(solution, peripherie1, indice0);
        }
        return solution;
    }
    
    //effacer les elements d'inice 0 et les remplacer par les index de l'element a echanger 
    public static void clearIndice0(Vector<Integer> indice0, Vector<Integer> toExchange){
        indice0.clear();
        indice0.add(toExchange.get(1));
        indice0.add(toExchange.get(2));
    }
    
    //exchange with zero (new method)
    public static Vector<Vector<Integer>> exchangeWith0New(Vector<Integer> indice0,Vector<Integer> peripherie, Vector<Vector<Integer>> position){
        for(int i = 0; i < position.size(); i++){
            for(int j = 0; j < position.get(i).size(); j++){
                if(i == indice0.get(0) && j == indice0.get(1)){
                    position.get(i).set(j, peripherie.get(0));
                }
                if(i == peripherie.get(1) && j == peripherie.get(2)){
                    position.get(i).set(j,0);
                }
            }
        }
        return position;
    }
    
    //echange tous les peripheries avec 0
}
