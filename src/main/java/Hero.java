import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

class Hero extends Element {

    Hero(int x, int y) {
        position.setY(y);
        position.setX(x);

    }

    public int getX(){
        return position.getX();
    }
    public int getY(){
        return position.getY();
    }

    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveRight(){
        return new Position(position.getX()+1, position.getY());
    }
    public Position moveLeft(){
        return new Position(position.getX()-1, position.getY());
    }

    public void setPosition(Position p){
        position.setX(p.getX());
        position.setY(p.getY());
    }


}
