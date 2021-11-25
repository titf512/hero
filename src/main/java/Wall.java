import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

class Wall extends Element {


    public Wall(int x, int y){
        super(x,y);
    }

    public Position getPosition(){
        return  new Position(position.getX(), position.getY());
    }
}
