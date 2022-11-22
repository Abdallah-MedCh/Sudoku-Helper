package com.helpe.sudoku.sudokuhelper;


public class Test {
    public boolean Possible(int[][] puz,int lig, int col, int n) {
        //verify if the ligne contain the n
        // if the column contain the n
        //if the box contain the n
        //		if no one is true then n is possible



        if (!Dligne(puz,lig, n) && !Dcolonne(puz,col, n)
                && !Dcarre(puz,lig - lig % 3, col - col % 3, n)) {
            return true;
        }
        return false;
    }

    // verifier si n est deja sur la ligne
    public boolean Dligne(int[][] puz,int lig, int n) {
        for (int i = 0; i < 9; i++) {
            if (puz[lig][i] == n) {
                return true;
            }
        }
        return false;
    }

    // vverifier si n est deja sur la colonne
    public boolean Dcolonne(int[][] puz,int col, int n) {
        for (int i = 0; i < 9; i++) {
            if (puz[i][col] == n) {
                return true;
            }
        }
        return false;
    }

    // verifier si n est deja sur le caree
    public boolean Dcarre(int[][] puz,int DCL, int DCC , int n) {
        for (int i = DCL ; i < DCL + 3; i++) {
            for (int j = DCC; j <DCC+ 3; j++) {
                if (puz[i][j] == n) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[] CaseVide(int[][] puz) {
        int[] Tab = new int[2];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puz[i][j] == 0) {
                    Tab[0] = i;
                    Tab[1] = j;
                    return Tab;
                }
            }
        }
        Tab[0] = -1;
        Tab[1] = -1;
        return Tab; // puzzel est plein
    }
    public boolean Pos(GameBoard GB, int i){  // Test if the Value i is a possible choice in the chosen case
        int s = 0;
        int m = 0;
        int k = 0;
        for (int z = 0; z < 9; z++) {

            //------Column Test------//
            if (GB.cells[z][GB.cells_ind_j].Val_Real != i) {
                s = s + 1;
            }
            GB.cells.clone();
            //----- Ligne Test------//
            if (GB.cells[GB.cells_ind_i][z].Val_Real != i) {
                m = m + 1;

            }
            //-------BOX Test --------//
            int bx = GB.cells_ind_j / 3;
            int by = GB.cells_ind_i / 3;
            for (int dy = 0; dy < 3; dy++) {
                for (int dx = 0; dx < 3; dx++) {
                    int tx = bx * 3 + dx;
                    int ty = by * 3 + dy;
                    if ( tx != GB.cells_ind_j && ty != GB.cells_ind_i &&
                            GB.cells[ty][tx].Val_Real ==i) {
                        k=1;
                        break;
                    }
                }
            }

        }
        if (s == 9 && m == 9 && k==0){return true;}
        return false;
    }

}
