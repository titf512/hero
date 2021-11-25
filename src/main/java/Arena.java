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
    private Hero hero = new Hero(10, 10);
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    Arena(int width, int height){
        this.width=width;
        this.height=height;
        this.walls=createWalls();
        this.coins=createCoins();
        monsters = createMonsters();
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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for(int i=0; i<5; i++){
            Coin newcoin = new Coin(random.nextInt(width-2) + 1,
                    random.nextInt(height-2)+1);
            if(!coins.contains(newcoin) && !newcoin.getPosition().equals(hero.getPosition()))
                coins.add(newcoin);
        }
        return coins;
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

        for(Coin coin : coins)
            coin.draw(graphics);

        for(Monster monster : monsters)
            monster.draw(graphics);


    }


    private boolean canHeroMove(Position position) {
        boolean value=false;
        for (Wall wall : walls){
            if (wall.getPosition().equals(position)) return false;
            else if (position.getX()< width && position.getY()< height) value=true;
        }
        return value;
    }


    private void retrieveCoins(){
        for(Coin coin : coins){
            if(hero.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                break;
            }
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
        retrieveCoins();
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for(int i=0; i<5; i++){
            Monster newmonster = new Monster(random.nextInt(width-2) + 1,
                    random.nextInt(height-2)+1);
            if(!monsters.contains(newmonster) && !newmonster.getPosition().equals(hero.getPosition()))
                monsters.add(newmonster);
        }
        return monsters;
    }

    public void moveMonsters(){
        for(Monster monster : monsters){
            monster.setPosition(monster.move(this));
        }
    }

    public boolean verifyMonsterCollisions(){
        for(Monster monster : monsters){
            if(monster.getPosition().equals(hero.getPosition())){
                System.out.println("Death.");
                return true;
            }
        }
        return false;
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
