package observer;

import board.Board;

public interface Observer {
    double rateModifier = 0.30;

    // Dwie wersje update() dla łatwiejszej implementacji
    void updateDollar(Board board);
    void updateEuro(Board board);


} 