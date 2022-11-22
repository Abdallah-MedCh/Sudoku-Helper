package com.helpe.sudoku.sudokuhelper;


public class GameBoard {

    public static class CelPlayed{

        public CelPlayed(int Val_Real, int Val_Init ) {
            this.Val_Init = Val_Init == 1;
            if ( this.Val_Init ) this.Val_Real = Val_Real;
        }

        public boolean Val_Init = false;
        public int Val_Real;
        public boolean [] mark = { false, false, false, false, false, false, false, false, false };



    }
    public CelPlayed[][] cells;
    public int cells_ind_i = -1;
    public int cells_ind_j = -1;

    private GameBoard(  CelPlayed[][] cells ) {

        this.cells = cells;
    }

    //Renvoie la valeur actuellement sélectionnée
    public int getSelectedValue() {
        if ( this.cells_ind_j == -1 || this.cells_ind_i == -1 ) return 0;
        CelPlayed currentCell = this.cells[ this.cells_ind_i ][ this.cells_ind_j ];
        return currentCell.Val_Real;
    }

    public void ecrire(int valeur ) {
        if ( this.cells_ind_j == -1 || this.cells_ind_i == -1 ) return;
        CelPlayed currentCell = this.cells[ this.cells_ind_i ][ this.cells_ind_j ];
        currentCell.Val_Real = valeur;
    }

    public void Effacé_cellule() {
        if ( this.cells_ind_j == -1 ) return;
        if ( this.cells_ind_i == -1 ) return;

        CelPlayed cel_act = this.cells[ this.cells_ind_i ][ this.cells_ind_j ];
        cel_act.Val_Real = 0;
        cel_act.mark = new boolean[] { false, false, false, false, false, false, false, false, false };
    }

    public static GameBoard getGameBoard(  ) {
        return new GameBoard(  new CelPlayed[][] {
                { new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0) },
                { new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0) },
                { new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0) },

                { new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0) },
                { new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0) },
                { new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0) },

                { new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0) },
                { new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0) },
                { new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0),
                        new CelPlayed(0,0), new CelPlayed(0,0), new CelPlayed(0,0) }
        });
    }

}
