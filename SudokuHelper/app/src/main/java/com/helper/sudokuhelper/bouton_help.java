package com.helper.sudokuhelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class bouton_help {




    public void Help(GameBoard GB,int valeur[][]) {
        boolean[] tab = new boolean[]{true, true, true, true, true, true, true, true, true,};

        ArrayList<Integer> rand = new ArrayList<Integer>(Arrays.asList(4,1,8,3,6,0,2,7,5)); // just to make the mark table going false look random
        int[][] puz = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puz[i][j] = GB.cells[i][j].Val_Real;
            }
        }
        Test t= new Test();
        Sudoku s = new Sudoku();
        s.rS(puz);
                 for(int n=1;n<=9;n++){
                     int k=n-1;
                     if(!t.Pos(GB,n)){
                         tab[k]=false;
                     }
                 }



      for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (GB.cells_ind_i==i &&GB.cells_ind_j==j ) {

                     for(int k=1;k<=9;k++){
                        if(puz[i][j] == k){

                            if (valeur[i][j] != 8) {
                                for(int f=0;f<=valeur[i][j];f++){
                                    tab[rand.get(f)]=false;
                                }
                                tab[k-1]=true;
                                GB.cells[GB.cells_ind_i][GB.cells_ind_j].mark = tab;
                                valeur[i][j]++;
                                Collections.shuffle(rand);
                            }

                            else  {
                                GB.cells[GB.cells_ind_i][GB.cells_ind_j].Val_Real = puz[i][j];
                                valeur[i][j] = 0;


                            }


                        }

                    }


                }

            }
        }
       return;
         }


}
