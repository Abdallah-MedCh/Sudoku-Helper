package com.helper.sudokuhelper;


import android.annotation.SuppressLint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Toast;

public class InterfaceGrafique extends View implements GestureDetector.OnGestureListener{


    private GestureDetector gestureDetector;
    private Paint paint = new Paint( Paint.ANTI_ALIAS_FLAG );
    private GameBoard GB = GameBoard.getGameBoard(  );
    int[][] valeur = new int[9][9];
    int[][] prove = new int[9][9];
    Test t= new Test();
    bouton_help Eg = new bouton_help();
    int solve_une_seul_fois=0;
    private float gridWidth;
    private float gridSeparatorSize;
    private float cellSize;
    private float buttonWidth;
    private float buttonRadius;
    private float buttonMargin;
    private float btnL;
    public InterfaceGrafique(Context context) {
        super(context);
        this.init();
    }

    public InterfaceGrafique(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    private void init() {
        // Activer le détecteur de gestes
        gestureDetector = new GestureDetector( getContext(),  this );


    }

    @Override
    protected void onSizeChanged(int carre, int d, int k, int x) {
        super.onSizeChanged(carre, d, k, x);

        // Nous calculons certaines tailles
        gridSeparatorSize = (carre / 9f) / 20f;

        gridWidth = carre;                                  // Taille de la grille (c'est un carré)
        cellSize = gridWidth / 9f;                     // Taille d'une cellule (c'est aussi un carré)
        buttonWidth = carre / 7f;                           // taille du  button
        buttonMargin = (carre - 6*buttonWidth) / 7f;        // Marge entre deux boutons

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        // --- Dessiner des cellules ---

        for( int i=0; i<9; i++ ) {
            for( int j=0; j<9; j++ ) {
                int backgroundColor = Color.WHITE;
                // Coloration des ligne et colone
                if ( GB.cells_ind_j != -1 && GB.cells_ind_i != -1 ){
                    if (
                            (j == GB.cells_ind_j && i != GB.cells_ind_i) ||
                                    (j != GB.cells_ind_j && i == GB.cells_ind_i)  ) {
                        backgroundColor = 0xFF_FF_F0_F5;
                    }
                }
                // Cells that are not filed by the user
                if ( GB.cells[i][j].Val_Init ) {
                    backgroundColor = 0xDD_AA_AA_AA;
                }

                // Change the color of the chosen value
                if ( GB.getSelectedValue() > 0 &&
                        GB.cells[i][j].Val_Real == GB.getSelectedValue() ) {
                    backgroundColor = 0xFF3498DB ;
                }
                // Dessine l'arrière-plan de la cellule actuelle
                paint.setColor( backgroundColor );
                canvas.drawRect(j * cellSize,
                        i * cellSize ,
                        (j+1) * cellSize,
                        (i+1) * cellSize,
                        paint);

                if (GB.cells[i][j].Val_Real != 0) {
                    // Dessinez la valeur supposée pour la cellule
                    paint.setColor(0xFF000000);
                    paint.setTextSize(cellSize * 0.7f);
                    canvas.drawText("" + GB.cells[i][j].Val_Real,
                            j * cellSize + cellSize / 2,
                            i * cellSize + cellSize * 0.75f, paint);
                }

                else {

                    // Dessinez chaque marque si elle existe
                    paint.setTextSize(cellSize * 0.33f);
                    paint.setColor(0xFFA0A0A0);
                    if ( GB.cells[i][j].mark[0] ) {
                        canvas.drawText("1",
                                j * cellSize + cellSize * 0.2f,
                                i * cellSize + cellSize * 0.3f, paint);
                    }
                    if ( GB.cells[i][j].mark[1] ) {
                        canvas.drawText("2",
                                j * cellSize + cellSize * 0.5f,
                                i * cellSize + cellSize * 0.3f, paint);
                    }
                    if ( GB.cells[i][j].mark[2] ) {
                        canvas.drawText("3",
                                j * cellSize + cellSize * 0.8f,
                                i * cellSize + cellSize * 0.3f, paint);
                    }
                    if ( GB.cells[i][j].mark[3] ) {
                        canvas.drawText("4",
                                j * cellSize + cellSize * 0.2f,
                                i * cellSize + cellSize * 0.6f, paint);
                    }
                    if ( GB.cells[i][j].mark[4] ) {
                        canvas.drawText("5",
                                j * cellSize + cellSize * 0.5f,
                                i * cellSize + cellSize * 0.6f, paint);
                    }
                    if ( GB.cells[i][j].mark[5] ) {
                        canvas.drawText("6",
                                j * cellSize + cellSize * 0.8f,
                                i * cellSize + cellSize * 0.6f, paint);
                    }
                    if ( GB.cells[i][j].mark[6] ) {
                        canvas.drawText("7",
                                j * cellSize + cellSize * 0.2f,
                                i * cellSize + cellSize * 0.9f, paint);
                    }
                    if ( GB.cells[i][j].mark[7] ) {
                        canvas.drawText("8",
                                j * cellSize + cellSize * 0.5f,
                                i * cellSize + cellSize * 0.9f, paint);
                    }
                    if ( GB.cells[i][j].mark[8] ) {
                        canvas.drawText("9",
                                j * cellSize + cellSize * 0.8f,
                                i * cellSize + cellSize * 0.9f, paint);
                    }
                }
            }
        }

        // --- Tracer les lignes de la grille et les colonne de la grille---
        paint.setColor( Color.GRAY );
        paint.setStrokeWidth( gridSeparatorSize/2 );
       for( int i=0; i<=9; i++ ) {
            canvas.drawLine( cellSize*i, 0, cellSize*i, cellSize*9, paint );
            canvas.drawLine( 0,i*cellSize, cellSize*9, cellSize*i, paint );
        }
        paint.setColor( Color.BLACK );
        paint.setStrokeWidth( gridSeparatorSize );
        for( int i=0; i<=3; i++ ) {
            canvas.drawLine( i*(cellSize*3), 0, i*(cellSize*3), cellSize*9, paint );
            canvas.drawLine( 0,i*(cellSize*3), cellSize*9, i*(cellSize*3), paint );
        }


        // --- Dessiner la bordure pour la cellule actuellement sélectionnée ---
        if ( GB.cells_ind_j != -1 && GB.cells_ind_i != -1 ) {
            paint.setStrokeWidth( gridSeparatorSize * 1.5f );
            paint.setStyle( Paint.Style.STROKE );
            canvas.drawRect( GB.cells_ind_j * cellSize,
                    GB.cells_ind_i * cellSize,
                    (GB.cells_ind_j+1) * cellSize,
                    (GB.cells_ind_i+1) * cellSize,
                    paint);
            paint.setStyle( Paint.Style.FILL_AND_STROKE );
            paint.setStrokeWidth( 1 );
        }

        // --- les boutons numiroté ---
        float buttonsTop = 9*cellSize;
        paint.setColor(0xFF1565C0); //background pour les button
        canvas.drawRect(0, buttonsTop, gridWidth, getHeight(), paint);

        float buttonLeft = buttonMargin;
        float buttonTop = buttonsTop + buttonMargin;

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(buttonWidth * 0.7f);

        for (int i = 1; i <= 9; i++) {

            paint.setColor( 0xFFFFFFFF );

            RectF rectF = new RectF(buttonLeft, buttonTop, buttonLeft + buttonWidth, buttonTop + buttonWidth);
            canvas.drawRoundRect(rectF, buttonRadius, buttonRadius, paint); // déssigné le buoutton "i"

            paint.setColor( 0xFF000000 );
            canvas.drawText("" + i, rectF.centerX(), rectF.top + rectF.height() * 0.75f, paint); // écrrire le numiro "i"
            buttonLeft += buttonWidth + buttonMargin;

            if (i == 3 || i==6 ) {//pour saut de ligne
                buttonLeft = buttonMargin;
                buttonTop += buttonWidth + buttonMargin;
            }
        }

        // --- Clear ---;
        buttonTop = buttonsTop + buttonMargin;
        paint.setColor(0xDDDDDDDD);
        RectF rectF = new RectF(buttonLeft, buttonTop,
                buttonLeft + buttonWidth, buttonTop + buttonWidth );
        canvas.drawRoundRect(rectF, buttonRadius, buttonRadius, paint); //déssigne bouton clear

        paint.setColor(0xFF000000);
        paint.setTextSize(buttonWidth * 0.3f);
        canvas.drawText("clear", rectF.centerX(), rectF.top + rectF.height() * 0.75f, paint); // ecrrire dans la bouton "clear
        buttonLeft += buttonWidth + buttonMargin;


        // --- Help ---

        paint.setColor(0xDDDDDDDD);
        rectF = new RectF(buttonLeft, buttonTop, buttonLeft + buttonWidth, buttonTop + buttonWidth);
        canvas.drawRoundRect(rectF, buttonRadius, buttonRadius, paint);
        paint.setColor(0xFF000000);
        paint.setTextSize(buttonWidth * 0.3f);
        canvas.drawText("Help", rectF.centerX(), rectF.top + rectF.height() * 0.75f, paint);

        buttonLeft += buttonWidth + buttonMargin;


        //-------Solve
        paint.setColor(0xDDDDDDDD);
        rectF = new RectF(buttonLeft, buttonTop, buttonLeft + buttonWidth, buttonTop + buttonWidth);
        canvas.drawRoundRect(rectF, buttonRadius, buttonRadius, paint);
        paint.setColor(0xFF000000);
        paint.setTextSize(buttonWidth * 0.3f);
        canvas.drawText("Solve", rectF.centerX(), rectF.top + rectF.height() * 0.75f, paint);


        //--------SolveAll
        btnL = buttonMargin;
        buttonLeft = buttonMargin;
        buttonLeft += (buttonWidth + buttonMargin)*3;
        btnL +=buttonWidth*3+buttonLeft;
        buttonTop += buttonWidth + buttonMargin;
        paint.setColor(0xDDDDDDDD);
        rectF = new RectF(buttonLeft,  buttonTop,  btnL+buttonMargin, buttonTop + buttonWidth);
        canvas.drawRoundRect(rectF, buttonRadius, buttonRadius, paint);
        paint.setColor(0xFF000000);
        paint.setTextSize(buttonWidth * 0.3f);
        canvas.drawText("Solve All", rectF.centerX(), rectF.top + rectF.height() * 0.75f, paint);


        //--------ClearAll
        btnL = buttonMargin;
        buttonLeft = buttonMargin;
        buttonLeft += (buttonWidth + buttonMargin)*3;
        btnL +=buttonWidth*3+buttonLeft;
        buttonTop += buttonWidth + buttonMargin;
        paint.setColor(0xDDDDDDDD);
        rectF = new RectF(buttonLeft,  buttonTop,  btnL+buttonMargin, buttonTop + buttonWidth);
        canvas.drawRoundRect(rectF, buttonRadius, buttonRadius, paint);
        paint.setColor(0xFF000000);
        //paint.setTextAlign(Paint.A);
        paint.setTextSize(buttonWidth * 0.3f);
        canvas.drawText("Clear All", rectF.centerX(), rectF.top + rectF.height() * 0.75f, paint);


    }
    // --- Events handlers ---

    // Override from View
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    // Override from OnGestureDectector
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        RectF rectF;
        // --- Vérifier la cellule de la grille cliquer ---
        if (e.getY() < gridWidth) {
            int cellX = (int) (e.getX() / cellSize);
            int cellY = (int) (e.getY() / cellSize);

            GB.cells_ind_j = cellX;
            GB.cells_ind_i = cellY;
            postInvalidate();
            return true;
        }

        float buttonLeft = buttonMargin;
        float buttonTop = 9 * cellSize;
        if (GB.cells_ind_i != -1 && GB.cells_ind_j != -1 ) {

            // --- bouton numiroté  ---
            for (int i = 1; i <= 9; i++) {
                int s = 0;int tempourair_i=0;
                int m = 0;int tempourair_j=0;
                int k = 0;

                rectF = new RectF(buttonLeft, buttonTop, buttonLeft + buttonWidth, buttonTop + buttonWidth);
                if (rectF.contains(e.getX(), e.getY())) {


                     if (t.Pos(GB,i)) { // cette condition pour désactivé les boutons
                    GB.ecrire(i);;
                    if (i != prove[GB.cells_ind_i][GB.cells_ind_j]) {
                        for (int x=0;x<9;x++){
                            for (int y=0;y<9;y++) {
                                valeur[x][y] = 0;
                                //GB.cellules[x][y].valeur_proposé = new boolean[]{false, false, false, false, false, false, false, false, false};
                            }
                        }
                    }
                    postInvalidate();
                    return true;
                     }
                     else { //si cette nombre i est déja existe dans la collonne
                         Toast.makeText(this.getContext(),"Checked your column/line or square you already have a '"+
                                 i ,Toast.LENGTH_LONG).show();
                     }


                }
                buttonLeft += buttonWidth + buttonMargin;
                if (i == 3 || i == 6) {
                    buttonLeft = buttonMargin;
                    buttonTop += buttonWidth + buttonMargin;
                }
            }
        }
        // --------- clear  ---------
        float buttonsTop = 9 * cellSize;
        buttonTop = buttonsTop + buttonMargin;
        if (GB.cells_ind_j != -1 && GB.cells_ind_i != -1) {
            rectF = new RectF(buttonLeft, buttonTop, buttonLeft + buttonWidth, buttonTop + buttonWidth);
            if (rectF.contains(e.getX(), e.getY())) {
                GB.Effacé_cellule();
                valeur[GB.cells_ind_i][GB.cells_ind_j]=0;
                GB.cells[GB.cells_ind_i][GB.cells_ind_j].Val_Init = false;
                this.invalidate();
                return true;
            }
        }
        // --------- clear  ---------



        // -------- help celleule --------

        buttonLeft += buttonWidth + buttonMargin;
        rectF = new RectF(buttonLeft, buttonTop, buttonLeft + buttonWidth, buttonTop + buttonWidth);
        if (rectF.contains(e.getX(), e.getY())) {


            Eg.Help(GB,valeur);
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    if(valeur[y][x]==9){
                        valeur[y][x]=0;
                        //flag=1;

                        //for (int i=0;i<9;i++){tab[i]=true;}
                    }

                }
                //tab[y]=true;
            }
            //for (int i=0;i<9;i++){tab[i]=true;}
            this.invalidate();
            return true;
        }
        // -------- help button --------


        // ----------- solve button -----------
        buttonLeft += buttonWidth + buttonMargin;
        if (GB.cells_ind_j != -1 && GB.cells_ind_i != -1) {
            rectF = new RectF(buttonLeft, buttonTop, buttonLeft + buttonWidth, buttonTop + buttonWidth);
            if (rectF.contains(e.getX(), e.getY())) {
                    int[][] puza = new int[9][9];
                    Sudoku s = new Sudoku();
                    for (int y = 0; y < 9; y++) {
                        for (int x = 0; x < 9; x++) {
                            puza[y][x] = GB.cells[y][x].Val_Real;
                        }
                    }

                    if (s.rS(puza)==true) {
                        GB.cells[GB.cells_ind_i][GB.cells_ind_j].Val_Real =
                                puza[GB.cells_ind_i][GB.cells_ind_j];
                        this.invalidate();
                        return true;
                    }
                    else {
                    }

            }
        }
        // ----------- solve button -----------

        //-----------Solve All-------
        buttonLeft = buttonMargin;
        buttonLeft += (buttonWidth + buttonMargin)*3;
        btnL +=buttonWidth*3+buttonLeft;
        buttonTop += buttonWidth + buttonMargin;
        if (GB.cells_ind_j != -1 && GB.cells_ind_i != -1) {
            rectF = new RectF(buttonLeft,  buttonTop,  btnL+buttonMargin, buttonTop + buttonWidth);
            if (rectF.contains(e.getX(), e.getY())) {
                  if (solve_une_seul_fois==0) {
                      int[][] puza = new int[9][9];
                      Sudoku s = new Sudoku();
                      for (int y = 0; y < 9; y++) {
                          for (int x = 0; x < 9; x++) {
                              puza[y][x] = GB.cells[y][x].Val_Real;
                              if (GB.cells[y][x].Val_Real == 0) {
                                  GB.cells[y][x].Val_Init = true;
                              }
                          }
                      }

                      if (s.rS(puza) == true) {
                          for (int i = 0; i < 9; i++) {
                              for (int j = 0; j < 9; j++) {
                                  GB.cells[i][j].Val_Real = puza[i][j];
                              }
                          }
                          solve_une_seul_fois = 1;
                          this.invalidate();
                          return true;
                      }
                  }
            }
        }

        //-------------Clear ALl
        buttonLeft = buttonMargin;
        buttonLeft += (buttonWidth + buttonMargin)*3;
        btnL +=buttonWidth*3+buttonLeft;
        buttonTop += buttonWidth + buttonMargin;
        if (GB.cells_ind_j != -1 && GB.cells_ind_i != -1) {
            rectF = new RectF(buttonLeft,  buttonTop,  btnL+buttonMargin, buttonTop + buttonWidth);
            if (rectF.contains(e.getX(), e.getY())) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        GB.cells[i][j].Val_Real = 0;
                        GB.cells[i][j].mark = new boolean[] {false,false,false,false,false,false,false,false,false};
                        valeur[i][j] = 0;
                        GB.cells[i][j].Val_Init = false;
                        solve_une_seul_fois=0;
                    }
                }
                this.invalidate();
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e)
    {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

}

