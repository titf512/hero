import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{


    public Monster(int x, int y){
        super(x,y);
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#339933"));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "m");
    }

    public Position move() {
        switch (new Random().nextInt(4)) {
            case 0:
                return new Position(position.getX(), position.getY() - 1);
            case 1:
                return new Position(position.getX() + 1, position.getY());
            case 2:
                return new Position(position.getX(), position.getY() + 1);
            case 3:
                return new Position(position.getX() - 1, position.getY());
        }
        return new Position(position.getX(), position.getY());
    }
}
