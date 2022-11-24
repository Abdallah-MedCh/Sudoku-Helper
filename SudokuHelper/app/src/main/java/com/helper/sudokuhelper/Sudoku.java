package com.helper.sudokuhelper;

public class Sudoku {


    int cont =0;
    Test t = new Test();
    public boolean rS(int[][] puz) { // resouder le Sudoku

        int lig;
        int col;

        int[] blankCell = t.CaseVide(puz);
        lig = blankCell[0];
        col = blankCell[1];
        if (lig == -1) {
            // le puzzle est plien
            return true;
        }
        for (int i = 1; i <= 9; i++) {
            // si le num est possible ici on a le i
            if (t.Possible(puz, lig, col, i)) {
                puz[lig][col] = i;
                // appel recursive pour le rest du puzzle
                if (rS(puz)) {
                    cont++;
                    return true;
                }

                else{
                    // si on est ici veut dire que la selection na pas marcher
                    puz[lig][col] = 0;
                    cont++;
                }
            }
        }
        return false; // activation de la recursivite

    }

}
