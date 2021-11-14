import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    int width, height;
    private Hero hero = new Hero(10, 10);
    private List<Wall> walls;



    Arena(int width, int height){
        this.width=width;
        this.height=height;
        this.walls = createWalls();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        for (int c = 0; c < width; c++) {
            System.out.println(walls.get(c));
        }
        return walls;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    void draw(TextGraphics graphics){
        hero.draw( graphics);
        for (Wall wall : walls)
            wall.draw(graphics);


    }


    private boolean canHeroMove(Position position) {
        boolean value=false;
        for (Wall wall : walls){
            if (wall.getPosition().equals(position)) return false;
            else if (position.getX()< width && position.getY()< height) value=true;
        }
        return value;
    }



    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }


    public void processKey(KeyStroke key) throws IOException {
        /*switch (key.getKeyType()) {
            case (KeyType.ArrowUp): hero.getY()-=1;
            case (KeyType.ArrowDown): y+=1;
            case (KeyType.ArrowLeft): x-=1;
            case (KeyType.ArrowRight): x+=1;
        }*/
        if (key.getKeyType()==KeyType.ArrowUp) moveHero(hero.moveUp());
        else if (key.getKeyType()==KeyType.ArrowDown)  moveHero(hero.moveDown());
        else if (key.getKeyType()==KeyType.ArrowLeft) moveHero(hero.moveLeft());
        else if (key.getKeyType()==KeyType.ArrowRight) moveHero(hero.moveRight());

    }





}
