import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;

public class Game {
    private int x = 10;
    private int y = 10;
    Terminal terminal = new DefaultTerminalFactory().createTerminal();
    Screen screen = new TerminalScreen(terminal);

    public Game() throws IOException {
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
    }






    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }


    public void run() throws IOException {

        while (true) {
            draw();
            KeyStroke key = readInput();
            if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')) screen.close();
            if (key.getKeyType() == KeyType.EOF) break;
            else processKey(key);
        }

    }

    public KeyStroke readInput() throws IOException {
        KeyStroke key = screen.readInput();
        return key;
    }

    private void processKey(KeyStroke key) throws IOException {
        /*switch (key.getKeyType()) {
            case (KeyType.ArrowUp): y-=1;
            case (KeyType.ArrowDown): y+=1;
            case (KeyType.ArrowLeft): x-=1;
            case (KeyType.ArrowRight): x+=1;
        }*/
        if (key.getKeyType()==KeyType.ArrowUp) y-=1;
        else if (key.getKeyType()==KeyType.ArrowDown) y+=1;
        else if (key.getKeyType()==KeyType.ArrowLeft) x-=1;
        else if (key.getKeyType()==KeyType.ArrowRight) x+=1;
        draw();
    }
}