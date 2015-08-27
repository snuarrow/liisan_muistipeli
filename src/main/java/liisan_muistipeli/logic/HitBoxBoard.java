package liisan_muistipeli.logic;

/**
 * Luokka koostuu 2 ulotteisesta taulukosta ja taulukon alkioihin kirjoitetaan osumalaatikon muotoisia muotoja kortin id:llä, eli esim kortti jonka id on 1 löytyy laudalta neliönä jonka laidat ovat ykkösiä.
 * HitBoxBoardilla hallitaan korttien osumista toisiinsa.
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
    
    /**
     * alustaa taulukon arvolla c
     * @param c 
     */
    public void memset(int c) 
    {
        for (int y = 0; y < g.getY_max_index(); y++)
        {
            for (int x = 0; x < g.getX_max_index(); x++)
            {
                  board[y][x] = c;
            }
        }
    } // write every cell in board with c.
    
    /**
     * piirtää matriisiin onton neliön kortin id:tä
     * @param card 
     */
    public void setCard(Card card) // piirtää laudalle onton neliön kortin id:tä
    {
        for (int i = 0; i < g.getCardsize(); i++)
        {
            if (board[card.y()][card.x()+i] == 0)
            {
                board[card.y()][card.x()+i] = card.id();
            }
            if (board[card.y()+g.getCardsize()-1][card.x()+i] == 0)
            {
                board[card.y()+g.getCardsize()-1][card.x()+i] = card.id();
            }
            if (board[card.y()+i][card.x()] == 0)
            {
                board[card.y()+i][card.x()] = card.id();
            }
            if (board[card.y()+i][card.x()+g.getCardsize()-1] == 0)
            {
                board[card.y()+i][card.x()+g.getCardsize()-1] = card.id();
            }
        }
        // muuta hitboksi ympyräks sit ku toimii kaikki muuten, siihe asti ontolla neliöllä.
        
        /*
            id vasemmas yläkulmassa ja ympyrä id:tä
            ratkaise matikka, ei oo amerikantemppu.
        */
    }
    
    /**
     * poistaa matriisista kortin id:t
     * @param card 
     */
    public void delCard(Card card) // poistaa kortin id:t
    {
        for (int i = 0; i < g.getCardsize(); i++)
        {
            if (board[card.y()][card.x()+i] == card.id()) board[card.y()][card.x()+i] = 0;
            if (board[card.y()+g.getCardsize()-1][card.x()+i] == card.id()) board[card.y()+g.getCardsize()-1][card.x()+i] = 0;
            if (board[card.y()+i][card.x()] == card.id()) board[card.y()+i][card.x()] = 0;
            if (board[card.y()+i][card.x()+g.getCardsize()-1] == card.id()) board[card.y()+i][card.x()+g.getCardsize()-1] = 0;
        }
    }
    
    /**
     * tarkistaa onko joku kortin id:stä toisen kortin id, eli on törmännyt
     * @param card
     * @return 
     */
    public int checkCollision(Card card)
    {
        // tän vois laittaa palauttamaan taulukon iideitä, mikäli osuu useampaan samanaikaisesti, mut ekan version fysiikka tulee olee basic
        
        for (int i = 0; i < g.getCardsize(); i++)
        {
            if (board[card.y()][card.x()+i] != card.id()) return board[card.y()][card.x()+i];
            if (board[card.y()+g.getCardsize()-1][card.x()+i] != card.id()) return board[card.y()+g.getCardsize()-1][card.x()+i];
            if (board[card.y()+i][card.x()] != card.id()) return board[card.y()+i][card.x()];
            if (board[card.y()+i][card.x()+g.getCardsize()-1] != card.id()) return board[card.y()+i][card.x()+g.getCardsize()-1];
        }
        return 0;
    }
    public int checkCell(int y, int x)
    {
        return board[y][x];
    }
    
    /**
     * tämä funktio vain debuggausta varten
     */
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
