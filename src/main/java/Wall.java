import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

class Wall extends Element {

    public Wall(int c, int i) {
        position.setX(c);
        position.setY(i);
    }

    public Position getPosition(){
        return  new Position(position.getX(), position.getY());
    }
}
