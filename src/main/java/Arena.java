import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    int width, height;
    Hero hero = new Hero(10, 10);
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    Arena(int width, int height){
        this.width=width;
        this.height=height;
        this.hero = new Hero(10, 10);
        this.walls=createWalls();
        this.coins=createCoins();
        this.monsters = createMonsters();
    }


    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();

        for(int c=0; c < width; c++){
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height-1));
        }

        for(int r=0; r < height; r++){
            walls.add(new Wall(0, r));
            walls.add(new Wall(width-1, r));
        }

        return walls;
    }


    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return monsters;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        hero.draw( graphics);

        for (Wall wall : walls)
            wall.draw(graphics);

        for(Coin coin : coins)
            coin.draw(graphics);

        for(Monster monster : monsters)
            monster.draw(graphics);


    }


    public Position moveUp() {
        return new Position(hero.getPosition().getX(), hero.getPosition().getY() - 1);
    }

    public Position moveDown() {
        return new Position(hero.getPosition().getX(), hero.getPosition().getY() + 1);
    }

    public Position moveLeft() {
        return new Position(hero.getPosition().getX()-1, hero.getPosition().getY());
    }

    public Position moveRight() {
        return new Position(hero.getPosition().getX()+1, hero.getPosition().getY());
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
        }
    }

    private boolean canHeroMove(Position pos){
        return (pos.getX() > 0 && pos.getX() < width-1) &&
                (pos.getY() > 0 && pos.getY() < height-1) &&
                !walls.contains(new Wall(pos.getX(), pos.getY()));
    }


    private void retrieveCoins(){
        for(Coin coin : coins){
            if(hero.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                break;
            }
        }
    }





    private void moveMonsters() {
        for (Monster monster : monsters) {
            Position monsterPosition = monster.move();
            if (canHeroMove(monsterPosition))
                monster.setPosition(monsterPosition);
        }
    }

    private void verifyMonsterCollisions() {
        for (Monster monster : monsters)
            if (hero.getPosition().equals(monster.getPosition())) {
                System.out.println("Sorry, You died!");
                System.exit(0);
            }
    }



    public void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowUp) moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());
        if (key.getKeyType() == KeyType.ArrowDown) moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());

        retrieveCoins();

        verifyMonsterCollisions();
        moveMonsters();
        verifyMonsterCollisions();
    }

    private class Hero extends Element{
        private Hero(int x, int y) {
            super(x,y);
        }

        public void draw(TextGraphics graphics){
            graphics.setBackgroundColor(TextColor.Factory.fromString("#01579B"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
            graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "X");
        }
        public Position moveUp() {
            return new Position(position.getX(), position.getY() - 1);
        }

        public Position moveRight() {
            return new Position(position.getX() + 1, position.getY());
        }

        public Position moveDown() {
            return new Position(position.getX(), position.getY() + 1);
        }

        public Position moveLeft() {
            return new Position(position.getX() - 1, position.getY());
        }
    }



}
