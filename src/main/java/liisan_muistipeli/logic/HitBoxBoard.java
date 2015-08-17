/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liisan_muistipeli.logic;

/**
 *
 * @author hexvaara
 */
public class HitBoxBoard
{
    private int[][] board;
    private final Global g;
    
    public HitBoxBoard(Global global)
    {
        g = global;
        board = new int[g.getVerticalsize()][g.getHorizontalsize()];
        memset(0);
    }
    private void memset(int c) { for (int[] board1 : board) { for (int i : board1) { i = c; } } } // write every cell in board with c.

    public void setCard(Card card) // piirtää laudalle onton neliön kortin id:tä
    {
        for (int i = 0; i < g.getCardsize(); i++)
        {
            if (board[card.y()][card.x()+i] == 0) board[card.y()][card.x()+i] = card.id();
            if (board[card.y()+g.getCardsize()][card.x()+i] == 0) board[card.y()+g.getCardsize()][card.x()+i] = card.id();
            if (board[card.y()+i][card.x()] == 0) board[card.y()+i][card.x()] = card.id();
            if (board[card.y()+i][card.x()+g.getCardsize()] == 0) board[card.y()+i][card.x()+g.getCardsize()] = card.id();
        }

        // muuta hitboksi ympyräks sit ku toimii kaikki muuten, siihe asti ontolla neliöllä.
        
        /*
            id vasemmas yläkulmassa ja ympyrä id:tä
            ratkaise matikka, ei oo amerikantemppu.
        */
    }
    public void delCard(Card card) // poistaa kortin id:t
    {
        for (int i = 0; i < g.getCardsize(); i++)
        {
            if (board[card.y()][card.x()+i] == card.id()) board[card.y()][card.x()+i] = 0;
            if (board[card.y()+g.getCardsize()][card.x()+i] == card.id()) board[card.y()+g.getCardsize()][card.x()+i] = 0;
            if (board[card.y()+i][card.x()] == card.id()) board[card.y()+i][card.x()] = 0;
            if (board[card.y()+i][card.x()+g.getCardsize()] == card.id()) board[card.y()+i][card.x()+g.getCardsize()] = 0;
        }
    }
    public int checkCollision(Card card) // tarkistaa onko joku kortin id:stä toisen kortin id, eli on törmännyt
    {
        // tän vois laittaa palauttamaan taulukon iideitä, mikäli osuu useampaan samanaikaisesti, mut ekan version fysiikka tulee olee basic
        
        for (int i = 0; i < g.getCardsize(); i++)
        {
            if (board[card.y()][card.x()+i] != card.id()) return board[card.y()][card.x()+i];
            if (board[card.y()+g.getCardsize()][card.x()+i] != card.id()) return board[card.y()+g.getCardsize()][card.x()+i];
            if (board[card.y()+i][card.x()] != card.id()) return board[card.y()+i][card.x()];
            if (board[card.y()+i][card.x()+g.getCardsize()] != card.id()) return board[card.y()+i][card.x()+g.getCardsize()];
        }
        return 0;
    }
    public int checkCell(int y, int x)
    {
        return board[y][x];
    }
    
    public void print()
    {
        for (int y = 0; y < g.getVerticalsize(); y++) {
            for (int x = 0; x < g.getHorizontalsize(); x++) {
                System.out.print(board[y][x]+" ");
            }
            System.out.println("");
        }
        for (int i = 0; i < g.getHorizontalsize(); i++) {
            System.out.print("--");
        }System.out.println("");
    }
    

}
