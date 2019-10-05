import java.util.*;
class Game {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        System.out.println(">>Enter total number of tiles on the race track (length)");
        int len = c.nextInt();
        GameMap g = new GameMap(len);
        //////////////Initialise graph
        System.out.println(">>Enter the Player Name");
        Player p = new Player(len-1,c.next());
        System.out.println(">>Hit x to start the game");
        while (true){
            String x = c.next();
            // System.out.println(x);
            if (x.equals("x")){
                break;
            }   
        }
        System.out.println(">>Game Started ======================>");


        int sbCount, vbCount , cbCount , tCount  ;
        sbCount= vbCount = cbCount = tCount =0;



        while(true){
            try{
                p.roll(g);
                if (p.free()){
                    g.getLocation(p.get_cLocation()).shake(p, g);
                }


            }
            catch(GameWinnerException e){
                break;
            }
            catch(SnakeBiteException e){
                sbCount +=1;

            }
            catch(CricketBiteException e){
                cbCount +=1;
            }
            catch(VultureBiteException e){
                vbCount+=1;
            }
            catch(TrampolineException e){
                tCount+=1;
            }
        }
        System.out.println(p.getName()+" wins the race in "+p.getRollCount());
        System.out.println("Total Snake Bites = " + sbCount);
        System.out.println("Total Vulture Bites = " + vbCount);
        System.out.println("Total Criket Bites = " + cbCount);
        System.out.println("Total Trampolines = " + tCount);


        
    }
}

class Player {
    private int rollCount ;
    private int cLocation;
    private int maxLocation;
    private final String name ;
    private final Random ran ;
    private boolean isFree;
    public Player (int x , String name ){
        this.rollCount =0;
        this.cLocation= 1;
        this.maxLocation = x;
        this.name = name ;
        this.ran = new Random();
        this.isFree = false;
        System.out.println(">>Starting the game with "+this.name+" at Tile-1");
        System.out.println(">>Control transferred to Computer for rolling the Dice for "+this.name);
    }


    public String getName(){ return this.name;}
    public int getRollCount(){ return this.rollCount;}

    public void roll(GameMap g){
        int r = ran.nextInt(6)+1;
        int prev_l =this.cLocation; // prev location
        System.out.print(">>[Roll-"+(this.rollCount+1)+"]: "+this.name+" rolled "+r+" at "+g.getLocation(prev_l)+", ");
        this.rollCount+=1;
        if (!isFree){
            if (r==6){
                this.isFree=true;
                System.out.println("You are out of the cage! You get a free roll");

            }
            else {
                System.out.println("OOPs you need 6 to start");
            }
        }
        else {
            try{
                this.changePosition(r, "f", 1,g);
                System.out.println(" landed on "+g.getLocation(this.cLocation)+".");
                }
            catch(GameWinnerException e){
                System.out.println(" landed on "+g.getLocation(this.cLocation)+".");
                throw new GameWinnerException();

            }
        }
    }





    public boolean free(){ return this.isFree;}
    public int get_cLocation(){ return this.cLocation;}






    public void changePosition(int val , String s,int x, GameMap g){// val is change in position , s is to denote direction of change ,  x is for print. x==0 print, otherwise not 
        if (s.equals("f")){
            if (val+cLocation==maxLocation){
                cLocation= maxLocation;
                throw new GameWinnerException();
            }
            else if (val + cLocation<maxLocation){
                cLocation = cLocation+ val;
                
            }
            if(x==0){System.out.println(">> "+this.name + " moved to "+g.getLocation(cLocation));}



        }else if (s.equals("b")){
            if (cLocation-val >= 0){
                cLocation = cLocation -val;
                if(x==0){System.out.println(">> "+this.name + " moved to "+g.getLocation(cLocation));}
            }
            else {
                cLocation = 0;
                this.isFree = false;
                if (x==0){
                    System.out.println(">> "+this.name+" moved to "+g.getLocation(0)+" as it can't go back further");
                }
            }

        }else {
            System.out.println("Some error occured");
            System.exit(0);
        }
    }
}







class SnakeTile extends Tile {
    public SnakeTile(int change,int id){
        super(change,id);
    }

    @Override
    public void makeSound(){
        System.out.println(">>"+"Hiss...! I am a Snake, you go back "+this.change+" tiles!");
    }
    @Override 
    public void shake(Player p , GameMap g){
        super.shake(p,g);
        //this.makeSound();
        p.changePosition(this.change,"b" , 0, g);
        throw new SnakeBiteException();
    }
}
class CricketTile extends Tile{
    public CricketTile(int change,int id){
        super(change,id);
    }
    @Override
    public void makeSound(){
        System.out.println(">>"+"Chirp...! I am a Cricket, you go back "+this.change+" tiles!");
    }
    @Override 
    public void shake(Player p , GameMap g){
        super.shake(p,g);
        //this.makeSound();
        p.changePosition(this.change,"b" , 0, g);
        throw new CricketBiteException();
    }
}
class TrampolineTile extends Tile{
    public TrampolineTile(int change,int id){
        super(change,id);
    }
    @Override
    public void makeSound(){
        System.out.println(">>"+"PingPong! I am a Trampoline, you advance "+this.change+ " tiles");
    }
    @Override 
    public void shake(Player p , GameMap g){
        super.shake(p,g);
        //this.makeSound();
        p.changePosition(this.change,"f" , 0, g);
        throw new TrampolineException();
    }
}
class VultureTile extends Tile{
    public VultureTile(int change,int id){
        super(change,id);
    }
    @Override
    public void makeSound(){
        System.out.println(">>"+"Yapping...! I am a Vulture, you go back " +this.change+" tiles!");
    } 
    @Override 
    public void shake(Player p , GameMap g){
        super.shake(p,g);
        //this.makeSound();
        p.changePosition(this.change,"b" , 0, g);
        throw new VultureBiteException();
    }
}
class WhiteTile extends Tile{
    public WhiteTile(int change,int id){
        super(change,id);
    }
    @Override
    public void makeSound(){
        System.out.println(">>"+"I am a White tile!");
    } 
    @Override 
    public void shake(Player p , GameMap g){
        super.shake(p,g);
        //this.makeSound();
        p.changePosition(this.change,"f" , 0, g);
    }
}
class Tile {
    protected  int id;
    protected int change;// identifier to address change in position for each type of tile

    public Tile(int change,int id){
        this.id = id;
        this.change = change;
    }

    protected void makeSound(){}

    public void shake(Player p,GameMap g){
        System.out.println(">>"+"Trying to shake the "+this);
        this.makeSound();
    }
    public String toString(){
        return new String("Tile-"+(id+1));
    }
    public int getId(){
        return this.id;
    }

    protected void raiseException(){}
}
class GameMap{
    private ArrayList<Tile> locations;
    public GameMap(int len){
        int sCount, tCount , vCount , cCount;
        sCount =vCount = cCount = tCount =0;
        locations = new ArrayList<Tile>();
        Random ran = new Random();
        int sChange = ran.nextInt(9)+1;
        int vChange = ran.nextInt(9)+1;
        int tChange = ran.nextInt(9)+1;
        int cChange = ran.nextInt(9)+1;
        // System.out.println(locations.size());
        for (int x =0 ; x<len;x++){
            int v = ran.nextInt(4);
            switch(v+1 ){
                case 1:
                    locations.add(new SnakeTile(sChange,x));
                    sCount++;
                    break;
                
                case 2:
                    locations.add(new CricketTile(cChange,x));
                    cCount++;
                    break;
                
                case 3:
                    locations.add(x, new VultureTile(vChange,x));
                    vCount++;
                    break;
                
                case 4:
                    locations.add(x, new TrampolineTile(tChange,x));
                    tCount++;
                    break;
                
                case 5:
                   
                    locations.add(x, new WhiteTile(0,x));
                    break;
                
                    
            }
   		}
        this.startPrint(sCount, cCount, vCount, tCount, sChange, vChange, cChange, tChange);
    }
    private void startPrint(int sCount ,int cCount , int vCount , int tCount,int sChange,int vChange ,int cChange,int tChange){
        System.out.println(">>Setting up the race track...");
        System.out.println(">>Danger: There are "+sCount+"," +cCount+", "+ vCount+" numbers of Snakes, Cricket, and Vultures respectively on your track!");
        System.out.println(">>Danger: Each Snake, Cricket, and Vultures can throw you back by "+sChange+","+cChange+", "+vChange+" number of Tiles respectively!");
        System.out.println(">>Good News: There are " + tCount+" number of Trampolines on your track!");
        System.out.println(">>Good News: Each Trampoline can help you advance by "+tChange+" number of Tiles");

    }
    public Tile getLocation(int x){
        return this.locations.get(x);
    }
}


class SnakeBiteException extends RuntimeException{
}

class CricketBiteException extends RuntimeException{
}

class VultureBiteException extends RuntimeException{
}

class TrampolineException extends RuntimeException{
}

class GameWinnerException extends RuntimeException{
}




















